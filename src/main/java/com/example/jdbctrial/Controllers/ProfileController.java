package com.example.jdbctrial.Controllers;
import com.example.jdbctrial.Database;
import com.example.jdbctrial.InformationObjects.User;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import java.io.IOException;
import java.sql.SQLException;

@Controller
public class ProfileController {

    @Autowired
    Database database;

    @MessageMapping("/completeProfile")
    public void profile(String json) throws IOException, SQLException {

        ObjectMapper mapper = new ObjectMapper();
        System.out.println("REQUEST");
        User user = mapper.readValue(json, User.class);
        database.insertToUser(user);

    }
}