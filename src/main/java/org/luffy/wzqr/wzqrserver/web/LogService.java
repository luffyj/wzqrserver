/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luffy.wzqr.wzqrserver.web;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import org.luffy.wzqr.wzqrserver.beans.App;
import org.luffy.wzqr.wzqrserver.beans.DBType;
import org.luffy.wzqr.wzqrserver.entity.OLog;
import org.luffy.wzqr.wzqrserver.entity.User;
import org.luffy.wzqr.wzqrserver.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author luffy
 */
@Controller
@RequestMapping("log")
public class LogService {

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private PagedResourcesAssembler<Object> assembler;

    @Autowired
    private EntityManagerFactory entityManagerFactory;
    
    @Autowired
    private App app;

//    @PersistenceContext
//    private EntityManager entityManager;
//    @RequestMapping("/{id}")
//    public String showUserForm(@PathVariable("id") OLog user, Model model) {
//
//        model.addAttribute("user", user);
//        return "userForm";
//    }
//    
    @RequestMapping(value = "/underlogs", method = RequestMethod.GET)
    HttpEntity<PagedResources<OLog>> persons(Pageable pageable,
            //需要使用那个么？ 算了吧。。
            @RequestParam("type") String type,
            @RequestParam("roleName") String roleName,
            @RequestParam("time") String time,
            @RequestParam("loginName") String loginName,
            PagedResourcesAssembler assembler) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        if (!(auth.getPrincipal() instanceof User)) {
            return null;
        }
        User user = (User) auth.getPrincipal();

        StringBuilder jql = new StringBuilder("select u from OLog u");
        boolean and = false;
        if (!user.isAbleManage()) {
            jql.append(" where (u.who.org.superOrg.id = :superid or u.who.org.id = :superid)");
            and = true;
        }

        if (StringUtils.hasLength(type)) {
            if (and) {
                jql.append(" and");
            } else {
                jql.append(" where");
            }
            jql.append(" u.type like :type");
            and = true;
        }

        if (StringUtils.hasLength(roleName)) {
            if (and) {
                jql.append(" and");
            } else {
                jql.append(" where");
            }
            jql.append(" (u.who.role.name like :roleName or u.who.org.name like :roleName)");
            and = true;
        }

        if (StringUtils.hasLength(loginName)) {
            if (and) {
                jql.append(" and");
            } else {
                jql.append(" where");
            }
            jql.append(" u.who.loginName like :loginName");
            and = true;
        }

        Date ltime = null;
        if (StringUtils.hasLength(time)) {
            ltime = new Date(NumberUtils.parseNumber(time, Long.class));
            if (and) {
                jql.append(" and");
            } else {
                jql.append(" where");
            }
            //mysql  TIMESTAMPDIFF(MICROSECOND,?,?)
            if(app.getDbtype()==DBType.derby){
                jql.append(" SQL('abs(cmpdate(?,?)) < 86400000',u.optime,:optime)");
            }else{
                jql.append(" SQL('abs(TIMESTAMPDIFF(MICROSECOND,?,?)) < 86400000',u.optime,:optime)");
            }
            
//            jql.append(" SQL('UNIX_TIMESTAMP(?)=UNIX_TIMESTAMP(?)',u.optime,:optime)");
        }
        EntityManager em = this.entityManagerFactory.createEntityManager();

        try {
            Query query = em.createQuery(jql.toString());

            if (!user.isAbleManage()) {
                query.setParameter("superid", user.getOrg().getId());
            }

            if (StringUtils.hasLength(type)) {
                query.setParameter("type", "%" + type + "%");
            }
            if (StringUtils.hasLength(roleName)) {
                query.setParameter("roleName", "%" + roleName + "%");
            }
            if (StringUtils.hasLength(loginName)) {
                query.setParameter("loginName", "%" + loginName + "%");
            }

            if (ltime != null) {
                query.setParameter("optime", ltime,TemporalType.TIMESTAMP);
            }

            List<OLog> allLogs = query.getResultList();
            Page<OLog> persons = new PageImpl(allLogs.subList(Math.min(pageable.getOffset(), allLogs.size()), Math.min(pageable.getOffset() + pageable.getPageSize(), allLogs.size())), pageable, allLogs.size());
            PagedResources<OLog> resource = assembler.toResource(persons);
            return new ResponseEntity(resource, HttpStatus.OK);

        } finally {
            em.close();
        }

        //        PersistentEntityResource a;
        //        PersistentEntityResourceAssembler b;
        //        PagedResourcesAssembler<Object> self;            
//            Page<OLog> persons = this.logRepository.findAll(pageable);   
    }

}
