package com.example.jdbctrial.InsertIntoTables;

import com.example.jdbctrial.InformationObjects.BoughtStocks;
import com.example.jdbctrial.InformationObjects.SoldStocks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertToSold {

    private String sql;
    private SoldStocks soldStocks;
    Connection connection;
    static int i;

    public void executeSql() throws SQLException {

        sql="INSERT INTO transactionhistory values(?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,"SOLD"+Integer.toString(i++));
        preparedStatement.setString(2,soldStocks.getSymbol());
        preparedStatement.setString(3,soldStocks.getUsername());
        preparedStatement.setInt(4,soldStocks.getQuantity());
        preparedStatement.setDate(5,soldStocks.getDate());
        preparedStatement.setInt(6,soldStocks.getSellPrice());
        preparedStatement.setInt(7,soldStocks.getProfitLoss());
        preparedStatement.executeUpdate();
    }

    public InsertToSold (Connection connection, SoldStocks soldStocks) throws SQLException {

        this.soldStocks=soldStocks;
        this.connection=connection;
        executeSql();
    }
}
