/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luffy.wzqr.wzqrserver.entity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 *
 * @author luffy
 */
@Entity
@Table(name = "pApplication5")
@Inheritance(strategy = InheritanceType.JOINED)
public class Application5 extends Application4 {

    private String idType;
    private String idNumber;
    private String personMobile,personPhone;

    private String address;
    private String addressOut, phoneOut;
    private String email;

    //关系
    private String relationship1, relationshipName1, relationshipAge1, relationshipCountry1, relationshipJob1;
    private String relationship2, relationshipName2, relationshipAge2, relationshipCountry2, relationshipJob2;
    private String relationship3, relationshipName3, relationshipAge3, relationshipCountry3, relationshipJob3;
    private String relationship4, relationshipName4, relationshipAge4, relationshipCountry4, relationshipJob4;
    private String relationship5, relationshipName5, relationshipAge5, relationshipCountry5, relationshipJob5;
    private String relationship6, relationshipName6, relationshipAge6, relationshipCountry6, relationshipJob6;
    private String relationship7, relationshipName7, relationshipAge7, relationshipCountry7, relationshipJob7;
    private String relationship8, relationshipName8, relationshipAge8, relationshipCountry8, relationshipJob8;
    private String relationship9, relationshipName9, relationshipAge9, relationshipCountry9, relationshipJob9;
    private String relationship10, relationshipName10, relationshipAge10, relationshipCountry10, relationshipJob10;

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

    public String getPersonMobile() {
        return personMobile;
    }

    public void setPersonMobile(String personMobile) {
        this.personMobile = personMobile;
    }

    public String getPersonPhone() {
        return personPhone;
    }

    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone;
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

}
