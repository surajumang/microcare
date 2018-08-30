package com.care.dto.form;

import com.care.annotations.Date;
import com.care.annotations.Number;
import com.care.validation.FormBean;

import java.util.Map;

public class JobFormDTO extends FormBean {
    private String title;
    private String hourlyPay;
    // should be greater than current Date and time.
    private String startDate;
    // should be greater than or equal to current Date and time.
    private String endDate;


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
    @Date
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
    public String toString() {
        return "JobForm{" +
                "title='" + title + '\'' +
                ", hourlyPay='" + hourlyPay + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }

    @Override
    public void validateCustom(Map<String, String> errors) {
        //check if end date is greater than start date.
    }

}
