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

package pers.lbf.yeju.consumer.base.service;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;
import pers.lbf.yeju.common.core.exception.service.ServiceException;
import pers.lbf.yeju.common.core.result.IResult;
import pers.lbf.yeju.service.interfaces.auth.interfaces.IAccountService;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/3/12 8:43
 */
@Service
public class NativeAccountService {

    @DubboReference
    private IAccountService accountService;

    /**
     * 根据账户查找账户及权限信息
     *
     * @param principal 抽象账户
     * @return account
     */
    public IResult<Long> findAccountIdByPrincipal(String principal) throws ServiceException {

        return accountService.findAccountIdByPrincipal(principal);

    }


}
