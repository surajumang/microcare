/*
        Bean class corresponding to Application.
*/

package com.care.beans;

import java.util.Date;

public class Application {

        private int id;
        private int jobId;
        private int sitterId;
        private double expectedPay;
        private Status status;
        private Date dateOfCreation;
        private Date lastModified;

        public static final Application EMPTY_APPLICATION = new Application();

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

        public Date getDateOfCreation() {
                return dateOfCreation;
        }

        public void setDateOfCreation(Date dateOfCreation) {
                this.dateOfCreation = dateOfCreation;
        }

        public Date getLastModified() {
                return lastModified;
        }

        public void setLastModified(Date lastModified) {
                this.lastModified = lastModified;
        }

}
