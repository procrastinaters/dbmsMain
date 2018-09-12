package com.example.jdbctrial.Controllers;

import com.example.jdbctrial.Database;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

//@RestController
@EnableScheduling
@Controller
public class IndexController {

    Database database=new Database();

    IndexController() throws SQLException {
        try {
            database.connectAndCreate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private SimpMessagingTemplate template;
    //@GetMapping("/api")
//    @MessageMapping("/hello")
//    @SendTo("/topic/mssgs")
    @Scheduled(fixedRate = 5000)
    public void api() throws SQLException, UnsupportedEncodingException {
        ObjectMapper mapperObj = new ObjectMapper();
        String jsonStr=null;
        try {
           jsonStr = mapperObj.writeValueAsString(database.readStocks());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.template.convertAndSend("/topic/mssgs", jsonStr);

    }
}
