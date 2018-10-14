package com.example.jdbctrial.ReadTables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReadCurrentStocks {
    private ResultSet resultSet;

    public ResultSet read(Connection connection, String username, String ticker){
        Statement statement;

        try{
            statement = connection.createStatement();
            System.out.println(username);
            String sql="SELECT * FROM CurrentStocks WHERE Username='" + username +"' and Ticker='" + ticker + "'";
            resultSet=statement.executeQuery(sql);
        }catch (Exception exc){
            exc.printStackTrace();
        }
        return resultSet;
    }
}
