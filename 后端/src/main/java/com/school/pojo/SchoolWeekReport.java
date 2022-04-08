package com.school.pojo;

import com.school.util.UidUtil;

import java.util.Date;

public class SchoolWeekReport {
    private String id;

    private String userTableId;

    private String practiceWeek;

    private String auditTeacherId;

    private String creatBy;

    private Date creatDate;

    private String updateBy;

    private Date updateDate;

    private String practiceWeekReport;

    private String weekReportAudit;

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

    public String getPracticeWeek() {
        return practiceWeek;
    }

    public void setPracticeWeek(String practiceWeek) {
        this.practiceWeek = practiceWeek;
    }

    public String getAuditTeacherId() {
        return auditTeacherId;
    }

    public void setAuditTeacherId(String auditTeacherId) {
        this.auditTeacherId = auditTeacherId;
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
        this.creatDate = creatDate==null?new Date():creatDate;
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

    public String getPracticeWeekReport() {
        return practiceWeekReport;
    }

    public void setPracticeWeekReport(String practiceWeekReport) {
        this.practiceWeekReport = practiceWeekReport;
    }

    public String getWeekReportAudit() {
        return weekReportAudit;
    }

    public void setWeekReportAudit(String weekReportAudit) {
        this.weekReportAudit = weekReportAudit;
    }
}