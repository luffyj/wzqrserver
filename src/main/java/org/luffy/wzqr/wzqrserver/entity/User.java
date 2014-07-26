/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.luffy.wzqr.wzqrserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 
 * @author luffy
 */
@Entity
@Table(name = "pUser", uniqueConstraints = @UniqueConstraint(columnNames = {"loginName"}))
public class User implements UserDetails,Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String loginName;
    private String realName;
    //职务
    private String position;
    private String realEnglishName;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private boolean accountNonExpired=true;
    @JsonIgnore
    private boolean accountNonLocked=true;
    @JsonIgnore
    private boolean credentialsNonExpired=true;        
    private boolean enabled=true;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;
    
    @ManyToOne
    private Role role;
    @ManyToOne
    private Organization org;
    @Embedded
    private ContactWay contact;
        
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(role==null)
            return Collections.EMPTY_LIST;
        return role.getGrantedAuthority();
    }
    
    @Override
    @JsonIgnore
    public String getUsername(){
        return this.loginName;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", loginName=" + loginName + ", realName=" + realName + ", position=" + position + ", realEnglishName=" + realEnglishName + ", password=" + password + ", accountNonExpired=" + accountNonExpired + ", accountNonLocked=" + accountNonLocked + ", credentialsNonExpired=" + credentialsNonExpired + ", enabled=" + enabled + ", lastLogin=" + lastLogin + ", role=" + role + ", org=" + org + ", contact=" + contact + '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Organization getOrg() {
        return org;
    }

    public void setOrg(Organization org) {
        this.org = org;
    }

    public ContactWay getContact() {
        return contact;
    }

    public void setContact(ContactWay contact) {
        this.contact = contact;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRealEnglishName() {
        return realEnglishName;
    }

    public void setRealEnglishName(String realEnglishName) {
        this.realEnglishName = realEnglishName;
    }

}
