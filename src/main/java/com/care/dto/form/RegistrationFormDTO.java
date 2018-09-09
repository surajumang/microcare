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
    private String spouseName;
    private String experience;
    private String numberOfChildren;
    private String expectedPay;


    @Email(message = "Email must be alphanumeric and contain an @")
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

    @Number(regex = "\\d{6}", message = "exactly six digits required")
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Name(regex = "[@#!\\w\\d]{5,}", required = true, message = "Must be 5 or more characters without whitespaces ")
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

    @Name(required = false)
    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    @Number(regex = "\\d{1,2}", message = "At most two digits and at least one digit")
    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    @Number(required = false)
    public String getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(String numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    @Number(required = false, regex = "\\d{1,3}(\\.\\d{1,2})?", message = "At Max three digits before decimal")
    public String getExpectedPay() {
        return expectedPay;
    }

    public void setExpectedPay(String expectedPay) {
        this.expectedPay = expectedPay;
    }

    @Number(regex = "\\d{10}", message = "Must be exactly 10 digits ")
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
            errors.put("password2", "Passwords should match");
        }
    }

    @Override
    public String toString() {
        return "RegistrationFormDTO{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", memberType='" + memberType + '\'' +
                ", password='" + password + '\'' +
                ", password2='" + password2 + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", spouseName='" + spouseName + '\'' +
                ", experience='" + experience + '\'' +
                ", numberOfChildren='" + numberOfChildren + '\'' +
                ", expectedPay='" + expectedPay + '\'' +
                '}';
    }
}
