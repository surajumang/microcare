/*

*/

package com.care.dao;

import com.care.model.Sitter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SitterDAOImpl extends MemberDAOImpl implements SitterDAO {
    private Logger logger = Logger.getLogger("SitterDaoImpl");

    public SitterDAOImpl(){ }

    @Override
    public int addSitter(Sitter sitter) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        addMember(sitter);
        sitter.setId(getMember(sitter.getEmail()).getId());

        PreparedStatement statement = connection.prepareStatement("INSERT INTO SITTER(ID, EXPERIENCE, EXPECTED_PAY)" +
                "VALUES(?,?,?)");
        statement.setInt(1,sitter.getId());
        statement.setInt(2, sitter.getExperience());
        statement.setDouble(3, sitter.getExpectedPay());

        return statement.executeUpdate();
    }

    @Override
    public int editSitter(int sitterId, Sitter sitter) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        editMember(sitterId, sitter);

        PreparedStatement statement = connection.prepareStatement("UPDATE SITTER SET EXPERIENCE=?, EXPECTED_PAY=? WHERE ID =?");
        statement.setInt(1, sitter.getExperience());
        statement.setDouble(2, sitter.getExpectedPay());
        statement.setInt(3, sitterId);

        return statement.executeUpdate();
    }

    @Override
    public Sitter getSitter(int memberId) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        //Member member = getMember(memberId);
        Sitter sitter = new Sitter();

        PreparedStatement statement = connection.prepareStatement("SELECT SITTER.ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE, ZIP_CODE, EXPERIENCE,EXPECTED_PAY FROM SITTER JOIN MEMBER ON MEMBER.ID=SITTER.ID WHERE MEMBER.ID =?");
        statement.setInt(1, memberId);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()){
            sitter = populateSitter(resultSet);
            logger.info(sitter + " ");
        }
        return sitter;
    }

    @Override
    public List<Sitter> getSitterByEmail(String email) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        List<Sitter> sitters = new ArrayList<>();
        logger.info("Fetching sitters from DB" );
        PreparedStatement statement = connection.prepareStatement("SELECT SITTER.ID, FIRST_NAME, LAST_NAME, ADDRESS, PHONE, ZIP_CODE, EXPERIENCE,EXPECTED_PAY FROM SITTER JOIN MEMBER ON MEMBER.ID=SITTER.ID WHERE MEMBER.EMAIL LIKE ? ");
        statement.setString(1, "%" + email + "%");

        logger.info(statement.toString());
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){
            logger.info("Match found");
            sitters.add(populateSitter(resultSet));
        }
        return sitters;
    }

    private Sitter populateSitter(ResultSet resultSet) throws SQLException{
        Sitter sitter = new Sitter();

        sitter.setId(resultSet.getInt("SITTER.ID"));
        sitter.setExpectedPay(resultSet.getDouble("EXPECTED_PAY"));
        sitter.setExperience(resultSet.getInt("EXPERIENCE"));
        sitter.setFirstName(resultSet.getString("FIRST_NAME"));
        sitter.setLastName(resultSet.getString("LAST_NAME"));
        sitter.setAddress(resultSet.getString("ADDRESS"));
        sitter.setZipCode(resultSet.getInt("ZIP_CODE"));
        sitter.setPhone(resultSet.getInt("PHONE"));

        return sitter;
    }
}
