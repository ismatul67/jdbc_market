package org.example.Impl;

import org.example.Entity.Item;
import org.example.service.ItemService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemServiceImpl implements ItemService {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/market";
    static final String USER = "root";
    static final String PASS = "";

    static Connection conn;
    static Statement stmt;
    static ResultSet rs;

    static PreparedStatement prepstmt;

    public ItemServiceImpl() {
        connectData();
    }

    int thisId = 0;
    String thisName = "";
    int thisPrice = 0;
    int thisUnitId = 0;
    Item item = null;
    List<Item> items = new ArrayList<>();

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
    public Item findByName(String name) throws SQLException {
        prepstmt = conn.prepareStatement("SELECT * FROM market.item WHERE `name` like ?");

        prepstmt.setString(1, name);

        rs = prepstmt.executeQuery();

        while (rs.next()) {
            thisId = rs.getInt("id");
            thisName = rs.getString("name");
            thisPrice = rs.getInt("price");
            thisUnitId = rs.getInt("unit_id");
        }
        item = new Item(thisId, thisName, thisPrice, thisUnitId);
        return item;
    }

    @Override
    public List<Item> findMostItemInStock() throws SQLException {
        prepstmt = conn.prepareStatement("SELECT item.id as id,item.name as name ,item.unit_id as unit_id, item.price as price, stock.quantity as quantity FROM market.item as item join market.stock as stock on item.id = stock.item_id HAVING quantity=(select max(quantity) from market.stock)");

        rs = prepstmt.executeQuery();
        while (rs.next()) {
            thisId = rs.getInt("id");
            thisName = rs.getString("name");
            thisPrice = rs.getInt("price");
            thisUnitId = rs.getInt("unit_id");
            System.out.println("Quantity item\t: "+ rs.getInt("quantity"));
            Item item = new Item(thisId, thisName, thisPrice, thisUnitId);
            items.add(item);
        }

        return items;
    }

    @Override
    public String add(Item item) throws SQLException {
        prepstmt = conn.prepareStatement("insert into market.item (`name`,`price`, `unit_id`) values(?,?,?)");

        prepstmt.setString(1, item.getName());
        prepstmt.setInt(2, item.getPrice());
        prepstmt.setInt(3, item.getUnitId());


        prepstmt.executeUpdate();

        return "Item " + item.getName() + " with price " + item.getPrice() + " have been added";
    }

    @Override
    public String edit(Item item) throws SQLException {
        prepstmt = conn.prepareStatement("UPDATE market.item SET `price`=? WHERE `id`= ?");
        prepstmt.setInt(1, item.getPrice());
        prepstmt.setInt(2, item.getId());
        prepstmt.executeUpdate();

        return "Item " + item.getName() + " has been updated";

    }

    @Override
    public String removeById(Integer id) throws SQLException {
        prepstmt = conn.prepareStatement("DELETE FROM market.item WHERE id=? ");

        prepstmt.setInt(1, id);

        prepstmt.executeUpdate();

        return "Item with ID " + id + " has been deleted!";
    }

    @Override
    public Item findById(Integer id) throws SQLException {
        prepstmt = conn.prepareStatement("SELECT * FROM market.item WHERE `id`=?");

        prepstmt.setInt(1, id);

        rs = prepstmt.executeQuery();

        while (rs.next()) {
            thisId = rs.getInt("id");
            thisName = rs.getString("name");
            thisPrice = rs.getInt("price");
            thisUnitId = rs.getInt("unit_id");
        }
        item = new Item(thisId, thisName, thisPrice, thisUnitId);
        return item;
    }

    @Override
    public List<Item> findAll() throws SQLException {

        rs = stmt.executeQuery("Select * from item");

        while (rs.next()) {
            thisId = rs.getInt("id");
            thisName = rs.getString("name");
            thisPrice = rs.getInt("price");
            thisUnitId = rs.getInt("unit_id");
            Item item = new Item(thisId, thisName, thisPrice, thisUnitId);
            items.add(item);
        }
        return items;

    }
}
