/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.luffy.wzqr.wzqrserver.repositories;

import org.luffy.wzqr.wzqrserver.entity.SystemValue;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author luffy
 */
@RepositoryRestResource(collectionResourceRel = "systemValue", path = "systemValue")
public interface SystemValueRepository extends PagingAndSortingRepository<SystemValue, Long> {

    SystemValue findByName(String name);
    
}
