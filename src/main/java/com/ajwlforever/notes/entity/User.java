package com.ajwlforever.notes.entity;

public class User {
    private String cnUserId;
    private String cnUserName;
    private String cnUserPassword;
    private String cnUserToken;
    private String cnUserNick;
    private String cnIP;

    public String getCnUserId() {
        return cnUserId;
    }

    public void setCnUserId(String cnUserId) {
        this.cnUserId = cnUserId;
    }

    public String getCnUserName() {
        return cnUserName;
    }

    public void setCnUserName(String cnUserName) {
        this.cnUserName = cnUserName;
    }

    public String getCnUserPassword() {
        return cnUserPassword;
    }

    public void setCnUserPassword(String cnUserPassword) {
        this.cnUserPassword = cnUserPassword;
    }

    public String getCnUserToken() {
        return cnUserToken;
    }

    public void setCnUserToken(String cnUserToken) {
        this.cnUserToken = cnUserToken;
    }

    public String getCnUserNick() {
        return cnUserNick;
    }

    public void setCnUserNick(String cnUserNick) {
        this.cnUserNick = cnUserNick;
    }

    public String getCnIP() {
        return cnIP;
    }

    public void setCnIP(String cnIP) {
        this.cnIP = cnIP;
    }

    @Override
    public String toString() {
        return "User{" +
                "cnUserId='" + cnUserId + '\'' +
                ", cnUserName='" + cnUserName + '\'' +
                ", cnUserPassword='" + cnUserPassword + '\'' +
                ", cnUserToken='" + cnUserToken + '\'' +
                ", cnUserNick='" + cnUserNick + '\'' +
                ", cnUserIP='" + cnIP + '\'' +
                '}';
    }
}
