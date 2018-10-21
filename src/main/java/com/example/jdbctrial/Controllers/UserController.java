package com.example.jdbctrial.Controllers;


import com.example.jdbctrial.Database;
import com.example.jdbctrial.InformationObjects.Account;
import com.example.jdbctrial.InformationObjects.User;
import com.example.jdbctrial.ReadTables.ReadAccount;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.json.JSONString;
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
        Account account = mapper.readValue(json, Account.class);
        ResultSet resultSet = database.readAccount();
        try {
            resultSet.first();
            do {
                if (resultSet.getString("Username").equals(account.getUsername())) {
                    if (resultSet.getString("Password").equals(account.getPassword())) {
                        return true;
//                        simpMessagingTemplate.convertAndSend("/user/{username}/reply",true);
                    }
                }
            } while (resultSet.next());
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @MessageMapping("/signin")
    @SendToUser("/queue/register")
    public Boolean signin(String json) throws IOException, SQLException {
        ObjectMapper mapper = new ObjectMapper();
        ResultSet resultSet = database.readAccount();
        System.out.println("REQUEST");
        Account account = mapper.readValue(json, Account.class);
        resultSet.first();
        try {
            do {
                if (resultSet.getString("Username").equals(account.getUsername()))
                    return false;
            } while (resultSet.next());
        }catch(Exception e)
        {

        }
        database.insertToAccount(account);
        return true;
    }

    @MessageMapping("/balance")
    @SendToUser("/queue/balance")
    public Account balance(String json) throws IOException, SQLException {

        Account account = null;
        JSONObject obj = new JSONObject(json);

        String username= obj.getString("username");
        ResultSet resultSet=database.readAccount();
        resultSet.first();
        do{
            if(resultSet.getString("Username").equals(username))
                account = new Account(resultSet.getString("Username"),resultSet.getInt("Balance"),resultSet.getString("Password"));
        }while(resultSet.next());
        return account;
    }
}