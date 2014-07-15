/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.luffy.wzqr.wzqrserver.entity;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author luffy
 */
@Entity
@Table(name = "pApplication")
public class Application implements java.io.Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private User owner;
    @ManyToOne
    private Organization org;
    //状态
    private String status;
    //批次
    private String batch;
    //创新人才 创业人才
    private String type;
    //专业领域
    private String specialty;
    private String profession;
    
    private String number;
    private String realName,realEnglishName;
    //申报单位
    private String appOrgName;
    
    private String people;
    private String mobile,phone;
    @Temporal(TemporalType.DATE)
    private Date comletionDate;//填表日期
    
    private int sex;
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    private String birthPlace;
    private String nationality;
    
    private String mgChineseCountry,mgChineseSchool,mgChineseMajor,mgChineseDegree;
    private String mgEnglishCountry,mgEnglishSchool,mgEnglishMajor,mgEnglishDegree;
    
    //回国前
    private String foreignJobChinese,foreignJobEnglish;
    //现任
    private String employer,position,employerAddress,zip;
    
    private String degree1,time1,country1,university1,major1;
    private String degree2,time2,country2,university2,major2;
    private String degree3,time3,country3,university3,major3;
    private String degree4,time4,country4,university4,major4;
    private String degree5,time5,country5,university5,major5;
    private String degree6,time6,country6,university6,major6;
    private String degree7,time7,country7,university7,major7;
    
    private String jobPosition1,jobTime1,jobCountry1,jobOrg1;
    private String jobPosition2,jobTime2,jobCountry2,jobOrg2;
    private String jobPosition3,jobTime3,jobCountry3,jobOrg3;
    private String jobPosition4,jobTime4,jobCountry4,jobOrg4;
    private String jobPosition5,jobTime5,jobCountry5,jobOrg5;
    private String jobPosition6,jobTime6,jobCountry6,jobOrg6;
    private String jobPosition7,jobTime7,jobCountry7,jobOrg7;
    private String jobPosition8,jobTime8,jobCountry8,jobOrg8;
    private String jobPosition9,jobTime9,jobCountry9,jobOrg9;
    
    @Lob
    private String expertTo;//专长
    
    private String projectTime1,projectDesc1,projectBudget1,projectPeoples1,projectResponsibility1;
    private String projectTime2,projectDesc2,projectBudget2,projectPeoples2,projectResponsibility2;
    private String projectTime3,projectDesc3,projectBudget3,projectPeoples3,projectResponsibility3;
    private String projectTime4,projectDesc4,projectBudget4,projectPeoples4,projectResponsibility4;
    private String projectTime5,projectDesc5,projectBudget5,projectPeoples5,projectResponsibility5;
    private String projectTime6,projectDesc6,projectBudget6,projectPeoples6,projectResponsibility6;
    private String projectTime7,projectDesc7,projectBudget7,projectPeoples7,projectResponsibility7;
    private String projectTime8,projectDesc8,projectBudget8,projectPeoples8,projectResponsibility8;
    private String projectTime9,projectDesc9,projectBudget9,projectPeoples9,projectResponsibility9;
    
    private String paperTime1,paperTitle1,paperMedia1,paperAuthor1;
    private String paperTime2,paperTitle2,paperMedia2,paperAuthor2;
    private String paperTime3,paperTitle3,paperMedia3,paperAuthor3;
    private String paperTime4,paperTitle4,paperMedia4,paperAuthor4;
    private String paperTime5,paperTitle5,paperMedia5,paperAuthor5;
    private String paperTime6,paperTitle6,paperMedia6,paperAuthor6;
    private String paperTime7,paperTitle7,paperMedia7,paperAuthor7;
    private String paperTime8,paperTitle8,paperMedia8,paperAuthor8;
    private String paperTime9,paperTitle9,paperMedia9,paperAuthor9;
    private String paperTime10,paperTitle10,paperMedia10,paperAuthor10;
    private String paperTime11,paperTitle11,paperMedia11,paperAuthor11;
    private String paperTime12,paperTitle12,paperMedia12,paperAuthor12;
    private String paperTime13,paperTitle13,paperMedia13,paperAuthor13;
    private String paperTime14,paperTitle14,paperMedia14,paperAuthor14;
    private String paperTime15,paperTitle15,paperMedia15,paperAuthor15;
    private String paperTime16,paperTitle16,paperMedia16,paperAuthor16;
    private String paperTime17,paperTitle17,paperMedia17,paperAuthor17;
    private String paperTime18,paperTitle18,paperMedia18,paperAuthor18;
    private String paperTime19,paperTitle19,paperMedia19,paperAuthor19;
    private String paperTime20,paperTitle20,paperMedia20,paperAuthor20;
    
    //专利
    private String patentTime1,patentName1,patentCountry1,patentAuthor1;
    private String patentTime2,patentName2,patentCountry2,patentAuthor2;
    private String patentTime3,patentName3,patentCountry3,patentAuthor3;
    private String patentTime4,patentName4,patentCountry4,patentAuthor4;
    private String patentTime5,patentName5,patentCountry5,patentAuthor5;
    private String patentTime6,patentName6,patentCountry6,patentAuthor6;
    private String patentTime7,patentName7,patentCountry7,patentAuthor7;
    private String patentTime8,patentName8,patentCountry8,patentAuthor8;
    private String patentTime9,patentName9,patentCountry9,patentAuthor9;
    private String patentTime10,patentName10,patentCountry10,patentAuthor10;
    private String patentTime11,patentName11,patentCountry11,patentAuthor11;
    private String patentTime12,patentName12,patentCountry12,patentAuthor12;
    private String patentTime13,patentName13,patentCountry13,patentAuthor13;
    private String patentTime14,patentName14,patentCountry14,patentAuthor14;
    private String patentTime15,patentName15,patentCountry15,patentAuthor15;
    private String patentTime16,patentName16,patentCountry16,patentAuthor16;
    private String patentTime17,patentName17,patentCountry17,patentAuthor17;
    private String patentTime18,patentName18,patentCountry18,patentAuthor18;
    private String patentTime19,patentName19,patentCountry19,patentAuthor19;
    private String patentTime20,patentName20,patentCountry20,patentAuthor20;
    
    @Lob
    private String product;//产品
    @Lob
    private String otherProduct,objectives,agreement;
    
    private String idType;
    private String idNumber;
    
    private String address;
    private String addressOut,phoneOut;
    private String email;
    
    //关系
    private String relationship1,relationshipName1,relationshipAge1,relationshipCountry1,relationshipJob1;
    private String relationship2,relationshipName2,relationshipAge2,relationshipCountry2,relationshipJob2;
    private String relationship3,relationshipName3,relationshipAge3,relationshipCountry3,relationshipJob3;
    private String relationship4,relationshipName4,relationshipAge4,relationshipCountry4,relationshipJob4;
    private String relationship5,relationshipName5,relationshipAge5,relationshipCountry5,relationshipJob5;
    private String relationship6,relationshipName6,relationshipAge6,relationshipCountry6,relationshipJob6;
    private String relationship7,relationshipName7,relationshipAge7,relationshipCountry7,relationshipJob7;
    private String relationship8,relationshipName8,relationshipAge8,relationshipCountry8,relationshipJob8;
    private String relationship9,relationshipName9,relationshipAge9,relationshipCountry9,relationshipJob9;
    private String relationship10,relationshipName10,relationshipAge10,relationshipCountry10,relationshipJob10;
    
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
    private byte[] attachment;
    
    private String unitApproveReason,unitApproveSupport;
    private String pogeReason;
    private String orgApproveReason,orgApproveSupport;
    
    private String managerReason;//市委意见
    private String returnOrg;//退回
    private String returnReason;
        

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRealEnglishName() {
        return realEnglishName;
    }

    public void setRealEnglishName(String realEnglishName) {
        this.realEnglishName = realEnglishName;
    }

    public String getAppOrgName() {
        return appOrgName;
    }

    public void setAppOrgName(String appOrgName) {
        this.appOrgName = appOrgName;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getComletionDate() {
        return comletionDate;
    }

    public void setComletionDate(Date comletionDate) {
        this.comletionDate = comletionDate;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getMgChineseCountry() {
        return mgChineseCountry;
    }

    public void setMgChineseCountry(String mgChineseCountry) {
        this.mgChineseCountry = mgChineseCountry;
    }

    public String getMgChineseSchool() {
        return mgChineseSchool;
    }

    public void setMgChineseSchool(String mgChineseSchool) {
        this.mgChineseSchool = mgChineseSchool;
    }

    public String getMgChineseMajor() {
        return mgChineseMajor;
    }

    public void setMgChineseMajor(String mgChineseMajor) {
        this.mgChineseMajor = mgChineseMajor;
    }

    public String getMgChineseDegree() {
        return mgChineseDegree;
    }

    public void setMgChineseDegree(String mgChineseDegree) {
        this.mgChineseDegree = mgChineseDegree;
    }

    public String getMgEnglishCountry() {
        return mgEnglishCountry;
    }

    public void setMgEnglishCountry(String mgEnglishCountry) {
        this.mgEnglishCountry = mgEnglishCountry;
    }

    public String getMgEnglishSchool() {
        return mgEnglishSchool;
    }

    public void setMgEnglishSchool(String mgEnglishSchool) {
        this.mgEnglishSchool = mgEnglishSchool;
    }

    public String getMgEnglishMajor() {
        return mgEnglishMajor;
    }

    public void setMgEnglishMajor(String mgEnglishMajor) {
        this.mgEnglishMajor = mgEnglishMajor;
    }

    public String getMgEnglishDegree() {
        return mgEnglishDegree;
    }

    public void setMgEnglishDegree(String mgEnglishDegree) {
        this.mgEnglishDegree = mgEnglishDegree;
    }

    public String getForeignJobChinese() {
        return foreignJobChinese;
    }

    public void setForeignJobChinese(String foreignJobChinese) {
        this.foreignJobChinese = foreignJobChinese;
    }

    public String getForeignJobEnglish() {
        return foreignJobEnglish;
    }

    public void setForeignJobEnglish(String foreignJobEnglish) {
        this.foreignJobEnglish = foreignJobEnglish;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmployerAddress() {
        return employerAddress;
    }

    public void setEmployerAddress(String employerAddress) {
        this.employerAddress = employerAddress;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getDegree1() {
        return degree1;
    }

    public void setDegree1(String degree1) {
        this.degree1 = degree1;
    }

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getCountry1() {
        return country1;
    }

    public void setCountry1(String country1) {
        this.country1 = country1;
    }

    public String getUniversity1() {
        return university1;
    }

    public void setUniversity1(String university1) {
        this.university1 = university1;
    }

    public String getMajor1() {
        return major1;
    }

    public void setMajor1(String major1) {
        this.major1 = major1;
    }

    public String getDegree2() {
        return degree2;
    }

    public void setDegree2(String degree2) {
        this.degree2 = degree2;
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }

    public String getCountry2() {
        return country2;
    }

    public void setCountry2(String country2) {
        this.country2 = country2;
    }

    public String getUniversity2() {
        return university2;
    }

    public void setUniversity2(String university2) {
        this.university2 = university2;
    }

    public String getMajor2() {
        return major2;
    }

    public void setMajor2(String major2) {
        this.major2 = major2;
    }

    public String getDegree3() {
        return degree3;
    }

    public void setDegree3(String degree3) {
        this.degree3 = degree3;
    }

    public String getTime3() {
        return time3;
    }

    public void setTime3(String time3) {
        this.time3 = time3;
    }

    public String getCountry3() {
        return country3;
    }

    public void setCountry3(String country3) {
        this.country3 = country3;
    }

    public String getUniversity3() {
        return university3;
    }

    public void setUniversity3(String university3) {
        this.university3 = university3;
    }

    public String getMajor3() {
        return major3;
    }

    public void setMajor3(String major3) {
        this.major3 = major3;
    }

    public String getDegree4() {
        return degree4;
    }

    public void setDegree4(String degree4) {
        this.degree4 = degree4;
    }

    public String getTime4() {
        return time4;
    }

    public void setTime4(String time4) {
        this.time4 = time4;
    }

    public String getCountry4() {
        return country4;
    }

    public void setCountry4(String country4) {
        this.country4 = country4;
    }

    public String getUniversity4() {
        return university4;
    }

    public void setUniversity4(String university4) {
        this.university4 = university4;
    }

    public String getMajor4() {
        return major4;
    }

    public void setMajor4(String major4) {
        this.major4 = major4;
    }

    public String getDegree5() {
        return degree5;
    }

    public void setDegree5(String degree5) {
        this.degree5 = degree5;
    }

    public String getTime5() {
        return time5;
    }

    public void setTime5(String time5) {
        this.time5 = time5;
    }

    public String getCountry5() {
        return country5;
    }

    public void setCountry5(String country5) {
        this.country5 = country5;
    }

    public String getUniversity5() {
        return university5;
    }

    public void setUniversity5(String university5) {
        this.university5 = university5;
    }

    public String getMajor5() {
        return major5;
    }

    public void setMajor5(String major5) {
        this.major5 = major5;
    }

    public String getDegree6() {
        return degree6;
    }

    public void setDegree6(String degree6) {
        this.degree6 = degree6;
    }

    public String getTime6() {
        return time6;
    }

    public void setTime6(String time6) {
        this.time6 = time6;
    }

    public String getCountry6() {
        return country6;
    }

    public void setCountry6(String country6) {
        this.country6 = country6;
    }

    public String getUniversity6() {
        return university6;
    }

    public void setUniversity6(String university6) {
        this.university6 = university6;
    }

    public String getMajor6() {
        return major6;
    }

    public void setMajor6(String major6) {
        this.major6 = major6;
    }

    public String getDegree7() {
        return degree7;
    }

    public void setDegree7(String degree7) {
        this.degree7 = degree7;
    }

    public String getTime7() {
        return time7;
    }

    public void setTime7(String time7) {
        this.time7 = time7;
    }

    public String getCountry7() {
        return country7;
    }

    public void setCountry7(String country7) {
        this.country7 = country7;
    }

    public String getUniversity7() {
        return university7;
    }

    public void setUniversity7(String university7) {
        this.university7 = university7;
    }

    public String getMajor7() {
        return major7;
    }

    public void setMajor7(String major7) {
        this.major7 = major7;
    }

    public String getJobPosition1() {
        return jobPosition1;
    }

    public void setJobPosition1(String jobPosition1) {
        this.jobPosition1 = jobPosition1;
    }

    public String getJobTime1() {
        return jobTime1;
    }

    public void setJobTime1(String jobTime1) {
        this.jobTime1 = jobTime1;
    }

    public String getJobCountry1() {
        return jobCountry1;
    }

    public void setJobCountry1(String jobCountry1) {
        this.jobCountry1 = jobCountry1;
    }

    public String getJobOrg1() {
        return jobOrg1;
    }

    public void setJobOrg1(String jobOrg1) {
        this.jobOrg1 = jobOrg1;
    }

    public String getJobPosition2() {
        return jobPosition2;
    }

    public void setJobPosition2(String jobPosition2) {
        this.jobPosition2 = jobPosition2;
    }

    public String getJobTime2() {
        return jobTime2;
    }

    public void setJobTime2(String jobTime2) {
        this.jobTime2 = jobTime2;
    }

    public String getJobCountry2() {
        return jobCountry2;
    }

    public void setJobCountry2(String jobCountry2) {
        this.jobCountry2 = jobCountry2;
    }

    public String getJobOrg2() {
        return jobOrg2;
    }

    public void setJobOrg2(String jobOrg2) {
        this.jobOrg2 = jobOrg2;
    }

    public String getJobPosition3() {
        return jobPosition3;
    }

    public void setJobPosition3(String jobPosition3) {
        this.jobPosition3 = jobPosition3;
    }

    public String getJobTime3() {
        return jobTime3;
    }

    public void setJobTime3(String jobTime3) {
        this.jobTime3 = jobTime3;
    }

    public String getJobCountry3() {
        return jobCountry3;
    }

    public void setJobCountry3(String jobCountry3) {
        this.jobCountry3 = jobCountry3;
    }

    public String getJobOrg3() {
        return jobOrg3;
    }

    public void setJobOrg3(String jobOrg3) {
        this.jobOrg3 = jobOrg3;
    }

    public String getJobPosition4() {
        return jobPosition4;
    }

    public void setJobPosition4(String jobPosition4) {
        this.jobPosition4 = jobPosition4;
    }

    public String getJobTime4() {
        return jobTime4;
    }

    public void setJobTime4(String jobTime4) {
        this.jobTime4 = jobTime4;
    }

    public String getJobCountry4() {
        return jobCountry4;
    }

    public void setJobCountry4(String jobCountry4) {
        this.jobCountry4 = jobCountry4;
    }

    public String getJobOrg4() {
        return jobOrg4;
    }

    public void setJobOrg4(String jobOrg4) {
        this.jobOrg4 = jobOrg4;
    }

    public String getJobPosition5() {
        return jobPosition5;
    }

    public void setJobPosition5(String jobPosition5) {
        this.jobPosition5 = jobPosition5;
    }

    public String getJobTime5() {
        return jobTime5;
    }

    public void setJobTime5(String jobTime5) {
        this.jobTime5 = jobTime5;
    }

    public String getJobCountry5() {
        return jobCountry5;
    }

    public void setJobCountry5(String jobCountry5) {
        this.jobCountry5 = jobCountry5;
    }

    public String getJobOrg5() {
        return jobOrg5;
    }

    public void setJobOrg5(String jobOrg5) {
        this.jobOrg5 = jobOrg5;
    }

    public String getJobPosition6() {
        return jobPosition6;
    }

    public void setJobPosition6(String jobPosition6) {
        this.jobPosition6 = jobPosition6;
    }

    public String getJobTime6() {
        return jobTime6;
    }

    public void setJobTime6(String jobTime6) {
        this.jobTime6 = jobTime6;
    }

    public String getJobCountry6() {
        return jobCountry6;
    }

    public void setJobCountry6(String jobCountry6) {
        this.jobCountry6 = jobCountry6;
    }

    public String getJobOrg6() {
        return jobOrg6;
    }

    public void setJobOrg6(String jobOrg6) {
        this.jobOrg6 = jobOrg6;
    }

    public String getJobPosition7() {
        return jobPosition7;
    }

    public void setJobPosition7(String jobPosition7) {
        this.jobPosition7 = jobPosition7;
    }

    public String getJobTime7() {
        return jobTime7;
    }

    public void setJobTime7(String jobTime7) {
        this.jobTime7 = jobTime7;
    }

    public String getJobCountry7() {
        return jobCountry7;
    }

    public void setJobCountry7(String jobCountry7) {
        this.jobCountry7 = jobCountry7;
    }

    public String getJobOrg7() {
        return jobOrg7;
    }

    public void setJobOrg7(String jobOrg7) {
        this.jobOrg7 = jobOrg7;
    }

    public String getJobPosition8() {
        return jobPosition8;
    }

    public void setJobPosition8(String jobPosition8) {
        this.jobPosition8 = jobPosition8;
    }

    public String getJobTime8() {
        return jobTime8;
    }

    public void setJobTime8(String jobTime8) {
        this.jobTime8 = jobTime8;
    }

    public String getJobCountry8() {
        return jobCountry8;
    }

    public void setJobCountry8(String jobCountry8) {
        this.jobCountry8 = jobCountry8;
    }

    public String getJobOrg8() {
        return jobOrg8;
    }

    public void setJobOrg8(String jobOrg8) {
        this.jobOrg8 = jobOrg8;
    }

    public String getJobPosition9() {
        return jobPosition9;
    }

    public void setJobPosition9(String jobPosition9) {
        this.jobPosition9 = jobPosition9;
    }

    public String getJobTime9() {
        return jobTime9;
    }

    public void setJobTime9(String jobTime9) {
        this.jobTime9 = jobTime9;
    }

    public String getJobCountry9() {
        return jobCountry9;
    }

    public void setJobCountry9(String jobCountry9) {
        this.jobCountry9 = jobCountry9;
    }

    public String getJobOrg9() {
        return jobOrg9;
    }

    public void setJobOrg9(String jobOrg9) {
        this.jobOrg9 = jobOrg9;
    }

    public String getExpertTo() {
        return expertTo;
    }

    public void setExpertTo(String expertTo) {
        this.expertTo = expertTo;
    }

    public String getProjectTime1() {
        return projectTime1;
    }

    public void setProjectTime1(String projectTime1) {
        this.projectTime1 = projectTime1;
    }

    public String getProjectDesc1() {
        return projectDesc1;
    }

    public void setProjectDesc1(String projectDesc1) {
        this.projectDesc1 = projectDesc1;
    }

    public String getProjectBudget1() {
        return projectBudget1;
    }

    public void setProjectBudget1(String projectBudget1) {
        this.projectBudget1 = projectBudget1;
    }

    public String getProjectPeoples1() {
        return projectPeoples1;
    }

    public void setProjectPeoples1(String projectPeoples1) {
        this.projectPeoples1 = projectPeoples1;
    }

    public String getProjectResponsibility1() {
        return projectResponsibility1;
    }

    public void setProjectResponsibility1(String projectResponsibility1) {
        this.projectResponsibility1 = projectResponsibility1;
    }

    public String getProjectTime2() {
        return projectTime2;
    }

    public void setProjectTime2(String projectTime2) {
        this.projectTime2 = projectTime2;
    }

    public String getProjectDesc2() {
        return projectDesc2;
    }

    public void setProjectDesc2(String projectDesc2) {
        this.projectDesc2 = projectDesc2;
    }

    public String getProjectBudget2() {
        return projectBudget2;
    }

    public void setProjectBudget2(String projectBudget2) {
        this.projectBudget2 = projectBudget2;
    }

    public String getProjectPeoples2() {
        return projectPeoples2;
    }

    public void setProjectPeoples2(String projectPeoples2) {
        this.projectPeoples2 = projectPeoples2;
    }

    public String getProjectResponsibility2() {
        return projectResponsibility2;
    }

    public void setProjectResponsibility2(String projectResponsibility2) {
        this.projectResponsibility2 = projectResponsibility2;
    }

    public String getProjectTime3() {
        return projectTime3;
    }

    public void setProjectTime3(String projectTime3) {
        this.projectTime3 = projectTime3;
    }

    public String getProjectDesc3() {
        return projectDesc3;
    }

    public void setProjectDesc3(String projectDesc3) {
        this.projectDesc3 = projectDesc3;
    }

    public String getProjectBudget3() {
        return projectBudget3;
    }

    public void setProjectBudget3(String projectBudget3) {
        this.projectBudget3 = projectBudget3;
    }

    public String getProjectPeoples3() {
        return projectPeoples3;
    }

    public void setProjectPeoples3(String projectPeoples3) {
        this.projectPeoples3 = projectPeoples3;
    }

    public String getProjectResponsibility3() {
        return projectResponsibility3;
    }

    public void setProjectResponsibility3(String projectResponsibility3) {
        this.projectResponsibility3 = projectResponsibility3;
    }

    public String getProjectTime4() {
        return projectTime4;
    }

    public void setProjectTime4(String projectTime4) {
        this.projectTime4 = projectTime4;
    }

    public String getProjectDesc4() {
        return projectDesc4;
    }

    public void setProjectDesc4(String projectDesc4) {
        this.projectDesc4 = projectDesc4;
    }

    public String getProjectBudget4() {
        return projectBudget4;
    }

    public void setProjectBudget4(String projectBudget4) {
        this.projectBudget4 = projectBudget4;
    }

    public String getProjectPeoples4() {
        return projectPeoples4;
    }

    public void setProjectPeoples4(String projectPeoples4) {
        this.projectPeoples4 = projectPeoples4;
    }

    public String getProjectResponsibility4() {
        return projectResponsibility4;
    }

    public void setProjectResponsibility4(String projectResponsibility4) {
        this.projectResponsibility4 = projectResponsibility4;
    }

    public String getProjectTime5() {
        return projectTime5;
    }

    public void setProjectTime5(String projectTime5) {
        this.projectTime5 = projectTime5;
    }

    public String getProjectDesc5() {
        return projectDesc5;
    }

    public void setProjectDesc5(String projectDesc5) {
        this.projectDesc5 = projectDesc5;
    }

    public String getProjectBudget5() {
        return projectBudget5;
    }

    public void setProjectBudget5(String projectBudget5) {
        this.projectBudget5 = projectBudget5;
    }

    public String getProjectPeoples5() {
        return projectPeoples5;
    }

    public void setProjectPeoples5(String projectPeoples5) {
        this.projectPeoples5 = projectPeoples5;
    }

    public String getProjectResponsibility5() {
        return projectResponsibility5;
    }

    public void setProjectResponsibility5(String projectResponsibility5) {
        this.projectResponsibility5 = projectResponsibility5;
    }

    public String getProjectTime6() {
        return projectTime6;
    }

    public void setProjectTime6(String projectTime6) {
        this.projectTime6 = projectTime6;
    }

    public String getProjectDesc6() {
        return projectDesc6;
    }

    public void setProjectDesc6(String projectDesc6) {
        this.projectDesc6 = projectDesc6;
    }

    public String getProjectBudget6() {
        return projectBudget6;
    }

    public void setProjectBudget6(String projectBudget6) {
        this.projectBudget6 = projectBudget6;
    }

    public String getProjectPeoples6() {
        return projectPeoples6;
    }

    public void setProjectPeoples6(String projectPeoples6) {
        this.projectPeoples6 = projectPeoples6;
    }

    public String getProjectResponsibility6() {
        return projectResponsibility6;
    }

    public void setProjectResponsibility6(String projectResponsibility6) {
        this.projectResponsibility6 = projectResponsibility6;
    }

    public String getProjectTime7() {
        return projectTime7;
    }

    public void setProjectTime7(String projectTime7) {
        this.projectTime7 = projectTime7;
    }

    public String getProjectDesc7() {
        return projectDesc7;
    }

    public void setProjectDesc7(String projectDesc7) {
        this.projectDesc7 = projectDesc7;
    }

    public String getProjectBudget7() {
        return projectBudget7;
    }

    public void setProjectBudget7(String projectBudget7) {
        this.projectBudget7 = projectBudget7;
    }

    public String getProjectPeoples7() {
        return projectPeoples7;
    }

    public void setProjectPeoples7(String projectPeoples7) {
        this.projectPeoples7 = projectPeoples7;
    }

    public String getProjectResponsibility7() {
        return projectResponsibility7;
    }

    public void setProjectResponsibility7(String projectResponsibility7) {
        this.projectResponsibility7 = projectResponsibility7;
    }

    public String getProjectTime8() {
        return projectTime8;
    }

    public void setProjectTime8(String projectTime8) {
        this.projectTime8 = projectTime8;
    }

    public String getProjectDesc8() {
        return projectDesc8;
    }

    public void setProjectDesc8(String projectDesc8) {
        this.projectDesc8 = projectDesc8;
    }

    public String getProjectBudget8() {
        return projectBudget8;
    }

    public void setProjectBudget8(String projectBudget8) {
        this.projectBudget8 = projectBudget8;
    }

    public String getProjectPeoples8() {
        return projectPeoples8;
    }

    public void setProjectPeoples8(String projectPeoples8) {
        this.projectPeoples8 = projectPeoples8;
    }

    public String getProjectResponsibility8() {
        return projectResponsibility8;
    }

    public void setProjectResponsibility8(String projectResponsibility8) {
        this.projectResponsibility8 = projectResponsibility8;
    }

    public String getProjectTime9() {
        return projectTime9;
    }

    public void setProjectTime9(String projectTime9) {
        this.projectTime9 = projectTime9;
    }

    public String getProjectDesc9() {
        return projectDesc9;
    }

    public void setProjectDesc9(String projectDesc9) {
        this.projectDesc9 = projectDesc9;
    }

    public String getProjectBudget9() {
        return projectBudget9;
    }

    public void setProjectBudget9(String projectBudget9) {
        this.projectBudget9 = projectBudget9;
    }

    public String getProjectPeoples9() {
        return projectPeoples9;
    }

    public void setProjectPeoples9(String projectPeoples9) {
        this.projectPeoples9 = projectPeoples9;
    }

    public String getProjectResponsibility9() {
        return projectResponsibility9;
    }

    public void setProjectResponsibility9(String projectResponsibility9) {
        this.projectResponsibility9 = projectResponsibility9;
    }

    public String getPaperTime1() {
        return paperTime1;
    }

    public void setPaperTime1(String paperTime1) {
        this.paperTime1 = paperTime1;
    }

    public String getPaperTitle1() {
        return paperTitle1;
    }

    public void setPaperTitle1(String paperTitle1) {
        this.paperTitle1 = paperTitle1;
    }

    public String getPaperMedia1() {
        return paperMedia1;
    }

    public void setPaperMedia1(String paperMedia1) {
        this.paperMedia1 = paperMedia1;
    }

    public String getPaperAuthor1() {
        return paperAuthor1;
    }

    public void setPaperAuthor1(String paperAuthor1) {
        this.paperAuthor1 = paperAuthor1;
    }

    public String getPaperTime2() {
        return paperTime2;
    }

    public void setPaperTime2(String paperTime2) {
        this.paperTime2 = paperTime2;
    }

    public String getPaperTitle2() {
        return paperTitle2;
    }

    public void setPaperTitle2(String paperTitle2) {
        this.paperTitle2 = paperTitle2;
    }

    public String getPaperMedia2() {
        return paperMedia2;
    }

    public void setPaperMedia2(String paperMedia2) {
        this.paperMedia2 = paperMedia2;
    }

    public String getPaperAuthor2() {
        return paperAuthor2;
    }

    public void setPaperAuthor2(String paperAuthor2) {
        this.paperAuthor2 = paperAuthor2;
    }

    public String getPaperTime3() {
        return paperTime3;
    }

    public void setPaperTime3(String paperTime3) {
        this.paperTime3 = paperTime3;
    }

    public String getPaperTitle3() {
        return paperTitle3;
    }

    public void setPaperTitle3(String paperTitle3) {
        this.paperTitle3 = paperTitle3;
    }

    public String getPaperMedia3() {
        return paperMedia3;
    }

    public void setPaperMedia3(String paperMedia3) {
        this.paperMedia3 = paperMedia3;
    }

    public String getPaperAuthor3() {
        return paperAuthor3;
    }

    public void setPaperAuthor3(String paperAuthor3) {
        this.paperAuthor3 = paperAuthor3;
    }

    public String getPaperTime4() {
        return paperTime4;
    }

    public void setPaperTime4(String paperTime4) {
        this.paperTime4 = paperTime4;
    }

    public String getPaperTitle4() {
        return paperTitle4;
    }

    public void setPaperTitle4(String paperTitle4) {
        this.paperTitle4 = paperTitle4;
    }

    public String getPaperMedia4() {
        return paperMedia4;
    }

    public void setPaperMedia4(String paperMedia4) {
        this.paperMedia4 = paperMedia4;
    }

    public String getPaperAuthor4() {
        return paperAuthor4;
    }

    public void setPaperAuthor4(String paperAuthor4) {
        this.paperAuthor4 = paperAuthor4;
    }

    public String getPaperTime5() {
        return paperTime5;
    }

    public void setPaperTime5(String paperTime5) {
        this.paperTime5 = paperTime5;
    }

    public String getPaperTitle5() {
        return paperTitle5;
    }

    public void setPaperTitle5(String paperTitle5) {
        this.paperTitle5 = paperTitle5;
    }

    public String getPaperMedia5() {
        return paperMedia5;
    }

    public void setPaperMedia5(String paperMedia5) {
        this.paperMedia5 = paperMedia5;
    }

    public String getPaperAuthor5() {
        return paperAuthor5;
    }

    public void setPaperAuthor5(String paperAuthor5) {
        this.paperAuthor5 = paperAuthor5;
    }

    public String getPaperTime6() {
        return paperTime6;
    }

    public void setPaperTime6(String paperTime6) {
        this.paperTime6 = paperTime6;
    }

    public String getPaperTitle6() {
        return paperTitle6;
    }

    public void setPaperTitle6(String paperTitle6) {
        this.paperTitle6 = paperTitle6;
    }

    public String getPaperMedia6() {
        return paperMedia6;
    }

    public void setPaperMedia6(String paperMedia6) {
        this.paperMedia6 = paperMedia6;
    }

    public String getPaperAuthor6() {
        return paperAuthor6;
    }

    public void setPaperAuthor6(String paperAuthor6) {
        this.paperAuthor6 = paperAuthor6;
    }

    public String getPaperTime7() {
        return paperTime7;
    }

    public void setPaperTime7(String paperTime7) {
        this.paperTime7 = paperTime7;
    }

    public String getPaperTitle7() {
        return paperTitle7;
    }

    public void setPaperTitle7(String paperTitle7) {
        this.paperTitle7 = paperTitle7;
    }

    public String getPaperMedia7() {
        return paperMedia7;
    }

    public void setPaperMedia7(String paperMedia7) {
        this.paperMedia7 = paperMedia7;
    }

    public String getPaperAuthor7() {
        return paperAuthor7;
    }

    public void setPaperAuthor7(String paperAuthor7) {
        this.paperAuthor7 = paperAuthor7;
    }

    public String getPaperTime8() {
        return paperTime8;
    }

    public void setPaperTime8(String paperTime8) {
        this.paperTime8 = paperTime8;
    }

    public String getPaperTitle8() {
        return paperTitle8;
    }

    public void setPaperTitle8(String paperTitle8) {
        this.paperTitle8 = paperTitle8;
    }

    public String getPaperMedia8() {
        return paperMedia8;
    }

    public void setPaperMedia8(String paperMedia8) {
        this.paperMedia8 = paperMedia8;
    }

    public String getPaperAuthor8() {
        return paperAuthor8;
    }

    public void setPaperAuthor8(String paperAuthor8) {
        this.paperAuthor8 = paperAuthor8;
    }

    public String getPaperTime9() {
        return paperTime9;
    }

    public void setPaperTime9(String paperTime9) {
        this.paperTime9 = paperTime9;
    }

    public String getPaperTitle9() {
        return paperTitle9;
    }

    public void setPaperTitle9(String paperTitle9) {
        this.paperTitle9 = paperTitle9;
    }

    public String getPaperMedia9() {
        return paperMedia9;
    }

    public void setPaperMedia9(String paperMedia9) {
        this.paperMedia9 = paperMedia9;
    }

    public String getPaperAuthor9() {
        return paperAuthor9;
    }

    public void setPaperAuthor9(String paperAuthor9) {
        this.paperAuthor9 = paperAuthor9;
    }

    public String getPaperTime10() {
        return paperTime10;
    }

    public void setPaperTime10(String paperTime10) {
        this.paperTime10 = paperTime10;
    }

    public String getPaperTitle10() {
        return paperTitle10;
    }

    public void setPaperTitle10(String paperTitle10) {
        this.paperTitle10 = paperTitle10;
    }

    public String getPaperMedia10() {
        return paperMedia10;
    }

    public void setPaperMedia10(String paperMedia10) {
        this.paperMedia10 = paperMedia10;
    }

    public String getPaperAuthor10() {
        return paperAuthor10;
    }

    public void setPaperAuthor10(String paperAuthor10) {
        this.paperAuthor10 = paperAuthor10;
    }

    public String getPaperTime11() {
        return paperTime11;
    }

    public void setPaperTime11(String paperTime11) {
        this.paperTime11 = paperTime11;
    }

    public String getPaperTitle11() {
        return paperTitle11;
    }

    public void setPaperTitle11(String paperTitle11) {
        this.paperTitle11 = paperTitle11;
    }

    public String getPaperMedia11() {
        return paperMedia11;
    }

    public void setPaperMedia11(String paperMedia11) {
        this.paperMedia11 = paperMedia11;
    }

    public String getPaperAuthor11() {
        return paperAuthor11;
    }

    public void setPaperAuthor11(String paperAuthor11) {
        this.paperAuthor11 = paperAuthor11;
    }

    public String getPaperTime12() {
        return paperTime12;
    }

    public void setPaperTime12(String paperTime12) {
        this.paperTime12 = paperTime12;
    }

    public String getPaperTitle12() {
        return paperTitle12;
    }

    public void setPaperTitle12(String paperTitle12) {
        this.paperTitle12 = paperTitle12;
    }

    public String getPaperMedia12() {
        return paperMedia12;
    }

    public void setPaperMedia12(String paperMedia12) {
        this.paperMedia12 = paperMedia12;
    }

    public String getPaperAuthor12() {
        return paperAuthor12;
    }

    public void setPaperAuthor12(String paperAuthor12) {
        this.paperAuthor12 = paperAuthor12;
    }

    public String getPaperTime13() {
        return paperTime13;
    }

    public void setPaperTime13(String paperTime13) {
        this.paperTime13 = paperTime13;
    }

    public String getPaperTitle13() {
        return paperTitle13;
    }

    public void setPaperTitle13(String paperTitle13) {
        this.paperTitle13 = paperTitle13;
    }

    public String getPaperMedia13() {
        return paperMedia13;
    }

    public void setPaperMedia13(String paperMedia13) {
        this.paperMedia13 = paperMedia13;
    }

    public String getPaperAuthor13() {
        return paperAuthor13;
    }

    public void setPaperAuthor13(String paperAuthor13) {
        this.paperAuthor13 = paperAuthor13;
    }

    public String getPaperTime14() {
        return paperTime14;
    }

    public void setPaperTime14(String paperTime14) {
        this.paperTime14 = paperTime14;
    }

    public String getPaperTitle14() {
        return paperTitle14;
    }

    public void setPaperTitle14(String paperTitle14) {
        this.paperTitle14 = paperTitle14;
    }

    public String getPaperMedia14() {
        return paperMedia14;
    }

    public void setPaperMedia14(String paperMedia14) {
        this.paperMedia14 = paperMedia14;
    }

    public String getPaperAuthor14() {
        return paperAuthor14;
    }

    public void setPaperAuthor14(String paperAuthor14) {
        this.paperAuthor14 = paperAuthor14;
    }

    public String getPaperTime15() {
        return paperTime15;
    }

    public void setPaperTime15(String paperTime15) {
        this.paperTime15 = paperTime15;
    }

    public String getPaperTitle15() {
        return paperTitle15;
    }

    public void setPaperTitle15(String paperTitle15) {
        this.paperTitle15 = paperTitle15;
    }

    public String getPaperMedia15() {
        return paperMedia15;
    }

    public void setPaperMedia15(String paperMedia15) {
        this.paperMedia15 = paperMedia15;
    }

    public String getPaperAuthor15() {
        return paperAuthor15;
    }

    public void setPaperAuthor15(String paperAuthor15) {
        this.paperAuthor15 = paperAuthor15;
    }

    public String getPaperTime16() {
        return paperTime16;
    }

    public void setPaperTime16(String paperTime16) {
        this.paperTime16 = paperTime16;
    }

    public String getPaperTitle16() {
        return paperTitle16;
    }

    public void setPaperTitle16(String paperTitle16) {
        this.paperTitle16 = paperTitle16;
    }

    public String getPaperMedia16() {
        return paperMedia16;
    }

    public void setPaperMedia16(String paperMedia16) {
        this.paperMedia16 = paperMedia16;
    }

    public String getPaperAuthor16() {
        return paperAuthor16;
    }

    public void setPaperAuthor16(String paperAuthor16) {
        this.paperAuthor16 = paperAuthor16;
    }

    public String getPaperTime17() {
        return paperTime17;
    }

    public void setPaperTime17(String paperTime17) {
        this.paperTime17 = paperTime17;
    }

    public String getPaperTitle17() {
        return paperTitle17;
    }

    public void setPaperTitle17(String paperTitle17) {
        this.paperTitle17 = paperTitle17;
    }

    public String getPaperMedia17() {
        return paperMedia17;
    }

    public void setPaperMedia17(String paperMedia17) {
        this.paperMedia17 = paperMedia17;
    }

    public String getPaperAuthor17() {
        return paperAuthor17;
    }

    public void setPaperAuthor17(String paperAuthor17) {
        this.paperAuthor17 = paperAuthor17;
    }

    public String getPaperTime18() {
        return paperTime18;
    }

    public void setPaperTime18(String paperTime18) {
        this.paperTime18 = paperTime18;
    }

    public String getPaperTitle18() {
        return paperTitle18;
    }

    public void setPaperTitle18(String paperTitle18) {
        this.paperTitle18 = paperTitle18;
    }

    public String getPaperMedia18() {
        return paperMedia18;
    }

    public void setPaperMedia18(String paperMedia18) {
        this.paperMedia18 = paperMedia18;
    }

    public String getPaperAuthor18() {
        return paperAuthor18;
    }

    public void setPaperAuthor18(String paperAuthor18) {
        this.paperAuthor18 = paperAuthor18;
    }

    public String getPaperTime19() {
        return paperTime19;
    }

    public void setPaperTime19(String paperTime19) {
        this.paperTime19 = paperTime19;
    }

    public String getPaperTitle19() {
        return paperTitle19;
    }

    public void setPaperTitle19(String paperTitle19) {
        this.paperTitle19 = paperTitle19;
    }

    public String getPaperMedia19() {
        return paperMedia19;
    }

    public void setPaperMedia19(String paperMedia19) {
        this.paperMedia19 = paperMedia19;
    }

    public String getPaperAuthor19() {
        return paperAuthor19;
    }

    public void setPaperAuthor19(String paperAuthor19) {
        this.paperAuthor19 = paperAuthor19;
    }

    public String getPaperTime20() {
        return paperTime20;
    }

    public void setPaperTime20(String paperTime20) {
        this.paperTime20 = paperTime20;
    }

    public String getPaperTitle20() {
        return paperTitle20;
    }

    public void setPaperTitle20(String paperTitle20) {
        this.paperTitle20 = paperTitle20;
    }

    public String getPaperMedia20() {
        return paperMedia20;
    }

    public void setPaperMedia20(String paperMedia20) {
        this.paperMedia20 = paperMedia20;
    }

    public String getPaperAuthor20() {
        return paperAuthor20;
    }

    public void setPaperAuthor20(String paperAuthor20) {
        this.paperAuthor20 = paperAuthor20;
    }

    public String getPatentTime1() {
        return patentTime1;
    }

    public void setPatentTime1(String patentTime1) {
        this.patentTime1 = patentTime1;
    }

    public String getPatentName1() {
        return patentName1;
    }

    public void setPatentName1(String patentName1) {
        this.patentName1 = patentName1;
    }

    public String getPatentCountry1() {
        return patentCountry1;
    }

    public void setPatentCountry1(String patentCountry1) {
        this.patentCountry1 = patentCountry1;
    }

    public String getPatentAuthor1() {
        return patentAuthor1;
    }

    public void setPatentAuthor1(String patentAuthor1) {
        this.patentAuthor1 = patentAuthor1;
    }

    public String getPatentTime2() {
        return patentTime2;
    }

    public void setPatentTime2(String patentTime2) {
        this.patentTime2 = patentTime2;
    }

    public String getPatentName2() {
        return patentName2;
    }

    public void setPatentName2(String patentName2) {
        this.patentName2 = patentName2;
    }

    public String getPatentCountry2() {
        return patentCountry2;
    }

    public void setPatentCountry2(String patentCountry2) {
        this.patentCountry2 = patentCountry2;
    }

    public String getPatentAuthor2() {
        return patentAuthor2;
    }

    public void setPatentAuthor2(String patentAuthor2) {
        this.patentAuthor2 = patentAuthor2;
    }

    public String getPatentTime3() {
        return patentTime3;
    }

    public void setPatentTime3(String patentTime3) {
        this.patentTime3 = patentTime3;
    }

    public String getPatentName3() {
        return patentName3;
    }

    public void setPatentName3(String patentName3) {
        this.patentName3 = patentName3;
    }

    public String getPatentCountry3() {
        return patentCountry3;
    }

    public void setPatentCountry3(String patentCountry3) {
        this.patentCountry3 = patentCountry3;
    }

    public String getPatentAuthor3() {
        return patentAuthor3;
    }

    public void setPatentAuthor3(String patentAuthor3) {
        this.patentAuthor3 = patentAuthor3;
    }

    public String getPatentTime4() {
        return patentTime4;
    }

    public void setPatentTime4(String patentTime4) {
        this.patentTime4 = patentTime4;
    }

    public String getPatentName4() {
        return patentName4;
    }

    public void setPatentName4(String patentName4) {
        this.patentName4 = patentName4;
    }

    public String getPatentCountry4() {
        return patentCountry4;
    }

    public void setPatentCountry4(String patentCountry4) {
        this.patentCountry4 = patentCountry4;
    }

    public String getPatentAuthor4() {
        return patentAuthor4;
    }

    public void setPatentAuthor4(String patentAuthor4) {
        this.patentAuthor4 = patentAuthor4;
    }

    public String getPatentTime5() {
        return patentTime5;
    }

    public void setPatentTime5(String patentTime5) {
        this.patentTime5 = patentTime5;
    }

    public String getPatentName5() {
        return patentName5;
    }

    public void setPatentName5(String patentName5) {
        this.patentName5 = patentName5;
    }

    public String getPatentCountry5() {
        return patentCountry5;
    }

    public void setPatentCountry5(String patentCountry5) {
        this.patentCountry5 = patentCountry5;
    }

    public String getPatentAuthor5() {
        return patentAuthor5;
    }

    public void setPatentAuthor5(String patentAuthor5) {
        this.patentAuthor5 = patentAuthor5;
    }

    public String getPatentTime6() {
        return patentTime6;
    }

    public void setPatentTime6(String patentTime6) {
        this.patentTime6 = patentTime6;
    }

    public String getPatentName6() {
        return patentName6;
    }

    public void setPatentName6(String patentName6) {
        this.patentName6 = patentName6;
    }

    public String getPatentCountry6() {
        return patentCountry6;
    }

    public void setPatentCountry6(String patentCountry6) {
        this.patentCountry6 = patentCountry6;
    }

    public String getPatentAuthor6() {
        return patentAuthor6;
    }

    public void setPatentAuthor6(String patentAuthor6) {
        this.patentAuthor6 = patentAuthor6;
    }

    public String getPatentTime7() {
        return patentTime7;
    }

    public void setPatentTime7(String patentTime7) {
        this.patentTime7 = patentTime7;
    }

    public String getPatentName7() {
        return patentName7;
    }

    public void setPatentName7(String patentName7) {
        this.patentName7 = patentName7;
    }

    public String getPatentCountry7() {
        return patentCountry7;
    }

    public void setPatentCountry7(String patentCountry7) {
        this.patentCountry7 = patentCountry7;
    }

    public String getPatentAuthor7() {
        return patentAuthor7;
    }

    public void setPatentAuthor7(String patentAuthor7) {
        this.patentAuthor7 = patentAuthor7;
    }

    public String getPatentTime8() {
        return patentTime8;
    }

    public void setPatentTime8(String patentTime8) {
        this.patentTime8 = patentTime8;
    }

    public String getPatentName8() {
        return patentName8;
    }

    public void setPatentName8(String patentName8) {
        this.patentName8 = patentName8;
    }

    public String getPatentCountry8() {
        return patentCountry8;
    }

    public void setPatentCountry8(String patentCountry8) {
        this.patentCountry8 = patentCountry8;
    }

    public String getPatentAuthor8() {
        return patentAuthor8;
    }

    public void setPatentAuthor8(String patentAuthor8) {
        this.patentAuthor8 = patentAuthor8;
    }

    public String getPatentTime9() {
        return patentTime9;
    }

    public void setPatentTime9(String patentTime9) {
        this.patentTime9 = patentTime9;
    }

    public String getPatentName9() {
        return patentName9;
    }

    public void setPatentName9(String patentName9) {
        this.patentName9 = patentName9;
    }

    public String getPatentCountry9() {
        return patentCountry9;
    }

    public void setPatentCountry9(String patentCountry9) {
        this.patentCountry9 = patentCountry9;
    }

    public String getPatentAuthor9() {
        return patentAuthor9;
    }

    public void setPatentAuthor9(String patentAuthor9) {
        this.patentAuthor9 = patentAuthor9;
    }

    public String getPatentTime10() {
        return patentTime10;
    }

    public void setPatentTime10(String patentTime10) {
        this.patentTime10 = patentTime10;
    }

    public String getPatentName10() {
        return patentName10;
    }

    public void setPatentName10(String patentName10) {
        this.patentName10 = patentName10;
    }

    public String getPatentCountry10() {
        return patentCountry10;
    }

    public void setPatentCountry10(String patentCountry10) {
        this.patentCountry10 = patentCountry10;
    }

    public String getPatentAuthor10() {
        return patentAuthor10;
    }

    public void setPatentAuthor10(String patentAuthor10) {
        this.patentAuthor10 = patentAuthor10;
    }

    public String getPatentTime11() {
        return patentTime11;
    }

    public void setPatentTime11(String patentTime11) {
        this.patentTime11 = patentTime11;
    }

    public String getPatentName11() {
        return patentName11;
    }

    public void setPatentName11(String patentName11) {
        this.patentName11 = patentName11;
    }

    public String getPatentCountry11() {
        return patentCountry11;
    }

    public void setPatentCountry11(String patentCountry11) {
        this.patentCountry11 = patentCountry11;
    }

    public String getPatentAuthor11() {
        return patentAuthor11;
    }

    public void setPatentAuthor11(String patentAuthor11) {
        this.patentAuthor11 = patentAuthor11;
    }

    public String getPatentTime12() {
        return patentTime12;
    }

    public void setPatentTime12(String patentTime12) {
        this.patentTime12 = patentTime12;
    }

    public String getPatentName12() {
        return patentName12;
    }

    public void setPatentName12(String patentName12) {
        this.patentName12 = patentName12;
    }

    public String getPatentCountry12() {
        return patentCountry12;
    }

    public void setPatentCountry12(String patentCountry12) {
        this.patentCountry12 = patentCountry12;
    }

    public String getPatentAuthor12() {
        return patentAuthor12;
    }

    public void setPatentAuthor12(String patentAuthor12) {
        this.patentAuthor12 = patentAuthor12;
    }

    public String getPatentTime13() {
        return patentTime13;
    }

    public void setPatentTime13(String patentTime13) {
        this.patentTime13 = patentTime13;
    }

    public String getPatentName13() {
        return patentName13;
    }

    public void setPatentName13(String patentName13) {
        this.patentName13 = patentName13;
    }

    public String getPatentCountry13() {
        return patentCountry13;
    }

    public void setPatentCountry13(String patentCountry13) {
        this.patentCountry13 = patentCountry13;
    }

    public String getPatentAuthor13() {
        return patentAuthor13;
    }

    public void setPatentAuthor13(String patentAuthor13) {
        this.patentAuthor13 = patentAuthor13;
    }

    public String getPatentTime14() {
        return patentTime14;
    }

    public void setPatentTime14(String patentTime14) {
        this.patentTime14 = patentTime14;
    }

    public String getPatentName14() {
        return patentName14;
    }

    public void setPatentName14(String patentName14) {
        this.patentName14 = patentName14;
    }

    public String getPatentCountry14() {
        return patentCountry14;
    }

    public void setPatentCountry14(String patentCountry14) {
        this.patentCountry14 = patentCountry14;
    }

    public String getPatentAuthor14() {
        return patentAuthor14;
    }

    public void setPatentAuthor14(String patentAuthor14) {
        this.patentAuthor14 = patentAuthor14;
    }

    public String getPatentTime15() {
        return patentTime15;
    }

    public void setPatentTime15(String patentTime15) {
        this.patentTime15 = patentTime15;
    }

    public String getPatentName15() {
        return patentName15;
    }

    public void setPatentName15(String patentName15) {
        this.patentName15 = patentName15;
    }

    public String getPatentCountry15() {
        return patentCountry15;
    }

    public void setPatentCountry15(String patentCountry15) {
        this.patentCountry15 = patentCountry15;
    }

    public String getPatentAuthor15() {
        return patentAuthor15;
    }

    public void setPatentAuthor15(String patentAuthor15) {
        this.patentAuthor15 = patentAuthor15;
    }

    public String getPatentTime16() {
        return patentTime16;
    }

    public void setPatentTime16(String patentTime16) {
        this.patentTime16 = patentTime16;
    }

    public String getPatentName16() {
        return patentName16;
    }

    public void setPatentName16(String patentName16) {
        this.patentName16 = patentName16;
    }

    public String getPatentCountry16() {
        return patentCountry16;
    }

    public void setPatentCountry16(String patentCountry16) {
        this.patentCountry16 = patentCountry16;
    }

    public String getPatentAuthor16() {
        return patentAuthor16;
    }

    public void setPatentAuthor16(String patentAuthor16) {
        this.patentAuthor16 = patentAuthor16;
    }

    public String getPatentTime17() {
        return patentTime17;
    }

    public void setPatentTime17(String patentTime17) {
        this.patentTime17 = patentTime17;
    }

    public String getPatentName17() {
        return patentName17;
    }

    public void setPatentName17(String patentName17) {
        this.patentName17 = patentName17;
    }

    public String getPatentCountry17() {
        return patentCountry17;
    }

    public void setPatentCountry17(String patentCountry17) {
        this.patentCountry17 = patentCountry17;
    }

    public String getPatentAuthor17() {
        return patentAuthor17;
    }

    public void setPatentAuthor17(String patentAuthor17) {
        this.patentAuthor17 = patentAuthor17;
    }

    public String getPatentTime18() {
        return patentTime18;
    }

    public void setPatentTime18(String patentTime18) {
        this.patentTime18 = patentTime18;
    }

    public String getPatentName18() {
        return patentName18;
    }

    public void setPatentName18(String patentName18) {
        this.patentName18 = patentName18;
    }

    public String getPatentCountry18() {
        return patentCountry18;
    }

    public void setPatentCountry18(String patentCountry18) {
        this.patentCountry18 = patentCountry18;
    }

    public String getPatentAuthor18() {
        return patentAuthor18;
    }

    public void setPatentAuthor18(String patentAuthor18) {
        this.patentAuthor18 = patentAuthor18;
    }

    public String getPatentTime19() {
        return patentTime19;
    }

    public void setPatentTime19(String patentTime19) {
        this.patentTime19 = patentTime19;
    }

    public String getPatentName19() {
        return patentName19;
    }

    public void setPatentName19(String patentName19) {
        this.patentName19 = patentName19;
    }

    public String getPatentCountry19() {
        return patentCountry19;
    }

    public void setPatentCountry19(String patentCountry19) {
        this.patentCountry19 = patentCountry19;
    }

    public String getPatentAuthor19() {
        return patentAuthor19;
    }

    public void setPatentAuthor19(String patentAuthor19) {
        this.patentAuthor19 = patentAuthor19;
    }

    public String getPatentTime20() {
        return patentTime20;
    }

    public void setPatentTime20(String patentTime20) {
        this.patentTime20 = patentTime20;
    }

    public String getPatentName20() {
        return patentName20;
    }

    public void setPatentName20(String patentName20) {
        this.patentName20 = patentName20;
    }

    public String getPatentCountry20() {
        return patentCountry20;
    }

    public void setPatentCountry20(String patentCountry20) {
        this.patentCountry20 = patentCountry20;
    }

    public String getPatentAuthor20() {
        return patentAuthor20;
    }

    public void setPatentAuthor20(String patentAuthor20) {
        this.patentAuthor20 = patentAuthor20;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getOtherProduct() {
        return otherProduct;
    }

    public void setOtherProduct(String otherProduct) {
        this.otherProduct = otherProduct;
    }

    public String getObjectives() {
        return objectives;
    }

    public void setObjectives(String objectives) {
        this.objectives = objectives;
    }

    public String getAgreement() {
        return agreement;
    }

    public void setAgreement(String agreement) {
        this.agreement = agreement;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressOut() {
        return addressOut;
    }

    public void setAddressOut(String addressOut) {
        this.addressOut = addressOut;
    }

    public String getPhoneOut() {
        return phoneOut;
    }

    public void setPhoneOut(String phoneOut) {
        this.phoneOut = phoneOut;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRelationship1() {
        return relationship1;
    }

    public void setRelationship1(String relationship1) {
        this.relationship1 = relationship1;
    }

    public String getRelationshipName1() {
        return relationshipName1;
    }

    public void setRelationshipName1(String relationshipName1) {
        this.relationshipName1 = relationshipName1;
    }

    public String getRelationshipAge1() {
        return relationshipAge1;
    }

    public void setRelationshipAge1(String relationshipAge1) {
        this.relationshipAge1 = relationshipAge1;
    }

    public String getRelationshipCountry1() {
        return relationshipCountry1;
    }

    public void setRelationshipCountry1(String relationshipCountry1) {
        this.relationshipCountry1 = relationshipCountry1;
    }

    public String getRelationshipJob1() {
        return relationshipJob1;
    }

    public void setRelationshipJob1(String relationshipJob1) {
        this.relationshipJob1 = relationshipJob1;
    }

    public String getRelationship2() {
        return relationship2;
    }

    public void setRelationship2(String relationship2) {
        this.relationship2 = relationship2;
    }

    public String getRelationshipName2() {
        return relationshipName2;
    }

    public void setRelationshipName2(String relationshipName2) {
        this.relationshipName2 = relationshipName2;
    }

    public String getRelationshipAge2() {
        return relationshipAge2;
    }

    public void setRelationshipAge2(String relationshipAge2) {
        this.relationshipAge2 = relationshipAge2;
    }

    public String getRelationshipCountry2() {
        return relationshipCountry2;
    }

    public void setRelationshipCountry2(String relationshipCountry2) {
        this.relationshipCountry2 = relationshipCountry2;
    }

    public String getRelationshipJob2() {
        return relationshipJob2;
    }

    public void setRelationshipJob2(String relationshipJob2) {
        this.relationshipJob2 = relationshipJob2;
    }

    public String getRelationship3() {
        return relationship3;
    }

    public void setRelationship3(String relationship3) {
        this.relationship3 = relationship3;
    }

    public String getRelationshipName3() {
        return relationshipName3;
    }

    public void setRelationshipName3(String relationshipName3) {
        this.relationshipName3 = relationshipName3;
    }

    public String getRelationshipAge3() {
        return relationshipAge3;
    }

    public void setRelationshipAge3(String relationshipAge3) {
        this.relationshipAge3 = relationshipAge3;
    }

    public String getRelationshipCountry3() {
        return relationshipCountry3;
    }

    public void setRelationshipCountry3(String relationshipCountry3) {
        this.relationshipCountry3 = relationshipCountry3;
    }

    public String getRelationshipJob3() {
        return relationshipJob3;
    }

    public void setRelationshipJob3(String relationshipJob3) {
        this.relationshipJob3 = relationshipJob3;
    }

    public String getRelationship4() {
        return relationship4;
    }

    public void setRelationship4(String relationship4) {
        this.relationship4 = relationship4;
    }

    public String getRelationshipName4() {
        return relationshipName4;
    }

    public void setRelationshipName4(String relationshipName4) {
        this.relationshipName4 = relationshipName4;
    }

    public String getRelationshipAge4() {
        return relationshipAge4;
    }

    public void setRelationshipAge4(String relationshipAge4) {
        this.relationshipAge4 = relationshipAge4;
    }

    public String getRelationshipCountry4() {
        return relationshipCountry4;
    }

    public void setRelationshipCountry4(String relationshipCountry4) {
        this.relationshipCountry4 = relationshipCountry4;
    }

    public String getRelationshipJob4() {
        return relationshipJob4;
    }

    public void setRelationshipJob4(String relationshipJob4) {
        this.relationshipJob4 = relationshipJob4;
    }

    public String getRelationship5() {
        return relationship5;
    }

    public void setRelationship5(String relationship5) {
        this.relationship5 = relationship5;
    }

    public String getRelationshipName5() {
        return relationshipName5;
    }

    public void setRelationshipName5(String relationshipName5) {
        this.relationshipName5 = relationshipName5;
    }

    public String getRelationshipAge5() {
        return relationshipAge5;
    }

    public void setRelationshipAge5(String relationshipAge5) {
        this.relationshipAge5 = relationshipAge5;
    }

    public String getRelationshipCountry5() {
        return relationshipCountry5;
    }

    public void setRelationshipCountry5(String relationshipCountry5) {
        this.relationshipCountry5 = relationshipCountry5;
    }

    public String getRelationshipJob5() {
        return relationshipJob5;
    }

    public void setRelationshipJob5(String relationshipJob5) {
        this.relationshipJob5 = relationshipJob5;
    }

    public String getRelationship6() {
        return relationship6;
    }

    public void setRelationship6(String relationship6) {
        this.relationship6 = relationship6;
    }

    public String getRelationshipName6() {
        return relationshipName6;
    }

    public void setRelationshipName6(String relationshipName6) {
        this.relationshipName6 = relationshipName6;
    }

    public String getRelationshipAge6() {
        return relationshipAge6;
    }

    public void setRelationshipAge6(String relationshipAge6) {
        this.relationshipAge6 = relationshipAge6;
    }

    public String getRelationshipCountry6() {
        return relationshipCountry6;
    }

    public void setRelationshipCountry6(String relationshipCountry6) {
        this.relationshipCountry6 = relationshipCountry6;
    }

    public String getRelationshipJob6() {
        return relationshipJob6;
    }

    public void setRelationshipJob6(String relationshipJob6) {
        this.relationshipJob6 = relationshipJob6;
    }

    public String getRelationship7() {
        return relationship7;
    }

    public void setRelationship7(String relationship7) {
        this.relationship7 = relationship7;
    }

    public String getRelationshipName7() {
        return relationshipName7;
    }

    public void setRelationshipName7(String relationshipName7) {
        this.relationshipName7 = relationshipName7;
    }

    public String getRelationshipAge7() {
        return relationshipAge7;
    }

    public void setRelationshipAge7(String relationshipAge7) {
        this.relationshipAge7 = relationshipAge7;
    }

    public String getRelationshipCountry7() {
        return relationshipCountry7;
    }

    public void setRelationshipCountry7(String relationshipCountry7) {
        this.relationshipCountry7 = relationshipCountry7;
    }

    public String getRelationshipJob7() {
        return relationshipJob7;
    }

    public void setRelationshipJob7(String relationshipJob7) {
        this.relationshipJob7 = relationshipJob7;
    }

    public String getRelationship8() {
        return relationship8;
    }

    public void setRelationship8(String relationship8) {
        this.relationship8 = relationship8;
    }

    public String getRelationshipName8() {
        return relationshipName8;
    }

    public void setRelationshipName8(String relationshipName8) {
        this.relationshipName8 = relationshipName8;
    }

    public String getRelationshipAge8() {
        return relationshipAge8;
    }

    public void setRelationshipAge8(String relationshipAge8) {
        this.relationshipAge8 = relationshipAge8;
    }

    public String getRelationshipCountry8() {
        return relationshipCountry8;
    }

    public void setRelationshipCountry8(String relationshipCountry8) {
        this.relationshipCountry8 = relationshipCountry8;
    }

    public String getRelationshipJob8() {
        return relationshipJob8;
    }

    public void setRelationshipJob8(String relationshipJob8) {
        this.relationshipJob8 = relationshipJob8;
    }

    public String getRelationship9() {
        return relationship9;
    }

    public void setRelationship9(String relationship9) {
        this.relationship9 = relationship9;
    }

    public String getRelationshipName9() {
        return relationshipName9;
    }

    public void setRelationshipName9(String relationshipName9) {
        this.relationshipName9 = relationshipName9;
    }

    public String getRelationshipAge9() {
        return relationshipAge9;
    }

    public void setRelationshipAge9(String relationshipAge9) {
        this.relationshipAge9 = relationshipAge9;
    }

    public String getRelationshipCountry9() {
        return relationshipCountry9;
    }

    public void setRelationshipCountry9(String relationshipCountry9) {
        this.relationshipCountry9 = relationshipCountry9;
    }

    public String getRelationshipJob9() {
        return relationshipJob9;
    }

    public void setRelationshipJob9(String relationshipJob9) {
        this.relationshipJob9 = relationshipJob9;
    }

    public String getRelationship10() {
        return relationship10;
    }

    public void setRelationship10(String relationship10) {
        this.relationship10 = relationship10;
    }

    public String getRelationshipName10() {
        return relationshipName10;
    }

    public void setRelationshipName10(String relationshipName10) {
        this.relationshipName10 = relationshipName10;
    }

    public String getRelationshipAge10() {
        return relationshipAge10;
    }

    public void setRelationshipAge10(String relationshipAge10) {
        this.relationshipAge10 = relationshipAge10;
    }

    public String getRelationshipCountry10() {
        return relationshipCountry10;
    }

    public void setRelationshipCountry10(String relationshipCountry10) {
        this.relationshipCountry10 = relationshipCountry10;
    }

    public String getRelationshipJob10() {
        return relationshipJob10;
    }

    public void setRelationshipJob10(String relationshipJob10) {
        this.relationshipJob10 = relationshipJob10;
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

    public Organization getOrg() {
        return org;
    }

    public void setOrg(Organization org) {
        this.org = org;
    }
    
}
