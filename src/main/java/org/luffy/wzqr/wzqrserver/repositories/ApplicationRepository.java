/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.luffy.wzqr.wzqrserver.repositories;

import org.luffy.wzqr.wzqrserver.entity.Application;
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
@RepositoryRestResource(collectionResourceRel = "application", path = "application")
public interface ApplicationRepository extends PagingAndSortingRepository<Application, Long> {
    
    /**
     * 以上级部门检索
     * @param pageable
     * @return list
     * @param superid id of org
     */
    @Query("select u from Application u where u.status <> '已删除' and ( u.myorg.id = :superid or u.myorg.superOrg.id = :superid)")
    Page<Application> findBySuperOrg(@Param("superid") Long superid,Pageable pageable); 
    
    @Query("select u from Application u where u.status <> '已删除' and ( u.owner.id = :userid )")
    Page<Application> findByOwner(@Param("userid") Long userid,Pageable pageable); 
    
    @Query("select u from Application u where u.status <> '已删除'")
    @Override
    Page<Application> findAll(Pageable pageable); 
    
    

}
