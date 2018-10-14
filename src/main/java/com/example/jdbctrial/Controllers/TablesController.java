package com.example.jdbctrial.Controllers;


import com.example.jdbctrial.Database;
import com.example.jdbctrial.InformationObjects.BoughtStocks;
import com.example.jdbctrial.InformationObjects.TransactionHistory;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class TablesController {

    @Autowired
    Database database;

    private ArrayList<TransactionHistory> transactionHistories;
    private ArrayList<BoughtStocks> boughtStocks;

    ObjectMapper mapper = new ObjectMapper();

    @MessageMapping("/transaction")
    @SendToUser("/queue/reply")
    public ArrayList<TransactionHistory> trans(String json) throws IOException, SQLException {

        JSONObject obj = new JSONObject(json);

        transactionHistories=new ArrayList<>();

        String username= obj.getString("username");
        ResultSet resultSet=database.readCustom(username);
        resultSet.first();
        try {
            do {
                transactionHistories.add(new TransactionHistory(resultSet.getString("TransId"),
                        resultSet.getString("symbol"),
                        resultSet.getInt("Quantity"),
                        resultSet.getDouble("price"),
                        resultSet.getDouble("PL"),
                        resultSet.getDate(  "DateTime")));

            } while (resultSet.next());
        }catch (Exception e){
            e.printStackTrace();
        }
        return transactionHistories;
    }

    @MessageMapping("/boughtStocks")
    @SendToUser("/queue/reply")
    public ArrayList<BoughtStocks> info(String json) throws IOException, SQLException {

        JSONObject obj = new JSONObject(json);

        boughtStocks=new ArrayList<>();


        String username= obj.getString("username");
        ResultSet resultSet=database.readBoughtStocks(username);
        resultSet.first();
        try {
            do {
                int qty=0,qty1=0;
                float sellPrice=0,pl=0;
                try{
                    ResultSet resultSet1=(database.readCurrentStocks(username,resultSet.getString("StockId")));
                    resultSet1.first();
                    qty=resultSet1.getInt("Quantity");
                    qty1=resultSet.getInt("Quantity");
                    qty1=qty1-qty;

                }catch (Exception e){
//                  qty=0;
//                  qty1=0;
//                  pl=0;
                }

                try{
                    ResultSet resultSet2=(database.readSellPrice(username,resultSet.getString("StockId")));
                    resultSet2.first();
                    sellPrice=resultSet2.getFloat("Sell_Price");
                    pl=sellPrice-(qty1*resultSet.getFloat("CostPrice"));
                }catch (Exception e){
//                    e.printStackTrace();
                }
                boughtStocks.add(new BoughtStocks(resultSet.getString("Username"),
                        resultSet.getString("StockId"),
                        qty,
                        resultSet.getFloat("CostPrice"),
                        resultSet.getFloat("TotalPrice"),
                        pl));

            } while (resultSet.next());
        }catch (Exception e){
            e.printStackTrace();
        }
        return boughtStocks;
    }
}