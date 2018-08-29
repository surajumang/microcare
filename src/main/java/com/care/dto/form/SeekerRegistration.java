package com.care.dto.form;

import com.care.annotations.Name;

public class SeekerRegistration extends RegistrationForm {

    private String children;
    private String spouseName;

    @Name
    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    @Name
    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "SeekerRegistration{" +
                "children='" + children + '\'' +
                ", spouseName='" + spouseName + '\'' +
                '}';
    }
}
