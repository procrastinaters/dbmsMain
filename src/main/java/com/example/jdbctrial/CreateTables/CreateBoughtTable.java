package com.example.jdbctrial.CreateTables;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateBoughtTable {

    private String setSql(){
        String sql;
        sql="CREATE TABLE IF NOT EXISTS boughtstocks (" +
                "TransId bigint(20) DEFAULT NULL," +
                "RealisedProfitLoss float NOT NULL," +
                "RealisedProfitLossPer float NOT NULL," +
                "UnrealisedProfitLoss float NOT NULL," +
                "UnrealisedProfitLossPer float NOT NULL," +
                "CostPrice float NOT NULL," +
                "AvgCostPrice float NOT NULL," +
                "KEY TransId (TransId)," +
                "FOREIGN KEY(TransId) REFERENCES transactionhistory(transid)" +
                ")";
        return sql;
    }

    public CreateBoughtTable(Connection connection) throws SQLException {

        Statement statement=connection.createStatement();
        statement.executeUpdate(setSql());    }
}
