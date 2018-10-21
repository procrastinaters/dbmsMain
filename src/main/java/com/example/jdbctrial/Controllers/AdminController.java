package com.example.jdbctrial.Controllers;

import com.example.jdbctrial.Database;
import com.example.jdbctrial.InformationObjects.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class AdminController {

    @Autowired
    Database database;

    @MessageMapping("/Admin")
    @SendTo("/queue/reply/admin")
    public ArrayList<Admin> getData() throws SQLException {
        return database.readAdmin();
    }
}