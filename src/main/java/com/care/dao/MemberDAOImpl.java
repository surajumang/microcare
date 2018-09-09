/*
        The purpose of this class is to access the database for objects of
        type Member and nothing other than that.
*/
package com.care.dao;

import com.care.model.Member;
import com.care.model.MemberType;
import com.care.model.PasswordResetToken;
import com.care.model.Status;

import java.sql.*;
import java.util.logging.Logger;

public class MemberDAOImpl implements MemberDAO {
    private Logger logger = Logger.getLogger("MemberDAOImpl");

    public MemberDAOImpl(){ }

    public Member getMember(String email) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        logger.info("Connection acquired  :" );
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * "+
                "FROM MEMBER WHERE EMAIL=?");
        preparedStatement.setString(1, email);

        ResultSet resultSet = preparedStatement.executeQuery();
        Member member = Member.EMPTY_MEMBER;
        if (resultSet.next()){
            member = populateMember(resultSet);
            logger.info(member + "");
        }
        return member;
    }

    public Member getMember(long memberId) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        logger.info("Connection acquired  :" );
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT MEMBER.ID, FIRST_NAME, LAST_NAME, ADDRESS, ZIP_CODE, MEMBER_TYPE, EMAIL, PHONE, PASSWORD FROM MEMBER WHERE ID=?");
        preparedStatement.setLong(1, memberId);

        ResultSet resultSet = preparedStatement.executeQuery();
        Member member = Member.EMPTY_MEMBER;
        if (resultSet.next()){
            member = populateMember(resultSet);
            logger.info(member + "");
        }
        return member;
    }

    @Override
    public Member getMemberUsingToken(String token) throws SQLException {
        logger.info(token + "Getting memebr using token ");
        Connection connection = ConnectionUtil.getConnection();

        PasswordResetToken passwordResetToken = PasswordResetToken.EMPTY_TOKEN;
        PreparedStatement statement = connection.prepareStatement("SELECT MEMBER.ID, FIRST_NAME, LAST_NAME, ADDRESS, ZIP_CODE, MEMBER_TYPE, EMAIL, PHONE, PASSWORD FROM MEMBER JOIN TOKEN ON MEMBER.ID=TOKEN.ID WHERE TOKEN=?");
        statement.setString(1, token);

        ResultSet resultSet = statement.executeQuery();
        Member member = Member.EMPTY_MEMBER;
        if (resultSet.next()){
            logger.info("Found a member using token");
            member = populateMember(resultSet);
            logger.info(member + "");
        }
        return member;
    }

    @Override
    public int updatePassword(Member member) throws SQLException {
        logger.info(member + "Updating password with token ");
        Connection connection = ConnectionUtil.getConnection();

        PreparedStatement statement = connection.prepareStatement("UPDATE MEMBER SET PASSWORD=? WHERE ID = ?");
        statement.setString(1, member.getPassword());
        statement.setLong(2, member.getId());

        return statement.executeUpdate();
    }

    @Override
    public PasswordResetToken getToken(String email) throws SQLException {
        logger.info(email + "Getting token ");
        Connection connection = ConnectionUtil.getConnection();

        PasswordResetToken passwordResetToken = PasswordResetToken.EMPTY_TOKEN;
        PreparedStatement statement = connection.prepareStatement("SELECT MEMBER.ID, TOKEN, EXPIRATION_DATE, STATUS FROM MEMBER JOIN TOKEN ON MEMBER.ID=TOKEN.ID WHERE EMAIL=?");
        statement.setString(1, email);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()){
            passwordResetToken = new PasswordResetToken();
            passwordResetToken.setId(resultSet.getLong("MEMBER.ID"));
            passwordResetToken.setToken(resultSet.getString("TOKEN"));
            passwordResetToken.setExpirationDate(resultSet.getDate("EXPIRATION_DATE"));
            passwordResetToken.setStatus(Status.valueOf(resultSet.getString("STATUS")));
        }
        return passwordResetToken;
    }

    public int addToken(PasswordResetToken passwordResetToken) throws SQLException {
        logger.info(passwordResetToken + " ");
        Connection connection = ConnectionUtil.getConnection();

        PreparedStatement statement = connection.prepareStatement("INSERT INTO TOKEN(ID, TOKEN, EXPIRATION_DATE, STATUS) VALUES(?,?,?,?)");
        statement.setLong(1, passwordResetToken.getId());
        statement.setString(2, passwordResetToken.getToken());
        statement.setDate(3, passwordResetToken.getExpirationDate());
        statement.setString(4, passwordResetToken.getStatus().name());

        return statement.executeUpdate();
    }

    @Override
    public int invalidateToken(String token) throws SQLException {
        logger.info(token + " to be invalidated ");
        Connection connection = ConnectionUtil.getConnection();

        PreparedStatement statement = connection.prepareStatement("DELETE FROM TOKEN WHERE TOKEN.TOKEN=? ");
        statement.setString(1, token);

        return statement.executeUpdate();
    }

    @Override
    public int expireStaleTokens() throws SQLException {
        logger.info("BackOffice deleting tokens");
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM TOKEN WHERE EXPIRATION_DATE < ? OR STATUS = ?");

        statement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
        statement.setString(2, Status.CLOSED.name());

        logger.info("" + statement);
        return statement.executeUpdate();
    }

    public int addMember(Member member) throws SQLException {
        logger.info(member + " ");
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO MEMBER(FIRST_NAME, LAST_NAME, PHONE, MEMBER_TYPE," +
                "EMAIL, ADDRESS, ZIP_CODE, PASSWORD) VALUES(?,?,?,?,?,?,?,?)" );
        preparedStatement.setString(1, member.getFirstName());
        preparedStatement.setString(2, member.getLastName());
        preparedStatement.setLong(3, member.getPhone());
        preparedStatement.setString(4, member.getMemberType().name());
        preparedStatement.setString(5, member.getEmail());
        preparedStatement.setString(6, member.getAddress());
        preparedStatement.setLong(7, member.getZipCode());
        preparedStatement.setString(8, member.getPassword());

        return preparedStatement.executeUpdate();
    }

    public int editMember(long memberId, Member member) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        logger.info("Editing Member");
        PreparedStatement statement = connection.prepareStatement("UPDATE MEMBER SET FIRST_NAME=?, LAST_NAME=?, PHONE=?, ADDRESS=?, ZIP_CODE=? WHERE ID=?");
        statement.setString(1, member.getFirstName());
        statement.setString(2, member.getLastName());
        statement.setLong(3, member.getPhone());
        statement.setString(4, member.getAddress());
        statement.setLong(5, member.getZipCode());
        statement.setLong(6, memberId);

        return statement.executeUpdate();
    }

    public int setMemberStatus(long memberId, Status status) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE MEMBER SET STATUS = ? WHERE ID = ?");
        statement.setString(1, status.name());
        statement.setLong(2, memberId);

        return statement.executeUpdate();
    }

    private Member populateMember(ResultSet resultSet) throws SQLException{
        Member member = new Member();
        logger.info("Row exist");
        member.setId(resultSet.getLong("MEMBER.ID"));
        member.setEmail(resultSet.getString("EMAIL"));
        member.setFirstName(resultSet.getString("FIRST_NAME"));
        member.setLastName(resultSet.getString("LAST_NAME"));
        member.setMemberType(MemberType.valueOf(resultSet.getString("MEMBER_TYPE")));
        member.setAddress(resultSet.getString("ADDRESS"));
        member.setPhone(resultSet.getLong("PHONE"));
        member.setZipCode(resultSet.getLong("ZIP_CODE"));
        member.setPassword(resultSet.getString("PASSWORD"));

        return member;
    }
}
