package com.example.jdbctrial.ReadTables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReadCustom {
    private ResultSet resultSet;

    public ResultSet readUnion(Connection connection, String username){
        Statement statement;

        System.out.println("reading");
        try{
            statement = connection.createStatement();
            String sql="select TransId," +
                    "DateTime as DateTime," +
                    "StockId as symbol," +
                    "Quantity," +
                    "CostPrice as price," +
                    "TotalPrice as PL " +
                    "from boughtstocks " +
                    "WHERE Username='" +
                     username +
                    "' union all " +
                    "select TransId," +
                    "DateTime as DateTime," +
                    "Ticker as symbol," +
                    "Quantity," +
                    "Sell_Price as price," +
                    "ProfitLoss as PL " +
                    "from soldstocks " +
                    "WHERE Username='" +
                    username +
                    "' order by DateTime";
            resultSet=statement.executeQuery(sql);
        }catch (Exception exc){
            exc.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet readSellPrice (Connection connection, String username, String stockid) throws SQLException {

        String sql="SELECT sum(Sell_Price) as Sell_Price FROM soldstocks WHERE Username='" + username + "' and Ticker='" + stockid + "'";
        Statement statement=connection.createStatement();

        return statement.executeQuery(sql);
    }
}
