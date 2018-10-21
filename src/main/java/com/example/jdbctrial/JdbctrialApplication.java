package com.example.jdbctrial;

import com.example.jdbctrial.InformationObjects.Stock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class JdbctrialApplication  {


	public static void main(String[] args) throws Exception {
		SpringApplication.run(JdbctrialApplication.class, args);


		Database database=new Database();

		database.connectAndCreate();

		RestTemplate restTemplate = new RestTemplate();

		Stock[] stocks = restTemplate.getForObject("http://localhost:9000/api", Stock[].class);

		database.insertToStock(stocks);

		database.close();
	}

}
