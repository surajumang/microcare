package com.care.service;

import com.care.dto.form.ApplicationForm;

public interface ApplicationService extends Service {
    /*
    Methods related to Application. Add, Edit, Close,
     */
    int add(ApplicationForm applicationForm);
    int edit(ApplicationForm applicationForm);
    int delete(int applicationId);

}
