package com.example.jdbctrial.CreateTables;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateUserTable {


    private String setSql(){
        String sql;
        sql="CREATE TABLE IF NOT EXISTS user(" +
                "Email varchar(30) NOT NULL," +
                "Name varchar(30) DEFAULT NULL," +
                "DOB date DEFAULT NULL," +
                "Age int(11) DEFAULT NULL," +
                "Sex char(1) DEFAULT NULL," +
                "Phone bigint(20) DEFAULT NULL," +
                "PRIMARY KEY (Email)" +
                ")";
        return sql;
    }

    public CreateUserTable(Connection connection) throws SQLException {

        Statement statement=connection.createStatement();
        statement.executeUpdate(setSql());
    }
}
