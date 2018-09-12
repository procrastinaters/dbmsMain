package com.example.jdbctrial.InsertIntoTables;

import com.example.jdbctrial.InformationObjects.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertToUser {

    private String sql;
    private User user;
    Connection connection;


    public void executeSql() throws SQLException {

        sql="INSERT INTO user values(?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,user.getEmail());
        preparedStatement.setString(2,user.getName());
        preparedStatement.setDate(3,user.getDob());
        preparedStatement.setInt(4,user.getAge());
        preparedStatement.setString(5,user.getSex());
        preparedStatement.setString(6,user.getContactNo());

        preparedStatement.executeUpdate();
    }

    public InsertToUser (Connection connection, User user) throws SQLException {

        this.user=user;
        this.connection=connection;
        executeSql();
    }
}
