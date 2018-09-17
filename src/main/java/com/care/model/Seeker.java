
package com.care.model;

import java.util.Set;

public class Seeker extends Member{

    private long id;
    private int numberOfChildren;
    private String spouseName;
    private Set<Job> jobs;

    private static final Seeker EMPTY_SEEKER = new Seeker();

    public Seeker() {
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public int getNumberOfChildren() {
                return numberOfChildren;
        }

    public void setNumberOfChildren(int numberOfChildren) {
         this.numberOfChildren = numberOfChildren;
    }

    public String getSpouseName() {
                return spouseName;
        }

    public void setSpouseName(String spouseName) {
                this.spouseName = spouseName;
        }

    public Set<Job> getJobs() {
        return jobs;
    }

    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
    }

    public static Seeker emptySeeker(){
        return EMPTY_SEEKER;
    }

}
