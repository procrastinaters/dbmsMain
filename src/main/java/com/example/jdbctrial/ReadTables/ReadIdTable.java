package com.example.jdbctrial.ReadTables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReadIdTable {

    public int readBuyId(Connection connection){

        String sql;
        sql="SELECT * FROM Id";
        try {

            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);
            resultSet.first();
            return (resultSet.getInt("BuyId"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int readSellId(Connection connection){

        String sql;
        sql="SELECT * FROM Id";
        try {

            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);
            resultSet.first();
            return (resultSet.getInt("SellId"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
