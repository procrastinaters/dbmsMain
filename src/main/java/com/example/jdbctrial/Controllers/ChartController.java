package com.example.jdbctrial.Controllers;

import com.example.jdbctrial.Database;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import com.example.jdbctrial.InformationObjects.BoughtStocks;
import com.example.jdbctrial.InformationObjects.StockInfo;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;


@Controller
public class ChartController {

    @Autowired
    Database database;

    ObjectMapper mapper = new ObjectMapper();

    @MessageMapping("/graph")
    @SendToUser("/queue/reply")

    public StockInfo[] graph(String symbol) throws IOException, SQLException {

        System.out.println("1\n");


        final String uri ="http://localhost:9000/chart/{symbol}";

        Map<String, String> params = new HashMap<String, String>();
        params.put("symbol", symbol);

        RestTemplate restTemplate = new RestTemplate();
        StockInfo[] stockinfo = restTemplate.getForObject(uri, StockInfo[].class, params);


//        RestTemplate restTemplate = new RestTemplate();
//
//        StockInfo[] stockinfo = restTemplate.getForObject("http://localhost:9000/chart", StockInfo[].class);

        System.out.println("Plotting Graph");
        System.out.println(stockinfo[0].getPrevClose());
        System.out.println("5\n");

        return stockinfo;
    }



}



















//
//    public ArrayList<StockInfo> stockinfo = new ArrayList<StockInfo>();
//    private SimpMessagingTemplate template;
//
//
//    @Autowired
//    Database database;
//    public void GreetingController(SimpMessagingTemplate template) {
//        this.template = template;
//    }
//
//    ObjectMapper mapper = new ObjectMapper();
//
//    @MessageMapping("/graph")
//    @SendToUser("/queue/reply")
//
//    public ArrayList<StockInfo> graph(String symbol) throws IOException, SQLException {
//
//        ResultSet rs = database.readHistory(symbol);
//
//
//        System.out.println("Plotting Graph");
//
//        while(rs.next())
//        {
//            stockinfo.add(new StockInfo(
//                            rs.getDouble("StockIndex"),
//                            rs.getString("Symbol"),
//                            rs.getString("Series"),
//                            rs.getString("Date"),
//                            rs.getDouble("Prev_Close"),
//                            rs.getDouble("Open_Price"),
//                            rs.getDouble("High_Price"),
//                            rs.getDouble("Low_Price"),
//                            rs.getDouble("Last_Price"),
//                            rs.getDouble("Close_Price"),
//                            rs.getDouble("Average_Price"),
//                            rs.getInt("Total_Traded_Quantity"),
//                            rs.getDouble("Turnover"),
//                            rs.getInt("No_of_Trades"),
////                    rs.getInt("Deliverable_Qty"),
//                            0,
//                            rs.getDouble("_Dly_Qt_to_Traded_Qty")
//                    )
//            );
//        }
//
//
//        return stockinfo;
//
//    }
//
////    public ResultSet graph(String symbol) throws IOException, SQLException {
//////
//////        ResultSet resultSet;
//////
//////        resultSet = database.readHistory(symbol);
//////
//////        System.out.println("Plotting Graph");
//////
//////
//////        return resultSet;
//////    }