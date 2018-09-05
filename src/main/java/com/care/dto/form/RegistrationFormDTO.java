package com.care.dto.form;

import com.care.annotation.Email;
import com.care.annotation.Name;
import com.care.annotation.Number;
import com.care.validation.FormBean;
import com.care.validation.FormValidator;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegistrationFormDTO extends FormBean{

    private Logger logger = Logger.getLogger("RegistrationFormDto");

    private String email;
    private String firstName;
    private String lastName;
    private String zipCode;
    private String memberType;
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

    @Number(regex = "\\d{6}")
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

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    @Number(regex = "\\d{10}")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public void validateCustom(Map<String, String> errors) {
        try {
            FormValidator.validate(this, errors);
        } catch (InvocationTargetException e) {
            logger.log(Level.SEVERE, "While validating", e);
        } catch (IllegalAccessException e) {
            logger.log(Level.SEVERE, "While validating", e);
        }
        String errorValue = "";
        boolean flag = false;

        if(! password.equals(password2)){
            errors.put("password2", "Passwords don't match");
        }


    }

    @Override
    public String toString() {
        return "RegistrationFormDTO{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", password='" + password + '\'' +
                ", memberType='" + memberType + '\'' +
                ", password2='" + password2 + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
