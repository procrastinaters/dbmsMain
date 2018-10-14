package com.example.jdbctrial.CreateTables;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateBoughtTable {

    private String setSql(){
        String sql;
        sql="CREATE TABLE IF NOT EXISTS boughtstocks (" +
                "TransId varchar(30) PRIMARY KEY," +
                "CostPrice float NOT NULL," +
                "TotalPrice float NOT NULL," +
                "Username varchar(30) NOT NULL," +
                "StockId text NOT NULL," +
                "Quantity INTEGER NOT NULL,"+
                "DateTime Timestamp," +
                "Foreign Key (Username) references account(Username)" +
                ")";
        return sql;
    }

    public CreateBoughtTable(Connection connection) throws SQLException {
        Statement statement=connection.createStatement();
        statement.executeUpdate(setSql());    }
}