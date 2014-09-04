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
import javax.persistence.Table;

/**
 *
 * @author luffy
 */
@Entity
@Table(name = "pApplication22")
@Inheritance(strategy=InheritanceType.JOINED)
public class Application22 extends Application2{ 
    @Column(length=100)
    private String jobPosition10,jobTime10;    
    @Column(length=200)
    private String jobCountry10,jobOrg10;
    @Column(length=100)
    private String jobPosition11,jobTime11;
    @Column(length=200)
    private String jobCountry11,jobOrg11;
    @Column(length=100)
    private String jobPosition12,jobTime12;
    @Column(length=200)
    private String jobCountry12,jobOrg12;
    @Column(length=100)
    private String jobPosition13,jobTime13;
    @Column(length=200)
    private String jobCountry13,jobOrg13;
    @Column(length=100)
    private String jobPosition14,jobTime14;
    @Column(length=200)
    private String jobCountry14,jobOrg14;
    @Column(length=100)
    private String jobPosition15,jobTime15;
    @Column(length=200)
    private String jobCountry15,jobOrg15;
    @Column(length=100)
    private String jobPosition16,jobTime16;
    @Column(length=200)
    private String jobCountry16,jobOrg16;
    @Column(length=100)
    private String jobPosition17,jobTime17;
    @Column(length=200)
    private String jobCountry17,jobOrg17;
    @Column(length=100)
    private String jobPosition18,jobTime18;
    @Column(length=200)
    private String jobCountry18,jobOrg18;
    @Column(length=100)
    private String jobPosition19,jobTime19;
    @Column(length=200)
    private String jobCountry19,jobOrg19;
    @Column(length=100)
    private String jobPosition20,jobTime20;
    @Column(length=200)
    private String jobCountry20,jobOrg20;
    
    private String projectTime10,projectDesc10,projectBudget10,projectPeoples10,projectResponsibility10;
    private String projectTime11,projectDesc11,projectBudget11,projectPeoples11,projectResponsibility11;
    private String projectTime12,projectDesc12,projectBudget12,projectPeoples12,projectResponsibility12;
    private String projectTime13,projectDesc13,projectBudget13,projectPeoples13,projectResponsibility13;
    private String projectTime14,projectDesc14,projectBudget14,projectPeoples14,projectResponsibility14;
    private String projectTime15,projectDesc15,projectBudget15,projectPeoples15,projectResponsibility15;
    private String projectTime16,projectDesc16,projectBudget16,projectPeoples16,projectResponsibility16;
    private String projectTime17,projectDesc17,projectBudget17,projectPeoples17,projectResponsibility17;
    private String projectTime18,projectDesc18,projectBudget18,projectPeoples18,projectResponsibility18;
    private String projectTime19,projectDesc19,projectBudget19,projectPeoples19,projectResponsibility19;
    private String projectTime20,projectDesc20,projectBudget20,projectPeoples20,projectResponsibility20;

    public String getJobPosition10() {
        return jobPosition10;
    }

    public void setJobPosition10(String jobPosition10) {
        this.jobPosition10 = jobPosition10;
    }

    public String getJobTime10() {
        return jobTime10;
    }

    public void setJobTime10(String jobTime10) {
        this.jobTime10 = jobTime10;
    }

    public String getJobCountry10() {
        return jobCountry10;
    }

    public void setJobCountry10(String jobCountry10) {
        this.jobCountry10 = jobCountry10;
    }

    public String getJobOrg10() {
        return jobOrg10;
    }

    public void setJobOrg10(String jobOrg10) {
        this.jobOrg10 = jobOrg10;
    }

    public String getJobPosition11() {
        return jobPosition11;
    }

    public void setJobPosition11(String jobPosition11) {
        this.jobPosition11 = jobPosition11;
    }

    public String getJobTime11() {
        return jobTime11;
    }

    public void setJobTime11(String jobTime11) {
        this.jobTime11 = jobTime11;
    }

    public String getJobCountry11() {
        return jobCountry11;
    }

    public void setJobCountry11(String jobCountry11) {
        this.jobCountry11 = jobCountry11;
    }

    public String getJobOrg11() {
        return jobOrg11;
    }

    public void setJobOrg11(String jobOrg11) {
        this.jobOrg11 = jobOrg11;
    }

    public String getJobPosition12() {
        return jobPosition12;
    }

    public void setJobPosition12(String jobPosition12) {
        this.jobPosition12 = jobPosition12;
    }

    public String getJobTime12() {
        return jobTime12;
    }

    public void setJobTime12(String jobTime12) {
        this.jobTime12 = jobTime12;
    }

    public String getJobCountry12() {
        return jobCountry12;
    }

    public void setJobCountry12(String jobCountry12) {
        this.jobCountry12 = jobCountry12;
    }

    public String getJobOrg12() {
        return jobOrg12;
    }

    public void setJobOrg12(String jobOrg12) {
        this.jobOrg12 = jobOrg12;
    }

    public String getJobPosition13() {
        return jobPosition13;
    }

    public void setJobPosition13(String jobPosition13) {
        this.jobPosition13 = jobPosition13;
    }

    public String getJobTime13() {
        return jobTime13;
    }

    public void setJobTime13(String jobTime13) {
        this.jobTime13 = jobTime13;
    }

    public String getJobCountry13() {
        return jobCountry13;
    }

    public void setJobCountry13(String jobCountry13) {
        this.jobCountry13 = jobCountry13;
    }

    public String getJobOrg13() {
        return jobOrg13;
    }

    public void setJobOrg13(String jobOrg13) {
        this.jobOrg13 = jobOrg13;
    }

    public String getJobPosition14() {
        return jobPosition14;
    }

    public void setJobPosition14(String jobPosition14) {
        this.jobPosition14 = jobPosition14;
    }

    public String getJobTime14() {
        return jobTime14;
    }

    public void setJobTime14(String jobTime14) {
        this.jobTime14 = jobTime14;
    }

    public String getJobCountry14() {
        return jobCountry14;
    }

    public void setJobCountry14(String jobCountry14) {
        this.jobCountry14 = jobCountry14;
    }

    public String getJobOrg14() {
        return jobOrg14;
    }

    public void setJobOrg14(String jobOrg14) {
        this.jobOrg14 = jobOrg14;
    }

    public String getJobPosition15() {
        return jobPosition15;
    }

    public void setJobPosition15(String jobPosition15) {
        this.jobPosition15 = jobPosition15;
    }

    public String getJobTime15() {
        return jobTime15;
    }

    public void setJobTime15(String jobTime15) {
        this.jobTime15 = jobTime15;
    }

    public String getJobCountry15() {
        return jobCountry15;
    }

    public void setJobCountry15(String jobCountry15) {
        this.jobCountry15 = jobCountry15;
    }

    public String getJobOrg15() {
        return jobOrg15;
    }

    public void setJobOrg15(String jobOrg15) {
        this.jobOrg15 = jobOrg15;
    }

    public String getJobPosition16() {
        return jobPosition16;
    }

    public void setJobPosition16(String jobPosition16) {
        this.jobPosition16 = jobPosition16;
    }

    public String getJobTime16() {
        return jobTime16;
    }

    public void setJobTime16(String jobTime16) {
        this.jobTime16 = jobTime16;
    }

    public String getJobCountry16() {
        return jobCountry16;
    }

    public void setJobCountry16(String jobCountry16) {
        this.jobCountry16 = jobCountry16;
    }

    public String getJobOrg16() {
        return jobOrg16;
    }

    public void setJobOrg16(String jobOrg16) {
        this.jobOrg16 = jobOrg16;
    }

    public String getJobPosition17() {
        return jobPosition17;
    }

    public void setJobPosition17(String jobPosition17) {
        this.jobPosition17 = jobPosition17;
    }

    public String getJobTime17() {
        return jobTime17;
    }

    public void setJobTime17(String jobTime17) {
        this.jobTime17 = jobTime17;
    }

    public String getJobCountry17() {
        return jobCountry17;
    }

    public void setJobCountry17(String jobCountry17) {
        this.jobCountry17 = jobCountry17;
    }

    public String getJobOrg17() {
        return jobOrg17;
    }

    public void setJobOrg17(String jobOrg17) {
        this.jobOrg17 = jobOrg17;
    }

    public String getJobPosition18() {
        return jobPosition18;
    }

    public void setJobPosition18(String jobPosition18) {
        this.jobPosition18 = jobPosition18;
    }

    public String getJobTime18() {
        return jobTime18;
    }

    public void setJobTime18(String jobTime18) {
        this.jobTime18 = jobTime18;
    }

    public String getJobCountry18() {
        return jobCountry18;
    }

    public void setJobCountry18(String jobCountry18) {
        this.jobCountry18 = jobCountry18;
    }

    public String getJobOrg18() {
        return jobOrg18;
    }

    public void setJobOrg18(String jobOrg18) {
        this.jobOrg18 = jobOrg18;
    }

    public String getJobPosition19() {
        return jobPosition19;
    }

    public void setJobPosition19(String jobPosition19) {
        this.jobPosition19 = jobPosition19;
    }

    public String getJobTime19() {
        return jobTime19;
    }

    public void setJobTime19(String jobTime19) {
        this.jobTime19 = jobTime19;
    }

    public String getJobCountry19() {
        return jobCountry19;
    }

    public void setJobCountry19(String jobCountry19) {
        this.jobCountry19 = jobCountry19;
    }

    public String getJobOrg19() {
        return jobOrg19;
    }

    public void setJobOrg19(String jobOrg19) {
        this.jobOrg19 = jobOrg19;
    }

    public String getJobPosition20() {
        return jobPosition20;
    }

    public void setJobPosition20(String jobPosition20) {
        this.jobPosition20 = jobPosition20;
    }

    public String getJobTime20() {
        return jobTime20;
    }

    public void setJobTime20(String jobTime20) {
        this.jobTime20 = jobTime20;
    }

    public String getJobCountry20() {
        return jobCountry20;
    }

    public void setJobCountry20(String jobCountry20) {
        this.jobCountry20 = jobCountry20;
    }

    public String getJobOrg20() {
        return jobOrg20;
    }

    public void setJobOrg20(String jobOrg20) {
        this.jobOrg20 = jobOrg20;
    }

    public String getProjectTime10() {
        return projectTime10;
    }

    public void setProjectTime10(String projectTime10) {
        this.projectTime10 = projectTime10;
    }

    public String getProjectDesc10() {
        return projectDesc10;
    }

    public void setProjectDesc10(String projectDesc10) {
        this.projectDesc10 = projectDesc10;
    }

    public String getProjectBudget10() {
        return projectBudget10;
    }

    public void setProjectBudget10(String projectBudget10) {
        this.projectBudget10 = projectBudget10;
    }

    public String getProjectPeoples10() {
        return projectPeoples10;
    }

    public void setProjectPeoples10(String projectPeoples10) {
        this.projectPeoples10 = projectPeoples10;
    }

    public String getProjectResponsibility10() {
        return projectResponsibility10;
    }

    public void setProjectResponsibility10(String projectResponsibility10) {
        this.projectResponsibility10 = projectResponsibility10;
    }

    public String getProjectTime11() {
        return projectTime11;
    }

    public void setProjectTime11(String projectTime11) {
        this.projectTime11 = projectTime11;
    }

    public String getProjectDesc11() {
        return projectDesc11;
    }

    public void setProjectDesc11(String projectDesc11) {
        this.projectDesc11 = projectDesc11;
    }

    public String getProjectBudget11() {
        return projectBudget11;
    }

    public void setProjectBudget11(String projectBudget11) {
        this.projectBudget11 = projectBudget11;
    }

    public String getProjectPeoples11() {
        return projectPeoples11;
    }

    public void setProjectPeoples11(String projectPeoples11) {
        this.projectPeoples11 = projectPeoples11;
    }

    public String getProjectResponsibility11() {
        return projectResponsibility11;
    }

    public void setProjectResponsibility11(String projectResponsibility11) {
        this.projectResponsibility11 = projectResponsibility11;
    }

    public String getProjectTime12() {
        return projectTime12;
    }

    public void setProjectTime12(String projectTime12) {
        this.projectTime12 = projectTime12;
    }

    public String getProjectDesc12() {
        return projectDesc12;
    }

    public void setProjectDesc12(String projectDesc12) {
        this.projectDesc12 = projectDesc12;
    }

    public String getProjectBudget12() {
        return projectBudget12;
    }

    public void setProjectBudget12(String projectBudget12) {
        this.projectBudget12 = projectBudget12;
    }

    public String getProjectPeoples12() {
        return projectPeoples12;
    }

    public void setProjectPeoples12(String projectPeoples12) {
        this.projectPeoples12 = projectPeoples12;
    }

    public String getProjectResponsibility12() {
        return projectResponsibility12;
    }

    public void setProjectResponsibility12(String projectResponsibility12) {
        this.projectResponsibility12 = projectResponsibility12;
    }

    public String getProjectTime13() {
        return projectTime13;
    }

    public void setProjectTime13(String projectTime13) {
        this.projectTime13 = projectTime13;
    }

    public String getProjectDesc13() {
        return projectDesc13;
    }

    public void setProjectDesc13(String projectDesc13) {
        this.projectDesc13 = projectDesc13;
    }

    public String getProjectBudget13() {
        return projectBudget13;
    }

    public void setProjectBudget13(String projectBudget13) {
        this.projectBudget13 = projectBudget13;
    }

    public String getProjectPeoples13() {
        return projectPeoples13;
    }

    public void setProjectPeoples13(String projectPeoples13) {
        this.projectPeoples13 = projectPeoples13;
    }

    public String getProjectResponsibility13() {
        return projectResponsibility13;
    }

    public void setProjectResponsibility13(String projectResponsibility13) {
        this.projectResponsibility13 = projectResponsibility13;
    }

    public String getProjectTime14() {
        return projectTime14;
    }

    public void setProjectTime14(String projectTime14) {
        this.projectTime14 = projectTime14;
    }

    public String getProjectDesc14() {
        return projectDesc14;
    }

    public void setProjectDesc14(String projectDesc14) {
        this.projectDesc14 = projectDesc14;
    }

    public String getProjectBudget14() {
        return projectBudget14;
    }

    public void setProjectBudget14(String projectBudget14) {
        this.projectBudget14 = projectBudget14;
    }

    public String getProjectPeoples14() {
        return projectPeoples14;
    }

    public void setProjectPeoples14(String projectPeoples14) {
        this.projectPeoples14 = projectPeoples14;
    }

    public String getProjectResponsibility14() {
        return projectResponsibility14;
    }

    public void setProjectResponsibility14(String projectResponsibility14) {
        this.projectResponsibility14 = projectResponsibility14;
    }

    public String getProjectTime15() {
        return projectTime15;
    }

    public void setProjectTime15(String projectTime15) {
        this.projectTime15 = projectTime15;
    }

    public String getProjectDesc15() {
        return projectDesc15;
    }

    public void setProjectDesc15(String projectDesc15) {
        this.projectDesc15 = projectDesc15;
    }

    public String getProjectBudget15() {
        return projectBudget15;
    }

    public void setProjectBudget15(String projectBudget15) {
        this.projectBudget15 = projectBudget15;
    }

    public String getProjectPeoples15() {
        return projectPeoples15;
    }

    public void setProjectPeoples15(String projectPeoples15) {
        this.projectPeoples15 = projectPeoples15;
    }

    public String getProjectResponsibility15() {
        return projectResponsibility15;
    }

    public void setProjectResponsibility15(String projectResponsibility15) {
        this.projectResponsibility15 = projectResponsibility15;
    }

    public String getProjectTime16() {
        return projectTime16;
    }

    public void setProjectTime16(String projectTime16) {
        this.projectTime16 = projectTime16;
    }

    public String getProjectDesc16() {
        return projectDesc16;
    }

    public void setProjectDesc16(String projectDesc16) {
        this.projectDesc16 = projectDesc16;
    }

    public String getProjectBudget16() {
        return projectBudget16;
    }

    public void setProjectBudget16(String projectBudget16) {
        this.projectBudget16 = projectBudget16;
    }

    public String getProjectPeoples16() {
        return projectPeoples16;
    }

    public void setProjectPeoples16(String projectPeoples16) {
        this.projectPeoples16 = projectPeoples16;
    }

    public String getProjectResponsibility16() {
        return projectResponsibility16;
    }

    public void setProjectResponsibility16(String projectResponsibility16) {
        this.projectResponsibility16 = projectResponsibility16;
    }

    public String getProjectTime17() {
        return projectTime17;
    }

    public void setProjectTime17(String projectTime17) {
        this.projectTime17 = projectTime17;
    }

    public String getProjectDesc17() {
        return projectDesc17;
    }

    public void setProjectDesc17(String projectDesc17) {
        this.projectDesc17 = projectDesc17;
    }

    public String getProjectBudget17() {
        return projectBudget17;
    }

    public void setProjectBudget17(String projectBudget17) {
        this.projectBudget17 = projectBudget17;
    }

    public String getProjectPeoples17() {
        return projectPeoples17;
    }

    public void setProjectPeoples17(String projectPeoples17) {
        this.projectPeoples17 = projectPeoples17;
    }

    public String getProjectResponsibility17() {
        return projectResponsibility17;
    }

    public void setProjectResponsibility17(String projectResponsibility17) {
        this.projectResponsibility17 = projectResponsibility17;
    }

    public String getProjectTime18() {
        return projectTime18;
    }

    public void setProjectTime18(String projectTime18) {
        this.projectTime18 = projectTime18;
    }

    public String getProjectDesc18() {
        return projectDesc18;
    }

    public void setProjectDesc18(String projectDesc18) {
        this.projectDesc18 = projectDesc18;
    }

    public String getProjectBudget18() {
        return projectBudget18;
    }

    public void setProjectBudget18(String projectBudget18) {
        this.projectBudget18 = projectBudget18;
    }

    public String getProjectPeoples18() {
        return projectPeoples18;
    }

    public void setProjectPeoples18(String projectPeoples18) {
        this.projectPeoples18 = projectPeoples18;
    }

    public String getProjectResponsibility18() {
        return projectResponsibility18;
    }

    public void setProjectResponsibility18(String projectResponsibility18) {
        this.projectResponsibility18 = projectResponsibility18;
    }

    public String getProjectTime19() {
        return projectTime19;
    }

    public void setProjectTime19(String projectTime19) {
        this.projectTime19 = projectTime19;
    }

    public String getProjectDesc19() {
        return projectDesc19;
    }

    public void setProjectDesc19(String projectDesc19) {
        this.projectDesc19 = projectDesc19;
    }

    public String getProjectBudget19() {
        return projectBudget19;
    }

    public void setProjectBudget19(String projectBudget19) {
        this.projectBudget19 = projectBudget19;
    }

    public String getProjectPeoples19() {
        return projectPeoples19;
    }

    public void setProjectPeoples19(String projectPeoples19) {
        this.projectPeoples19 = projectPeoples19;
    }

    public String getProjectResponsibility19() {
        return projectResponsibility19;
    }

    public void setProjectResponsibility19(String projectResponsibility19) {
        this.projectResponsibility19 = projectResponsibility19;
    }

    public String getProjectTime20() {
        return projectTime20;
    }

    public void setProjectTime20(String projectTime20) {
        this.projectTime20 = projectTime20;
    }

    public String getProjectDesc20() {
        return projectDesc20;
    }

    public void setProjectDesc20(String projectDesc20) {
        this.projectDesc20 = projectDesc20;
    }

    public String getProjectBudget20() {
        return projectBudget20;
    }

    public void setProjectBudget20(String projectBudget20) {
        this.projectBudget20 = projectBudget20;
    }

    public String getProjectPeoples20() {
        return projectPeoples20;
    }

    public void setProjectPeoples20(String projectPeoples20) {
        this.projectPeoples20 = projectPeoples20;
    }

    public String getProjectResponsibility20() {
        return projectResponsibility20;
    }

    public void setProjectResponsibility20(String projectResponsibility20) {
        this.projectResponsibility20 = projectResponsibility20;
    }

    
    
}
