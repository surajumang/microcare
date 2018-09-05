
package com.care.beans;

public class Sitter extends Member {
        private int id;
        private int experience;
        private int expectedPay;

        public static final Sitter EMPTY_SITTER = new Sitter();

        @Override
        public int getId() {
                return id;
        }

        @Override
        public void setId(int id) {
                this.id = id;
        }

        public int getExperience() {
                return experience;
        }

        public void setExperience(int experience) {
                this.experience = experience;
        }

        public int getExpectedPay() {
                return expectedPay;
        }

        public void setExpectedPay(int expectedPay) {
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
