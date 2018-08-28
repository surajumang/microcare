package com.care.dto.form;

import com.care.validation.FormBean;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class RegistrationForm extends FormBean{

    private String email;
    private String firstName;
    private String lastName;
    private String zipCode;
    private String password;
    private String password2;
    private String address;
    private String phone;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public void validateCustom(HttpServletRequest req) {
        String errorvalue = "";
        boolean flag = false;

        Map<String, String> myErrors = (Map<String, String>)req.getAttribute("errors");

        if(! password.equals(password2)){
            errorvalue += " Passwords don't match";
            flag = true;
        }

        if (flag)
            myErrors.put("password2", errorvalue);
    }
}
