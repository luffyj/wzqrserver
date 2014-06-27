/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.luffy.wzqr.wzqrserver.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author luffy
 */
@Entity
@Table(name = "pOrganization", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Organization implements Serializable {
    
    public static final String NameRoot = "温州市委组织部";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    private String description;    
    @Embedded
    private ContactWay contact;
    @ManyToOne
    private Organization superOrg;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "superOrg")
    private Set<Organization> subOrgs;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "org")
    private Set<User> users;

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

    public Set<Organization> getSubOrgs() {
        return subOrgs;
    }

    public void setSubOrgs(Set<Organization> subOrgs) {
        this.subOrgs = subOrgs;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
    
}
