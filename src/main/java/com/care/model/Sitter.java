
package com.care.model;

public class Sitter extends Member {
        private int id;
        private int experience;
        private double expectedPay;

        public static final Sitter EMPTY_SITTER = new Sitter();

        public int getId() {
                return id;
        }

        public void setId(int id) {
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

        @Override
        public String toString() {
                return "Sitter{" +
                        "id=" + id +
                        ", experience=" + experience +
                        ", expectedPay=" + expectedPay +
                        '}' + super.toString();
        }
}
