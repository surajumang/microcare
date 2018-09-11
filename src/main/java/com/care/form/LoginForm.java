package com.care.form;

import com.care.annotation.Email;
import com.care.validation.FormBean;
import com.care.validation.FormValidator;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class LoginForm extends ActionForm {
    private String email;
    private String password;

    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




    @Override
    public String toString() {
        return "LoginForm{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

//    @Override
//    public void validateCustom(Map<String, String> errors) {
//        try {
//            FormValidator.validate(this, errors);
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        email = "";
        password = "";
    }

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (password.equals("")){
            errors.add("password", new ActionMessage("errors.required"));
        }
        if (email.equals("")){
            errors.add("email", new ActionMessage("errors.required"));
        }
        return errors;
    }
}
