package com.example.jdbctrial.Controllers;


import com.example.jdbctrial.Database;
import com.example.jdbctrial.InformationObjects.Account;
import com.example.jdbctrial.ReadTables.ReadAccount;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@Controller
public class UserController {

    @Autowired
    Database database;

    @MessageMapping("/login")
    @SendTo("/topic/client")
    public Boolean login(String json) throws IOException, SQLException {
        ObjectMapper mapper = new ObjectMapper();
        Account account=mapper.readValue(json,Account.class);
        ResultSet resultSet=database.readAccount();
        try {
            resultSet.first();
            do{
                if(resultSet.getString("username").equals(account.getUsername())){
                    if(resultSet.getString("password").equals(account.getPassword())) {
                        return true;
                    }
                }
            }while (resultSet.next());
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
