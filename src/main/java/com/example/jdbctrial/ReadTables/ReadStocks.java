package com.example.jdbctrial.ReadTables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReadStocks {
    private ResultSet resultSet;

    public ResultSet read(Connection connection){
        Statement statement;

        System.out.println("reading");
        try{
            statement = connection.createStatement();
            String sql="SELECT * FROM stocks";
            resultSet=statement.executeQuery(sql);
        }catch (Exception exc){
            exc.printStackTrace();
        }
        return resultSet;
    }
}
