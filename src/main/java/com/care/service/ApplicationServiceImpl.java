package com.care.service;

import com.care.dto.form.ApplicationForm;

public class ApplicationServiceImpl implements ApplicationService {

    private static ApplicationServiceImpl ourInstance = new ApplicationServiceImpl();

    public static <T extends Service> T getInstance() {
        return (T)ourInstance;
    }

    private ApplicationServiceImpl() {
    }

    public int add(ApplicationForm applicationForm) {
        // call DAO for the operation. Do a mapping between applicationBean and Application form
        return 0;
    }

    public int edit(ApplicationForm applicationForm) {
        /*
        Check if the user which requests to edit this application is the owner of that application.
         */
        return 0;
    }

    public int delete(int applicationId) {
        /*
        Check if user is allowed to perform this operation.
        A user can delete only Applications which he has created.
         */
        return 0;
    }

}
