/*
 * (#) net.brainage.nest.service.AclGroupServiceImpl.java
 * Created on 2016-05-12
 *
 * Copyright 2015 brainage.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package net.brainage.nest.service;

import lombok.extern.slf4j.Slf4j;
import net.brainage.nest.data.model.AclGroup;
import net.brainage.nest.data.repository.AclGroupRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author <a href="mailto:ms29.seo+ara@gmail.com">ms29.seo</a>
 */
@Slf4j
@Service("aclGroupService")
@Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
public class AclGroupServiceImpl implements AclGroupService, InitializingBean {

    @Autowired
    private AclGroupRepository aclGroupRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(aclGroupRepository, "The 'aclGroupRepository' property must not be null.");
    }

    @Override
    public List<AclGroup> getAclGroups() {
        return aclGroupRepository.findAll();
    }

    @Override
    public AclGroup getAclGroup(int id, boolean isWithHosts) {
        AclGroup aclGroup = aclGroupRepository.findOne(id);
        if (isWithHosts) {
            // 맵핑되어 있는 Host 목록을 조회한다.
            aclGroup.getHosts();
        }
        return aclGroup;
    }

}
