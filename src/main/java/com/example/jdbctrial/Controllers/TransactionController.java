package com.example.jdbctrial.Controllers;

import com.example.jdbctrial.Database;
import com.example.jdbctrial.InformationObjects.BoughtStocks;
import com.example.jdbctrial.InformationObjects.SoldStocks;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import java.io.IOException;
import java.sql.SQLException;

@Controller
public class TransactionController {

    @Autowired
    Database database;

    ObjectMapper mapper = new ObjectMapper();

    @MessageMapping("/buy")
    @SendToUser("/queue/buy")
    public Boolean buy(String json) throws IOException {
        BoughtStocks boughtStocks = mapper.readValue(json, BoughtStocks.class);
        System.out.println(boughtStocks.getStock());
        try {
            database.insertToBoughtTable(boughtStocks);
            return true;
        }catch (Exception e){

            return false;
        }
    }
    @MessageMapping("/sell")
    @SendToUser("/queue/sell")
    public Boolean sell(String json) throws IOException {

        SoldStocks soldStocks = mapper.readValue(json, SoldStocks.class);
        System.out.println(soldStocks.getSymbol());
        try {
            database.insertToSoldTable(soldStocks);
            return true;
        }catch (Exception e){
            return false;
        }

    }
}