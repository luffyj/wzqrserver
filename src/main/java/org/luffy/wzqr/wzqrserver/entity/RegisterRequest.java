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
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.luffy.wzqr.wzqrserver.repositories.OrgRepository;
import org.luffy.wzqr.wzqrserver.web.utils.RegisterBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author luffy
 */
@Entity
@Table(name = "pRegisterRequest")
public class RegisterRequest implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Embedded
    private RegisterBean bean;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createDate;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date changeDate;
    private String status;//申请 拒绝 允许
    private String reason;
    
//    @Autowired
//    @javax.persistence.Transient
//    private OrgRepository orgRepository;
    
    @JsonAnyGetter
    @JsonInclude
    public Map<String,Object> getJsonData(){
        Map<String,Object> data = new HashMap();
        data.put("superOrgName", this.getSuperOrgName());
        return data;
    }
    
    public String getSuperOrgName(){
        if(this.getBean()==null)
            return null;
        if(this.getBean().getSuperOrg()==null)
            return null;
        return this.getBean().getSuperOrg().getName();
//        if(this.getBean().getSuperPk()==null)
//            return null;
//        return this.orgRepository.findOne(this.getBean().getSuperPk()).getName();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RegisterBean getBean() {
        return bean;
    }

    public void setBean(RegisterBean bean) {
        this.bean = bean;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
