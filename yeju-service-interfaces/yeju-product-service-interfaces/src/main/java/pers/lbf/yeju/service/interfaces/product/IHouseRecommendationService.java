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

package pers.lbf.yeju.service.interfaces.product;

import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.service.interfaces.product.pojo.CustomerCfArgs;
import pers.lbf.yeju.service.interfaces.product.pojo.HouseCfArgs;
import pers.lbf.yeju.service.interfaces.product.pojo.HouseInfoDoc;

import java.io.IOException;
import java.util.List;

/**
 * 房源推荐服务接口类
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/5/30 20:58
 */
public interface IHouseRecommendationService {

    /**
     * 基于客户相似度的
     *
     * @param args 客户相似度推荐请求参数
     * @return 房源信息
     * @throws ServiceException e
     */
    IResult<List<HouseInfoDoc>> customerCf(CustomerCfArgs args) throws ServiceException, IOException;

    /**
     * 基于房源相似度的
     *
     * @param args 房源相似度推荐请求参数
     * @return 房源信息列表
     * @throws ServiceException e
     */
    IResult<List<HouseInfoDoc>> houseCf(HouseCfArgs args) throws ServiceException, IOException;
}
