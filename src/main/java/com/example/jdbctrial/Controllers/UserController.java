package com.example.jdbctrial.Controllers;


import com.example.jdbctrial.Database;
import com.example.jdbctrial.InformationObjects.Account;
import com.example.jdbctrial.InformationObjects.User;
import com.example.jdbctrial.ReadTables.ReadAccount;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@Controller
public class UserController {

    @Autowired
    Database database;
    ObjectMapper mapper = new ObjectMapper();

    SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/login")
    @SendToUser("/queue/reply")
    public Boolean login(String json) throws IOException, SQLException {
        Account account=mapper.readValue(json,Account.class);
        ResultSet resultSet=database.readAccount();
        try {
            resultSet.first();
            do{
                if(resultSet.getString("username").equals(account.getUsername())){
                    if(resultSet.getString("password").equals(account.getPassword())) {
                        return true;
//                        simpMessagingTemplate.convertAndSend("/user/{username}/reply",true);
                    }
                }
            }while (resultSet.next());
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @MessageMapping("/signin")
    @SendToUser("/queue/reply")
    public Boolean signin(String json) throws IOException, SQLException {
        ObjectMapper mapper = new ObjectMapper();
        Account account=mapper.readValue(json,Account.class);
        ResultSet resultSet=database.readAccount();

        User user=mapper.readValue(json,User.class);

}
