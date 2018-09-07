package com.care.dao;

import com.care.model.*;
import com.care.service.ObjectMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SeekerDAOImpl extends MemberDAOImpl implements SeekerDAO {
    private Logger logger = Logger.getLogger("SeekerDAOImpl");

    public SeekerDAOImpl(){ }

    @Override
    public int addSeeker(Seeker seeker) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        addMember(seeker);
        seeker.setId(getMember(seeker.getEmail()).getId());
        PreparedStatement statement = connection.prepareStatement("INSERT INTO SEEKER(ID, NO_CHILDREN, SPOUSE_NAME)" +
                "VALUES(?,?,?)");
        statement.setInt(1,seeker.getId());
        statement.setInt(2, seeker.getNumberOfChildren());
        statement.setString(3, seeker.getSpouseName());

        return statement.executeUpdate();
    }

    @Override
    public Seeker getSeeker(int seekerId) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        Member member = getMember(seekerId);
        Seeker seeker = new Seeker();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM SEEKER WHERE ID = ?" );
        statement.setInt(1, seekerId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()){
            seeker.setNumberOfChildren(resultSet.getInt("NO_CHILDREN"));
            seeker.setSpouseName(resultSet.getString("SPOUSE_NAME"));
        }
        ObjectMapper.mapObject(member, seeker, false);
        logger.info(seeker + "");

        return seeker;
    }

    @Override
    public List<Seeker> getSeekerByEmail(String email) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        logger.info("Fetching seekers from DB");
        List<Seeker> seekers = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT SEEKER.ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE, ZIP_CODE, SPOUSE_NAME, NO_CHILDREN  FROM SEEKER JOIN MEMBER ON MEMBER.ID=SITTER.ID WHERE MEMBER.EMAIL LIKE ? ");
        statement.setString(1, "%" + email + "%");

        logger.info(statement.toString());
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            seekers.add(populateSitter(resultSet));
        }
        return null;
    }

    @Override
    public int editSeeker(int seekerId, Seeker seeker) throws SQLException {
        editMember(seekerId, seeker);
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE SEEKER SET NO_CHILDREN = ?, SPOUSE_NAME=? WHERE ID=?");
        statement.setInt(1, seeker.getNumberOfChildren());
        statement.setString(2, seeker.getSpouseName());
        statement.setInt(3, seekerId);

        return statement.executeUpdate();
    }

    public List<Job> listAllMyJobs(int postedBy) throws SQLException {
        JobDAO jobDAO = DAOFactory.get(JobDAOImpl.class);
        return jobDAO.getAllAvailableJobs(postedBy);
    }

    public List<Application> listAllApplicationsOnMyJob(int jobId) throws SQLException {
        ApplicationDAO applicationDAO = DAOFactory.get(ApplicationDAOImpl.class);
        //return applicationDAO.getAllApplicationsOnJob(jobId);
        return null;
    }
    private Seeker populateSitter(ResultSet resultSet) throws SQLException{
        Seeker seeker = new Seeker();

        seeker.setPhone(resultSet.getInt("PHONE"));
        seeker.setId(resultSet.getInt("SITTER.ID"));
        seeker.setZipCode(resultSet.getInt("ZIP_CODE"));
        seeker.setSpouseName(resultSet.getString("SPOUSE_NAME"));
        seeker.setAddress(resultSet.getString("ADDRESS"));
        seeker.setNumberOfChildren(resultSet.getInt("NO_CHILDREN"));

        return seeker;
    }
}
