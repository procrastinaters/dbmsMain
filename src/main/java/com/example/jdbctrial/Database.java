package com.example.jdbctrial;

import com.example.jdbctrial.CreateTables.*;
import com.example.jdbctrial.InformationObjects.*;
import com.example.jdbctrial.InsertIntoTables.*;
import com.example.jdbctrial.ReadTables.*;
import com.example.jdbctrial.UpdateTables.UpdateId;
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
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=false", "root", "password");
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


        public void insertToAccount(Account account) throws SQLException{

            new InsertToAccount(myConn,account);

        }

    public void insertToBoughtTable(BoughtStocks boughtStocks) throws SQLException{

        new InsertToBought(myConn,boughtStocks);

    }


    public void insertToSoldTable(SoldStocks soldStocks) throws SQLException{

        new InsertToSold(myConn,soldStocks);

    }


    public void insertToUser(User user) throws SQLException{

        new InsertToUser(myConn,user);

    }

//    public void insertIntoId(Integer buy, Integer sell) throws SQLException {
//
//        new InsertIntoId(myConn,buy,sell);
//
//    }

    public void updateBuyId(int id){

        (new UpdateId()).updateBuyId(id,myConn);
    }

    public void updateSellId(int id){

        (new UpdateId()).updateSellId(id,myConn);
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

    public ResultSet readBoughtStocks(String username){

        ReadBoughtStocks readBoughtStocks =new ReadBoughtStocks();
        return readBoughtStocks.read(myConn,username);
    }

    public ResultSet readCurrentStocks(String username, String ticker){

        ReadCurrentStocks readCurrentStocks =new ReadCurrentStocks();
        return readCurrentStocks.read(myConn,username,ticker);
    }

    public  ResultSet readCustom(String username){
        ReadCustom readCustom=new ReadCustom();
        return readCustom.readUnion(myConn, username);
    }

    public  ResultSet readSellPrice(String username, String ticker) throws SQLException {
        ReadCustom readCustom=new ReadCustom();
        return readCustom.readSellPrice(myConn, username, ticker);
    }

    public ResultSet readUser(String user){

        ReadUser readUser =new ReadUser();
        return readUser.read(myConn,user);
    }

    public Integer readBuyId(){

        return (new ReadIdTable().readBuyId(myConn));
    }
    public Integer readSellId(){

        return (new ReadIdTable().readSellId(myConn));
    }
    public ArrayList<Admin> readAdmin() throws SQLException {

        return (new ReadCustom().readAdmin(myConn));
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
        new CreateCurrentStocks(myConn);
        new CreateIdTable(myConn);
    }

    public void close() throws SQLException {
        myConn.close();
    }
}