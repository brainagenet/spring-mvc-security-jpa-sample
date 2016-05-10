/*
 * (#) net.brainage.nest.support.security.CustomUserDetailsService.java
 * Created on 2016-05-10
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
import net.brainage.nest.data.repository.UserRepository;
import net.brainage.nest.model.CustomeUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

/**
 * @author <a href="mailto:ms29.seo+ara@gmail.com">ms29.seo</a>
 */
@Slf4j
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("input username: {}", username);
        User user = userRepository.findByUsername(username);
        log.debug("find by username : {}", user);
        if (user == null && username.indexOf("@") > 0) {
            Optional<User> optionalUser = userRepository.findOneByEmail(username);
            if (optionalUser.isPresent()) {
                user = optionalUser.get();
            }
        }

        if (user == null) {
            throw new UsernameNotFoundException("No user present with username: " + username);
        }
        log.debug("user: {}", user.toString());
        Set<UserRole> roles = user.getRoles();
        log.debug("  - roles: {}", roles);

        return new CustomeUserDetails(user);
    }

}
