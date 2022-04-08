package com.school.pojo;

import com.school.util.UidUtil;

import java.util.Date;

public class SchoolPracticeEvaluate {
    private String id;

    private String userTableId;

    private String teacherId;

    private String teacherAppraise;

    private String teacherResult;

    private String firmResult;

    private String firmId;

    private String attendQualified;

    private String workComplete;

    private String firmComment;

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

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherAppraise() {
        return teacherAppraise;
    }

    public void setTeacherAppraise(String teacherAppraise) {
        this.teacherAppraise = teacherAppraise;
    }

    public String getFirmId() {
        return firmId;
    }

    public void setFirmId(String firmId) {
        this.firmId = firmId;
    }

    public String getAttendQualified() {
        return attendQualified;
    }

    public void setAttendQualified(String attendQualified) {
        this.attendQualified = attendQualified;
    }

    public String getWorkComplete() {
        return workComplete;
    }

    public void setWorkComplete(String workComplete) {
        this.workComplete = workComplete;
    }

    public String getFirmComment() {
        return firmComment;
    }

    public void setFirmComment(String firmComment) {
        this.firmComment = firmComment;
    }

    public String getTeacherResult() {
        return teacherResult;
    }

    public void setTeacherResult(String teacherResult) {
        this.teacherResult = teacherResult;
    }

    public String getFirmResult() {
        return firmResult;
    }

    public void setFirmResult(String firmResult) {
        this.firmResult = firmResult;
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
}