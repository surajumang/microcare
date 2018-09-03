package com.care.dto;

import java.util.Date;

public class AppliedJob {
    private int id;
    private String title;
    private int postedBy;
    private double hourlyPay;
    private Date startDate;
    private Date endDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(int postedBy) {
        this.postedBy = postedBy;
    }

    public double getHourlyPay() {
        return hourlyPay;
    }

    public void setHourlyPay(double hourlyPay) {
        this.hourlyPay = hourlyPay;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "AppliedJob{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", postedBy=" + postedBy +
                ", hourlyPay=" + hourlyPay +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
