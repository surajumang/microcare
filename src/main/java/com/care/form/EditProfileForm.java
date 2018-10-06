package com.care.form;

import com.care.annotation.*;
import com.care.annotation.Number;
import com.care.controller.ControllerUtil;
import com.care.model.Member;
import com.care.model.MemberType;
import com.care.service.AccountService;
import com.care.service.AccountServiceImpl;
import com.care.service.ServiceFactory;
import com.care.validation.FormValidator;
import com.mysql.jdbc.StringUtils;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

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
    private String id;

    @NotNull
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @DecimalNumber
    @PositiveNumber
    @Size(min=0, max = 6, message = "error.expectedpay.size")
    public String getExpectedPay() {
        return expectedPay;
    }

    public void setExpectedPay(String expectedPay) {
        this.expectedPay = expectedPay;
    }

    @Number
    @PositiveNumber
    @Size(min = 0, max = 2, message = "errors.experience.size")
    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    @Name
    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    @Number
    @PositiveNumber
    @Size(min=0, max=2, message = "errors.children.size")
    public String getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(String numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    @NotNull
    @Name
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull
    @Name
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotNull
    @Number
    @PositiveNumber
    @Size(min = 6, max=6, message = "errors.zipcode.size")
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

    @NotNull
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @NotNull
    @Number
    @PositiveNumber
    @Size(min = 10, max = 10, message = "errors.phone.size")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public ActionErrors validateCustom(HttpServletRequest request) {
        Member member = (Member) request.getSession().getAttribute(ControllerUtil.CURRENT_USER);
        ActionErrors errors = new ActionErrors();
        try {
            FormValidator.validate(this, errors);
        } catch (InvocationTargetException e) {
            logger.log(Level.SEVERE, "While validating", e);
        } catch (IllegalAccessException e) {
            logger.log(Level.SEVERE, "While validating", e);
        }

        if (! errors.isEmpty()){
            return errors;
        }
        // The below lines may not be required. Removal required [todo]
        try {
            if (memberType.equals(MemberType.SITTER.name())){
                if (StringUtils.isEmptyOrWhitespaceOnly(expectedPay)){
                    errors.add("expectedPay", new ActionMessage("errors.notnull"));
                }
                if (StringUtils.isEmptyOrWhitespaceOnly(experience)){
                    errors.add("experience", new ActionMessage("errors.notnull"));
                }
            }
        } catch (Exception e) {
            // this property is not being printed as error.
            errors.add("memberType", new ActionMessage("errors.invalid"));
        }
        /*
        Check if the email entered is already in use
         */
        AccountService accountService = ServiceFactory.get(AccountServiceImpl.class);
       // errors.properties()
        // if Id is null then assume it is for Registration.
        if (errors.isEmpty()){

            Member member1 = accountService.getMember(getEmail());
            // Only if there is already a member registered.
            if (! member1.isEmpty()){
                // it is for registration.
                //String ID = String.valueOf(member1.getId());
                if (getId() == null){
                    errors.add("email", new ActionMessage("errors.email.exist"));
                }

                //This will take care of Edit Email, Checks if the emailId is unchanged.
                //equals method should be used.
                else if( member1.getId() != member.getId()){
                    errors.add("email", new ActionMessage("errors.email.exist"));
                }
            }
        }
        if (StringUtils.isEmptyOrWhitespaceOnly(numberOfChildren) && errors.isEmpty()){
            numberOfChildren="0";
        }
        return errors;
    }
}
