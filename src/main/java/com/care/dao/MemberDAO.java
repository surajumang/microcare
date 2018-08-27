/*
        The purpose of this class is to access the database for objects of
        type Member and nothing other than that.
*/
package com.care.dao;

import com.care.beans.Member;
import com.care.beans.MemberType;
import com.care.dto.form.LoginDetails;

import java.sql.*;

public class MemberDAO {
    public static boolean addMember(Member member, Connection connection){
        int k = 0;
        try{
            if(connection == null){
                System.err.println("Null Connection");
            }
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO MEMBER" +
                    "(FIRST_NAME, LAST_NAME, PHONE, MENBER_TYPE, EMAIL, ADDRESS, ZIP_CODE, PASSWORD)" +
                    "VALUES(?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, member.getFirstName());
            preparedStatement.setString(2, member.getLastName());
            preparedStatement.setInt(3, member.getPhone());
            preparedStatement.setString(4, member.getMemberType().name());
            preparedStatement.setString(5, member.getEmail());
            preparedStatement.setString(6, member.getAddress());
            preparedStatement.setInt(7,member.getZipCode());
            preparedStatement.setString(8, member.getPassword());

            k = preparedStatement.executeUpdate();
            System.err.println("Rows affected" + k);

        }catch (SQLException e){
            e.getCause();
        }
        return k != 0;
    }

    public static Member checkMember(LoginDetails user, Connection connection){
        Member member = new Member();

        try {
            String pass = "";

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT PASSWORD FROM MEMBER WHERE EMAIL = ?");
            preparedStatement.setString(1, user.getEmail());

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                pass = resultSet.getString("PASSWORD");
                if(pass.equals(user.getPassword())){
                    member.setAddress(resultSet.getString("ADDRESS"));
                    member.setEmail(resultSet.getString("EMAIL"));
                    member.setFirstName(resultSet.getString("FIRSTNAME"));
                    String mtype = resultSet.getString("MENBER_TYPE");
                    member.setMemberType(MemberType.valueOf(mtype));

                }
            }

        }catch(SQLException e){
            e.getCause();
        }
        return member;
    }
}
