package com.care.dto.form;

import com.care.annotation.Email;
import com.care.annotation.Name;
import com.care.annotation.Number;
import com.care.validation.FormBean;

import java.util.Map;

public class RegistrationFormDTO extends FormBean{

    private String email;
    private String firstName;
    private String lastName;
    private String zipCode;
    private String password;
    private String password2;
    private String address;
    private String phone;

    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Name
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Name
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Number
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Number
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public void validateCustom(Map<String, String> errors) {
        String errorvalue = "";
        boolean flag = false;

        if(! password.equals(password2)){
            errorvalue += " Passwords don't match";
            flag = true;
        }

        if (flag)
            errors.put("password2", errorvalue);
    }
}
