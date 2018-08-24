package com.care.validator;

/*This class will read the value of Date annotation and verify that the
 *Form object's value conforms to the regex provided.

*/

import java.lang.annotation.Annotation;

public class DateValidator extends Validator {
    private static final DateValidator ourInstance = new DateValidator();

    public static DateValidator getInstance() {
        return ourInstance;
    }

    private DateValidator() {
    }

    @Override
    public void validate(String value, Annotation a) {

    }
}
