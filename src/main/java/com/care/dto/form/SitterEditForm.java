package com.care.dto.form;

import com.care.annotation.Number;

import java.util.Map;

public class SitterEditForm extends EditForm {

    private String expectedPay;
    private String experience;
    @Number(regex = "\\d{1,3}(\\.\\d{0,2})?")
    public String getExpectedPay() {
        return expectedPay;
    }

    public void setExpectedPay(String expectedPay) {
        this.expectedPay = expectedPay;
    }
    @Number(regex = "\\d{1,2}")
    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "SitterEditForm{" +
                "expectedPay='" + expectedPay + '\'' +
                ", experience='" + experience + '\'' +
                '}';
    }

    @Override
    public void validateCustom(Map<String, String> errors) {
        super.validateCustom(errors);
    }
}
