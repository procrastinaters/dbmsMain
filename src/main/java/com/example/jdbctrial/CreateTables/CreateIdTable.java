package com.example.jdbctrial.CreateTables;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateIdTable {

    private String setSql(){
        String sql;
        sql="CREATE TABLE Id(" +
                "BuyId INTEGER," +
                "SellId INTEGER" +
                ")";
        return sql;
    }

    public CreateIdTable(Connection connection){

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(setSql());
            statement.executeUpdate("INSERT INTO Id VALUES(0,0)");
        }catch(Exception e){

        }
    }
}