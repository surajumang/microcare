package com.care.dto.form;

import com.care.annotations.Name;
import com.care.annotations.Number;
import com.care.validation.FormBean;

import javax.servlet.http.HttpServletRequest;

public class PostJobForm extends FormBean {
    private String title;
    private String hourlyPay;
    private String startDate;
    private String endDate;

    @Name
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Number(pattern = "\\d+\\.\\d+")
    public String getHourlyPay() {
        return hourlyPay;
    }

    public void setHourlyPay(String hourlyPay) {
        this.hourlyPay = hourlyPay;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public void validateCustom(HttpServletRequest req) {

    }
}
