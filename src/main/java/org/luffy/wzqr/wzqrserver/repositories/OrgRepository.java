/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.luffy.wzqr.wzqrserver.repositories;

import java.util.List;
import org.luffy.wzqr.wzqrserver.entity.Organization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author luffy
 */
@RepositoryRestResource(collectionResourceRel = "org", path = "org")
public interface OrgRepository extends PagingAndSortingRepository<Organization, Long> {

    Organization findByName(String name);
    
    
    /**
     * 增加次级部门选择器
     */
    @Query("select u from Organization u where u.superOrg.name = '温州市委组织部'")
    Page<Organization> findSubOrg(Pageable pageable);
    
    /**
     * 以上级部门检索
     * @param pageable
     * @return list
     * @param superid id of org
     */
    @Query("select u from Organization u where u.superOrg.id = :superid")
    Page<Organization> findBySuperOrg(@Param("superid") Long superid,Pageable pageable);    
}
