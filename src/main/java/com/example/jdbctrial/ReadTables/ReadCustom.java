package com.example.jdbctrial.ReadTables;

import com.example.jdbctrial.InformationObjects.Admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

    public ArrayList<Admin> readAdmin (Connection connection) throws SQLException {
        String sql="SELECT Username FROM account";
        Statement statement=connection.createStatement();
        ResultSet usernames;
        usernames=statement.executeQuery(sql);
        ArrayList<String> username=new ArrayList<>();
        usernames.first();
        do{
            username.add(usernames.getString("Username"));
        }while(usernames.next());
        ArrayList<Admin> admin=new ArrayList<>();
        int i=0;
        do{
            sql="select b1.username as Username,b as boughtNo,s as soldNo,balance from (select username,count(quantity) as b from boughtstocks group by username) as b1," +
                    "(select username,count(quantity) as s from soldstocks group by username) as s1,account as a where b1.username=s1.  username and a.username=b1.username and b1.username='" + username.get(i) +"'";
            try {
                this.resultSet = statement.executeQuery(sql);
                this.resultSet.first();
                admin.add(new Admin(username.get(i),
                        this.resultSet.getInt("boughtNo"),
                        this.resultSet.getInt("soldNo"),
                        this.resultSet.getFloat("balance")));
            }catch (Exception e){
                admin.add(new Admin(username.get(i),
                        0,0,1000f));

            }
            i++;
        }while(i<username.size());

        return admin;
    }
}