
package com.care.beans;

public class Sitter extends Member {
        private int id;
        private int experience;
        private int expectedPay;

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
}
