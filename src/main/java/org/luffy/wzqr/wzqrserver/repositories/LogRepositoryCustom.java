/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.luffy.wzqr.wzqrserver.repositories;

import java.util.Date;
import org.luffy.wzqr.wzqrserver.entity.OLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author luffy
 */
public interface LogRepositoryCustom {
    
//    @Query("select u from OLog u where (u.who.org.superOrg.id = :superid or u.who.org.id = :superid)"
//            + " and u.type like %:type%"
////            + " and u.who.role.name like %:roleName%"
////            + " and OPERATOR(DateDifference, u.optime, :optime) < 4000"
//            + " and SQL('year(?)=year(?)', u.optime,:optime) and SQL('month(?)=month(?)', u.optime,:optime) and SQL('day(?)=day(?)', u.optime,:optime)"
////            + " and ( EXTRACT(YEAR,u.optime)=EXTRACT(YEAR,:optime) and EXTRACT(MONTH,u.optime)=EXTRACT(MONTH,:optime) and EXTRACT(DAY,u.optime)=EXTRACT(DAY,:optime))"
////            + " and u.who.loginName like %:loginName%"
//    )
    Page<OLog> customMethod(@Param("superid") Long superid,
            @Param("type") String type,
            @Param("roleName") String roleName,
            @Param("optime") Date time,
            @Param("loginName") String loginName,
            Pageable pageable); 
}
