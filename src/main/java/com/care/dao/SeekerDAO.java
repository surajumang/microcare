package com.care.dao;

public interface SeekerDAO extends MemberDAO {
    int postJob();
    int editJob();
    int deleteJob();

}
