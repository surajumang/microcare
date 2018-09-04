
package com.care.beans;

public class Seeker extends Member {

        private int id;
        private int numberOfChildren;
        private String spouseName;

        public static final Seeker EMPTY_SEEKER = new Seeker();

        @Override
        public int getId() {
                return id;
        }

        @Override
        public void setId(int id) {
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

    @Override
    public String toString() {
        return "Seeker{" +
                "id=" + id +
                ", numberOfChildren=" + numberOfChildren +
                ", spouseName='" + spouseName + '\'' +
                '}';
    }
}
