package com.care.form;

import com.care.annotation.Name;
import com.care.annotation.Number;
import com.care.validation.FormBean;
import com.care.validation.FormValidator;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditProfileForm extends ActionForm {
    private Logger logger = Logger.getLogger("PutProfileInfo");

    private String firstName;
    private String lastName;
    private String zipCode;
    private String memberType;
    private String address;
    private String phone;

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
    @Number(regex = "\\d{6}", message = "Must be exactly six digits")
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    @Number(regex = "\\d{10}", message = "Must be exactly ten digits only")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

//    @Override
//    public void validateCustom(Map<String, String> errors) {
//        try {
//            FormValidator.validate(this, errors);
//        } catch (InvocationTargetException e) {
//            logger.log(Level.SEVERE, "While validating", e);
//        } catch (IllegalAccessException e) {
//            logger.log(Level.SEVERE, "While validating", e);
//        }
//    }

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (firstName.matches("")){

        }
        if (lastName.matches("")){

        }
        if (zipCode.matches("")){

        }
        if (phone.matches("")){

        }
        return errors;
    }
}
