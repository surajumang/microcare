/*
        The purpose of this class is to access the database for objects of
        type Member and nothing other than that.
*/
package com.care.dao;

import com.care.beans.Member;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

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
            e.getErrorCode();
            System.err.println("Error");
            e.printStackTrace();
        }
        return k != 0;
    }

}
