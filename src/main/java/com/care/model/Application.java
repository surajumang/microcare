/*
        Bean class corresponding to Application.
*/

package com.care.model;

import java.sql.Date;

public class Application {

        private int id;
        private int jobId;
        private int sitterId;
        private double expectedPay;
        private Status status;
        private Date dateCreated;
        private Date lastModified;
        private Job job;
        private Sitter sitter;

        public static final Application EMPTY_APPLICATION = new Application();

        public Job getJob() {
                return job;
        }

        public void setJob(Job job) {
                this.job = job;
        }

        public Sitter getSitter() {
                return sitter;
        }

        public void setSitter(Sitter sitter) {
                this.sitter = sitter;
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public int getJobId() {
                return jobId;
        }

        public void setJobId(int jobId) {
                this.jobId = jobId;
        }

        public int getSitterId() {
                return sitterId;
        }

        public void setSitterId(int sitterId) {
                this.sitterId = sitterId;
        }

        public double getExpectedPay() {
                return expectedPay;
        }

        public void setExpectedPay(double expectedPay) {
                this.expectedPay = expectedPay;
        }

        public Status getStatus() {
                return status;
        }

        public void setStatus(Status status) {
                this.status = status;
        }

        public Date getDateCreated() {
                return dateCreated;
        }

        public void setDateCreated(Date dateCreated) {
                this.dateCreated = dateCreated;
        }

        public Date getLastModified() {
                return lastModified;
        }

        public void setLastModified(Date lastModified) {
                this.lastModified = lastModified;
        }

}
