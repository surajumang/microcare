
package com.care.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class Member extends BaseModel {
    private long id;
    private String firstName;
    private String lastName;
    private String phone;
    private MemberType memberType;
    private String email;
    private String address;
    private String zipCode;
    private Status status = Status.ACTIVE;
    private Timestamp lastModified;
    private String password;
    private Set<Token> tokens = new HashSet<>();

    private static final Member EMPTY_MEMBER = new Member();

    public Member() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Token> getTokens() {
        return tokens;
    }

    public void setTokens(Set<Token> tokens) {
        this.tokens = tokens;
    }

    public static Member emptyMember() {
        return EMPTY_MEMBER;
    }

    public boolean isSeeker(){
        return memberType == MemberType.SEEKER;
    }

    public boolean isSitter(){
        return memberType == MemberType.SITTER;
    }

    public boolean isEmpty(){
        return this == EMPTY_MEMBER;
    }

    public boolean isActive(){
        return getStatus() == Status.ACTIVE;
    }


}
