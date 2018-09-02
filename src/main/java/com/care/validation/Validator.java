package com.care.validation;



public abstract class Validator {
    public abstract <T> boolean isValid(T value);
}
