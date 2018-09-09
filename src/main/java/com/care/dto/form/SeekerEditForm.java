package com.care.dto.form;

import com.care.annotation.Name;
import com.care.annotation.Number;

import java.util.Map;

public class SeekerEditForm extends EditForm {

    private String spouseName;
    private String numberOfChildren;

    @Name(required = false)
    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    @Number(required = false, regex = "\\d{0,2}", message = "At max two digits allowed")
    public String getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(String numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    @Override
    public String toString() {
        return "SeekerEditForm{" +
                "spouseName='" + spouseName + '\'' +
                ", numberOfChildren='" + numberOfChildren + '\'' +
                '}';
    }

    @Override
    public void validateCustom(Map<String, String> errors) {
        super.validateCustom(errors);
    }
}
