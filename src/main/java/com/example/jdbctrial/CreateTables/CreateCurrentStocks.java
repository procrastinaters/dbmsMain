package com.example.jdbctrial.CreateTables;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateCurrentStocks {

        private String setSql(){
            String sql;
            sql="CREATE TABLE IF NOT EXISTS CurrentStocks (" +
                    "Ticker varchar(20) NOT NULL," +
                    "Quantity INT NOT NULL," +
                    "Username varchar(30)," +
                    "foreign key (Username) references boughtstocks(Username)" +
                    ")";
            return sql;
        }

        public CreateCurrentStocks(Connection connection) throws SQLException {

            Statement statement = connection.createStatement();
            statement.executeUpdate(setSql());
        }


}
