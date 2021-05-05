package com.wh.js02.entity;

import java.util.Date;

public class Js03Log {
    private Integer logId;

    private String logNotes;

    private String logAction;

    private Integer logUserId;

    private String logUserIp;

    private String logUserName;

    private String logContextPath;

    private String logUserRoleId;

    private Integer logRunTime;

    private String logBName;

    private String logOsName;

    private String logUserRoleName;

    private Boolean status;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getLogNotes() {
        return logNotes;
    }

    public void setLogNotes(String logNotes) {
        this.logNotes = logNotes;
    }

    public String getLogAction() {
        return logAction;
    }

    public void setLogAction(String logAction) {
        this.logAction = logAction == null ? null : logAction.trim();
    }

    public Integer getLogUserId() {
        return logUserId;
    }

    public void setLogUserId(Integer logUserId) {
        this.logUserId = logUserId;
    }

    public String getLogUserIp() {
        return logUserIp;
    }

    public void setLogUserIp(String logUserIp) {
        this.logUserIp = logUserIp == null ? null : logUserIp.trim();
    }

    public String getLogUserName() {
        return logUserName;
    }

    public void setLogUserName(String logUserName) {
        this.logUserName = logUserName == null ? null : logUserName.trim();
    }

    public String getLogContextPath() {
        return logContextPath;
    }

    public void setLogContextPath(String logContextPath) {
        this.logContextPath = logContextPath == null ? null : logContextPath.trim();
    }

    public String getLogUserRoleId() {
        return logUserRoleId;
    }

    public void setLogUserRoleId(String logUserRoleId) {
        this.logUserRoleId = logUserRoleId == null ? null : logUserRoleId.trim();
    }

    public Integer getLogRunTime() {
        return logRunTime;
    }

    public void setLogRunTime(Integer logRunTime) {
        this.logRunTime = logRunTime;
    }

    public String getLogBName() {
        return logBName;
    }

    public void setLogBName(String logBName) {
        this.logBName = logBName == null ? null : logBName.trim();
    }

    public String getLogOsName() {
        return logOsName;
    }

    public void setLogOsName(String logOsName) {
        this.logOsName = logOsName == null ? null : logOsName.trim();
    }

    public String getLogUserRoleName() {
        return logUserRoleName;
    }

    public void setLogUserRoleName(String logUserRoleName) {
        this.logUserRoleName = logUserRoleName == null ? null : logUserRoleName.trim();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}