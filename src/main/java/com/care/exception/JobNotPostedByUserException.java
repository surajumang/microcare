package com.care.exception;

public class JobNotPostedByUserException extends RuntimeException{
    public JobNotPostedByUserException() {
    }
    public JobNotPostedByUserException(String s) {
        super(s);
    }
    public JobNotPostedByUserException(Throwable throwable){
        super(throwable);
    }
}
