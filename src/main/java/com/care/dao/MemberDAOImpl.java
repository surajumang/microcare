/*
        The purpose of this class is to access the database for objects of
        type Member and nothing other than that.
*/
package com.care.dao;

import com.care.model.Member;
import com.care.model.MemberType;
import com.care.model.Status;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Collections;
import java.util.logging.Logger;

public class MemberDAOImpl implements MemberDAO {
    private Logger logger = Logger.getLogger("MemberDAOImpl");

    public MemberDAOImpl(){ }

    public Member getMember(String email) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        logger.info("Connection acquired  :" );
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM MEMBER WHERE EMAIL=?");
        preparedStatement.setString(1, email);

        ResultSet resultSet = preparedStatement.executeQuery();
        Member member = Member.EMPTY_MEMBER;
        if (resultSet.next()){
            member = populateMember(resultSet);
        }
        return member;
    }

    @Override
    public Member getMember(int memberId) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        logger.info("Connection acquired  :" );
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM MEMBER WHERE ID=?");
        preparedStatement.setInt(1, memberId);

        ResultSet resultSet = preparedStatement.executeQuery();
        Member member = Member.EMPTY_MEMBER;
        if (resultSet.next()){
            member = populateMember(resultSet);
        }
        return member;
    }

    public int addMember(Member member) throws SQLException {
        logger.info(member + " ");
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO MEMBER(FIRST_NAME, LAST_NAME, PHONE, MEMBER_TYPE," +
                "EMAIL, ADDRESS, ZIP_CODE, PASSWORD) VALUES(?,?,?,?,?,?,?,?)" );
        preparedStatement.setString(1, member.getFirstName());
        preparedStatement.setString(2, member.getLastName());
        preparedStatement.setInt(3, member.getPhone());
        preparedStatement.setString(4, member.getMemberType().name());
        preparedStatement.setString(5, member.getEmail());
        preparedStatement.setString(6, member.getAddress());
        preparedStatement.setInt(7, member.getZipCode());
        preparedStatement.setString(8, Hash.createHash(member.getPassword()));

        return preparedStatement.executeUpdate();
    }

    public int editMember(int memberId, Member member) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        logger.info("Editing Member");
        PreparedStatement statement = connection.prepareStatement("UPDATE MEMBER SET FIRST_NAME=?, LAST_NAME=?, PHONE=?, ADDRESS=?, ZIP_CODE=? WHERE ID=?");
        statement.setString(1, member.getFirstName());
        statement.setString(2, member.getLastName());
        statement.setInt(3, member.getPhone());
        statement.setString(4, member.getAddress());
        statement.setInt(5, member.getZipCode());
        statement.setInt(6, memberId);

        return statement.executeUpdate();
    }

    public int deleteMember(int memberId) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE MEMBER SET STATUS = ? WHERE ID = ?");
        statement.setString(1, Status.CLOSED.name());
        statement.setInt(2, memberId);

        return statement.executeUpdate();
    }

    private Member populateMember(ResultSet resultSet) throws SQLException{
        Member member = new Member();
        logger.info("Row exist");
        member.setId(resultSet.getInt("ID"));
        member.setEmail(resultSet.getString("EMAIL"));
        member.setFirstName(resultSet.getString("FIRST_NAME"));
        member.setLastName(resultSet.getString("LAST_NAME"));
        member.setMemberType(MemberType.valueOf(resultSet.getString("MEMBER_TYPE")));
        member.setPassword(resultSet.getString("PASSWORD"));

        return member;
    }

    private static class Hash {
        private static final char[] hexArray = "0123456789ABCDEF".toCharArray();

        private static String bytesToHex(byte[] bytes) {
            char[] hexChars = new char[bytes.length * 2];
            for ( int j = 0; j < bytes.length; j++ ) {
                int v = bytes[j] & 0xFF;
                hexChars[j * 2] = hexArray[v >>> 4];
                hexChars[j * 2 + 1] = hexArray[v & 0x0F];
            }
            return new String(hexChars);
        }

        private static String createHash(String text){
            String finalHash = "";
            try{
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
                finalHash = bytesToHex(hash);
            }catch (NoSuchAlgorithmException e){
                e.printStackTrace();
            }
            return finalHash;
        }

    }
}
