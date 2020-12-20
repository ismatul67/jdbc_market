package org.example.service;


import org.example.Entity.Unit;

import java.sql.SQLException;

public interface UnitService extends MarketInterface<Unit,Integer>{
    Unit findByCode(String code) throws SQLException;
}
