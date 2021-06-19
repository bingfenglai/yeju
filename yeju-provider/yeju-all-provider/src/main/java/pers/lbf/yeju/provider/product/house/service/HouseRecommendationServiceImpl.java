/*
 * Copyright 2020 赖柄沣 bingfengdev@aliyun.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package pers.lbf.yeju.provider.product.house.service;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.provider.product.house.config.HouseRecommendationConfig;
import pers.lbf.yeju.provider.product.house.status.HouseRecommendationServiceStatus;
import pers.lbf.yeju.service.interfaces.product.IHouseInfoService;
import pers.lbf.yeju.service.interfaces.product.IHouseRecommendationService;
import pers.lbf.yeju.service.interfaces.product.pojo.CustomerCfArgs;
import pers.lbf.yeju.service.interfaces.product.pojo.HouseCfArgs;
import pers.lbf.yeju.service.interfaces.product.pojo.HouseInfoDoc;
import pers.lbf.yeju.service.interfaces.trade.ITradeService;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * 协同过滤算法实现房源推荐
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/5/30 21:03
 */
@DubboService(interfaceClass = IHouseRecommendationService.class, timeout = 10000, retries = 0)
@Slf4j
@Log
public class HouseRecommendationServiceImpl implements IHouseRecommendationService {

    @DubboReference
    private ITradeService tradeService;

    @DubboReference
    private IHouseInfoService houseInfoService;

    @Autowired
    private HouseRecommendationConfig recommendationConfig;

    /**
     * 基于客户相似度的
     *
     * @param args 客户相似度推荐请求参数
     * @return 房源信息
     * @throws ServiceException e
     */
    @Override
    public IResult<List<HouseInfoDoc>> customerCf(CustomerCfArgs args) throws ServiceException, IOException {
        //1. 获取房源交易评价数据,文件不存在会直接抛出SE异常
        String fileUrl = tradeService.getCurrentCityTradeCsvUrl(args.getCityId()).getData();
        File houseTradeInfoFile = new File(fileUrl);

        //2.加载数据并构建数据模型
        DataModel currentDataModel = new FileDataModel(houseTradeInfoFile);
        UserSimilarity currentCustomerSimilarity = null;
        //3.计算客户相似度 (皮尔逊相关系数相似度)
        try {
            currentCustomerSimilarity = new PearsonCorrelationSimilarity(currentDataModel);
        } catch (TasteException e) {
            log.error(String.valueOf(e));
            throw ServiceException.getInstance(HouseRecommendationServiceStatus.failedToCalculateSimilarity);
        }

        //4. 计算客户最近邻域
        UserNeighborhood customerNeighborhood;

        try {
            customerNeighborhood = new NearestNUserNeighborhood(
                    recommendationConfig.getNearestnUserNeighborhoodNumber(),
                    currentCustomerSimilarity, currentDataModel);
        } catch (TasteException e) {
            log.error(String.valueOf(e));
            throw ServiceException.getInstance(HouseRecommendationServiceStatus.failedToCalculateNeighboringDomain);
        }
        //5. 根据客户相似度和邻近域构建基于用户的推荐器
        Recommender currentCustomerRecommender = new GenericUserBasedRecommender(
                currentDataModel, customerNeighborhood, currentCustomerSimilarity);

        //6. 获取推荐的房源ID List
        List<RecommendedItem> recommend;
        try {
            recommend = currentCustomerRecommender.recommend(args.getCustomerId(), args.getSize());
        } catch (TasteException e) {
            log.error(String.valueOf(e));
            throw ServiceException.getInstance(HouseRecommendationServiceStatus.failedToGetTheListOfRecommendedPropertyId);
        }
        List<Long> houseIdList = new LinkedList<>();
        for (RecommendedItem recommendedItem : recommend) {
            houseIdList.add(recommendedItem.getItemID());
        }

        //返回房源信息列表
        return houseInfoService.findHouseInfoDocByIdList(houseIdList);
    }

    /**
     * 基于房源相似度的
     *
     * @param args 房源相似度推荐请求参数
     * @return 房源信息列表
     * @throws ServiceException e
     */
    @Override
    public IResult<List<HouseInfoDoc>> houseCf(HouseCfArgs args) throws ServiceException, IOException {
        //1. 获取房源交易评价数据,文件不存在会直接抛出SE异常
        String fileUrl = tradeService.getCurrentCityTradeCsvUrl(args.getCityId()).getData();
        File houseTradeInfoFile = new File(fileUrl);

        //2.加载数据并构建数据模型
        DataModel currentDataModel = new FileDataModel(houseTradeInfoFile);

        // 3. 计算房源相似度
        ItemSimilarity houseSimilarity;
        try {
            houseSimilarity = new PearsonCorrelationSimilarity(currentDataModel);
        } catch (TasteException e) {
            log.error(String.valueOf(e));
            throw ServiceException.getInstance(HouseRecommendationServiceStatus.failedToCalculateSimilarity);
        }

        // 4. 构建推荐器
        GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(currentDataModel, houseSimilarity);

        //5 获取指定数目的推荐商品
        List<RecommendedItem> recommendedItemList;

        try {
            recommendedItemList = recommender.recommendedBecause(args.getCustomerId(), args.getHouseId(), args.getSize());
        } catch (TasteException e) {
            log.error(String.valueOf(e));
            throw ServiceException.getInstance(HouseRecommendationServiceStatus.failedToGetTheListOfRecommendedPropertyId);
        }
        List<Long> houseIdList = new LinkedList<>();
        for (RecommendedItem recommendedItem : recommendedItemList) {
            houseIdList.add(recommendedItem.getItemID());
        }

        return houseInfoService.findHouseInfoDocByIdList(houseIdList);
    }


}