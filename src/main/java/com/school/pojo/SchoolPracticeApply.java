package com.school.pojo;

import com.school.util.UidUtil;

import java.util.Date;

public class SchoolPracticeApply {
    private String id;

    private String userTableId;

    private String unitName;

    private String practiceJob;

    private String firmContact;

    private String firmTel;

    private String firmTutor;

    private String practiceTreatment;

    private Date practiceTime;

    private String practicePlace;

    private String uploaadProve;

    private String agreeApply;

    private String creatBy;

    private Date creatDate;

    private String updateBy;

    private Date updateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? UidUtil.genCodes("",15) : id.trim();
    }

    public String getUserTableId() {
        return userTableId;
    }

    public void setUserTableId(String userTableId) {
        this.userTableId = userTableId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getPracticeJob() {
        return practiceJob;
    }

    public void setPracticeJob(String practiceJob) {
        this.practiceJob = practiceJob;
    }

    public String getFirmContact() {
        return firmContact;
    }

    public void setFirmContact(String firmContact) {
        this.firmContact = firmContact;
    }

    public String getFirmTel() {
        return firmTel;
    }

    public void setFirmTel(String firmTel) {
        this.firmTel = firmTel;
    }

    public String getFirmTutor() {
        return firmTutor;
    }

    public void setFirmTutor(String firmTutor) {
        this.firmTutor = firmTutor;
    }

    public String getPracticeTreatment() {
        return practiceTreatment;
    }

    public void setPracticeTreatment(String practiceTreatment) {
        this.practiceTreatment = practiceTreatment;
    }

    public Date getPracticeTime() {
        return practiceTime;
    }

    public void setPracticeTime(Date practiceTime) {
        this.practiceTime = practiceTime;
    }

    public String getPracticePlace() {
        return practicePlace;
    }

    public void setPracticePlace(String practicePlace) {
        this.practicePlace = practicePlace;
    }

    public String getUploaadProve() {
        return uploaadProve;
    }

    public void setUploaadProve(String uploaadProve) {
        this.uploaadProve = uploaadProve;
    }

    public String getAgreeApply() {
        return agreeApply;
    }

    public void setAgreeApply(String agreeApply) {
        this.agreeApply = agreeApply;
    }

    public String getCreatBy() {
        return creatBy;
    }

    public void setCreatBy(String creatBy) {
        this.creatBy = creatBy == null ? "admin" : creatBy.trim();
    }

    public Date getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Date creatDate) {
        this.creatDate = creatDate==null? new Date():creatDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? "admin" : updateBy.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate==null?new Date():updateDate;
    }
}