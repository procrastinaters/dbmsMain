package com.example.jdbctrial.CreateTables;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateHistoryTable {

    private String setSql(){
        String sql;
        sql="CREATE TABLE IF NOT EXISTS transactionhistory (" +
                "TransId bigint(20) NOT NULL," +
                "Ticker varchar(20) NOT NULL," +
                "Username VARCHAR(30) DEFAULT NULL," +
                "Quantity int(11) NOT NULL," +
                "Date date NOT NULL," +
                "ProfitLoss varchar(10) DEFAULT NULL," +
                "PRIMARY KEY (TransId)," +
                "KEY Username (Username)," +
                "FOREIGN KEY (Username) REFERENCES account (username)" +
                ")";
        return sql;
    }

    public CreateHistoryTable(Connection connection) throws SQLException {

        Statement statement=connection.createStatement();
        statement.executeUpdate(setSql());    }
}
