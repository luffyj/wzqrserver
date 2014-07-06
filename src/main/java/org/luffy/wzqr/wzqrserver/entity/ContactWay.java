/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.luffy.wzqr.wzqrserver.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 * 联系方式
 * @author luffy
 */
@Embeddable
public class ContactWay implements Serializable{
    
    public ContactWay(){
        super();
    }
    public ContactWay(String phone){
        this();
        this.phone = phone;
    }

    public ContactWay(String email, String address, String phone) {
        this.email = email;
        this.address = address;
        this.phone = phone;
    }
    
    private String email;
    private String address;
    private String phone;    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
