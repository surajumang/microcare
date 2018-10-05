package com.care.validation;

/*This class will read the value of StringDate annotation and verify that the
 *Form object's value conforms to the regex provided.
 * -----------------------------------------
 * Check if the Date passed by StringDate is a proper date or not. For example 2018-99-99 will be accepted by the
 * StringDateValidator but is not a proper Date. So It is the purpose of this validator to make sure that a proper
 * date was entered by the User.
 * It is also required to make this a SuperClass of @Code FutureDateValidator if possible.
*/

import java.sql.Date;
import java.sql.Timestamp;

public class DateValidator extends Validator {

    public DateValidator() {
    }

    public <T> boolean isValid(T value) {
        boolean validity = false;
        try{
            Date date = Date.valueOf((String)value);
            validity = true;
        }catch (IllegalArgumentException e){

        }
        return validity;
    }
}
