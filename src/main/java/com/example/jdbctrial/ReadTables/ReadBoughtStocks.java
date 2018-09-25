package com.example.jdbctrial.ReadTables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReadBoughtStocks {
    private ResultSet resultSet;

    public ResultSet read(Connection connection, String username){
        Statement statement;

        try{
            statement = connection.createStatement();
            System.out.println(username);
            String sql="SELECT * FROM boughtstocks WHERE Username='" + username +"'";
            resultSet=statement.executeQuery(sql);
        }catch (Exception exc){
            exc.printStackTrace();
        }
        return resultSet;
    }
}
