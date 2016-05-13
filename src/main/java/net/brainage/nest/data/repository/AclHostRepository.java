/*
 * (#) net.brainage.nest.data.repository.AclHostRepository.java
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
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author <a href="mailto:ms29.seo+ara@gmail.com">ms29.seo</a>
 */
public interface AclHostRepository extends JpaRepository<AclHost, Integer> {

    // @Query("select h from AclHost h INNER JOIN h.groups g where g.id = :groupId")
    /*
    @Query("select h from AclHost h JOIN h.groups g where g.id = :groupId")
    List<AclHost> findAllByGroupId(@Param("groupId") int groupId);
    */


    /*
select h.id, h.name, h.description,
       h.created_by, h.created_by_name, h.created_on,
       h.last_modified_by, h.last_modified_by_name, h.last_modified_on,
       mg.group_id
from acl_hosts h
LEFT OUTER JOIN (
  select group_id, host_id from acl_mappings m
  left outer join acl_groups g on m.group_id = g.id
  where m.group_id = 1
) mg on h.id = mg.host_id
where mg.group_id is null
     */

    /**
     *
     * @param groupId
     * @return
     * @see <a href="http://stackoverflow.com/questions/26720565/jpql-query-for-many-to-many-relationship-where-item-does-not-exist-in-join-table">JPQL query for many to many relationship where item does not exist in join table</a>
     */
    /*
    @Query("select h from AclHost h where not exists (select 1 from AclGroup g where g.id = :groupId and h member of g.hosts)")
    List<AclHost> findAllNotAssginedByGroup(@Param("groupId") int groupId);
    */

}
