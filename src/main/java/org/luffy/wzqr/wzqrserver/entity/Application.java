/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luffy.wzqr.wzqrserver.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Basic;
import javax.persistence.Entity;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author luffy
 */
@Entity
@Table(name = "pApplication")
public class Application extends ApplicationCY implements java.io.Serializable {

    public Application() {
        super();
        this.setStatus("未上报");
    }

    /**
     * 允许由这个用户导出
     *
     * @param user
     * @return
     */
    public boolean ableReportTo(User user) {
        if (user.getRole().getName().equals(Role.RoleAdmin)
                || user.getRole().getName().equals(Role.RoleRoot)
                || user.getRole().getName().equals(Role.RoleManager)) {
            return true;
        }
        try {
            if (this.getOwner() != null && this.getOwner().getId().equals(user.getId())) {
                return true;
            }
            if (this.getMyorg().getId().equals(user.getOrg().getId())) {
                return true;
            }
            return this.getMyorg().getSuperOrg().getId().equals(user.getOrg().getId());
        } catch (java.lang.NullPointerException ex) {
            return false;
        }
    }

    @ManyToOne
    private User owner;
    @ManyToOne
    private Organization myorg;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date submitDate;

    //专利情况
    private String patentDesc;

    private String city;
    //中国前单位和职务
    private String beforeOrg, beforePosition;
    private String borderDate;//到中国时间
    private String wdate;//合同
    private String platform;//引入平台
    private boolean poge;//破格
    private String comment;

    @Lob
    @Basic(fetch = LAZY)
    @JsonIgnore
    private byte[] attachment;
    
    @Lob
    private String submitReason;
    @Lob
    private String submitSupport;
    
    @Lob
    private String unitApproveReason;
    @Lob
    private String unitApproveSupport;
    @Lob
    private String pogeReason;
    @Lob
    private String orgApproveReason;
    @Lob
    private String orgApproveSupport;

    @Lob
    private String managerReason;//市委意见
    private String returnOrg;//退回
    private String returnReason;

    @Lob
    @Basic(fetch = LAZY)
    @JsonIgnore
    private byte[] picture;
    
    @JsonAnyGetter
    @JsonInclude
    public Map<String,Object> getJsonData(){
        Map<String,Object> data = new HashMap();
        data.put("ownerLoginName", this.getOwnerLoginName());
        data.put("orgSubName", this.getOrgSubName());
        return data;
    }
    
    public String getOrgSubName(){
        if(this.getMyorg().getSuperOrg()==null)
            return "";
        return this.getMyorg().getSuperOrg().getName();
    }
    
    public String getOwnerLoginName(){
        if(this.getOwner()==null)
            return "";
        return this.getOwner().getLoginName();
    }

    public String getSubmitReason() {
        return submitReason;
    }

    public void setSubmitReason(String submitReason) {
        this.submitReason = submitReason;
    }

    public String getSubmitSupport() {
        return submitSupport;
    }

    public void setSubmitSupport(String submitSupport) {
        this.submitSupport = submitSupport;
    }

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

    public Organization getMyorg() {
        return myorg;
    }

    public void setMyorg(Organization myorg) {
        this.myorg = myorg;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

}
