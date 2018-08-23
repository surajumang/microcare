
package com.care.beans;

import java.util.Date;

public class Member {
        
        private int id;
        private String firstName;
        private String lastName;
        private String phone;
        private MemberType memberType;
        private String email;
        private String addressLine1;
        private String addressLine2;
        private int zipCode;
        private Status status;
        private Date lastModified;
        private String password;

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

        public String getPhone() {
                return phone;
        }

        public void setPhone(String phone) {
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

        public String getAddressLine1() {
                return addressLine1;
        }

        public void setAddressLine1(String addressLine1) {
                this.addressLine1 = addressLine1;
        }

        public String getAddressLine2() {
                return addressLine2;
        }

        public void setAddressLine2(String addressLine2) {
                this.addressLine2 = addressLine2;
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
}
