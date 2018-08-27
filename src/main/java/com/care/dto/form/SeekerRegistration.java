package com.care.dto.form;

public class SeekerRegistration extends RegistrationForm {

    private int children;
    private String spouseName;

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

}
