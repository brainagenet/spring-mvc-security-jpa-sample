/*
 * (#) net.brainage.nest.data.model.AclMapping.java
 * Created on 2016-05-13
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
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @author <a href="mailto:ms29.seo+ara@gmail.com">ms29.seo</a>
 */
@Data
@EqualsAndHashCode(exclude = {"group", "host", "createdBy", "createdByName", "createdOn"})
@ToString(exclude = {"group", "host"})
@Entity
@Table(name = "acl_mappings")
@IdClass(AclMappingId.class)
public class AclMapping {

    @Id
    @Column(name="group_id", insertable = false, updatable = false)
    private int groupId;

    @Id
    @Column(name="host_id", insertable = false, updatable = false)
    private int hostId;

    /* if this JPA model doesn't create a table for the "acl_mappings" entity,
     *  please comment out the @PrimaryKeyJoinColumn, and use the ff:
     *  @JoinColumn(name = "group_id", updatable = false, insertable = false)
     * or @JoinColumn(name = "group_id", updatable = false, insertable = false, referencedColumnName = "id")
     */
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "group_id", referencedColumnName = "id")
    private AclGroup group;

    /* the same goes here:
     *  if this JPA model doesn't create a table for the "acl_mappings" entity,
     *  please comment out the @PrimaryKeyJoinColumn, and use the ff:
     *  @JoinColumn(name = "host_id", updatable = false, insertable = false)
     * or @JoinColumn(name = "host_id", updatable = false, insertable = false, referencedColumnName = "id")
     */
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "host_id", referencedColumnName = "id")
    private AclHost host;

    private int createdBy;

    private String createdByName;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

}
