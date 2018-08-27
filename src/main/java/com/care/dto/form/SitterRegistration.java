package com.care.dto.form;

public class SitterRegistration extends RegistrationForm {
    private int experience;
    private double expectedPay;

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public double getExpectedPay() {
        return expectedPay;
    }

    public void setExpectedPay(double expectedPay) {
        this.expectedPay = expectedPay;
    }
}
