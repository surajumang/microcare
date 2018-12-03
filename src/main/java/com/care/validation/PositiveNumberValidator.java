package com.care.validation;

import com.mysql.jdbc.StringUtils;

public class PositiveNumberValidator extends NumberValidator {

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
        }catch (IllegalArgumentException e){

        }
        return validity;
    }
}
