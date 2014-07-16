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
@Table(name = "pApplication2")
@Inheritance(strategy=InheritanceType.JOINED)
public class Application2 extends Application1{
    
    
    
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
    
    
}
