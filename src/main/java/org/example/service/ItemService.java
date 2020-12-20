package org.example.service;

import org.example.Entity.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItemService extends MarketInterface<Item,Integer>{
    Item findByName(String name) throws SQLException;
    List<Item> findMostItemInStock() throws SQLException;


}
