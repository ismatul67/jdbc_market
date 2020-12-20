package org.example.Impl;

import org.example.Entity.Item;
import org.example.Entity.Stock;
import org.example.service.ItemService;
import org.example.service.StockService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockServiceImpl implements StockService {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/market";
    static final String USER = "root";
    static final String PASS = "";

    static Connection conn;
    static Statement stmt;
    static ResultSet rs;

    static PreparedStatement prepstmt;

    ItemService itemService = new ItemServiceImpl();

    int thisId = 0;
    int thisItemId = 0;
    int thisQuantity = 0;
    int thisTotalPrice = 0;
    Stock stock = null;
    List<Stock> stocks = new ArrayList<>();

    public StockServiceImpl() {
        connectData();
    }

    static void connectData() {

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Stock> findGreaterThanQuantity(int quantity) throws SQLException {
        prepstmt = conn.prepareStatement("SELECT * FROM market.stock WHERE `quantity`>?");
        prepstmt.setInt(1, quantity);

        rs = prepstmt.executeQuery();


        while (rs.next()) {
            thisId = (rs.getInt("id"));
            thisItemId = (rs.getInt("item_id"));
            thisQuantity = rs.getInt("quantity");
            thisTotalPrice = rs.getInt("total_price");
            Stock stock = new Stock(thisId, thisItemId, thisQuantity, thisTotalPrice);
            stocks.add(stock);
        }
        return stocks;
    }

    @Override
    public Stock findStockByItemId(int idItem) throws SQLException {
        prepstmt = conn.prepareStatement("SELECT * FROM market.stock WHERE `item_id`=?");

        prepstmt.setInt(1, idItem);

        rs = prepstmt.executeQuery();

        while (rs.next()) {
            thisId = (rs.getInt("id"));
            thisItemId = (rs.getInt("item_id"));
            thisQuantity = rs.getInt("quantity");
            thisTotalPrice = rs.getInt("total_price");
        }
        stock = new Stock(thisId, thisItemId, thisQuantity, thisTotalPrice);
        return stock;
    }

    @Override
    public List<Stock> findLessThanQuantity(int quantity) throws SQLException {
        prepstmt = conn.prepareStatement("SELECT * FROM market.stock WHERE `quantity`<?");
        prepstmt.setInt(1, quantity);

        rs = prepstmt.executeQuery();

        while (rs.next()) {
            thisId = (rs.getInt("id"));
            thisItemId = (rs.getInt("item_id"));
            thisQuantity = rs.getInt("quantity");
            thisTotalPrice = rs.getInt("total_price");
            Stock stock = new Stock(thisId, thisItemId, thisQuantity, thisTotalPrice);
            stocks.add(stock);
        }
        return stocks;
    }

    @Override
    public String add(Stock stock) throws SQLException {

        Item item = itemService.findById(stock.getItemId());

        prepstmt = conn.prepareStatement("insert into market.stock (`item_id`,`quantity`,`total_price`) values(?,?,?)");

        prepstmt.setInt(1, stock.getItemId());
        prepstmt.setInt(2, stock.getQuantity());
        prepstmt.setInt(3, (item.getPrice() * stock.getQuantity()));

        prepstmt.executeUpdate();

        return "Stock for item with id " + stock.getItemId() + " have been added";
    }

    @Override
    public String edit(Stock stock) throws SQLException {
        Item item = itemService.findById(stock.getItemId());
        prepstmt = conn.prepareStatement("UPDATE market.stock SET `quantity`=?,`total_price`=? WHERE `id`= ?");
        prepstmt.setInt(1, stock.getQuantity());
        prepstmt.setInt(2, (item.getPrice() * stock.getQuantity()));
        prepstmt.setInt(3, stock.getId());
        prepstmt.executeUpdate();

        return "Stock for item with id " + stock.getItemId() + " have been updated";

    }

    @Override
    public String removeById(Integer id) throws SQLException {
        prepstmt = conn.prepareStatement("DELETE FROM market.stock WHERE id=? ");

        prepstmt.setInt(1, id);

        prepstmt.executeUpdate();

        return "Stock with ID " + id + " has been deleted!";

    }

    @Override
    public Stock findById(Integer id) throws SQLException {
        Stock stock = null;

        prepstmt = conn.prepareStatement("SELECT * FROM market.stock WHERE `id`=?");

        prepstmt.setInt(1, id);

        rs = prepstmt.executeQuery();

        while (rs.next()) {
            thisId = (rs.getInt("id"));
            thisItemId = (rs.getInt("item_id"));
            thisQuantity = rs.getInt("quantity");
            thisTotalPrice = rs.getInt("total_price");
        }
        stock = new Stock(thisId, thisItemId, thisQuantity, thisTotalPrice);
        return stock;
    }

    @Override
    public List<Stock> findAll() throws SQLException {
        rs = stmt.executeQuery("Select * from stock");
        while (rs.next()) {
            thisId = (rs.getInt("id"));
            thisItemId = (rs.getInt("item_id"));
            thisQuantity = rs.getInt("quantity");
            thisTotalPrice = rs.getInt("total_price");
            Stock stock = new Stock(thisId, thisItemId, thisQuantity, thisTotalPrice);
            stocks.add(stock);
        }
        return stocks;
    }
}
