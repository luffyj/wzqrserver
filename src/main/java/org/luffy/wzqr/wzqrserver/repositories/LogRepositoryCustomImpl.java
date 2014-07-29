/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.luffy.wzqr.wzqrserver.repositories;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.luffy.wzqr.wzqrserver.entity.OLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author luffy
 */
public class LogRepositoryCustomImpl implements LogRepositoryCustom{
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<OLog> customMethod(Long superid, String type, String roleName, Date time, String loginName, Pageable pageable) {
        StringBuilder jql = new StringBuilder("select u from OLog u where (u.who.org.superOrg.id = :superid or u.who.org.id = :superid)");
                
        Query query = entityManager.createQuery(jql.toString());
        query.setParameter("superid", superid);
        
        return new PageImpl(query.getResultList(),pageable,query.getMaxResults());
    }
    
}
