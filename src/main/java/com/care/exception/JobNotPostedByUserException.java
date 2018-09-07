package com.care.exception;

public class JobNotPostedByUserException extends IllegalAccessException {
    public JobNotPostedByUserException(String s) {
        super(s);
    }
}
