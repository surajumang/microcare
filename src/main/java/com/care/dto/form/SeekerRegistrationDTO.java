package com.care.dto.form;

import com.care.annotation.Name;

import java.util.Map;

public class SeekerRegistrationDTO extends RegistrationFormDTO {

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
        return "SeekerRegistrationDTO{" +
                "children='" + children + '\'' +
                ", spouseName='" + spouseName + '\'' +
                '}' + super.toString() ;
    }

    @Override
    public void validateCustom(Map<String, String> errors) {
        super.validateCustom(errors);
    }
}
