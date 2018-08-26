package com.care.form;

import com.care.annotations.EmailCheck;
import com.care.annotations.NameCheck;
import com.care.annotations.NumberCheck;
import com.care.validation.FormBean;

public class RegistrationForm extends FormBean{
    private String email;
    private String firstname;
    private String lastname;
    private String zipcode;
    private String password;

    @EmailCheck
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NameCheck
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    @NameCheck
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    @NumberCheck(pattern = "\\d{6}")
    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RegistrationForm{" +
                "email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public void validate() {

    }
}
