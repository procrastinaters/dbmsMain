package com.example.jdbctrial.UpdateTables;

import com.example.jdbctrial.InformationObjects.Stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateStocks {

     Connection connection;
    String sql;
    Stock[] stock;

    public void executeSQL(int i){
        sql="UPDATE stocks " +
                "SET open=" +
                stock[i].getOpen().toString() +
                ", prevClose=" +
                stock[i].getPreviousClose().toString() +
                " WHERE symbol=" +
                "\'"+
                stock[i].getSymbol() +
                "\'";
        try {

            Statement statement=connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public UpdateStocks(Connection connection, Stock[] stock){

        this.stock=stock;
        this.connection=connection;

        for(int i=0;i<10;i++){

            executeSQL(i);
            System.out.println(stock[i].getOpen());

        }


    }

}
