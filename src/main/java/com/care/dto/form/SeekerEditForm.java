package com.care.dto.form;

import com.care.annotation.Name;
import com.care.annotation.Number;

import java.util.Map;

public class SeekerEditForm extends EditForm {

    private String spouseName;
    private String noOfChildren;

    @Name(required = false)
    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    @Number(required = false)
    public String getNoOfChildren() {
        return noOfChildren;
    }

    public void setNoOfChildren(String noOfChildren) {
        this.noOfChildren = noOfChildren;
    }

    @Override
    public String toString() {
        return "SeekerEditForm{" +
                "spouseName='" + spouseName + '\'' +
                ", noOfChildren='" + noOfChildren + '\'' +
                '}';
    }

    @Override
    public void validateCustom(Map<String, String> errors) {
        super.validateCustom(errors);
    }
}
