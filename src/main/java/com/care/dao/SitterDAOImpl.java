/*

*/

package com.care.dao;

import com.care.model.Member;
import com.care.model.Sitter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        Member member = getMember(memberId);
        Sitter sitter = new Sitter();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM SITTER WHERE ID =?");
        statement.setInt(1, memberId);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()){
            sitter.setId(resultSet.getInt("ID"));

        }
        return ;
    }
}
