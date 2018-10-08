package com.care.dao;

import com.care.model.Application;
import com.care.model.Job;
import com.care.model.Seeker;

import java.util.Set;

public interface SeekerDAO extends DAO {

    int addSeeker(Seeker seeker) ;

    Seeker getSeeker(long seekerId) ;

    Set<Seeker> getSeekerByEmail(String email);

}
