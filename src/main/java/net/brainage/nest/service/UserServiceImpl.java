/*
 * (#) net.brainage.nest.service.UserServiceImpl.java
 * Created on 2016-05-09
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
import net.brainage.nest.data.model.User;
import net.brainage.nest.data.model.UserRole;
import net.brainage.nest.data.model.enums.UserState;
import net.brainage.nest.data.repository.UserRepository;
import net.brainage.nest.data.repository.UserRoleRepository;
import net.brainage.nest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 *
 * @author <a href="mailto:ms29.seo@gmail.com">ms29.seo</a>
 */
@Slf4j
@Service("userService")
@Transactional(readOnly = true, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    public void create(final User user) {
        // password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // state
        user.setState(UserState.ACTIVE);
        // auditable fields
        Date rightNow = new Date();
        user.setCreatedOn(rightNow);
        user.setLastModifiedOn(rightNow);
        userRepository.save(user);

        UserRole role = new UserRole();
        role.setName("USER");
        role.setUser(user);
        userRoleRepository.save(role);
    }

}
