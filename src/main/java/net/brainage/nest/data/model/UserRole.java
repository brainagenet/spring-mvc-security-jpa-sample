/*
 * (#) net.brainage.nest.data.model.UserRole.java
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
package net.brainage.nest.data.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author <a href="mailto:ms29.seo@gmail.com">ms29.seo</a>
 */
@Data
@NoArgsConstructor
@ToString(exclude = {"user"})
@Entity
@Table(name = "user_roles", uniqueConstraints = {
        @UniqueConstraint(name = "UK_USERROLES_NAME", columnNames = {"name"})
})
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_USERROLES_USERID_USERS_ID"))
    private User user;

}
