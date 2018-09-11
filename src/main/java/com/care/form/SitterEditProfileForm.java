package com.care.form;

import com.care.annotation.Number;

import java.util.Map;

public class SitterEditProfileForm extends EditProfileForm {

    private String expectedPay;
    private String experience;
    @Number(regex = "\\d{1,3}(\\.\\d{0,2})?", message = "Number format is DDD.DD")
    public String getExpectedPay() {
        return expectedPay;
    }

    public void setExpectedPay(String expectedPay) {
        this.expectedPay = expectedPay;
    }
    @Number(regex = "\\d{1,2}", message = "At max two digits allowed")
    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "SitterEditProfileForm{" +
                "expectedPay='" + expectedPay + '\'' +
                ", experience='" + experience + '\'' +
                '}';
    }

    @Override
    public void validateCustom(Map<String, String> errors) {
        super.validateCustom(errors);
    }
}
