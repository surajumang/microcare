package com.care.validation;

import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/*First check if the passed date is valid or not, if valid then check if it is a future date or not.
 *It needs the time values as well for the comparison.
 * In order to simply compare the dates, It is required to find some other way.
 */
public class FutureDateValidator extends DateValidator {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    FutureDateValidator(){

    }
    /*Parameter needs to be a String as the DateValidator is expecting a String.[todo]
     */
    @Override
    public <T> boolean isValid(T value) {
        boolean validity = super.isValid(value);
        if (! validity){
            return validity;
        }
        String userValue = (String) value;
        try {
            Date currentDate = new Date(System.currentTimeMillis());
            Date userDate = Date.valueOf(userValue);
            validity = compareDateWithoutTime(currentDate, userDate);

        }catch (Exception e){
            validity = false;
            logger.log(Level.INFO, "Date Validation ", e);
        }
        return validity;
    }

    private boolean compareDateWithoutTime(Date now, Date start){
        return start.before(now);
    }
}
