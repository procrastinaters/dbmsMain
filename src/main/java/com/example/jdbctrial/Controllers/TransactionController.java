package com.example.jdbctrial.Controllers;

import com.example.jdbctrial.Database;
import com.example.jdbctrial.InformationObjects.BoughtStocks;
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
    @SendToUser("/queue/reply")
    public Boolean buy(String json) throws IOException, SQLException {
        BoughtStocks boughtStocks = mapper.readValue(json, BoughtStocks.class);
        System.out.println(boughtStocks.getStock());
        return true;
    }
}