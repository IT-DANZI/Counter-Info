package com.danzi.pac.domain;

import java.util.Date;

public class GroundInfoMessage {
    private Integer id;

    private Integer mainid;

    private String bornspace;

    private Integer ifstatus;

    private Date createTime;

    private Date updateTime;

    private String updateuser;

    private Integer delstatus;

    private String sysmessagecol1;

    private String sysmessagecol2;

    private String sysmessagecol3;

    private String sysmessagecol4;

    private String sysmessagecol5;

    private String createTimeStr;

    private String updateTimeStr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMainid() {
        return mainid;
    }

    public void setMainid(Integer mainid) {
        this.mainid = mainid;
    }

    public String getBornspace() {
        return bornspace;
    }

    public void setBornspace(String bornspace) {
        this.bornspace = bornspace == null ? null : bornspace.trim();
    }

    public Integer getIfstatus() {
        return ifstatus;
    }

    public void setIfstatus(Integer ifstatus) {
        this.ifstatus = ifstatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser == null ? null : updateuser.trim();
    }

    public Integer getDelstatus() {
        return delstatus;
    }

    public void setDelstatus(Integer delstatus) {
        this.delstatus = delstatus;
    }

    public String getSysmessagecol1() {
        return sysmessagecol1;
    }

    public void setSysmessagecol1(String sysmessagecol1) {
        this.sysmessagecol1 = sysmessagecol1 == null ? null : sysmessagecol1.trim();
    }

    public String getSysmessagecol2() {
        return sysmessagecol2;
    }

    public void setSysmessagecol2(String sysmessagecol2) {
        this.sysmessagecol2 = sysmessagecol2 == null ? null : sysmessagecol2.trim();
    }

    public String getSysmessagecol3() {
        return sysmessagecol3;
    }

    public void setSysmessagecol3(String sysmessagecol3) {
        this.sysmessagecol3 = sysmessagecol3 == null ? null : sysmessagecol3.trim();
    }

    public String getSysmessagecol4() {
        return sysmessagecol4;
    }

    public void setSysmessagecol4(String sysmessagecol4) {
        this.sysmessagecol4 = sysmessagecol4 == null ? null : sysmessagecol4.trim();
    }

    public String getSysmessagecol5() {
        return sysmessagecol5;
    }

    public void setSysmessagecol5(String sysmessagecol5) {
        this.sysmessagecol5 = sysmessagecol5 == null ? null : sysmessagecol5.trim();
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr == null ? null : createTimeStr.trim();
    }

    public String getUpdateTimeStr() {
        return updateTimeStr;
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr == null ? null : updateTimeStr.trim();
    }
}