/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.luffy.wzqr.wzqrserver.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author luffy
 */
@Entity
@Table(name = "pOrganization", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Organization implements Serializable {
    
    private static final long serialVersionUID = 436311670243359213L;
    
    public static final String NameRoot = "温州市委组织部";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    private String description;
    /**
     * 部门类型
     * 比如部门 县市区 高校 科研院所 国有企业 或者下级的 申报单位
     */
    private String type;    
    private String subType;
    @Embedded
    private ContactWay contact;
    @ManyToOne
    private Organization superOrg;
    
    @ManyToOne
    private User manager;
    
    @JsonAnyGetter
    @JsonInclude
    public Map<String,Object> getJsonData(){
        Map<String,Object> data = new HashMap();
        data.put("managerLoginName", this.getManagerLoginName());
        data.put("managerEnabled", this.isManagerEnabled());
        data.put("superOrgName", this.getSuperOrgName());
        data.put("managerLastLogin", this.getManagerLastLogin());
        return data;
    }
    
    public String getSuperOrgName(){
        if(this.getSuperOrg()==null)
            return null;
        return this.getSuperOrg().getName();
    }
    
    public Date getManagerLastLogin(){        
        if(this.getManager()==null)
            return null;
        return this.getManager().getLastLogin();
    }
    
    public String getManagerLoginName(){        
        if(this.getManager()==null)
            return null;
        return this.getManager().getLoginName();
    }
    
    public boolean isManagerEnabled(){
        if(this.getManager()==null)
            return false;
        return this.getManager().isEnabled();
    }
    
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "superOrg")
//    @JsonIgnore
//    private Set<Organization> subOrgs;
    
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "org",fetch=FetchType.EAGER)
//    @JsonIgnore
//    private Set<User> users;

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

    public ContactWay getContact() {
        return contact;
    }

    public void setContact(ContactWay contact) {
        this.contact = contact;
    }

    public Organization getSuperOrg() {
        return superOrg;
    }

    public void setSuperOrg(Organization superOrg) {
        this.superOrg = superOrg;
    }

//
//    public Set<Organization> getSubOrgs() {
//        return subOrgs;
//    }
//
//    public void setSubOrgs(Set<Organization> subOrgs) {
//        this.subOrgs = subOrgs;
//    }
//
//    public Set<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Set<User> users) {
//        this.users = users;
//    }
    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public String getType() {
        return type;
    }

    /**     
     * 部门类型
     * 比如部门 县市区 高校 科研院所 国有企业 或者下级的 申报单位
     */
    public void setType(String type) {
        this.type = type;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }
    
}
