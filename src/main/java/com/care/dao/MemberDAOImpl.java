/*
        The purpose of this class is to access the database for objects of
        type Member and nothing other than that.
*/
package com.care.dao;

import com.care.beans.Member;
import com.care.beans.MemberType;
import com.care.dto.form.LoginDetails;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MemberDAOImpl implements MemberDAO {


    private Logger logger = Logger.getLogger("MemberDAOImpl");

    public Member getMember(String email, String password) {
        Connection connection = ConnectionUtil.getConnection();
        Member member = null;
       try {
           PreparedStatement preparedStatement = connection.prepareStatement("SELECT PASSWORD FROM MEMBER WHERE EMAIL=?");
           preparedStatement.setString(1, email);

           ResultSet rs = preparedStatement.executeQuery();

       }catch (SQLException e){
           logger.log(Level.SEVERE, "getMember", e);
       }
        return member;
    }

    public int addMember(Member member) {
        Connection connection = ConnectionUtil.getConnection();

        int rowsAffected = 0;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO MEMBER(FIRST_NAME, LAST_NAME, PHONE, MEMBER_TYPE," +
                    "EMAIL, ADDRESS, ZIP_CODE) VALUES(?,?,?,?,?,?,?)");
            preparedStatement.setString(1, member.getFirstName());
            preparedStatement.setString(2, member.getLastName());
            preparedStatement.setInt(3, member.getPhone());
            preparedStatement.setString(4, member.getMemberType().name());
            preparedStatement.setString(5, member.getEmail());
            preparedStatement.setString(6, member.getAddress());
            preparedStatement.setInt(7, member.getZipCode());

            rowsAffected = preparedStatement.executeUpdate();
        }catch (SQLException e){
            logger.log(Level.SEVERE, "addMember", e);
        }
        return rowsAffected;
    }

    public int editMember(int memberId, Member member) {
        Connection connection = ConnectionUtil.getConnection();

        int rowsAffected = 0;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO MEMBER(FIRST_NAME, LAST_NAME, PHONE, MEMBER_TYPE," +
                    "EMAIL, ADDRESS, ZIP_CODE) VALUES(?,?,?,?,?,?,?) WHERE ID = ?" );
            preparedStatement.setString(1, member.getFirstName());
            preparedStatement.setString(2, member.getLastName());
            preparedStatement.setInt(3, member.getPhone());
            preparedStatement.setString(4, member.getMemberType().name());
            preparedStatement.setString(5, member.getEmail());
            preparedStatement.setString(6, member.getAddress());
            preparedStatement.setInt(7, member.getZipCode());
            preparedStatement.setInt(8, memberId);

            rowsAffected = preparedStatement.executeUpdate();
        }catch (SQLException e){
            logger.log(Level.SEVERE, "addMember", e);
        }
        return rowsAffected;
    }

    public int deleteMember(int memberId) {
        return 0;
    }
}
