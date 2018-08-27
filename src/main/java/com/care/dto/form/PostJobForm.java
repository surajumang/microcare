package com.care.dto.form;

import com.care.annotations.NameCheck;
import com.care.annotations.NumberCheck;
import com.care.validation.FormBean;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class PostJobForm extends FormBean {
    private String title;
    private String hourlyPay;
    private String startDate;
    private String endDate;

    @NameCheck
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @NumberCheck(pattern = "\\d+\\.\\d+")
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
    public void validate(HttpServletRequest req) {

    }
}
