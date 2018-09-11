package com.care.form;

import com.care.annotation.Number;
import com.care.validation.FormBean;
import org.apache.struts.action.ActionErrors;

import java.util.Map;

public class SitterRegistration extends RegistrationForm implements FormBean {

    private String experience;
    private String expectedPay;

    @Number(regex = "\\d{1,2}", message = "At most two digits and at least one digit")
    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    @Number(required = false, regex = "\\d{1,3}(\\.\\d{1,2})?", message = "At Max three digits before decimal")
    public String getExpectedPay() {
        return expectedPay;
    }

    public void setExpectedPay(String expectedPay) {
        this.expectedPay = expectedPay;
    }

    @Override
    public void validateCustom(ActionErrors errors) {
        super.validateCustom(errors);
    }

    @Override
    public String toString() {
        return "SitterRegistration{" +
                "experience='" + experience + '\'' +
                ", expectedPay='" + expectedPay + '\'' +
                '}' + super.toString();
    }

}

