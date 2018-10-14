package com.example.jdbctrial.InsertIntoTables;

import com.example.jdbctrial.Database;
import com.example.jdbctrial.InformationObjects.Account;
import com.example.jdbctrial.InformationObjects.BoughtStocks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class InsertToBought {

    Database database=new Database();
    private String sql;
    private BoughtStocks boughtStocks;
    Connection connection;
    static int i;

    public void executeSql() throws SQLException {

        i=database.readBuyId();

        sql="INSERT INTO boughtstocks(TransId,CostPrice,TotalPrice,Username,StockId,Quantity) values(?,?,?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,"BUY-"+ Integer.toString(i++));
        preparedStatement.setFloat(2,boughtStocks.getPricePerShare());
        preparedStatement.setFloat(3,boughtStocks.getPrice());
        preparedStatement.setString(4,boughtStocks.getUsername());
        preparedStatement.setString(5,boughtStocks.getStock());
        preparedStatement.setInt(6,boughtStocks.getQuantity());
//        preparedStatement.setString(7,"default");
        preparedStatement.executeUpdate();
    }

    public InsertToBought (Connection connection, BoughtStocks boughtStocks) throws SQLException {

        this.boughtStocks=boughtStocks;
        this.connection=connection;
        executeSql();
        database.updateBuyId(i);

    }

}