package com.care.form;

import com.care.annotation.Number;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

public class ApplicationForm extends ActionForm {

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

    @Number(required = true, regex = "\\d{1,3}(\\.\\d{1,2})?", message = "Format: DDD.DD")
    public String getExpectedPay() {
        return expectedPay;
    }

    public void setExpectedPay(String expectedPay) {
        this.expectedPay = expectedPay;
    }

//    @Override
//    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
//        ActionErrors actionErrors = new ActionErrors();
//
//        if (expectedPay == null){
//            actionErrors.add("expectedPay", new ActionMessage("errors.number"));
//        }
//        if (expectedPay != null && expectedPay.matches("\\d{1,3}(\\.\\d{1,2})?")){
//            actionErrors.add("expectedPay", new ActionMessage("errors.number"));
//        }
//        return actionErrors;
//    }

    @Override
    public String toString() {
        return "ApplicationForm{" +
                "jobId='" + jobId + '\'' +
                ", sitterId='" + sitterId + '\'' +
                ", expectedPay='" + expectedPay + '\'' +
                '}';
    }

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
    }

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        return super.validate(mapping, request);
    }
}
