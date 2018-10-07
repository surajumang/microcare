package com.care.form;

import com.care.annotation.DecimalNumber;
import com.care.annotation.NotNull;
import com.care.annotation.Range;
import com.care.validation.FormValidator;
import org.apache.struts.action.ActionErrors;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

public class ApplicationForm extends BaseForm {

    Logger logger = Logger.getLogger("ApplicationForm");

    private String jobId;
    private String sitterId;
    private String expectedPay;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getSitterId() {
        return sitterId;
    }

    public void setSitterId(String sitterId) {
        this.sitterId = sitterId;
    }

    @NotNull
    @DecimalNumber
    @Range(min = 1, max = 1000, message = "errors.amount.size")
    public String getExpectedPay() {
        return expectedPay;
    }

    public void setExpectedPay(String expectedPay) {
        this.expectedPay = expectedPay;
    }

    @Override
    public ActionErrors validateCustom(HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        try {
            FormValidator.validate(this, errors);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return errors;
    }

    @Override
    public String toString() {
        return "ApplicationForm{" +
                "jobId='" + jobId + '\'' +
                ", sitterId='" + sitterId + '\'' +
                ", expectedPay='" + expectedPay + '\'' +
                '}';
    }
}
