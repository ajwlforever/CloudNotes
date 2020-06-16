package com.ajwlforever.notes.entity;

import java.util.Date;

public class Notebook {
    private String cnNotebookId;
    private String cnUserId;
    private String cnNotebookTypeId;
    private String cnNotebookName;
    private String cnNotebookDesc;
    private Date   cnNotebookCreateTime;

    @Override
    public String toString() {
        return "Notebook{" +
                "cnNotebookId='" + cnNotebookId + '\'' +
                ", cnUserId='" + cnUserId + '\'' +
                ", cnNotebookTypeId='" + cnNotebookTypeId + '\'' +
                ", cnNotebookName='" + cnNotebookName + '\'' +
                ", cnNotebookDesc='" + cnNotebookDesc + '\'' +
                ", cnNotebookCreateTime=" + cnNotebookCreateTime +
                '}';
    }

    public String getCnNotebookId() {
        return cnNotebookId;
    }

    public void setCnNotebookId(String cnNotebookId) {
        this.cnNotebookId = cnNotebookId;
    }

    public String getCnUserId() {
        return cnUserId;
    }

    public void setCnUserId(String cnUserId) {
        this.cnUserId = cnUserId;
    }

    public String getCnNotebookTypeId() {
        return cnNotebookTypeId;
    }

    public void setCnNotebookTypeId(String cnNotebookTypeId) {
        this.cnNotebookTypeId = cnNotebookTypeId;
    }

    public String getCnNotebookName() {
        return cnNotebookName;
    }

    public void setCnNotebookName(String cnNotebookName) {
        this.cnNotebookName = cnNotebookName;
    }

    public String getCnNotebookDesc() {
        return cnNotebookDesc;
    }

    public void setCnNotebookDesc(String cnNotebookDesc) {
        this.cnNotebookDesc = cnNotebookDesc;
    }

    public Date getCnNotebookCreateTime() {
        return cnNotebookCreateTime;
    }

    public void setCnNotebookCreateTime(Date cnNotebookCreateTime) {
        this.cnNotebookCreateTime = cnNotebookCreateTime;
    }
}
