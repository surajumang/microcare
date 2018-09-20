package com.care.exception;

public class JobNotPostedByUserException extends Exception {
    public JobNotPostedByUserException(String s) {
        super(s);
    }
    public JobNotPostedByUserException(Throwable throwable){
        super(throwable);
    }
}
