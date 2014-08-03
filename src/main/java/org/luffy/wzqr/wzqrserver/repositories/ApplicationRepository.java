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
 * 
 * @author luffy
 */
@RepositoryRestResource(collectionResourceRel = "application", path = "application")
public interface ApplicationRepository extends PagingAndSortingRepository<Application, Long> {
    
    /**
     * 用于次级机构 查看
     */
    // u.myorg.id = :superid or 
    @Query("select u from Application u where u.status <> '已删除' and u.status <> '未上报' and (u.myorg.superOrg.id = :superid)"
            + " and u.batch like %:batch%"
            + " and u.realName like %:realName%"
            + " and u.appOrgName like %:appOrgName%"
            + " and u.type like %:type%"
            + " and u.specialty like %:specialty%"
            + " and u.myorg.superOrg.type like %:appOrgType%"
            + " and u.status like %:status%"
            + " and u.myorg.superOrg.name like %:subName%"
    )
    Page<Application> findBySubOrg(@Param("superid") Long superid,
            @Param("batch") String batch,
            @Param("realName") String realName,
            @Param("appOrgName") String appOrgName,
            @Param("type") String type,
            @Param("specialty") String specialty,
            @Param("appOrgType") String appOrgType,
            @Param("status") String status,
            @Param("subName") String subName,
            Pageable pageable); 
    
    
    // or u.myorg.superOrg.id = :superid
    /**
     * 用户 申报单位查看
     */
    @Query("select u from Application u where u.status <> '已删除' and ( u.myorg.id = :superid )"
            + " and u.batch like %:batch%"
            + " and u.realName like %:realName%"
            + " and u.appOrgName like %:appOrgName%"
            + " and u.type like %:type%"
            + " and u.specialty like %:specialty%"
            + " and u.myorg.superOrg.type like %:appOrgType%"
            + " and u.status like %:status%"
            + " and u.myorg.superOrg.name like %:subName%"
    )
    Page<Application> findByUnitOrg(@Param("superid") Long superid,
            @Param("batch") String batch,
            @Param("realName") String realName,
            @Param("appOrgName") String appOrgName,
            @Param("type") String type,
            @Param("specialty") String specialty,
            @Param("appOrgType") String appOrgType,
            @Param("status") String status,
            @Param("subName") String subName,
            Pageable pageable); 
    
    /**
     * 以上级部门检索
     * @param pageable
     * @return list
     * @param superid id of org
     */
    @Query("select u from Application u where u.status <> '已删除' and ( u.myorg.id = :superid or u.myorg.superOrg.id = :superid)"
    )
    Page<Application> findBySuperOrgSimple(@Param("superid") Long superid,
            Pageable pageable); 
    
    /**
     * 以上级部门检索
     * @param pageable
     * @return list
     * @param superid id of org
     */
    @Query("select u from Application u where u.status <> '已删除' and ( u.myorg.id = :superid or u.myorg.superOrg.id = :superid)"
            + " and u.batch like %:batch%"
            + " and u.realName like %:realName%"
            + " and u.appOrgName like %:appOrgName%"
            + " and u.type like %:type%"
            + " and u.specialty like %:specialty%"
            + " and u.myorg.superOrg.type like %:appOrgType%"
            + " and u.status like %:status%"
    )
    Page<Application> findBySuperOrg(@Param("superid") Long superid,
            @Param("batch") String batch,
            @Param("realName") String realName,
            @Param("appOrgName") String appOrgName,
            @Param("type") String type,
            @Param("specialty") String specialty,
            @Param("appOrgType") String appOrgType,
            @Param("status") String status,
            Pageable pageable); 
    
    @Query("select u from Application u where u.status <> '已删除' and ( u.owner.id = :userid )"
    )
    Page<Application> findByOwnerSimple(@Param("userid") Long userid,
            Pageable pageable); 
    
    /**
     * 用户申报人 查看自己的审核材料
     */
    @Query("select u from Application u where u.status <> '已删除' and ( u.owner.id = :userid )"
            + " and u.batch like %:batch%"
            + " and u.realName like %:realName%"
            + " and u.appOrgName like %:appOrgName%"
            + " and u.type like %:type%"
            + " and u.specialty like %:specialty%"
            + " and u.myorg.superOrg.type like %:appOrgType%"
            + " and u.status like %:status%"
            + " and u.myorg.superOrg.name like %:subName%"
    )
    Page<Application> findByOwner(@Param("userid") Long userid,
            @Param("batch") String batch,
            @Param("realName") String realName,
            @Param("appOrgName") String appOrgName,
            @Param("type") String type,
            @Param("specialty") String specialty,
            @Param("appOrgType") String appOrgType,
            @Param("status") String status,
            @Param("subName") String subName,
            Pageable pageable); 
    
    /**
     * 用于主管和管理员查看所有的审核材料
     */
    @Query("select u from Application u where u.status <> '已删除' and u.status <> '未上报'"
            + " and u.batch like %:batch%"
            + " and u.realName like %:realName%"
            + " and u.appOrgName like %:appOrgName%"
            + " and u.type like %:type%"
            + " and u.specialty like %:specialty%"
            + " and u.myorg.superOrg.type like %:appOrgType%"
            + " and u.status like %:status%"
            + " and u.myorg.superOrg.name like %:subName%"
    )
//    @Override
    Page<Application> findWhole(
            @Param("batch") String batch,
            @Param("realName") String realName,
            @Param("appOrgName") String appOrgName,
            @Param("type") String type,
            @Param("specialty") String specialty,
            @Param("appOrgType") String appOrgType,
            @Param("status") String status,
            @Param("subName") String subName,
            Pageable pageable); 
    
    @Query("select u from Application u where u.status <> '已删除' and ( u.realName = :name )")
    Page<Application> findByName(@Param("name") String name,Pageable pageable); 
    
}
