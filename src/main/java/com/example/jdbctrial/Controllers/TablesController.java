package com.example.jdbctrial.Controllers;


import com.example.jdbctrial.Database;
import com.example.jdbctrial.InformationObjects.BoughtStocks;
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

    private ArrayList<BoughtStocks> boughtStocks;

    ObjectMapper mapper = new ObjectMapper();

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
                boughtStocks.add(new BoughtStocks(resultSet.getString("Username"),
                        resultSet.getString("StockId"),
                        resultSet.getInt("Quantity"),
                        resultSet.getFloat("CostPrice"),
                        resultSet.getFloat("TotalPrice"),
                        resultSet.getInt("TransId"),
                        resultSet.getInt("QuantitySold")));

            } while (resultSet.next());
        }catch (Exception e){
            e.printStackTrace();
        }
        return boughtStocks;
    }
}