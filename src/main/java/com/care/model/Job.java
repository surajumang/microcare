
package com.care.model;

import java.sql.Date;
import java.util.List;

public class Job {
    private long id;
    private String title;
    private long seekerId;
    private double hourlyPay;
    private Date startDate;
    private Date endDate;
    private Status status;
    private Date dateOfCreation;
    private Seeker seeker;
    private List<Application> applications;

    public Seeker getSeeker() {
        return seeker;
    }

    public void setSeeker(Seeker seeker) {
        this.seeker = seeker;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public static final Job EMPTY_JOB = new Job();

    public double getHourlyPay() {
        return hourlyPay;
    }

    public void setHourlyPay(double hourlyPay) {
        this.hourlyPay = hourlyPay;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getSeekerId() {
        return seekerId;
    }

    public void setSeekerId(long seekerId) {
        this.seekerId = seekerId;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", seekerId=" + seekerId +
                ", hourlyPay=" + hourlyPay +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status=" + status +
                ", dateOfCreation=" + dateOfCreation +
                '}';
    }

}
