/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luffy.wzqr.wzqrserver.repositories;

import org.luffy.wzqr.wzqrserver.entity.Application;
import org.luffy.wzqr.wzqrserver.entity.RegisterRequest;
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
@RepositoryRestResource(collectionResourceRel = "registerrequest", path = "registerrequest")
public interface RegisterRequestRepository extends PagingAndSortingRepository<RegisterRequest, Long> {

    @Query("select u from RegisterRequest u where"
            + " u.status like %:status%"
            + " and u.bean.name like %:name%"
            + " and u.bean.superOrg.name like %:supername%"
    )
    Page<Application> findBySuperOrg(@Param("status") String status,
            @Param("name") String name,
            @Param("supername") String supername,
            Pageable pageable);
}
