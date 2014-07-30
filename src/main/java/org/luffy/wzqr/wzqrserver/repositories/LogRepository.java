/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luffy.wzqr.wzqrserver.repositories;

import org.luffy.wzqr.wzqrserver.entity.OLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * 查询要点为 部门 或者具体用户 时间 类型
 *
 * @author luffy
 */
@RepositoryRestResource(collectionResourceRel = "log", path = "log")
public interface LogRepository extends PagingAndSortingRepository<OLog, Long> {

    @Query("select u from OLog u where u.who.id = :userid")
    Page<OLog> findByWho(@Param("userid") Long userid, Pageable pageable);

//    @Query("select u from OLog u where (u.who.org.superOrg.id = :superid or u.who.org.id = :superid)"
////            + " and u.type like %:type%"
////            + " and u.who.role.name like %:roleName%"
////            + " and OPERATOR(DateDifference, u.optime, :optime) < 4000"
//            + " and SQL('abs(cmptime(?,?))', u.optime,:optime) <= 0"
////            + " and ( EXTRACT(YEAR,u.optime)=EXTRACT(YEAR,:optime) and EXTRACT(MONTH,u.optime)=EXTRACT(MONTH,:optime) and EXTRACT(DAY,u.optime)=EXTRACT(DAY,:optime))"
////            + " and u.who.loginName like %:loginName%"
//    )
//    Page<OLog> findBySuborgId(@Param("superid") Long superid,
////            @Param("type") String type,
////            @Param("roleName") String roleName,
//            @Param("optime") Date optime,
////            @Param("loginName") String loginName,
//            Pageable pageable); 
}
