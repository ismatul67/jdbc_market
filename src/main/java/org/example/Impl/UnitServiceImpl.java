package org.example.Impl;

import org.example.Entity.Unit;
import org.example.service.UnitService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UnitServiceImpl implements UnitService {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/market";
    static final String USER = "root";
    static final String PASS = "";

    static Connection conn;
    static Statement stmt;
    static ResultSet rs;

    static PreparedStatement prepstmt;

    int thisId = 0;
    String thisCode = "";
    String thisDescription = "";
    Unit unit = null;
    List<Unit> units = new ArrayList<>();

    public UnitServiceImpl() {
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
    public Unit findByCode(String code) throws SQLException {
        prepstmt = conn.prepareStatement("SELECT * FROM market.unit WHERE `code` like ?");

        prepstmt.setString(1, code);

        rs = prepstmt.executeQuery();

        while (rs.next()) {
            thisId = rs.getInt("id");
            thisCode = rs.getString("code");
            thisDescription = rs.getString("description");
        }
        unit = new Unit(thisId,thisCode,thisDescription);

        return unit;
    }

    @Override
    public String add(Unit unit) throws SQLException {
        prepstmt = conn.prepareStatement("insert into market.unit (`code`,`description`) values(?,?)");

        prepstmt.setString(1, unit.getCode());
        prepstmt.setString(2, unit.getDescription());


        prepstmt.executeUpdate();

        return "Unit code " + unit.getCode() + " with " + unit.getDescription() + " have been added";


    }

    @Override
    public String edit(Unit unit) throws SQLException {

        prepstmt = conn.prepareStatement("UPDATE market.unit SET `description`=? WHERE `id`= ?");
        prepstmt.setString(1, unit.getDescription());
        prepstmt.setInt(2, unit.getId());
        prepstmt.executeUpdate();

        return "Item " + unit.getCode() + "has been updated!";
    }

    @Override
    public String removeById(Integer id) throws SQLException {
        prepstmt = conn.prepareStatement("DELETE FROM market.unit WHERE id=? ");

        prepstmt.setInt(1, id);

        prepstmt.executeUpdate();

        return "Unit with ID " + id + " has been deleted!";

    }

    @Override
    public Unit findById(Integer id) throws SQLException {
        prepstmt = conn.prepareStatement("SELECT * FROM market.unit WHERE `id` like ?");

        prepstmt.setInt(1, id);

        rs = prepstmt.executeQuery();

        while (rs.next()) {
            thisId = rs.getInt("id");
            thisCode = rs.getString("code");
            thisDescription = rs.getString("description");
        }
        unit = new Unit(thisId, thisCode, thisDescription);

        return unit;
    }

    @Override
    public List<Unit> findAll() throws SQLException {
        String sql = "Select * from unit";

        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            int thisId = rs.getInt("id");
            String thisCode = rs.getString("code");
            String thisDesc = rs.getString("description");
            Unit unit = new Unit(thisId, thisCode,thisDesc);
            units.add(unit);
        }

        return units;
    }
}
