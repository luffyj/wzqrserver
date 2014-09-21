/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.luffy.wzqr.wzqrserver.web.utils;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import org.luffy.wzqr.wzqrserver.entity.ContactWay;
import org.luffy.wzqr.wzqrserver.entity.Organization;

/**
 *
 * @author luffy
 */
@Embeddable
public class RegisterBean implements Serializable {
    
    private String name;
    @Lob
    private String description;
    @ManyToOne
    private Organization superOrg;
    @Transient
    private Long superPk;
    private ContactWay contact;
    private String contactJob;    
//    private String type;    默认 申报单位
    private String username;
    private String password;

    public Organization getSuperOrg() {
        return superOrg;
    }

    public void setSuperOrg(Organization superOrg) {
        this.superOrg = superOrg;
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

    public Long getSuperPk() {
        return superPk;
    }

    public void setSuperPk(Long superPk) {
        this.superPk = superPk;
    }

    public ContactWay getContact() {
        return contact;
    }

    public void setContact(ContactWay contact) {
        this.contact = contact;
    }

    public String getContactJob() {
        return contactJob;
    }

    public void setContactJob(String contactJob) {
        this.contactJob = contactJob;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
