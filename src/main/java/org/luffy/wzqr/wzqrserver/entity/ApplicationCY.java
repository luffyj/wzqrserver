/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.luffy.wzqr.wzqrserver.entity;

import javax.persistence.Column;
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
@Table(name = "pApplication6")
@Inheritance(strategy = InheritanceType.JOINED)
public class ApplicationCY  extends Application5 implements java.io.Serializable{
    
    @Lob
    private String entInfo;
    @Column(precision=3)
    private float actualCurrentFunds;
    @Column(precision=3)
    private float actualCurrentFundsPer;
    @Column(precision=3)
    private float myFundsPer;
    @Column(precision=3)
    private float actualFunds;
        
    @Lob
    private String entTeam;
    @Lob
    private String entProject;
    @Lob
    private String entPlan;
    
    @Column(length=20)
    private String partnerName1;
    @Column(length=100)
    private String partnerContent1;
    @Column(length=100)
    private String partnerType1;
    @Column(length=100)
    private String partnerPer1;
    @Column(length=100)
    private String partnerPosition1;
    @Column(length=20)
    private String partnerName2;
    @Column(length=100)
    private String partnerContent2;
    @Column(length=100)
    private String partnerType2;
    @Column(length=100)
    private String partnerPer2;
    @Column(length=100)
    private String partnerPosition2;
    @Column(length=20)
    private String partnerName3;
    @Column(length=100)
    private String partnerContent3;
    @Column(length=100)
    private String partnerType3;
    @Column(length=100)
    private String partnerPer3;
    @Column(length=100)
    private String partnerPosition3;
    @Column(length=20)
    private String partnerName4;
    @Column(length=100)
    private String partnerContent4;
    @Column(length=100)
    private String partnerType4;
    @Column(length=100)
    private String partnerPer4;
    @Column(length=100)
    private String partnerPosition4;
    @Column(length=20)
    private String partnerName5;
    @Column(length=100)
    private String partnerContent5;
    @Column(length=100)
    private String partnerType5;
    @Column(length=100)
    private String partnerPer5;
    @Column(length=100)
    private String partnerPosition5;
    @Column(length=20)
    private String partnerName6;
    @Column(length=100)
    private String partnerContent6;
    @Column(length=100)
    private String partnerType6;
    @Column(length=100)
    private String partnerPer6;
    @Column(length=100)
    private String partnerPosition6;
    @Column(length=20)
    private String partnerName7;
    @Column(length=100)
    private String partnerContent7;
    @Column(length=100)
    private String partnerType7;
    @Column(length=100)
    private String partnerPer7;
    @Column(length=100)
    private String partnerPosition7;
    @Column(length=20)
    private String partnerName8;
    @Column(length=100)
    private String partnerContent8;
    @Column(length=100)
    private String partnerType8;
    @Column(length=100)
    private String partnerPer8;
    @Column(length=100)
    private String partnerPosition8;
    @Column(length=20)
    private String partnerName9;
    @Column(length=100)
    private String partnerContent9;
    @Column(length=100)
    private String partnerType9;
    @Column(length=100)
    private String partnerPer9;
    @Column(length=100)
    private String partnerPosition9;
    @Column(length=20)
    private String partnerName10;
    @Column(length=100)
    private String partnerContent10;
    @Column(length=100)
    private String partnerType10;
    @Column(length=100)
    private String partnerPer10;
    @Column(length=100)
    private String partnerPosition10;
    @Column(length=20)
    private String partnerName11;
    @Column(length=100)
    private String partnerContent11;
    @Column(length=100)
    private String partnerType11;
    @Column(length=100)
    private String partnerPer11;
    @Column(length=100)
    private String partnerPosition11;
    @Column(length=20)
    private String partnerName12;
    @Column(length=100)
    private String partnerContent12;
    @Column(length=100)
    private String partnerType12;
    @Column(length=100)
    private String partnerPer12;
    @Column(length=100)
    private String partnerPosition12;
    @Column(length=20)
    private String partnerName13;
    @Column(length=100)
    private String partnerContent13;
    @Column(length=100)
    private String partnerType13;
    @Column(length=100)
    private String partnerPer13;
    @Column(length=100)
    private String partnerPosition13;
    @Column(length=20)
    private String partnerName14;
    @Column(length=100)
    private String partnerContent14;
    @Column(length=100)
    private String partnerType14;
    @Column(length=100)
    private String partnerPer14;
    @Column(length=100)
    private String partnerPosition14;
    @Column(length=20)
    private String partnerName15;
    @Column(length=100)
    private String partnerContent15;
    @Column(length=100)
    private String partnerType15;
    @Column(length=100)
    private String partnerPer15;
    @Column(length=100)
    private String partnerPosition15;
    @Column(length=20)
    private String partnerName16;
    @Column(length=100)
    private String partnerContent16;
    @Column(length=100)
    private String partnerType16;
    @Column(length=100)
    private String partnerPer16;
    @Column(length=100)
    private String partnerPosition16;
    @Column(length=20)
    private String partnerName17;
    @Column(length=100)
    private String partnerContent17;
    @Column(length=100)
    private String partnerType17;
    @Column(length=100)
    private String partnerPer17;
    @Column(length=100)
    private String partnerPosition17;
    @Column(length=20)
    private String partnerName18;
    @Column(length=100)
    private String partnerContent18;
    @Column(length=100)
    private String partnerType18;
    @Column(length=100)
    private String partnerPer18;
    @Column(length=100)
    private String partnerPosition18;
    @Column(length=20)
    private String partnerName19;
    @Column(length=100)
    private String partnerContent19;
    @Column(length=100)
    private String partnerType19;
    @Column(length=100)
    private String partnerPer19;
    @Column(length=100)
    private String partnerPosition19;
    @Column(length=20)
    private String partnerName20;
    @Column(length=100)
    private String partnerContent20;
    @Column(length=100)
    private String partnerType20;
    @Column(length=100)
    private String partnerPer20;
    @Column(length=100)
    private String partnerPosition20;

    public String getEntInfo() {
        return entInfo;
    }

    public void setEntInfo(String entInfo) {
        this.entInfo = entInfo;
    }

    public float getActualCurrentFunds() {
        return actualCurrentFunds;
    }

    public void setActualCurrentFunds(float actualCurrentFunds) {
        this.actualCurrentFunds = actualCurrentFunds;
    }

    public float getActualCurrentFundsPer() {
        return actualCurrentFundsPer;
    }

    public void setActualCurrentFundsPer(float actualCurrentFundsPer) {
        this.actualCurrentFundsPer = actualCurrentFundsPer;
    }

    public float getMyFundsPer() {
        return myFundsPer;
    }

    public void setMyFundsPer(float myFundsPer) {
        this.myFundsPer = myFundsPer;
    }

    public float getActualFunds() {
        return actualFunds;
    }

    public void setActualFunds(float actualFunds) {
        this.actualFunds = actualFunds;
    }

    public String getEntTeam() {
        return entTeam;
    }

    public void setEntTeam(String entTeam) {
        this.entTeam = entTeam;
    }

    public String getEntProject() {
        return entProject;
    }

    public void setEntProject(String entProject) {
        this.entProject = entProject;
    }

    public String getEntPlan() {
        return entPlan;
    }

    public void setEntPlan(String entPlan) {
        this.entPlan = entPlan;
    }

    public String getPartnerName1() {
        return partnerName1;
    }

    public void setPartnerName1(String partnerName1) {
        this.partnerName1 = partnerName1;
    }

    public String getPartnerContent1() {
        return partnerContent1;
    }

    public void setPartnerContent1(String partnerContent1) {
        this.partnerContent1 = partnerContent1;
    }

    public String getPartnerType1() {
        return partnerType1;
    }

    public void setPartnerType1(String partnerType1) {
        this.partnerType1 = partnerType1;
    }

    public String getPartnerPer1() {
        return partnerPer1;
    }

    public void setPartnerPer1(String partnerPer1) {
        this.partnerPer1 = partnerPer1;
    }

    public String getPartnerPosition1() {
        return partnerPosition1;
    }

    public void setPartnerPosition1(String partnerPosition1) {
        this.partnerPosition1 = partnerPosition1;
    }

    public String getPartnerName2() {
        return partnerName2;
    }

    public void setPartnerName2(String partnerName2) {
        this.partnerName2 = partnerName2;
    }

    public String getPartnerContent2() {
        return partnerContent2;
    }

    public void setPartnerContent2(String partnerContent2) {
        this.partnerContent2 = partnerContent2;
    }

    public String getPartnerType2() {
        return partnerType2;
    }

    public void setPartnerType2(String partnerType2) {
        this.partnerType2 = partnerType2;
    }

    public String getPartnerPer2() {
        return partnerPer2;
    }

    public void setPartnerPer2(String partnerPer2) {
        this.partnerPer2 = partnerPer2;
    }

    public String getPartnerPosition2() {
        return partnerPosition2;
    }

    public void setPartnerPosition2(String partnerPosition2) {
        this.partnerPosition2 = partnerPosition2;
    }

    public String getPartnerName3() {
        return partnerName3;
    }

    public void setPartnerName3(String partnerName3) {
        this.partnerName3 = partnerName3;
    }

    public String getPartnerContent3() {
        return partnerContent3;
    }

    public void setPartnerContent3(String partnerContent3) {
        this.partnerContent3 = partnerContent3;
    }

    public String getPartnerType3() {
        return partnerType3;
    }

    public void setPartnerType3(String partnerType3) {
        this.partnerType3 = partnerType3;
    }

    public String getPartnerPer3() {
        return partnerPer3;
    }

    public void setPartnerPer3(String partnerPer3) {
        this.partnerPer3 = partnerPer3;
    }

    public String getPartnerPosition3() {
        return partnerPosition3;
    }

    public void setPartnerPosition3(String partnerPosition3) {
        this.partnerPosition3 = partnerPosition3;
    }

    public String getPartnerName4() {
        return partnerName4;
    }

    public void setPartnerName4(String partnerName4) {
        this.partnerName4 = partnerName4;
    }

    public String getPartnerContent4() {
        return partnerContent4;
    }

    public void setPartnerContent4(String partnerContent4) {
        this.partnerContent4 = partnerContent4;
    }

    public String getPartnerType4() {
        return partnerType4;
    }

    public void setPartnerType4(String partnerType4) {
        this.partnerType4 = partnerType4;
    }

    public String getPartnerPer4() {
        return partnerPer4;
    }

    public void setPartnerPer4(String partnerPer4) {
        this.partnerPer4 = partnerPer4;
    }

    public String getPartnerPosition4() {
        return partnerPosition4;
    }

    public void setPartnerPosition4(String partnerPosition4) {
        this.partnerPosition4 = partnerPosition4;
    }

    public String getPartnerName5() {
        return partnerName5;
    }

    public void setPartnerName5(String partnerName5) {
        this.partnerName5 = partnerName5;
    }

    public String getPartnerContent5() {
        return partnerContent5;
    }

    public void setPartnerContent5(String partnerContent5) {
        this.partnerContent5 = partnerContent5;
    }

    public String getPartnerType5() {
        return partnerType5;
    }

    public void setPartnerType5(String partnerType5) {
        this.partnerType5 = partnerType5;
    }

    public String getPartnerPer5() {
        return partnerPer5;
    }

    public void setPartnerPer5(String partnerPer5) {
        this.partnerPer5 = partnerPer5;
    }

    public String getPartnerPosition5() {
        return partnerPosition5;
    }

    public void setPartnerPosition5(String partnerPosition5) {
        this.partnerPosition5 = partnerPosition5;
    }

    public String getPartnerName6() {
        return partnerName6;
    }

    public void setPartnerName6(String partnerName6) {
        this.partnerName6 = partnerName6;
    }

    public String getPartnerContent6() {
        return partnerContent6;
    }

    public void setPartnerContent6(String partnerContent6) {
        this.partnerContent6 = partnerContent6;
    }

    public String getPartnerType6() {
        return partnerType6;
    }

    public void setPartnerType6(String partnerType6) {
        this.partnerType6 = partnerType6;
    }

    public String getPartnerPer6() {
        return partnerPer6;
    }

    public void setPartnerPer6(String partnerPer6) {
        this.partnerPer6 = partnerPer6;
    }

    public String getPartnerPosition6() {
        return partnerPosition6;
    }

    public void setPartnerPosition6(String partnerPosition6) {
        this.partnerPosition6 = partnerPosition6;
    }

    public String getPartnerName7() {
        return partnerName7;
    }

    public void setPartnerName7(String partnerName7) {
        this.partnerName7 = partnerName7;
    }

    public String getPartnerContent7() {
        return partnerContent7;
    }

    public void setPartnerContent7(String partnerContent7) {
        this.partnerContent7 = partnerContent7;
    }

    public String getPartnerType7() {
        return partnerType7;
    }

    public void setPartnerType7(String partnerType7) {
        this.partnerType7 = partnerType7;
    }

    public String getPartnerPer7() {
        return partnerPer7;
    }

    public void setPartnerPer7(String partnerPer7) {
        this.partnerPer7 = partnerPer7;
    }

    public String getPartnerPosition7() {
        return partnerPosition7;
    }

    public void setPartnerPosition7(String partnerPosition7) {
        this.partnerPosition7 = partnerPosition7;
    }

    public String getPartnerName8() {
        return partnerName8;
    }

    public void setPartnerName8(String partnerName8) {
        this.partnerName8 = partnerName8;
    }

    public String getPartnerContent8() {
        return partnerContent8;
    }

    public void setPartnerContent8(String partnerContent8) {
        this.partnerContent8 = partnerContent8;
    }

    public String getPartnerType8() {
        return partnerType8;
    }

    public void setPartnerType8(String partnerType8) {
        this.partnerType8 = partnerType8;
    }

    public String getPartnerPer8() {
        return partnerPer8;
    }

    public void setPartnerPer8(String partnerPer8) {
        this.partnerPer8 = partnerPer8;
    }

    public String getPartnerPosition8() {
        return partnerPosition8;
    }

    public void setPartnerPosition8(String partnerPosition8) {
        this.partnerPosition8 = partnerPosition8;
    }

    public String getPartnerName9() {
        return partnerName9;
    }

    public void setPartnerName9(String partnerName9) {
        this.partnerName9 = partnerName9;
    }

    public String getPartnerContent9() {
        return partnerContent9;
    }

    public void setPartnerContent9(String partnerContent9) {
        this.partnerContent9 = partnerContent9;
    }

    public String getPartnerType9() {
        return partnerType9;
    }

    public void setPartnerType9(String partnerType9) {
        this.partnerType9 = partnerType9;
    }

    public String getPartnerPer9() {
        return partnerPer9;
    }

    public void setPartnerPer9(String partnerPer9) {
        this.partnerPer9 = partnerPer9;
    }

    public String getPartnerPosition9() {
        return partnerPosition9;
    }

    public void setPartnerPosition9(String partnerPosition9) {
        this.partnerPosition9 = partnerPosition9;
    }

    public String getPartnerName10() {
        return partnerName10;
    }

    public void setPartnerName10(String partnerName10) {
        this.partnerName10 = partnerName10;
    }

    public String getPartnerContent10() {
        return partnerContent10;
    }

    public void setPartnerContent10(String partnerContent10) {
        this.partnerContent10 = partnerContent10;
    }

    public String getPartnerType10() {
        return partnerType10;
    }

    public void setPartnerType10(String partnerType10) {
        this.partnerType10 = partnerType10;
    }

    public String getPartnerPer10() {
        return partnerPer10;
    }

    public void setPartnerPer10(String partnerPer10) {
        this.partnerPer10 = partnerPer10;
    }

    public String getPartnerPosition10() {
        return partnerPosition10;
    }

    public void setPartnerPosition10(String partnerPosition10) {
        this.partnerPosition10 = partnerPosition10;
    }

    public String getPartnerName11() {
        return partnerName11;
    }

    public void setPartnerName11(String partnerName11) {
        this.partnerName11 = partnerName11;
    }

    public String getPartnerContent11() {
        return partnerContent11;
    }

    public void setPartnerContent11(String partnerContent11) {
        this.partnerContent11 = partnerContent11;
    }

    public String getPartnerType11() {
        return partnerType11;
    }

    public void setPartnerType11(String partnerType11) {
        this.partnerType11 = partnerType11;
    }

    public String getPartnerPer11() {
        return partnerPer11;
    }

    public void setPartnerPer11(String partnerPer11) {
        this.partnerPer11 = partnerPer11;
    }

    public String getPartnerPosition11() {
        return partnerPosition11;
    }

    public void setPartnerPosition11(String partnerPosition11) {
        this.partnerPosition11 = partnerPosition11;
    }

    public String getPartnerName12() {
        return partnerName12;
    }

    public void setPartnerName12(String partnerName12) {
        this.partnerName12 = partnerName12;
    }

    public String getPartnerContent12() {
        return partnerContent12;
    }

    public void setPartnerContent12(String partnerContent12) {
        this.partnerContent12 = partnerContent12;
    }

    public String getPartnerType12() {
        return partnerType12;
    }

    public void setPartnerType12(String partnerType12) {
        this.partnerType12 = partnerType12;
    }

    public String getPartnerPer12() {
        return partnerPer12;
    }

    public void setPartnerPer12(String partnerPer12) {
        this.partnerPer12 = partnerPer12;
    }

    public String getPartnerPosition12() {
        return partnerPosition12;
    }

    public void setPartnerPosition12(String partnerPosition12) {
        this.partnerPosition12 = partnerPosition12;
    }

    public String getPartnerName13() {
        return partnerName13;
    }

    public void setPartnerName13(String partnerName13) {
        this.partnerName13 = partnerName13;
    }

    public String getPartnerContent13() {
        return partnerContent13;
    }

    public void setPartnerContent13(String partnerContent13) {
        this.partnerContent13 = partnerContent13;
    }

    public String getPartnerType13() {
        return partnerType13;
    }

    public void setPartnerType13(String partnerType13) {
        this.partnerType13 = partnerType13;
    }

    public String getPartnerPer13() {
        return partnerPer13;
    }

    public void setPartnerPer13(String partnerPer13) {
        this.partnerPer13 = partnerPer13;
    }

    public String getPartnerPosition13() {
        return partnerPosition13;
    }

    public void setPartnerPosition13(String partnerPosition13) {
        this.partnerPosition13 = partnerPosition13;
    }

    public String getPartnerName14() {
        return partnerName14;
    }

    public void setPartnerName14(String partnerName14) {
        this.partnerName14 = partnerName14;
    }

    public String getPartnerContent14() {
        return partnerContent14;
    }

    public void setPartnerContent14(String partnerContent14) {
        this.partnerContent14 = partnerContent14;
    }

    public String getPartnerType14() {
        return partnerType14;
    }

    public void setPartnerType14(String partnerType14) {
        this.partnerType14 = partnerType14;
    }

    public String getPartnerPer14() {
        return partnerPer14;
    }

    public void setPartnerPer14(String partnerPer14) {
        this.partnerPer14 = partnerPer14;
    }

    public String getPartnerPosition14() {
        return partnerPosition14;
    }

    public void setPartnerPosition14(String partnerPosition14) {
        this.partnerPosition14 = partnerPosition14;
    }

    public String getPartnerName15() {
        return partnerName15;
    }

    public void setPartnerName15(String partnerName15) {
        this.partnerName15 = partnerName15;
    }

    public String getPartnerContent15() {
        return partnerContent15;
    }

    public void setPartnerContent15(String partnerContent15) {
        this.partnerContent15 = partnerContent15;
    }

    public String getPartnerType15() {
        return partnerType15;
    }

    public void setPartnerType15(String partnerType15) {
        this.partnerType15 = partnerType15;
    }

    public String getPartnerPer15() {
        return partnerPer15;
    }

    public void setPartnerPer15(String partnerPer15) {
        this.partnerPer15 = partnerPer15;
    }

    public String getPartnerPosition15() {
        return partnerPosition15;
    }

    public void setPartnerPosition15(String partnerPosition15) {
        this.partnerPosition15 = partnerPosition15;
    }

    public String getPartnerName16() {
        return partnerName16;
    }

    public void setPartnerName16(String partnerName16) {
        this.partnerName16 = partnerName16;
    }

    public String getPartnerContent16() {
        return partnerContent16;
    }

    public void setPartnerContent16(String partnerContent16) {
        this.partnerContent16 = partnerContent16;
    }

    public String getPartnerType16() {
        return partnerType16;
    }

    public void setPartnerType16(String partnerType16) {
        this.partnerType16 = partnerType16;
    }

    public String getPartnerPer16() {
        return partnerPer16;
    }

    public void setPartnerPer16(String partnerPer16) {
        this.partnerPer16 = partnerPer16;
    }

    public String getPartnerPosition16() {
        return partnerPosition16;
    }

    public void setPartnerPosition16(String partnerPosition16) {
        this.partnerPosition16 = partnerPosition16;
    }

    public String getPartnerName17() {
        return partnerName17;
    }

    public void setPartnerName17(String partnerName17) {
        this.partnerName17 = partnerName17;
    }

    public String getPartnerContent17() {
        return partnerContent17;
    }

    public void setPartnerContent17(String partnerContent17) {
        this.partnerContent17 = partnerContent17;
    }

    public String getPartnerType17() {
        return partnerType17;
    }

    public void setPartnerType17(String partnerType17) {
        this.partnerType17 = partnerType17;
    }

    public String getPartnerPer17() {
        return partnerPer17;
    }

    public void setPartnerPer17(String partnerPer17) {
        this.partnerPer17 = partnerPer17;
    }

    public String getPartnerPosition17() {
        return partnerPosition17;
    }

    public void setPartnerPosition17(String partnerPosition17) {
        this.partnerPosition17 = partnerPosition17;
    }

    public String getPartnerName18() {
        return partnerName18;
    }

    public void setPartnerName18(String partnerName18) {
        this.partnerName18 = partnerName18;
    }

    public String getPartnerContent18() {
        return partnerContent18;
    }

    public void setPartnerContent18(String partnerContent18) {
        this.partnerContent18 = partnerContent18;
    }

    public String getPartnerType18() {
        return partnerType18;
    }

    public void setPartnerType18(String partnerType18) {
        this.partnerType18 = partnerType18;
    }

    public String getPartnerPer18() {
        return partnerPer18;
    }

    public void setPartnerPer18(String partnerPer18) {
        this.partnerPer18 = partnerPer18;
    }

    public String getPartnerPosition18() {
        return partnerPosition18;
    }

    public void setPartnerPosition18(String partnerPosition18) {
        this.partnerPosition18 = partnerPosition18;
    }

    public String getPartnerName19() {
        return partnerName19;
    }

    public void setPartnerName19(String partnerName19) {
        this.partnerName19 = partnerName19;
    }

    public String getPartnerContent19() {
        return partnerContent19;
    }

    public void setPartnerContent19(String partnerContent19) {
        this.partnerContent19 = partnerContent19;
    }

    public String getPartnerType19() {
        return partnerType19;
    }

    public void setPartnerType19(String partnerType19) {
        this.partnerType19 = partnerType19;
    }

    public String getPartnerPer19() {
        return partnerPer19;
    }

    public void setPartnerPer19(String partnerPer19) {
        this.partnerPer19 = partnerPer19;
    }

    public String getPartnerPosition19() {
        return partnerPosition19;
    }

    public void setPartnerPosition19(String partnerPosition19) {
        this.partnerPosition19 = partnerPosition19;
    }

    public String getPartnerName20() {
        return partnerName20;
    }

    public void setPartnerName20(String partnerName20) {
        this.partnerName20 = partnerName20;
    }

    public String getPartnerContent20() {
        return partnerContent20;
    }

    public void setPartnerContent20(String partnerContent20) {
        this.partnerContent20 = partnerContent20;
    }

    public String getPartnerType20() {
        return partnerType20;
    }

    public void setPartnerType20(String partnerType20) {
        this.partnerType20 = partnerType20;
    }

    public String getPartnerPer20() {
        return partnerPer20;
    }

    public void setPartnerPer20(String partnerPer20) {
        this.partnerPer20 = partnerPer20;
    }

    public String getPartnerPosition20() {
        return partnerPosition20;
    }

    public void setPartnerPosition20(String partnerPosition20) {
        this.partnerPosition20 = partnerPosition20;
    }
    
    
    
}
