/*
 * (#) net.brainage.nest.model.CustomeUserDetails.java
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
package net.brainage.nest.model;

import net.brainage.nest.data.model.User;
import net.brainage.nest.data.model.enums.UserState;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 *
 *
 * @author <a href="mailto:ms29.seo+ara@gmail.com">ms29.seo</a>
 */
public class CustomeUserDetails extends User implements UserDetails {

    public CustomeUserDetails(final User user) {
        super();
        this.setId(user.getId());
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setName(user.getName());
        this.setEmail(user.getEmail());
        this.setLang(user.getLang());
        this.setState(user.getState());
        this.setRoles(user.getRoles());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (getRoles() != null) {
            return getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>(0);
    }

    @Override
    public boolean isAccountNonExpired() {
        return (getState() != UserState.DELETED);
    }

    @Override
    public boolean isAccountNonLocked() {
        return (getState() != UserState.LOCKED);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return (getState() == UserState.ACTIVE);
    }

}
