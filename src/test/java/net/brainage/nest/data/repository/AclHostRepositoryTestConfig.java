/*
 * (#) net.brainage.nest.data.repository.AclHostRepositoryTestConfig.java
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

import net.brainage.nest.data.model.AclGroup;
import net.brainage.nest.data.model.AclHost;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author <a href="mailto:ms29.seo+ara@gmail.com">ms29.seo</a>
 */
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackageClasses = {AclHost.class, AclGroup.class})
@EnableJpaRepositories(basePackageClasses = {AclHostRepository.class, AclGroupRepository.class})
@EnableTransactionManagement
public class AclHostRepositoryTestConfig {
}
