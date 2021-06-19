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
package pers.lbf.yeju.provider.product.house.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pers.lbf.yeju.common.domain.entity.business.product.house.HouseOtherAttribute;

/**
 * 房源其他属性表.包括一些可选的属性以及客户自定义属性（<=3）
 * 。(TableBusinessHouseOtherAttribute)表数据库访问层
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @since 2021-03-17 15:40:06
 */
public interface HouseOtherAttributeDao extends BaseMapper<HouseOtherAttribute> {

}