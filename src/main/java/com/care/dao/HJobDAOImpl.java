package com.care.dao;

import com.care.exception.DataReadException;
import com.care.exception.DataWriteException;
import com.care.model.Job;
import com.care.model.Seeker;
import com.care.model.Status;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.*;

public class HJobDAOImpl implements JobDAO {
    @Override
    public int addJob(Job job) {
        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.save(job);
        }catch (Exception e){
            throw new DataWriteException(e);
        }
        return 1;
    }


    @Override
    public int expireStaleJobs() throws Exception {
        return 0;
    }

    @Override
    public int editJob(Job job) throws DataWriteException {
        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.saveOrUpdate(job);
        }
        catch (Exception e){
            throw new DataWriteException(e);
        }
        return 1;
    }

    @Override
    public Job getJob(long jobId) throws DataReadException{
        Job job = Job.emptyJob();
        try{
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            job = (Job) session.get(Job.class, jobId);
        }catch (Exception e){
            throw new DataReadException(e);
        }
        return job;

    }

    @Override
    public List<Job> getAllJobs(long postedBy) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Query query = session.createQuery("from Job where seeker.id = :seekerId and status != :status ");
        query.setLong("seekerId", postedBy);
        query.setString("status", Status.CLOSED.name());
        List<Job> jobList = query.list();
        return jobList;
    }

    /*
        Get all jobs for which this sitter is eligible to apply. All active jobs for which this SITTER doesn't have any ACTIVE or
        CLOSED application.
     */
    @Override
    public List<Job> getAllAvailableJobs(long sitterId) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Query query = session.createQuery("from Job j where j.id not in (select job.id from Application where sitter.id = :sitterId and status = :appStatus) and status = :jobStatus");
        query.setLong("sitterId", sitterId);
        query.setString("appStatus", Status.ACTIVE.name());
        query.setString("jobStatus", Status.ACTIVE.name());
        List<Job> jobs = query.list();

        return jobs;
    }
}
