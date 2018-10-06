package com.care.form;

import com.care.annotation.Email;
import com.care.annotation.NotNull;
import com.care.model.Member;
import com.care.service.AccountService;
import com.care.service.AccountServiceImpl;
import com.care.service.ServiceFactory;
import com.care.validation.FormValidator;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

public class EmailForm extends FormBean {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private String email;

    @NotNull
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public ActionErrors validateCustom(HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        try {
            FormValidator.validate(this, errors);
            // validate if the email exist in the DB.
            AccountService accountService = ServiceFactory.get(AccountServiceImpl.class);
            if (errors.isEmpty()){
                if (accountService.getMember(email) == Member.emptyMember()){
                    //error
                    errors.add("email", new ActionMessage("errors.email.notexist"));
                }
            }

        } catch (InvocationTargetException e) {
            logger.log(Level.SEVERE, "Invok", e);
        } catch (IllegalAccessException e) {
            logger.log(Level.SEVERE, "Invok", e);
        }
        return errors;
    }
}
