package org.example.service;

import java.sql.SQLException;
import java.util.List;

public interface MarketInterface <T,U>{
    String add(T t) throws SQLException;
    String edit(T t) throws SQLException;
    String removeById(U id) throws SQLException;
    T findById(U id) throws SQLException;
    List<T> findAll() throws SQLException;

}
