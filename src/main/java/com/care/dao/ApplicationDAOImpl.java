package com.care.dao;

import com.care.exception.DataReadException;
import com.care.exception.DataWriteException;
import com.care.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.xml.crypto.Data;

import org.hibernate.Query;
import org.hibernate.Session;

public class ApplicationDAOImpl implements ApplicationDAO {
    @Override
    public int addApplication(Application application)  {
        /*
        Make sure that application has valid refrences to Sitter and Job. Otherwise this addition will not be meaningful.
         */
        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.save(application);
        }catch (Exception e){
            throw new DataWriteException(e);
        }

        return 1;
    }

    @Override
    public Application getApplication(long applicationId){
        Application application = Application.emptyApplication();
        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            application = (Application) session.get(Application.class, applicationId);
        }catch (Exception e){
            throw new DataReadException(e);
        }
        return application;
    }

    @Override
    public List<Application> getAllApplications(long sitterId)  {

//        Sitter sitter = (Sitter) session.get(Sitter.class, sitterId);
//        Set<Application> applications = Collections.emptySet();
//        if (sitter != null){
//            applications = sitter.getApplications();
//        }
        List<Application> applications = new ArrayList<>();
        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Query query = session.createQuery("from Application where sitter.id= :sitterId and status != :status");
            query.setLong("sitterId", sitterId);
            query.setString("status", Status.CLOSED.name());

            applications = query.list();

            Application app = getApplication(applications.get(0).getId());
        }catch (Exception e){
            throw new DataReadException(e);
        }

        return applications;
    }

    /*
    This operation is straightforward now. Fetching a job will do the work, which is already in JobDAO.
     */
    @Override
    public Set<Application> getAllApplicationsOnJob(long jobId) {
        Set<Application> applications = Collections.emptySet();
        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Job job = (Job) session.get(Job.class, jobId);
            applications = job.getApplications();
        }catch (Exception e){
            throw new DataReadException(e);
        }

        return applications;
    }

}
