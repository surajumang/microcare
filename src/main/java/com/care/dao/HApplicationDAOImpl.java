package com.care.dao;

import com.care.model.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;

public class HApplicationDAOImpl implements ApplicationDAO {
    @Override
    public int addApplication(Application application) throws Exception {
        /*
        Make sure that application has valid refrences to Sitter and Job. Otherwise this addition will not be meaningful.
         */
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.save(application);

        return 1;
    }

    @Override
    public Application getApplication(long applicationId) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Application application = (Application) session.get(Application.class, applicationId);
        if (application == null){
            application = Application.emptyApplication();
        }
        return application;
    }

    @Override
    public List<Application> getAllApplications(long sitterId) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        Sitter sitter = (Sitter) session.get(Sitter.class, sitterId);
//        Set<Application> applications = Collections.emptySet();
//        if (sitter != null){
//            applications = sitter.getApplications();
//        }

        Query query = session.createQuery("from Application where sitter.id= :sitterId and status != :status");
        query.setLong("sitterId", sitterId);
        query.setString("status", Status.CLOSED.name());

        List<Application> applications = query.list();
        if (applications == null){
            applications = Collections.emptyList();
        }
        Application app = getApplication(applications.get(0).getId());
        return applications;
    }

    /*
    This operation is straightforward now. Fetching a job will do the work, which is already in JobDAO.
     */
    @Override
    public Set<Application> getAllApplicationsOnJob(long jobId) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Job job = (Job) session.get(Job.class, jobId);
        Set<Application> applications = Collections.emptySet();
        if (job != null){
            applications = job.getApplications();
        }
        return applications;
    }
    //[todo] set  appropriate status
    @Override
    public int setApplicationStatus(long applicationId, Status status) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Application application = (Application) session.get(Application.class, applicationId);
        if (application != null){
            application.setStatus(status);
        }
        return 1;
    }
    /*
    Mainly used while a sitter has closed his account. We need to set all his Application's status to EXPIRED
    There is a good possibility that it should be put inside SitterDAO.
     */
    @Override
    public int setAllApplicationsStatusBySitter(long sitterId, Status status) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Sitter sitter = (Sitter)session.get(Sitter.class, sitterId);
        if (sitter != null){
            Set<Application> applications = sitter.getApplications();
            for (Application application : applications) {
                application.setStatus(status);
            }
        }
        return 1;
    }

    /*
        This is used for marking all applications as EXPIRED when a job gets deleted. Probably should be moved to
        the method which is taking care of closing the job.
     */
    @Override
    public int setAllApplicationStatusByJob(long jobId, Status status) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Job job = (Job)session.get(Job.class, jobId);
        Set<Application> applications = Collections.emptySet();
        if (job != null){
            applications = job.getApplications();
            for (Application application: applications){
                application.setStatus(status);
            }
        }
        return 1;
    }
    /*
    If seeker gets deleted, then all his jobs and applications on them needs to be deleted.
    Can be optimized.[todo]
     */
    @Override
    public int setAllApplicationsOnJobsPostedBy(long postedBy, Status status) throws Exception {
        SeekerDAO seekerDAO = DAOFactory.get(HSeekerDAOImpl.class);
        Seeker seeker = seekerDAO.getSeeker(postedBy);
        for (Job job : seeker.getJobs()) {
            setAllApplicationStatusByJob(job.getId(), status);
            //job.setStatus(status);
        }
        return 1;
    }
}
