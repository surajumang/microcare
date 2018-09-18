package com.care.form;

import com.care.annotation.Name;
import com.care.annotation.Number;
import com.care.annotation.StringDate;
import com.care.validation.FormBean;
import com.care.validation.FormValidator;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Number(regex = "\\d{1,3}(\\.\\d{0,2})?", message = "errors.amount")
    public String getHourlyPay() {
        return hourlyPay;
    }

    public void setHourlyPay(String hourlyPay) {
        this.hourlyPay = hourlyPay;
    }

    @StringDate
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    @StringDate
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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
        logger.info(errors+ "");
        boolean flag = true;
        // If the dates are not okay then a parse exception will be generated and that will be handled here.
        try {
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            Timestamp startTime = Timestamp.valueOf(startDate + ":00");
            Timestamp endTime = Timestamp.valueOf(endDate + ":00");

            if (startTime.before(currentTime)){
                errors.add("currentDate", new ActionMessage("errors.startdate.greater",  "CurrentDate"));
                flag = false;
            }
            if (endTime.before(startTime)){
                errors.add("startDate", new ActionMessage("errors.startdate.less", "EndDate"));
                flag = false;
            }
        }catch (Exception e){
            logger.log(Level.SEVERE, "Exception while validating Time values", e);
            errors.add("startDate", new ActionMessage("errors.date.format"));

            flag = false;
        }
        if (flag && errors.isEmpty()){
            startDate += ":00";
            endDate += ":00";
        }
        logger.info("Done with validation" + errors);
        return errors;
    }
}
