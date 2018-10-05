package com.care.form;

import com.care.annotation.Name;
import com.care.annotation.NotNull;
import com.care.annotation.Number;
import com.care.annotation.StringDate;
import com.care.validation.FormValidator;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JobForm extends FormBean {

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
    @Name
    public void setId(String id) {
        this.id = id;
    }

    @NotNull
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @NotNull
    @Number(regex = "\\d{1,3}(\\.\\d{0,2})?", message = "errors.amount")
    public String getHourlyPay() {
        return hourlyPay;
    }

    public void setHourlyPay(String hourlyPay) {
        this.hourlyPay = hourlyPay;
    }

    @NotNull
    @StringDate
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @NotNull
    @StringDate
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @NotNull
    @Name(regex = "\\d{2}:\\d{2}")
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @NotNull
    @Name(regex = "\\d{2}:\\d{2}")
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
    public ActionErrors validateCustom() {
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

        // try only if there are no validation errors [todo]
        try {
            Timestamp startTime = Timestamp.valueOf(getStartDate() + " " + getStartTime() + ":00");
            if (startTime.before(currentTime)){
                errors.add("startDate", new ActionMessage("errors.startdate.greater",  "CurrentDate"));
                flag = false;
            }
            try{
                Timestamp endTime = Timestamp.valueOf(getEndDate() + " " + getEndTime() + ":00");
                if (endTime.before(startTime)){
                    errors.add("endDate", new ActionMessage("errors.enddate.greater"));
                    flag = false;
                }
            }catch (Exception e){
                logger.log(Level.SEVERE, "Exception while validating Time values", e);
                errors.add("endDate", new ActionMessage("errors.date.format"));
                flag = false;
            }
        }catch (Exception e){
            logger.log(Level.SEVERE, "Exception while validating Time values", e);
            errors.add("startDate", new ActionMessage("errors.date.format"));
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
