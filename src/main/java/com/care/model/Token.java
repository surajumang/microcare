package com.care.model;

import java.sql.Date;

public class Token {
    private long id;
    private long memberId;
    private String token;
    private Date expirationDate;
    private Status status;  //tells whether token has been used or not.

    public static final Token EMPTY_TOKEN = new Token();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", token='" + token + '\'' +
                ", expirationDate=" + expirationDate +
                ", status=" + status +
                '}';
    }
}
