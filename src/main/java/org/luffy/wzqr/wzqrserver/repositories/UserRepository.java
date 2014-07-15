/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luffy.wzqr.wzqrserver.repositories;

import java.util.List;
import org.luffy.wzqr.wzqrserver.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author luffy
 */
@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    /**
     * 按名字查找用户
     *
     * @param name
     * @return
     */
    User findByLoginName(String name);

    /**
     * 按角色名查找用户
     *
     * @param name
     * @return
     */
    @Query("select u from User u where u.role.name = ?1")
    List<User> findByRolename(String name);

    /**
     * 以上级部门检索
     *
     * @param pageable
     * @return list
     * @param superid id of org
     */
    @Query("select u from User u where u.org.id = :orgid")
    Page<User> findByOrg(@Param("orgid") Long orgid, Pageable pageable);
}
