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

package pers.lbf.yeju.provider.product.house.config;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 房源推荐配置
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/5/30 22:56
 */
@Component
public class HouseRecommendationConfig implements Serializable {

    private final Integer NEARESTN_USER_NEIGHBORHOOD_NUMBER = 100;

    public Integer getNearestnUserNeighborhoodNumber() {
        return NEARESTN_USER_NEIGHBORHOOD_NUMBER;
    }
}
