package com.care.model;

import java.sql.Timestamp;


public class Token extends BaseModel{
    private long id;
    private Member member;
    private String token;
    private Timestamp expirationDate;
    private Status status = Status.ACTIVE;  //tells whether token has been used or not.

    private static final Token EMPTY_TOKEN = new Token();

    public Token() {
    }

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

    public Timestamp getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Timestamp expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public static Token emptyToken(){
        return EMPTY_TOKEN;
    }

    public boolean isExpired(){
        return expirationDate.before(new Timestamp(System.currentTimeMillis()));
    }

    public boolean isActive(){
        return status == Status.ACTIVE;
    }

    public boolean isEmpty(){
        return this == EMPTY_TOKEN;
    }

}
