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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author luffy
 */
@Entity
@Table(name = "pApplication1")
@Inheritance(strategy = InheritanceType.JOINED)
public class Application1 implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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
    private String realName, realEnglishName;
    //申报单位
    private String appOrgName;

    private String people;
    private String mobile, phone;
    @Temporal(TemporalType.DATE)
    private Date comletionDate;//填表日期

    private int sex;
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    private String birthPlace;
    private String nationality;

    private String mgChineseCountry, mgChineseSchool, mgChineseMajor, mgChineseDegree;
    private String mgEnglishCountry, mgEnglishSchool, mgEnglishMajor, mgEnglishDegree;

    //回国前
    private String foreignJobChinese, foreignJobEnglish;
    //现任
    private String employer, position, employerAddress, zip;

    public String toEduString() {
        StringBuilder sb = new StringBuilder();

        if (degree1 != null && degree1.length() > 0) {
            sb.append(degree1)
                    .append(" ")
                    .append(time1)
                    .append(" ")
                    .append(country1)
                    .append(" ")
                    .append(university1)
                    .append(" ")
                    .append(major1)
                    .append("\n");
        }
        if (degree2 != null && degree2.length() > 0) {
            sb.append(degree2)
                    .append(" ")
                    .append(time2)
                    .append(" ")
                    .append(country2)
                    .append(" ")
                    .append(university2)
                    .append(" ")
                    .append(major2)
                    .append("\n");
        }
        if (degree3 != null && degree3.length() > 0) {
            sb.append(degree3)
                    .append(" ")
                    .append(time3)
                    .append(" ")
                    .append(country3)
                    .append(" ")
                    .append(university3)
                    .append(" ")
                    .append(major3)
                    .append("\n");
        }
        if (degree4 != null && degree4.length() > 0) {
            sb.append(degree4)
                    .append(" ")
                    .append(time4)
                    .append(" ")
                    .append(country4)
                    .append(" ")
                    .append(university4)
                    .append(" ")
                    .append(major4)
                    .append("\n");
        }
        if (degree5 != null && degree5.length() > 0) {
            sb.append(degree5)
                    .append(" ")
                    .append(time5)
                    .append(" ")
                    .append(country5)
                    .append(" ")
                    .append(university5)
                    .append(" ")
                    .append(major5)
                    .append("\n");
        }
        if (degree6 != null && degree6.length() > 0) {
            sb.append(degree6)
                    .append(" ")
                    .append(time6)
                    .append(" ")
                    .append(country6)
                    .append(" ")
                    .append(university6)
                    .append(" ")
                    .append(major6)
                    .append("\n");
        }
        if (degree7 != null && degree7.length() > 0) {
            sb.append(degree7)
                    .append(" ")
                    .append(time7)
                    .append(" ")
                    .append(country7)
                    .append(" ")
                    .append(university7)
                    .append(" ")
                    .append(major7)
                    .append("\n");
        }

        return sb.toString();
    }

    private String degree1, time1, country1, university1, major1;
    private String degree2, time2, country2, university2, major2;
    private String degree3, time3, country3, university3, major3;
    private String degree4, time4, country4, university4, major4;
    private String degree5, time5, country5, university5, major5;
    private String degree6, time6, country6, university6, major6;
    private String degree7, time7, country7, university7, major7;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 未上报、等待形审、形审通过、形审未过、形审退回、复审通过、复审未过、复审退回、评审通过、评审未过几个状态
     */
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
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

}
