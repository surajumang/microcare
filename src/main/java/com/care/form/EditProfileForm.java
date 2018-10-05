package com.care.form;

import com.care.annotation.Email;
import com.care.annotation.Name;
import com.care.annotation.NotNull;
import com.care.annotation.Number;
import com.care.model.Member;
import com.care.model.MemberType;
import com.care.service.AccountService;
import com.care.service.AccountServiceImpl;
import com.care.service.ServiceFactory;
import com.care.validation.FormValidator;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

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
    private String id;

    @NotNull
    @Email
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

    @Number(required = false, regex = "\\d{0,2}", message = "errors.number.children")
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
    @Number(regex = "\\d{6}", message = "errors.number.zipcode")
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
    @Number(regex = "\\d{10}", message = "errors.number.phone")
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

        if (! errors.isEmpty()){
            return errors;
        }
        try {
            if (MemberType.valueOf(memberType) == MemberType.SITTER){
                if (! expectedPay.matches("\\d{1,3}(\\.\\d{0,2})?")){
                    errors.add("expectedPay", new ActionMessage("errors.amount"));
                }
                if (! experience.matches("\\d{1,2}")){
                    errors.add("experience", new ActionMessage("errors.experience"));
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
            Member member = accountService.getMember(getEmail());
            // Only if there is already a member registered.
            if (member != Member.emptyMember()){
                // it is for registration.
                String ID = String.valueOf(member.getId());
                if (getId() == null){
                    errors.add("email", new ActionMessage("errors.email.exist"));
                }

                //This will take care of Edit Email, Checks if the emailId is unchanged.
                else if( !ID.equals(getId())){
                    errors.add("email", new ActionMessage("errors.email.exist"));
                }
            }
        }
        if (numberOfChildren != null && numberOfChildren.equals("") && errors.isEmpty()){
            numberOfChildren="0";
        }
        return errors;
    }
}
