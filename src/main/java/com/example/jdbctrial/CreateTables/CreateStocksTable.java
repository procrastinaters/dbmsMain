package com.example.jdbctrial.CreateTables;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateStocksTable {

    private String setSql(){
        String sql;
        sql="CREATE TABLE IF NOT EXISTS stocks(" +
                "symbol text," +
                "company text, " +
                "exchng text, " +
                "sector text, " +
                "open double, " +
                "close float, " +
                "prevClose float, " +
                "price float, " +
                "per double" +
                ")";
        return sql;
    }

    public CreateStocksTable(Connection connection) throws SQLException {

        Statement statement=connection.createStatement();
        try{
        statement.executeUpdate(setSql());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
