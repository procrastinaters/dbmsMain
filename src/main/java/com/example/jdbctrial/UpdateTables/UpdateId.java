package com.example.jdbctrial.UpdateTables;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateId {

    public void updateBuyId(int id, Connection connection){

        String sql;
        sql="UPDATE Id SET BuyId="+Integer.toString(id);
        try {

            Statement statement=connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateSellId(int id, Connection connection){

        String sql;
        sql="UPDATE Id SET SellId="+Integer.toString(id);
        try {

            Statement statement=connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
