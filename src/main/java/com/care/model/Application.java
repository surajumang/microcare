/*
        Bean class corresponding to Application.
*/

package com.care.model;

import java.sql.Timestamp;

public class Application {

        private long id;
        private double expectedPay;
        private Status status = Status.ACTIVE;
        private Timestamp dateCreated;
        private Timestamp lastModified;
        private Job job;
        private Sitter sitter;

        private static final Application EMPTY_APPLICATION = new Application();

        public Application(){

        }

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

        public long getId() {
                return id;
        }

        public void setId(long id) {
                this.id = id;
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

        public static Application emptyApplication(){
                return EMPTY_APPLICATION;
        }


}
