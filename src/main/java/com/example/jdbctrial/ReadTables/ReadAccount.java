package com.example.jdbctrial.ReadTables;

import com.example.jdbctrial.InformationObjects.Stock;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ReadAccount {
//
//    private Connection connection;
    private ResultSet resultSet;

    public ResultSet read(Connection connection){
        Statement statement;

        System.out.println("reading");
        try{
            statement = connection.createStatement();
            String sql="SELECT * FROM account";
            resultSet=statement.executeQuery(sql);
        }catch (Exception exc){
            exc.printStackTrace();
        }
        return resultSet;
    }
}
