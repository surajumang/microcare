/*
        The purpose of this class is to access the database for objects of
        type Member and nothing other than that.
*/
package com.care.dao;

import com.care.beans.Member;
import com.care.beans.MemberType;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MemberDAOImpl implements MemberDAO {
    private Logger logger = Logger.getLogger("MemberDAOImpl");
    private static MemberDAOImpl ourInstance = new MemberDAOImpl();
    private MemberDAOImpl(){

    }
    public static MemberDAOImpl getInstance(){
        return ourInstance;
    }

    public Member getMember(String email) throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        System.err.println(connection);
        Member member = new Member();
       try {
           logger.info("Connection acquired  :" );
           PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM MEMBER WHERE EMAIL=?");
           preparedStatement.setString(1, email);

           ResultSet resultSet = preparedStatement.executeQuery();
           while (resultSet.next()){
               logger.info("Row exist");
               member.setEmail(resultSet.getString("EMAIL"));
               member.setFirstName(resultSet.getString("FIRST_NAME"));
               member.setLastName(resultSet.getString("LAST_NAME"));
               member.setMemberType(MemberType.valueOf(resultSet.getString("MENBER_TYPE")));
               member.setPassword(resultSet.getString("PASSWORD"));
           }

       }catch (SQLException e){
           logger.log(Level.SEVERE, "getMember", e);
       }
        return member;
    }

    public int addMember(Member member) throws SQLException {
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

    public int editMember(int memberId, Member member) throws SQLException {
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

    public int deleteMember(int memberId) throws SQLException {
        return 0;
    }
}
