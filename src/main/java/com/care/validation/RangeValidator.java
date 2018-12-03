package com.care.validation;

import com.mysql.jdbc.StringUtils;

public class RangeValidator extends Validator {
    private long min;
    private long max;

    public RangeValidator(long min, long max, String message) {
        super(message);
        this.min = min;
        this.max = max;
    }

    @Override
    public <T> boolean isValid(T value) {
        boolean validity = false;

        String userValue = (String) value;
        if (StringUtils.isEmptyOrWhitespaceOnly(userValue)){
            return true;
        }
        try {
            double number = Double.parseDouble(userValue);
            if (number >= min && number <= max){
                validity = true;
            }
        }catch (Exception e){

        }
        return validity;
    }
}
