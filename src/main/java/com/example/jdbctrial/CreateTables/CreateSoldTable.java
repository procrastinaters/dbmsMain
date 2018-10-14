package com.example.jdbctrial.CreateTables;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateSoldTable {

    private String setSql(){
        String sql;
        sql="CREATE TABLE IF NOT EXISTS soldstocks (" +
                "TransId varchar(20) DEFAULT NULL," +
                "RealisedProfitLoss float NOT NULL," +
                "RealisedProfitLossPer float NOT NULL," +
                "SellPrice float NOT NULL," +
                "AvgSellPrice float NOT NULL," +
                "KEY TransId (TransId)," +
                "FOREIGN KEY (TransId) REFERENCES transactionhistory (transid)" +
                ")";
        return sql;
    }

    public CreateSoldTable(Connection connection) throws SQLException {

        Statement statement = connection.createStatement();
//        statement.executeUpdate(setSql());    }
    }}

