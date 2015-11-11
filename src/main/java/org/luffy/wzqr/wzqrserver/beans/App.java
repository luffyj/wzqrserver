/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luffy.wzqr.wzqrserver.beans;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.luffy.wzqr.wzqrserver.entity.ContactWay;
import org.luffy.wzqr.wzqrserver.entity.Organization;
import org.luffy.wzqr.wzqrserver.entity.Role;
import org.luffy.wzqr.wzqrserver.entity.SystemValue;
import org.luffy.wzqr.wzqrserver.entity.User;
import org.luffy.wzqr.wzqrserver.repositories.OrgRepository;
import org.luffy.wzqr.wzqrserver.repositories.RoleRepository;
import org.luffy.wzqr.wzqrserver.repositories.SystemValueRepository;
import org.luffy.wzqr.wzqrserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 对应app自身
 *
 * @author luffy
 */
@Component
public class App{

    private final int version = 10001;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private OrgRepository orgRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SystemValueRepository systemValueRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EntityManagerFactory entityManagerFactory;
    private DBType dbtype;

    public DBType getDbtype() {
        return dbtype;
    }

    public void setDbtype(DBType dbtype) {
        this.dbtype = dbtype;
    }

//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent event) {
//        if (event.getApplicationContext().getParent() == null) {
//            try {
//                System.out.println("init App");
//                databaseInit();
//            } catch (SQLException ex) {
//                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }

    /**
     * 数据初始化
     *
     */
    @PostConstruct
    public void databaseInit() throws SQLException {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Connection con = em.unwrap(Connection.class);
        System.out.println(con.getClass().getName()+con);
        if (con.toString().contains("mysql")) {
            this.dbtype = DBType.mysql;
        } else {
            this.dbtype = DBType.derby;
        }
        try {
            if (roleRepository.count() == 0) {
                if (this.dbtype == DBType.derby) {
                    Statement stmt = con.createStatement();
                    try {
                        try {
                            stmt.execute("drop function cmpdate");
                        } catch (Throwable tx) {
                        }

                        stmt.execute("CREATE FUNCTION cmpdate\n"
                                + "( t1 TIMESTAMP,t2 TIMESTAMP )\n"
                                + "RETURNS INT\n"
                                + "PARAMETER STYLE JAVA\n"
                                + "NO SQL LANGUAGE JAVA\n"
                                + "EXTERNAL NAME 'org.luffy.derbyext.Functions.cmpDate'");
                    } finally {
                        stmt.close();
                    }

                }

                SystemValue sv = new SystemValue();
                sv.valueFor("dbversion", "" + version);

                systemValueRepository.save(sv);

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

                org = orgRepository.save(org);

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
//            Organization suborg = new Organization();
//            suborg.setName("某一个旗下部门");
//            suborg.setContact(new ContactWay("19999616"));
//            suborg.setDescription("负责本项目的次要部门");
//            suborg.setSuperOrg(org);
//            suborg.setManager(user);
//            orgRepository.save(suborg);
                user = new User();
                user.setLoginName("admin");
                user.setOrg(org);
                user.setRealName("系统管理员");
                user.setRole(roleRepository.findByName(Role.RoleRoot));
                user.setPassword(this.passwordEncoder.encode("admin"));
                userRepository.save(user);

//            user = new User();
//            user.setContact(new ContactWay("057788888888"));
//            user.setLoginName("test");
//            user.setOrg(org);
//            user.setRealName("测试账号");
//            user.setRole(roleRepository.findByName(Role.RoleAdmin));
//            user.setPassword(this.passwordEncoder.encode("123456"));
//            userRepository.save(user);
            } else {
                
//                if (this.dbtype == DBType.mysql){
//                    StringBuilder sb = new StringBuilder();
//                    sb.append("CREATE TABLE `papplication22` (")
//                            .append("`id` bigint(20) NOT NULL");
//                    
//                    String[] colums = new String[]{
//                        "jobPosition"
//                            ,"jobTime"
//                            ,"jobCountry"
//                            ,"jobOrg"
//                            ,"projectTime"
//                            ,"projectDesc"
//                            ,"projectBudget"
//                            ,"projectPeoples"
//                            ,"projectResponsibility"
//                    };
//                    for (int i = 10; i <= 20; i++) {
//                        for(String column:colums){
//                            sb.append(",`")
//                                    .append(column)
//                                    .append(i)
//                                    .append("` varchar(255) NULL");
//                        }
//                    }                    
//                    sb.append(",CONSTRAINT `FK_pApplication22_ID` FOREIGN KEY (`id`) REFERENCES `papplication1` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT");
//                    sb.append(") ENGINE=InnoDB ")
//                            .append("DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci;");
//                    System.out.println(sb);
//                }
                
                SystemValue sv = systemValueRepository.findByName("dbversion");
                if (sv.getIntValue() != version) {
                    //alter table papplication5 add column personPhone varchar(255)
//                    if(sv.getValue().equals("10001") && version==10002){
//                        Statement stmt = con.createStatement();
//                        for (int i = 10; i <= 20; i++) {
//                            stmt.execute("alter table papplication2 add column jobPosition"+i+" varchar(255)");
//                            stmt.execute("alter table papplication2 add column jobTime"+i+" varchar(255)");
//                            stmt.execute("alter table papplication2 add column jobCountry"+i+" varchar(255)");
//                            stmt.execute("alter table papplication2 add column jobOrg"+i+" varchar(255)");
//                            
//                            stmt.execute("alter table papplication2 add column projectTime"+i+" varchar(255)");
//                            stmt.execute("alter table papplication2 add column projectDesc"+i+" varchar(255)");
//                            stmt.execute("alter table papplication2 add column projectBudget"+i+" varchar(255)");
//                            stmt.execute("alter table papplication2 add column projectPeoples"+i+" varchar(255)");
//                            stmt.execute("alter table papplication2 add column projectResponsibility"+i+" varchar(255)");
//                        }                        
//                        stmt.close();
//                        sv.setValue("10002");
//                        systemValueRepository.save(sv);
//                    }else
                        throw new java.lang.IllegalStateException("当前数据库版本为" + sv.getValue() + "，应该为" + version);
                }
            }
        } finally {
            em.getTransaction().commit();
            em.close();
        }
    }
}
