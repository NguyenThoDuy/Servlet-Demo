package com.example.demo_servlet.DAO;

import java.sql.SQLException;
import java.util.List;

public interface CommonDAO<T> {
    List<T> getAll() throws SQLException;
}
