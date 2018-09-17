
package com.care.model;

import java.util.Set;

public class Sitter extends Member {
        private long id;
        private int experience;
        private double expectedPay;
        private Set<Application> applications;

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

        public static Sitter emptySitter(){
                return EMPTY_SITTER;
        }

}
