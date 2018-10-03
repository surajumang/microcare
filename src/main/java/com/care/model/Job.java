
package com.care.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class Job extends BaseModel{
    private long id;
    private String title;
    private double hourlyPay;
    private Timestamp startDate;
    private Timestamp endDate;
    private Status status = Status.ACTIVE;
    private Timestamp dateCreated;
    private Timestamp lastModified;
    private Seeker seeker;
    private Set<Application> applications = new HashSet<>();

    private static final Job EMPTY_JOB = new Job();

    public Job() {
    }

    public Seeker getSeeker() {
        return seeker;
    }

    public void setSeeker(Seeker seeker) {
        this.seeker = seeker;
    }

    public Set<Application> getApplications() {
        return applications;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }

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

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    public static Job emptyJob(){
        return EMPTY_JOB;
    }

    public void close(){
        this.setStatus(Status.CLOSED);
        for (Application application : getApplications()){
            if (application.getStatus() == Status.ACTIVE){
                application.setStatus(Status.EXPIRED);
            }
        }
    }

}
