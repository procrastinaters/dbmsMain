package com.example.jdbctrial.ReadTables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReadCustom {
    private ResultSet resultSet;

    public ResultSet readUnion(Connection connection, String username){
        Statement statement;

        System.out.println("reading");
        try{
            statement = connection.createStatement();
            String sql="select TransId," +
                    "StockId as symbol," +
                    "Quantity," +
                    "CostPrice as price," +
                    "TotalPrice as PL " +
                    "from boughtstocks " +
                    "WHERE Username='" +
                     username +
                    "' union all " +
                    "select TransId," +
                    "Ticker as symbol," +
                    "Quantity," +
                    "Sell_Price as price," +
                    "ProfitLoss as PL " +
                    "from transactionhistory " +
                    "WHERE Username='" +
                    username +
                    "' order by TransId";
            resultSet=statement.executeQuery(sql);
        }catch (Exception exc){
            exc.printStackTrace();
        }
        return resultSet;
    }
}
