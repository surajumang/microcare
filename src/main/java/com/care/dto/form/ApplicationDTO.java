package com.care.dto.form;

import com.care.annotation.Number;
import com.care.validation.FormBean;
import com.care.validation.FormValidator;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApplicationDTO extends FormBean {

    Logger logger = Logger.getLogger("ApplicationDTO");

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

    @Number(required = true, regex = "\\d{1,3}(\\.\\d{1,2})?", message = "Format: DDD.DD")
    public String getExpectedPay() {
        return expectedPay;
    }

    public void setExpectedPay(String expectedPay) {
        this.expectedPay = expectedPay;
    }

    @Override
    public void validateCustom(Map<String, String> errors) {
        try {
            FormValidator.validate(this, errors);
        } catch (InvocationTargetException e) {
            logger.log(Level.SEVERE, "Invok", e);
        } catch (IllegalAccessException e) {
            logger.log(Level.SEVERE, "ILLgalAccess", e);
        }
    }

    @Override
    public String toString() {
        return "ApplicationDTO{" +
                "jobId='" + jobId + '\'' +
                ", sitterId='" + sitterId + '\'' +
                ", expectedPay='" + expectedPay + '\'' +
                '}';
    }
}
