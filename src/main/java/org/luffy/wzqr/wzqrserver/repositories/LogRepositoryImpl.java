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
import org.luffy.wzqr.wzqrserver.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author luffy
 */
@Repository
@RestResource(path="mypath",rel="myrel")
public class LogRepositoryImpl implements LogRepositoryCustom{
    
    @PersistenceContext
    private EntityManager entityManager;

    public LogRepositoryImpl() {
        super();
    }
    

    @Override
    public Page<OLog> findDown(String type, String roleName, Date time, String loginName, Pageable pageable) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth==null)
            return null;
        if (!(auth.getPrincipal() instanceof User)) {
            return null;
        }
        User user = (User) auth.getPrincipal();
        StringBuilder jql = new StringBuilder("select u from OLog u where (u.who.org.superOrg.id = :superid or u.who.org.id = :superid)");
                
        Query query = entityManager.createQuery(jql.toString());
        query.setParameter("superid", user.getOrg().getId());
        
        return new PageImpl(query.getResultList(),pageable,query.getMaxResults());
    }
    
}
