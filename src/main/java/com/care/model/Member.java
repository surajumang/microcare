
package com.care.model;

import java.sql.Date;

public class Member {
        private int id;
        private String firstName;
        private String lastName;
        private int phone;
        private MemberType memberType;
        private String email;
        private String address;
        private int zipCode;
        private Status status;
        private Date lastModified;
        private String password;

        public static final Member EMPTY_MEMBER = new Member();

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public String getFirstName() {
                return firstName;
        }

        public void setFirstName(String firstName) {
                this.firstName = firstName;
        }

        public String getLastName() {
                return lastName;
        }

        public void setLastName(String lastName) {
                this.lastName = lastName;
        }

        public int getPhone() {
                return phone;
        }

        public void setPhone(int phone) {
                this.phone = phone;
        }

        public MemberType getMemberType() {
                return memberType;
        }

        public void setMemberType(MemberType memberType) {
                this.memberType = memberType;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getAddress() {
                return address;
        }

        public void setAddress(String address) {
                this.address = address;
        }

        public int getZipCode() {
                return zipCode;
        }

        public void setZipCode(int zipCode) {
                this.zipCode = zipCode;
        }

        public Status getStatus() {
                return status;
        }

        public void setStatus(Status status) {
                this.status = status;
        }

        public Date getLastModified() {
                return lastModified;
        }

        public void setLastModified(Date lastModified) {
                this.lastModified = lastModified;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone=" + phone +
                ", memberType=" + memberType +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", zipCode=" + zipCode +
                ", status=" + status +
                ", lastModified=" + lastModified +
                ", password='" + password + '\'' +
                '}';
    }


}
