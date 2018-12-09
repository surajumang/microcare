package com.care.form;

import com.care.annotation.*;
import com.care.validation.FormValidator;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

public class JobForm extends BaseForm {

    private Logger logger = Logger.getLogger("JobForm");
    private String id;
    private String  seekerId;
    private String title;
    private String hourlyPay;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private String startDateTime;
    private String endDateTime;

    public String getSeekerId() {
        return seekerId;
    }

    public void setSeekerId(String seekerId) {
        this.seekerId = seekerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotEmpty
    @Name
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NotEmpty
    @DecimalNumber
    @Range(min=1, max = 1000, message = "errors.hourlypay.range")
    public String getHourlyPay() {
        return hourlyPay;
    }

    public void setHourlyPay(String hourlyPay) {
        this.hourlyPay = hourlyPay;
    }

    @NotEmpty
    @StringDate
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @NotEmpty
    @StringDate
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @NotEmpty
    @Time
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @NotEmpty
    @Time
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    @Override
    public ActionErrors validateCustom(HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        try {
            FormValidator.validate(this, errors);
        } catch (InvocationTargetException e) {
            logger.log(Level.SEVERE, "Invok", e);
        } catch (IllegalAccessException e) {
            logger.log(Level.SEVERE, "Invok", e);
        }
        logger.info(errors + "");

        if (! errors.isEmpty()){
            return errors;
        }
        boolean flag = true;
        // If the dates are not okay then a parse exception will be generated and that will be handled here.
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        try {
            Timestamp startTime = Timestamp.valueOf(getStartDate() + " " + getStartTime() + ":00");
            if (startTime.before(currentTime)){
                errors.add("startDate", new ActionMessage("errors.startdate.greater",  "CurrentDate"));
                flag = false;
            }
            try{
                Timestamp endTime = Timestamp.valueOf(getEndDate() + " " + getEndTime() + ":00");
                if (endTime.before(startTime)){
                    errors.add("endDate", new ActionMessage("errors.enddate.greater", "EndDate"));
                    flag = false;
                }
            }catch (Exception e){
                logger.log(Level.SEVERE, "Exception while validating Time values", e);
                errors.add("endDate", new ActionMessage("errors.date.format", "EndDate"));
                flag = false;
            }
        }catch (Exception e){
            logger.log(Level.SEVERE, "Exception while validating Time values", e);
            errors.add("startDate", new ActionMessage("errors.date.format", "StartDate"));
            flag = false;
        }

        if (flag && errors.isEmpty()){
            //set startDateTime value such that it passes the ObjectMapper.
            setStartDateTime(getStartDate() + " " + getStartTime() + ":00");
            setEndDateTime(getEndDate() + " " + getEndTime() + ":00");
        }
        logger.info("Done with validation" + errors);
        return errors;
    }
}
