package com.care.form;

import com.care.annotation.Email;
import com.care.annotation.Name;
import com.care.annotation.Number;
import com.care.model.MemberType;
import com.care.validation.FormBean;
import com.care.validation.FormValidator;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditProfileForm extends  FormBean {
    private Logger logger = Logger.getLogger("PutProfileInfo");

    private String email;
    private String firstName;
    private String lastName;
    private String zipCode;
    private String memberType;
    private String address;
    private String phone;
    private String spouseName;
    private String numberOfChildren;
    private String expectedPay;
    private String experience;

    @Email(required = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExpectedPay() {
        return expectedPay;
    }

    public void setExpectedPay(String expectedPay) {
        this.expectedPay = expectedPay;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    @Name(required = false)
    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    @Number(required = false, regex = "\\d{0,2}", message = "errors.number.children.length")
    public String getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(String numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
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

    @Number(regex = "\\d{6}", message = "errors.number.zipcode.length")
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

    @Number(regex = "\\d{10}", message = "errors.number.phone.length")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    //[TODO] write custom error check for optional fields
    @Override
    public ActionErrors validateCustom() {
        ActionErrors errors = new ActionErrors();
        try {
            FormValidator.validate(this, errors);
        } catch (InvocationTargetException e) {
            logger.log(Level.SEVERE, "While validating", e);
        } catch (IllegalAccessException e) {
            logger.log(Level.SEVERE, "While validating", e);
        }

        if (numberOfChildren != null && numberOfChildren.equals("")){
            numberOfChildren="0";
        }

        if (MemberType.valueOf(memberType) == MemberType.SITTER){
            if (! expectedPay.matches("\\d{1,3}(\\.\\d{0,2})?")){
                errors.add("expectedPay", new ActionMessage("errors.amount"));
            }
            if (! experience.matches("\\d{1,2}")){
                errors.add("experience", new ActionMessage("errors.number"));
            }
        }
        return errors;
    }
}
