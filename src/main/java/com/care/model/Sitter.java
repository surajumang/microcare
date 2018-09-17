
package com.care.model;

public class Sitter extends Member {
        private long id;
        private int experience;
        private double expectedPay;

        public static final Sitter EMPTY_SITTER = new Sitter();
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

}
