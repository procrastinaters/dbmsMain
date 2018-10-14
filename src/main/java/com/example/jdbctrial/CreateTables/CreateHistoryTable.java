package com.example.jdbctrial.CreateTables;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateHistoryTable {

    private String setSql(){
        String sql;
        sql="CREATE TABLE IF NOT EXISTS soldstocks (" +
                "TransId varchar(20) NOT NULL," +
                "Ticker varchar(20) NOT NULL," +
                "Username VARCHAR(30) DEFAULT NULL," +
                "Quantity int(11) NOT NULL," +
                "DateTime timestamp," +
                "Sell_Price INT(11) NOT NULL,"+
                "ProfitLoss varchar(10) DEFAULT NULL," +
                "PRIMARY KEY (TransId)" +
                ")";
        return sql;
    }

    public CreateHistoryTable(Connection connection) throws SQLException {

        Statement statement=connection.createStatement();
        try{
        statement.executeUpdate(setSql());}catch(Exception e){
            e.printStackTrace();
        }
    }
}
