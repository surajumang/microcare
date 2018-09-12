package com.care.form;

import com.care.annotation.Name;
import com.care.annotation.Number;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletRequest;

public class SeekerEditProfileForm extends EditProfileForm {

    private String spouseName;
    private String numberOfChildren;

    @Name(required = false)
    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    @Number(required = false, regex = "\\d{0,2}", message = "At max two digits allowed")
    public String getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(String numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    @Override
    public ActionErrors validateCustom() {
        return super.validateCustom();
    }

    @Override
    public String toString() {
        return "SeekerEditProfileForm{" +
                "spouseName='" + spouseName + '\'' +
                ", numberOfChildren='" + numberOfChildren + '\'' +
                '}';
    }
}
