package com.example.jdbctrial;

import com.example.jdbctrial.CreateTables.*;
import com.example.jdbctrial.InformationObjects.Stock;
import com.example.jdbctrial.InsertIntoTables.InsertToStocks;
import com.example.jdbctrial.ReadTables.ReadAccount;
import com.example.jdbctrial.ReadTables.ReadStocks;
import com.example.jdbctrial.UpdateTables.UpdateStocks;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;

@Component
public class Database{

    private Connection myConn = null;
    private Statement myStmt = null;
    private ResultSet myRs = null;

    public void connectAndCreate() throws SQLException {


    }


    public Database() throws SQLException {
        try{
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useSSL=false", "student", "student");
        }catch (Exception exc) {
            exc.printStackTrace();
        }
        finally {
            if (myRs != null) {
                myRs.close();
            }
        }
        create();
        System.out.println("Created and Connected");
    }

    public void insertToStock(Stock[] stock) throws SQLException {

        myStmt = myConn.createStatement();
        String sql="SELECT count(*) as count FROM stocks";
        ResultSet resultSet=myStmt.executeQuery(sql);
        resultSet.first();
        int count=resultSet.getInt("count");
        if(count==0)
            new InsertToStocks(myConn, stock);
//            System.out.println(stock[i].getOpen());
        }


    public ArrayList<Stock> readStocks(){

        ArrayList<Stock> stocks =new ArrayList<Stock>();
        try{
            ReadStocks readStocks=new ReadStocks();
            ResultSet resultSet=readStocks.read(myConn);
            resultSet.first();
            do{
                stocks.add(new Stock(resultSet.getString("symbol"),
                        resultSet.getString("company"),
                        resultSet.getString("exchng"),
                        resultSet.getString("sector"),
                        resultSet.getDouble("open"),
                        resultSet.getFloat("close"),
                        resultSet.getFloat("prevClose"),
                        resultSet.getFloat("price"),
                        resultSet.getDouble("per")));
            }while(resultSet.next());
        }catch (Exception exc){
            exc.printStackTrace();
        }
        return stocks;
    }

    public ResultSet readAccount(){

        ReadAccount readAccount =new ReadAccount();
        return readAccount.read(myConn);
    }


    public void updateStocks(Stock[] stocks){

        new UpdateStocks(myConn,stocks);
    }
    private void create() throws SQLException {

        new CreateUserTable(myConn);
        new CreateAccountTable(myConn);
        new CreateStocksTable(myConn);
        new CreateHistoryTable(myConn);
        new CreateBoughtTable(myConn);
        new CreateSoldTable(myConn);

    }

    public void close() throws SQLException {
        myConn.close();
    }
}
