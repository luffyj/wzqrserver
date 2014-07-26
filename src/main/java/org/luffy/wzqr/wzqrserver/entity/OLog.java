/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luffy.wzqr.wzqrserver.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author luffy
 */
@Entity
@Table(name = "plog")
public class OLog implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User who;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date optime;
    private String ipaddress;
    private String hostname;
    private String type;
    private String targetType;//对象类别 有user有application
    private Long targetpk;
    private String message;

    public OLog() {
    }

    public OLog(User user, HttpServletRequest request, String type) {
        this();
        this.who = user;
        this.optime = new Date();
        this.ipaddress = request.getRemoteAddr();
        this.hostname = request.getRemoteHost();
        this.type = type;
    }

    @Override
    public String toString() {
        return "OLog{" + "id=" + id + ", who=" + who + ", optime=" + optime + ", ipaddress=" + ipaddress + ", hostname=" + hostname + ", type=" + type + ", targetType=" + targetType + ", targetpk=" + targetpk + ", message=" + message + '}';
    }
    
    public void opApplication(Application app) {
        this.targetType = "Application";
        this.targetpk = app.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getWho() {
        return who;
    }

    public void setWho(User who) {
        this.who = who;
    }

    public Date getOptime() {
        return optime;
    }

    public void setOptime(Date optime) {
        this.optime = optime;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public Long getTargetpk() {
        return targetpk;
    }

    public void setTargetpk(Long targetpk) {
        this.targetpk = targetpk;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
    
    
}
