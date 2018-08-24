/*

*/
package com.care.dao;

import com.care.beans.Application;
import com.care.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class ApplicationDAO {
    public Application getApplication(String applicationID){
        Connection conn = ConnectionUtil.getConnection();

    }
}
