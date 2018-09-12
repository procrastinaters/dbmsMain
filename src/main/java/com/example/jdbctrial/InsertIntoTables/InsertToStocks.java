package com.example.jdbctrial.InsertIntoTables;

import com.example.jdbctrial.InformationObjects.Stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertToStocks {


    private String sql;
    private Stock[] stock;
    Connection connection;


    public void executeSql(int i) {


        try {
            sql = "INSERT INTO stocks values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, stock[i].getSymbol());
            preparedStatement.setString(2, stock[i].getCompanyName());
            preparedStatement.setString(3, stock[i].getPrimaryExchange());
            preparedStatement.setString(4, stock[i].getSector());
            preparedStatement.setDouble(5, stock[i].getOpen());
            preparedStatement.setFloat(6, stock[i].getClose());
            preparedStatement.setFloat(7, stock[i].getPreviousClose());
            preparedStatement.setFloat(8, stock[i].getLatestPrice());
            preparedStatement.setDouble(9, 0);
            preparedStatement.executeUpdate();
            System.out.println("Executed");
        }catch (Exception e){

        }
    }

    public InsertToStocks (Connection connection, Stock[] stock) throws SQLException {

        this.stock=stock;
        this.connection=connection;
        for(int i=0;i<10;i++)
        {
        executeSql(i);
            System.out.println(stock[i].getSymbol());
    }}
}

