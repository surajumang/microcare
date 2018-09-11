package com.care.form;

import com.care.annotation.Number;
import com.care.annotation.StringDate;
import com.care.validation.FormBean;
import com.care.validation.FormValidator;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JobForm extends ActionForm {

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

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Number(regex = "\\d{1,3}(\\.\\d{0,2})?")
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

//    @Override
//    public void validateCustom(Map<String, String> errors) {
//        try {
//            FormValidator.validate(this, errors);
//        } catch (InvocationTargetException e) {
//            logger.log(Level.SEVERE, "Invok", e);
//        } catch (IllegalAccessException e) {
//            logger.log(Level.SEVERE, "Invok", e);
//        }
//        logger.info(errors+ "");
//        boolean dateError = errors.containsKey("startDate") || errors.containsKey("endDate");
//        //[TODO] use try catch
//        try {
//            if (! dateError ){
//                startDate +=":00";
//                endDate   +=":00";
//                Timestamp currentTime = new Timestamp(System.currentTimeMillis());
//                Timestamp startTime = Timestamp.valueOf(startDate);
//                Timestamp endTime = Timestamp.valueOf(endDate);
//                if (startTime.before(currentTime)){
//                    errors.put("currentDate", "Start Time must be greater than current Time");
//                }
//                if (endTime.before(startTime)){
//                    errors.put("startDate", "Start Time must be less than end Time");
//                }
//            }
//        }catch (Exception e){
//            logger.log(Level.SEVERE, "Exception while validating Time values", e);
//            errors.put("startDate", "Not in proper format");
//        }
//        logger.info("Done with validation");
//    }

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {

    }

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors actionErrors = new ActionErrors();
        String dateRegex = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}";

        if (hourlyPay == null){

        }
        if (! hourlyPay.matches("\\d{1,3}(\\.\\d{0,2})?")){
            actionErrors.add("hourlyPay", new ActionMessage("erorrs.number.double"));
        }
        if (startDate != null && endDate != null){
            if (! startDate.matches(dateRegex)){

            }else{

            }
            actionErrors.add("startDate", new ActionMessage("errors.date"));
        }
        if (! endDate.matches(dateRegex)){

        }
        return actionErrors;
    }

}
