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

import net.brainage.nest.data.criteria.SearchCriteria;
import net.brainage.nest.data.criteria.SearchCriteriaSpecification;
import net.brainage.nest.data.criteria.SearchOperation;
import net.brainage.nest.data.model.AclGroup;
import net.brainage.nest.data.model.AclHost;
import net.brainage.nest.data.model.AclMapping;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * @author <a href="mailto:ms29.seo+ara@gmail.com">ms29.seo</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {AclHostRepositoryTestConfig.class})
@Transactional
public class AclHostRepositoryTest {

    @Autowired
    AclHostRepository aclHostRepository;

    @Autowired
    AclGroupRepository aclGroupRepository;

    private int groupId;

    @Before
    public void setup() {

        AclGroup group = new AclGroup();
        group.setName("GRP_TEST");
        group.setDescription("Test Group");
        group.setCreatedBy(100L);
        group.setCreatedByName("tester");
        group.setCreatedOn(new Date());
        aclGroupRepository.save(group);

        this.groupId = group.getId();

        AclMapping mapping = new AclMapping();
        mapping.setGroup(group);

        AclHost host = new AclHost();
        host.setName("192.168.56.101");
        host.setDescription("192.168.56.101 host");
        host.setCreatedBy(100L);
        host.setCreatedByName("tester");
        host.setCreatedOn(new Date());
        aclHostRepository.save(host);




        host = new AclHost();
        host.setName("192.168.56.105");
        host.setDescription("192.168.56.105 host");
        host.setCreatedBy(100L);
        host.setCreatedByName("tester");
        host.setCreatedOn(new Date());
        aclHostRepository.save(host);

        host = new AclHost();
        host.setName("192.168.56.111");
        host.setDescription("192.168.56.111 host");
        host.setCreatedBy(100L);
        host.setCreatedByName("tester");
        host.setCreatedOn(new Date());
        aclHostRepository.save(host);
    }


    @Test
    public void testFindAll() {
        List<AclHost> hosts = aclHostRepository.findAll();
        hosts.stream().forEach(System.out::println);
    }

    @Test
    public void testFindAllWithSearchCriteria() {
        SearchCriteriaSpecification<AclHost> spec = new SearchCriteriaSpecification<>(new SearchCriteria("name",
                SearchOperation.STARTS_WITH, "192.168.56.10"));
        List<AclHost> hosts = aclHostRepository.findAll(Specifications.where(spec));
        hosts.stream().forEach(System.out::println);
    }


    @Test
    @Transactional
    public void testFindNotAssginedByGroup() {
        /*
        int groupId = 1;
        List<AclHost> hosts = aclHostRepository.findAllNotAssginedByGroup(groupId);
        hosts.forEach(System.out::println);
        */
    }


    @Test
    public void testFindAllByGroupId() {
        List<AclHost> hosts = aclHostRepository.findAllByGroup(groupId);
        hosts.forEach(System.out::println);
    }

}
