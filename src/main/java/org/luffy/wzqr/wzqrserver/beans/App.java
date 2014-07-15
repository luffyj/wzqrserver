/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.luffy.wzqr.wzqrserver.beans;

import org.luffy.wzqr.wzqrserver.entity.ContactWay;
import org.luffy.wzqr.wzqrserver.entity.Organization;
import org.luffy.wzqr.wzqrserver.entity.Role;
import org.luffy.wzqr.wzqrserver.entity.User;
import org.luffy.wzqr.wzqrserver.repositories.OrgRepository;
import org.luffy.wzqr.wzqrserver.repositories.RoleRepository;
import org.luffy.wzqr.wzqrserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 对应app自身
 * @author luffy
 */
@Component
public class App implements ApplicationListener<ContextRefreshedEvent>{
    
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private OrgRepository orgRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println(event);
        databaseInit();        
    }    

    /**
     * 数据初始化
     **/
    private void databaseInit() {
        if (roleRepository.count()==0){
            Role role = new Role();
            
            role.setName(Role.RoleAdmin);
            role.setDescription("系统管理员");
            role.addAuthority(Role.AuthorityAdmin);
            
            roleRepository.save(role);  
            
            role = new Role();
            
            role.setName(Role.RoleRoot);
            role.setDescription("");
            role.addAuthority(Role.AuthorityCross);
            role.addAuthority(Role.AuthorityLog);
            role.addAuthority(Role.AuthorityManageAppDiscuss);
            role.addAuthority(Role.AuthorityManageAppEdit);
            role.addAuthority(Role.AuthorityManageAppReport);
            role.addAuthority(Role.AuthorityManageAppReview);
            role.addAuthority(Role.AuthorityTalentsReport);
            role.addAuthority(Role.AuthorityManageExamineOrganization);
            role.addAuthority(Role.AuthorityManageOrganization);
            
            roleRepository.save(role); 
            
            
            role = new Role();
            
            role.setName(Role.RoleManager);
            role.setDescription("");
            role.addAuthority(Role.AuthorityCross);
            role.addAuthority(Role.AuthorityLog);
            role.addAuthority(Role.AuthorityManageAppReviewReturn);
            
            roleRepository.save(role); 
            
            
            role = new Role();
            
            role.setName(Role.RoleSubManager);
            role.setDescription("");
            role.addAuthority(Role.AuthorityLog);
            role.addAuthority(Role.AuthorityManageAppEdit);
            role.addAuthority(Role.AuthorityManageAppReport);
            role.addAuthority(Role.AuthorityManageAppCheck);
            role.addAuthority(Role.AuthorityTalentsReport);
            role.addAuthority(Role.AuthorityManageOrganization);
            
            roleRepository.save(role); 
            
            
            role = new Role();
            
            role.setName(Role.RoleUnit);
            role.setDescription("");
            role.addAuthority(Role.AuthorityManageAppEdit);
            role.addAuthority(Role.AuthorityManageAppReport);
            role.addAuthority(Role.AuthorityManageAppSubmit);            
            role.addAuthority(Role.AuthorityManagePeople);            
            role.addAuthority(Role.AuthorityAppsReport);
            roleRepository.save(role); 
            
            
            role = new Role();
            
            role.setName(Role.RolePeople);
            role.setDescription("");
            role.addAuthority(Role.AuthorityApp);
            
            roleRepository.save(role); 
            
            ///now for orgs
            Organization org = new Organization();
            org.setName(Organization.NameRoot);
            org.setContact(new ContactWay("18606509616"));            
            org.setDescription("负责本项目的主要部门");
            
            org=orgRepository.save(org);
            
            ///now for usrs
            User user = new User();
            user.setContact(new ContactWay("18606509616"));
            user.setLoginName("luffy");
            user.setOrg(org);
            user.setRealName("蒋才");
            user.setRole(roleRepository.findByName(Role.RoleAdmin));
            user.setPassword(this.passwordEncoder.encode("123"));
            user = userRepository.save(user);
            
            // 建立一个测试用的旗下部门
            Organization suborg = new Organization();
            suborg.setName("某一个旗下部门");
            suborg.setContact(new ContactWay("19999616"));            
            suborg.setDescription("负责本项目的次要部门");
            suborg.setSuperOrg(org);
            suborg.setManager(user);
            orgRepository.save(suborg);
            
            user = new User();
            user.setContact(new ContactWay("18606509616"));
            user.setLoginName("luffy2");
            user.setOrg(org);
            user.setRealName("蒋才");
            user.setRole(roleRepository.findByName(Role.RoleRoot));
            user.setPassword(this.passwordEncoder.encode("123"));
            userRepository.save(user);
            
            user = new User();
            user.setContact(new ContactWay("057788888888"));
            user.setLoginName("test");
            user.setOrg(org);
            user.setRealName("测试账号");
            user.setRole(roleRepository.findByName(Role.RoleAdmin));
            user.setPassword(this.passwordEncoder.encode("123456"));
            userRepository.save(user);
        }
    }
}
