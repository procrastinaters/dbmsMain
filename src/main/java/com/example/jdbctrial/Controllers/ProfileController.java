package com.example.jdbctrial.Controllers;
import com.example.jdbctrial.Database;
import com.example.jdbctrial.InformationObjects.User;
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

    @MessageMapping("/Profile")
    @SendToUser("/queue/reply/Profile")
    public User getData(String json) throws IOException, SQLException {

        JSONObject obj = new JSONObject(json);

        String username= obj.getString("username");

        ResultSet resultSet = database.readUser(username);
        resultSet.first();
        User user = new User(resultSet.getString("Name"),resultSet.getString("Email"),resultSet.getInt("Age"),resultSet.getString("Sex"),resultSet.getString("Phone"),resultSet.getDate("DOB"));
        return user;
    }
}