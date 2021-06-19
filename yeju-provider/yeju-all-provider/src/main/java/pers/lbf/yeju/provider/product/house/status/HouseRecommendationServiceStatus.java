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

package pers.lbf.yeju.provider.product.house.status;

import pers.lbf.yeju.common.core.status.insterfaces.IStatus;

import java.io.Serializable;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/5/30 22:48
 */
public enum HouseRecommendationServiceStatus implements IStatus, Serializable {
    /**
     * 计算相似度失败
     */
    failedToCalculateSimilarity("计算相似度失败", "hre01"),

    failedToCalculateNeighboringDomain("计算邻近域失败", "hre02"),

    failedToGetTheListOfRecommendedPropertyId("获取推荐的房源标识列表失败", "hre02");

    private final String message;
    private final String code;

    HouseRecommendationServiceStatus(String message, String code) {
        this.message = message;
        this.code = code;
    }

    /**
     * 状态消息
     *
     * @return msg
     */
    @Override
    public String getMessage() {
        return this.message;
    }

    /**
     * 状态编码
     *
     * @return code
     */
    @Override
    public String getCode() {
        return this.code;
    }
}
