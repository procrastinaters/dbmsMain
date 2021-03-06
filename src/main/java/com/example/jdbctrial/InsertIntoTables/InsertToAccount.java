package com.example.jdbctrial.InsertIntoTables;

import com.example.jdbctrial.InformationObjects.Account;
import com.example.jdbctrial.InformationObjects.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertToAccount {

    private String sql;
    private Account account;
    Connection connection;


    public void executeSql() throws SQLException {

        sql="INSERT INTO account(Username,Password) values(?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,account.getUsername());
        preparedStatement.setString(2,account.getPassword());
//        preparedStatement.setString(3,"default");

        preparedStatement.executeUpdate();
    }

    public InsertToAccount (Connection connection, Account account) throws SQLException {

        this.account=account;
        this.connection=connection;
        executeSql();
    }
}
