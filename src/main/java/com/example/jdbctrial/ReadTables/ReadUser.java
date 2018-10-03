package com.example.jdbctrial.ReadTables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReadUser {

    private ResultSet resultSet;

    public ResultSet read(Connection connection,String user){
        Statement statement;

        try{
            statement = connection.createStatement();
            String sql="SELECT * FROM user WHERE Email='" + user + "'";
            resultSet=statement.executeQuery(sql);
        }catch (Exception exc){
            exc.printStackTrace();
        }
        return resultSet;
    }
}