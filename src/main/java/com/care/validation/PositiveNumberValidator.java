package com.care.validation;

import com.mysql.jdbc.StringUtils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PositiveNumberValidator extends NumberValidator {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    private boolean includeZero;
    public PositiveNumberValidator(boolean includeZero, String message) {
        super(message);
        this.includeZero = includeZero;
    }

    /*First check if it is a proper NUMBER or not, If it is a number then
     *check if it is a positive number or not.
     */
    @Override
    public <T> boolean isValid(T value) {
        if (! super.isValid(value))
            return false;
        String userValue = (String) value;
        boolean validity = false;
        try{
            double number = Double.parseDouble(userValue);
            validity = includeZero ? number >= 0 : number > 0;
        }catch (Exception e){
            logger.log(Level.INFO, "Invalid value for number", e);
        }
        return validity;
    }
}
