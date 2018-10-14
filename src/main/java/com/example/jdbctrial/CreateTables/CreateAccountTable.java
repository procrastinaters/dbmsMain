package com.example.jdbctrial.CreateTables;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;


public class CreateAccountTable {

    private String setSql() {
        String sql;
        sql="CREATE TABLE IF NOT EXISTS account(" +
                "Username varchar(30) NOT NULL," +
                "Password varchar(15) NOT NULL," +
                "Balance float NOT NULL DEFAULT 1000," +
                "PRIMARY KEY (Username)" +
                ")";
        return sql;
    }

    public CreateAccountTable(Connection connection) throws SQLException {

        Statement statement=connection.createStatement();
        statement.executeUpdate(setSql());
    }
}
