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
package pers.lbf.yeju.service.interfaces.session.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * TODO
 *
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2021/2/5 16:52
 */
public class SessionDetails<T> implements Serializable {

    private SessionAccount accountDetailsInfo;

    private List<String> roles;

    private List<String> resources;

    private T subjectDetails;


    @Override
    public String toString() {
        return "SessionDetails{" +
                "accountDetailsInfo=" + accountDetailsInfo +
                ", roles=" + roles +
                ", resources=" + resources +
                ", subjectDetails=" + subjectDetails +
                '}';
    }

    public T getSubjectDetails() {
        return subjectDetails;
    }

    public void setSubjectDetails(T subjectDetails) {
        this.subjectDetails = subjectDetails;
    }

    public SessionAccount getAccountDetailsInfo() {
        return accountDetailsInfo;
    }

    public void setAccountDetailsInfo(SessionAccount accountDetailsInfo) {
        this.accountDetailsInfo = accountDetailsInfo;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getResources() {
        return resources;
    }

    public void setResources(List<String> resources) {
        this.resources = resources;
    }
}
