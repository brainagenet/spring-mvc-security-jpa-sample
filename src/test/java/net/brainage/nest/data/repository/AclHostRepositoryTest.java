/*
 * (#) net.brainage.nest.data.repository.AclHostRepositoryTest.java
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
package net.brainage.nest.data.repository;

import net.brainage.nest.data.model.AclHost;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author <a href="mailto:ms29.seo+ara@gmail.com">ms29.seo</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {AclHostRepositoryTestConfig.class})
public class AclHostRepositoryTest {

    @Autowired
    AclHostRepository aclHostRepository;

    @Test
    @Transactional
    public void testFindNotAssginedByGroup() {
        int groupId = 1;
        List<AclHost> hosts = aclHostRepository.findNotAssginedByGroup(groupId);
        hosts.forEach(System.out::println);
    }

}
