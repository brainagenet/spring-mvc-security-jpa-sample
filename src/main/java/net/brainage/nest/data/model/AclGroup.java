/*
 * (#) net.brainage.nest.data.model.AclRule.java
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
package net.brainage.nest.data.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.brainage.nest.data.model.enums.RuleType;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * @author <a href="mailto:ms29.seo+ara@gmail.com">ms29.seo</a>
 * @JoinTable
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"hosts", "lastModifiedBy", "lastModifiedByName", "lastModifiedOn"})
@ToString(exclude = {"hosts"})
@Entity
@Table(name = "acl_groups",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_ACLGROUPS_NAME", columnNames = {"name"})
        }
)
public class AclGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false, length = 50, updatable = false)
    private String name;

    @Column(length = 255)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private RuleType rule = RuleType.ALLOW_FROM;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<AclMapping> hosts;

    @Column(nullable = false)
    private Long createdBy;

    @Column(nullable = false)
    private String createdByName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdOn;

    private Long lastModifiedBy;

    private String lastModifiedByName;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedOn;

    public void setCreatedByUser(User user) {
        this.setCreatedBy(user.getId());
        this.setCreatedByName(user.getName());
    }

    public void setLastModifiedByUser(User user) {
        this.setLastModifiedBy(user.getId());
        this.setLastModifiedByName(user.getName());
    }

}
