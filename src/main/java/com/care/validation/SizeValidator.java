package com.care.validation;

public class SizeValidator extends Validator {

    private int min;
    private int max;
    private String message;

    public SizeValidator(int min, int max, String message) {
        this.min = min;
        this.max = max;
        this.message = message;
    }

    @Override
    public <T> boolean isValid(T value) {
        if (value == null){
            return true;
        }
        String userValue = (String) value;
        boolean validity = false;
        if (userValue.length() >= min && userValue.length() <= max)
            validity = true;
        return validity;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
