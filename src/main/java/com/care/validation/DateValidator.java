package com.care.validation;

/*This class will read the value of Date annotation and verify that the
 *Form object's value conforms to the regex provided.

*/

import com.care.annotations.Date;

public class DateValidator extends Validator {
    private static final DateValidator ourInstance = new DateValidator();

    public static DateValidator getInstance() {
        return ourInstance;
    }

    private DateValidator() {
    }


    public boolean validate(String value, Class<>) {
        return false;
    }
}
