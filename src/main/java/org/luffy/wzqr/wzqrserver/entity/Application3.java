/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.luffy.wzqr.wzqrserver.entity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 *
 * @author luffy
 */
@Entity
@Table(name = "pApplication3")
@Inheritance(strategy=InheritanceType.JOINED)
public class Application3 extends Application22{
        
    
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
    
}
