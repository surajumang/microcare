package com.care.service;

import com.care.dto.form.RegistrationForm;

public class SitterServiceImpl implements SitterService {
    private static SitterServiceImpl ourInstance = new SitterServiceImpl();
    public static <T extends Service> T getInstance(){
        return (T)ourInstance;
    }

    private SitterServiceImpl(){

    }
    public boolean registerMember(RegistrationForm registrationForm) {
        int rowsAffected = 0;

        return rowsAffected == 1;
    }
}
