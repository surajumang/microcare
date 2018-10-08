
package com.care.model;

import java.util.HashSet;
import java.util.Set;

public class Sitter extends Member {
    private long id;
    private int experience;
    private double expectedPay;
    private Set<Application> applications = new HashSet<>();

    private static final Sitter EMPTY_SITTER = new Sitter();

    public Sitter() {
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

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

    public Set<Application> getApplications() {
        return applications;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }

    public static Sitter emptySitter() {
        return EMPTY_SITTER;
    }

    public boolean hasApplied(long jobId) {
        return applications
                .stream()
                .anyMatch(application -> application.getJob().getId() == jobId);

    }

    public int closeAllApplications() {
        for (Application application : getApplications()) {
            if (application.getStatus() == Status.ACTIVE) {
                application.setStatus(Status.EXPIRED);
            }
        }
        return 1;
    }

    public int closeAccount() {
        this.closeAllApplications();
        this.setStatus(Status.CLOSED);
        this.setEmail(getEmail() + "CLOSED" + getId());
        return 1;
    }

}
