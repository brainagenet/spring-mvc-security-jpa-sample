/*
 * (#) net.brainage.nest.web.controller.AclGroupController.java
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
package net.brainage.nest.web.controller;

import lombok.extern.slf4j.Slf4j;
import net.brainage.nest.service.AclGroupService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author <a href="mailto:ms29.seo+ara@gmail.com">ms29.seo</a>
 */
@Slf4j
@Controller
@RequestMapping(path = {"/acl/groups"})
public class AclGroupController implements InitializingBean {

    @Autowired
    private AclGroupService aclGroupService;

    @RequestMapping(path = {"/"}, method = RequestMethod.GET)
    public String index(Model model) {
        return "acl/group_index";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(aclGroupService, "The 'aclGroupService' property must not be null.");

    }

}
