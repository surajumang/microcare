package com.care.dto.form;

import com.care.annotation.Name;
import com.care.annotation.Number;

import java.util.Map;

public class SeekerRegistrationDTO extends RegistrationFormDTO {

    private String numberOfchildren;
    private String spouseName;

    @Name(required = false)
    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    @Number(required = false)
    public String getNumberOfchildren() {
        return numberOfchildren;
    }

    public void setNumberOfchildren(String numberOfchildren) {
        this.numberOfchildren = numberOfchildren;
    }

    @Override
    public String toString() {
        return "SeekerRegistrationDTO{" +
                "numberOfchildren='" + numberOfchildren + '\'' +
                ", spouseName='" + spouseName + '\'' +
                '}' + super.toString() ;
    }

    @Override
    public void validateCustom(Map<String, String> errors) {
        super.validateCustom(errors);
    }
}
