/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.luffy.wzqr.wzqrserver.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 *
 * @author luffy
 */
@Entity
@Table(name = "pRole", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Role implements Serializable{
    
    /**
     * admin
     * 系统管理员
     * 开发机上拥有所有权限 生产机上无任何权限
     */
    public static final String AuthorityAdmin = "admin";    
    /**
     * cross
     * 跨部门管理权限
     */
    public static final String AuthorityCross = "cross";
    /**
     * manageAppEdit
     * 管理本部门申报-编辑
     */
    public static final String AuthorityManageAppEdit = "manageAppEdit";    
    /**
     * manageAppReview
     * 管理本部门申报-复审
     */
    public static final String AuthorityManageAppReview = "manageAppReview";
    /**
     * manageAppReviewReturn
     * 管理本部门申报-复审退回
     */
    public static final String AuthorityManageAppReviewReturn = "manageAppReviewReturn";
    /**
     * manageAppCheck
     * 管理本部门申报-形审
     */
    public static final String AuthorityManageAppCheck = "manageAppCheck";
    /**
     * manageAppDiscuss
     * 管理本部门申报-评审
     */
    public static final String AuthorityManageAppDiscuss = "manageAppDiscuss";        
    /**
     * manageAppReport
     * 管理本部门申报-导出
     */
    public static final String AuthorityManageAppReport = "manageAppReport";
    /**
     * manageAppSubmit
     * 管理本部门申报-上报
     */
    public static final String AuthorityManageAppSubmit = "manageAppSubmit";
    
    /**
     * talentsReport
     * 本部门统计各类人才的申报人数
     */
    public static final String AuthorityTalentsReport = "talentsReport";
    /**
     * manageOrganization
     * 创建和管理下级机构和管理员
     * 比如市委可以创建次级管理员
     * 次级管理员可以创建用人单位
     */
    public static final String AuthorityManageOrganization = "manageOrganization";
    /**
     * manageExamineOrganization
     * 创建非所属的核查机构 比如社保局
     */
    public static final String AuthorityManageExamineOrganization = "manageExamineOrganization";
    /**
     * log
     * 日志
     */
    public static final String AuthorityLog = "log";
    /**
     * app
     * 申报人才有的权限
     */
    public static final String AuthorityApp = "app";
    /**
     * managePeople
     * 管理本组织机构的申报人
     */
    public static final String AuthorityManagePeople = "managePeople";
    
    /**
     * manageAppReport
     * 查看本组织机构的申报情况
     */
    public static final String AuthorityAppsReport = "appsReport";
    
    
    
    public static final String RoleAdmin="系统管理员";
    
    /**
     * 对申报信息进行编辑、复审和导出,统计各类人才的申报人数,创建
     * 用人单位账号,创建市县区管理员账号,查看操作日志。
     * 
     * 所属部门 温州市委组织部
     * 可以创建次级机构和管理部门
     */
    public static final String RoleRoot="温州市委组织部管理员";
    /**
     * 查看申报信息,可以对申报信息进行复审退回,查看操作日志。
     * 
     * 部门一般包括温州市人力社保局等。与温州市组织部管理员属同一等级,账号由市管理员创建,但只有复审退回权限。
     * 
     */
    public static final String RoleManager="部门管理员";
    
    /**
     * 对申报信息进行编辑、形审和导出,统计各类人才的申报人数,创建用人单位账号,查看操作日志。
     * 
     */
    public static final String RoleSubManager="次级机构管理员";
    
    /**
     * 对申报信息进行增加、上报、编辑、打印,对申报人账号进行管理,统计本单位申报的情况。
     * 
     */
    public static final String RoleUnit="申报单位";
    public static final String RolePeople="申报人";
    
    public static GrantedAuthority grantedAuthorityByRole(String role){
        return new SimpleGrantedAuthority(role);
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    private String description;
    /**
     * 所拥有的权利字符串 以,为分隔符
     */
    private String authorities;
    
    /**
     * 增加权限
     * @param authority 具体权限
     */
    public void addAuthority(String authority) {
        String cauths = this.getAuthorities();
        if(cauths==null)
            cauths = "";
        StringBuilder sb = new StringBuilder(cauths);
        if (sb.length()>0){
            sb.append(',');
        }
        sb.append(authority);
        this.setAuthorities(sb.toString());
    }
    
    public Collection<? extends GrantedAuthority> getGrantedAuthority(){
        if(this.getAuthorities()==null){
            return Collections.EMPTY_LIST;
        }
        ArrayList<GrantedAuthority> list  = new ArrayList();
        for(String s:this.getAuthorities().split(",")){
            list.add(Role.grantedAuthorityByRole(s));
        }
        return list;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

}
