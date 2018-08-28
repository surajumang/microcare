package com.care.validation;

/*This class will read the value of Date annotation and verify that the
 *Form object's value conforms to the regex provided.

*/

import com.care.annotations.Date;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.util.Map;

public class DateValidator extends Validator {
    private static final DateValidator ourInstance = new DateValidator();

    public static DateValidator getInstance() {
        return ourInstance;
    }

    private DateValidator() {
    }

    @Override
    public void validate(String value, Annotation a, HttpServletRequest req) {

        String errvalue = "";
        boolean flag = false;
        Date date = (Date)a;

        Map<String, String> myErrors = (Map<String, String>)req.getAttribute("errors");

        if(value == null && date.required()){
            errvalue += "Can't be Null";
            flag =true;
        }
        if(!value.matches(date.pattern())){
            errvalue += "Not in proper format";
            flag = true;
        }
        if(flag)
            myErrors.put("date", errvalue);

    }
}
