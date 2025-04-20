package com.hexaware.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnetion {

    public static Connection connection;

    public static Connection getConnection() throws Exception
    {
        String url = PropertyUtil.getPropertyString();
        String[] userCredentials = PropertyUtil.getUserNameAndPassword();

        try{
            connection = DriverManager.getConnection(url,userCredentials[0],userCredentials[1]);
            
        }catch(Exception e){
            throw new SQLException("Error in making the connectoin " + e.getMessage());
        }

        return connection;
    }
}
