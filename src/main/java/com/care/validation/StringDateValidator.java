package com.care.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*StringDateValidator is a RegexValidator but it is also required that it uses the DateValidator
 */
public class StringDateValidator extends RegexValidator {

    private static final String regex = "\\d{4}-\\d{2}-\\d{2}";
    DateValidator dateValidator = new DateValidator();

    public StringDateValidator(String message){
        super(regex, message);
    }

    @Override
    public <T> boolean isValid(T value) {
        boolean validity = super.isValid(value);
        if (validity){
            validity = dateValidator.isValid(value);
        }
        return validity;
    }
}
