/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.luffy.wzqr.wzqrserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Basic;
import javax.persistence.Entity;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author luffy
 */
@Entity
@Table(name = "pApplication")
public class Application extends Application5 implements java.io.Serializable{
    
    @ManyToOne
    private User owner;
    @ManyToOne
    private Organization org;
    
    //专利情况
    private String patentDesc;
    
    private String city;
    //中国前单位和职务
    private String beforeOrg,beforePosition;
    private String borderDate;//到中国时间
    private String wdate;//合同
    private String platform;//引入平台
    private boolean poge;//破格
    private String comment;
    
    @Lob
    @Basic(fetch=LAZY)
    @JsonIgnore
    private byte[] attachment;
    
    private String unitApproveReason,unitApproveSupport;
    private String pogeReason;
    private String orgApproveReason,orgApproveSupport;
    
    private String managerReason;//市委意见
    private String returnOrg;//退回
    private String returnReason;

    public String getPatentDesc() {
        return patentDesc;
    }

    public void setPatentDesc(String patentDesc) {
        this.patentDesc = patentDesc;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBeforeOrg() {
        return beforeOrg;
    }

    public void setBeforeOrg(String beforeOrg) {
        this.beforeOrg = beforeOrg;
    }

    public String getBeforePosition() {
        return beforePosition;
    }

    public void setBeforePosition(String beforePosition) {
        this.beforePosition = beforePosition;
    }

    public String getBorderDate() {
        return borderDate;
    }

    public void setBorderDate(String borderDate) {
        this.borderDate = borderDate;
    }

    public String getWdate() {
        return wdate;
    }

    public void setWdate(String wdate) {
        this.wdate = wdate;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public boolean isPoge() {
        return poge;
    }

    public void setPoge(boolean poge) {
        this.poge = poge;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }

    public String getUnitApproveReason() {
        return unitApproveReason;
    }

    public void setUnitApproveReason(String unitApproveReason) {
        this.unitApproveReason = unitApproveReason;
    }

    public String getUnitApproveSupport() {
        return unitApproveSupport;
    }

    public void setUnitApproveSupport(String unitApproveSupport) {
        this.unitApproveSupport = unitApproveSupport;
    }

    public String getPogeReason() {
        return pogeReason;
    }

    public void setPogeReason(String pogeReason) {
        this.pogeReason = pogeReason;
    }

    public String getOrgApproveReason() {
        return orgApproveReason;
    }

    public void setOrgApproveReason(String orgApproveReason) {
        this.orgApproveReason = orgApproveReason;
    }

    public String getOrgApproveSupport() {
        return orgApproveSupport;
    }

    public void setOrgApproveSupport(String orgApproveSupport) {
        this.orgApproveSupport = orgApproveSupport;
    }

    public String getManagerReason() {
        return managerReason;
    }

    public void setManagerReason(String managerReason) {
        this.managerReason = managerReason;
    }

    public String getReturnOrg() {
        return returnOrg;
    }

    public void setReturnOrg(String returnOrg) {
        this.returnOrg = returnOrg;
    }

    public String getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Organization getOrg() {
        return org;
    }

    public void setOrg(Organization org) {
        this.org = org;
    }
    
    
}
