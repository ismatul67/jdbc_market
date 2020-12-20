package org.example.service;

import org.example.Entity.Stock;

import java.sql.SQLException;
import java.util.List;

public interface StockService extends MarketInterface<Stock,Integer> {
    List<Stock> findGreaterThanQuantity(int quantity) throws SQLException;
    Stock findStockByItemId(int idItem) throws SQLException;
    List<Stock> findLessThanQuantity(int quantity) throws SQLException;
}
