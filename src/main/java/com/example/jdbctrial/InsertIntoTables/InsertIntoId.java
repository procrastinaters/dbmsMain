package com.example.jdbctrial.InsertIntoTables;

import com.example.jdbctrial.InformationObjects.SoldStocks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertIntoId {


    private String sql;
    Connection connection;
    static int i;
    int buyId,sellId;

    public void executeSql() throws SQLException {

        sql="UPDATE Id INTO SET ";

        Statement statement = connection.prepareStatement(sql);
        statement.executeUpdate(sql);
    }

    public InsertIntoId (Connection connection, int buyId, int sellId) throws SQLException {

        this.connection=connection;
        this.buyId=buyId;
        this.sellId=sellId;
        executeSql(

        );
    }
}
