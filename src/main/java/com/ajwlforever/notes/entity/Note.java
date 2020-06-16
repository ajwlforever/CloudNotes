package com.ajwlforever.notes.entity;

import java.math.BigInteger;
import java.util.Date;

public class Note {
    private String cnNoteId;
    private String cnNoteBookId;
    private String cnUserId;
    private String cnNoteStatusId;
    private String cnNoteTypeId;
    private String cnNoteTitle;
    private String cnNoteBody;
    private long cnNoteCreateTime;
    private long cnNoteLastModifyTime;

    public String getCnNoteId() {
        return cnNoteId;
    }

    public void setCnNoteId(String cnNoteId) {
        this.cnNoteId = cnNoteId;
    }

    public String getCnNoteBookId() {
        return cnNoteBookId;
    }

    public void setCnNoteBookId(String cnNoteBookId) {
        this.cnNoteBookId = cnNoteBookId;
    }

    public String getCnUserId() {
        return cnUserId;
    }

    public void setCnUserId(String cnUserId) {
        this.cnUserId = cnUserId;
    }

    public String getCnNoteStatusId() {
        return cnNoteStatusId;
    }

    public void setCnNoteStatusId(String cnNoteStatusId) {
        this.cnNoteStatusId = cnNoteStatusId;
    }

    public String getCnNoteTypeId() {
        return cnNoteTypeId;
    }

    public void setCnNoteTypeId(String cnNoteTypeId) {
        this.cnNoteTypeId = cnNoteTypeId;
    }

    public String getCnNoteTitle() {
        return cnNoteTitle;
    }

    public void setCnNoteTitle(String cnNoteTitle) {
        this.cnNoteTitle = cnNoteTitle;
    }

    public String getCnNoteBody() {
        return cnNoteBody;
    }

    public void setCnNoteBody(String cnNoteBody) {
        this.cnNoteBody = cnNoteBody;
    }

    public long getCnNoteCreateTime() {
        return cnNoteCreateTime;
    }

    public void setCnNoteCreateTime(long cnNoteCreateTime) {
        this.cnNoteCreateTime = cnNoteCreateTime;
    }

    public long getCnNoteLastModifyTime() {
        return cnNoteLastModifyTime;
    }

    public void setCnNoteLastModifyTime(long cnNoteLastModifyTime) {
        this.cnNoteLastModifyTime = cnNoteLastModifyTime;
    }

    @Override
    public String toString() {
        return "Note{" +
                "cnNoteId='" + cnNoteId + '\'' +
                ", cnNoteBookId='" + cnNoteBookId + '\'' +
                ", cnUserId='" + cnUserId + '\'' +
                ", cnNoteStatusId='" + cnNoteStatusId + '\'' +
                ", cnNoteTypeId='" + cnNoteTypeId + '\'' +
                ", cnNoteTitle='" + cnNoteTitle + '\'' +
                ", cnNoteBody='" + cnNoteBody + '\'' +
                ", cnNoteCreateTime=" + cnNoteCreateTime +
                ", cnNoteLastModifyTime=" + cnNoteLastModifyTime +
                '}';
    }
}
