
package com.care.model;

import java.util.HashSet;
import java.util.Set;

public class Seeker extends Member{

    private long id;
    private int numberOfChildren;
    private String spouseName;
    private Set<Job> jobs = new HashSet<>();

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

//    public postJob(){
//
//    }

    public int closeAllJobs(){
        for(Job job : jobs){
            if (job.getStatus() == Status.ACTIVE){
                job.setStatus(Status.EXPIRED);
            }
        }
        return 1;
    }

    public int closeAccount(){
        closeAllJobs();
        this.setStatus(Status.CLOSED);
        this.setEmail(getEmail() + "CLOSED" + getId());
        return 1;
    }

}
