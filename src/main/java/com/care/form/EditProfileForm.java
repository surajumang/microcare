package com.care.form;

import com.care.annotation.*;
import com.care.annotation.Number;
import com.care.controller.ControllerUtil;
import com.care.model.Member;
import com.care.service.AccountService;
import com.care.service.AccountServiceImpl;
import com.care.service.ServiceFactory;
import com.care.validation.FormValidator;
import org.apache.commons.lang3.StringUtils;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

@Flow(flowId = "getMemberType")
public class EditProfileForm extends BaseForm {
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

    @NotEmpty
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @FlowCheck(flow = "SITTER")
    @NotEmpty
    @DecimalNumber
    @Range(min=1, max = 1000, message = "errors.amount.size")
    public String getExpectedPay() {
        return expectedPay;
    }

    public void setExpectedPay(String expectedPay) {
        this.expectedPay = expectedPay;
    }

    @FlowCheck(flow = "SITTER")
    @NotEmpty
    @Number
    @Range(min = 0, max = 100, message = "errors.experience.size")
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
    @Range(min = 0, max = 100, message = "errors.children.size")
    public String getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(String numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    @NotEmpty
    @Name
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotEmpty
    @Name
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotEmpty
    @Zipcode
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

    @NotEmpty
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @NotEmpty
    @Phone
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
            if (member != null && member.isSitter()){
                if (StringUtils.isBlank(expectedPay)){
                    errors.add("expectedPay", new ActionMessage("errors.notnull", "expectedPay"));
                }
                if (StringUtils.isBlank(experience)){
                    errors.add("experience", new ActionMessage("errors.notnull", "experience"));
                }
            }
        } catch (Exception e) {
            // this property is not being printed as error.
            errors.add("flow", new ActionMessage("errors.invalid"));
        }
        /*
        Check if the email entered is already in use
         */
        AccountService accountService = ServiceFactory.get(AccountServiceImpl.class);
       // errors.properties()
        // if Id is null then assume it is for Registration.
        if (errors.isEmpty()){
            Member member1 ;
            try {
                member1 = accountService.getMember(getEmail());
            } catch (Exception e) {
                member1 = Member.emptyMember();
            }
            // Only if there is already a member registered.
            if (! member1.isEmpty()){
                // it is for registration.
                //String ID = String.valueOf(member1.getId());
                if (member == null){
                    errors.add("email", new ActionMessage("errors.email.exist", "Email"));
                }

                //This will take care of Edit Email, Checks if the emailId is unchanged.
                //equals method should be used.
                else if( member1.getId() != member.getId()){
                    errors.add("email", new ActionMessage("errors.email.exist", "Email"));
                }
            }
        }
        if (StringUtils.isBlank(numberOfChildren) && errors.isEmpty()){
            numberOfChildren="0";
        }
        return errors;
    }
}
