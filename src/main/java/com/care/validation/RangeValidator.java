package com.care.validation;

import org.apache.commons.lang3.StringUtils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RangeValidator extends Validator {
    private Logger logger = Logger.getLogger(this.getClass().getName());
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
        if (StringUtils.isBlank(userValue)){
            return true;
        }
        try {
            double number = Double.parseDouble(userValue);
            if (number >= min && number <= max){
                validity = true;
            }
        }catch (Exception e){
            logger.log(Level.INFO, "Invalid value for Number", e);
        }
        return validity;
    }
}
