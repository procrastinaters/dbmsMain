package com.example.jdbctrial.InsertIntoTables;

import com.example.jdbctrial.Database;
import com.example.jdbctrial.InformationObjects.BoughtStocks;
import com.example.jdbctrial.InformationObjects.SoldStocks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertToSold {


    Database database=new Database();
    private String sql;
    private SoldStocks soldStocks;
    Connection connection;
    static int i;

    public void executeSql() throws SQLException {

        i=database.readSellId();

        sql="INSERT INTO soldstocks(TransId,Ticker,Username,Quantity,Sell_Price,ProfitLoss) values(?,?,?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,"SOLD-"+Integer.toString(i++));
        preparedStatement.setString(2,soldStocks.getSymbol());
        preparedStatement.setString(3,soldStocks.getUsername());
        preparedStatement.setInt(4,soldStocks.getQuantity());
//        preparedStatement.setString(5,"default");
        preparedStatement.setFloat(5,soldStocks.getSellPrice());
        preparedStatement.setDouble(6,soldStocks.getProfitLoss());
        preparedStatement.executeUpdate();
    }

    public InsertToSold (Connection connection, SoldStocks soldStocks) throws SQLException {

        this.soldStocks=soldStocks;
        this.connection=connection;
        executeSql();
        database.updateSellId(i);
    }
}
