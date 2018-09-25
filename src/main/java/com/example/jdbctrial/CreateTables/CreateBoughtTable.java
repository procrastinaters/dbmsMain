package com.example.jdbctrial.CreateTables;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateBoughtTable {

    private String setSql(){
        String sql;
        sql="CREATE TABLE IF NOT EXISTS boughtstocks (" +
                "TransId INTEGER PRIMARY KEY AUTO_INCREMENT," +
                "CostPrice float NOT NULL," +
                "TotalPrice float NOT NULL," +
                "Username text NOT NULL," +
                "StockId text NOT NULL," +
                "Quantity INTEGER NOT NULL,"+
                "QuantitySold INTEGER NOT NULL"+
                ")";
        return sql;
    }

    public CreateBoughtTable(Connection connection) throws SQLException {
        Statement statement=connection.createStatement();
        statement.executeUpdate(setSql());    }
}