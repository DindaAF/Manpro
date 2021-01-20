package com.tubes.dao;

import com.tubes.Entity.TopUp;
import com.tubes.util.DaoService;

import java.sql.SQLException;
import java.util.List;

public class TopUpDaoImpl implements DaoService<TopUp> {
    @Override
    public List<TopUp> fetchAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public int addData(TopUp object) throws SQLException, ClassNotFoundException {
        return 0;
    }

    @Override
    public int updateData(TopUp object) throws SQLException, ClassNotFoundException {
        return 0;
    }

    @Override
    public int deleteData(TopUp object) throws SQLException, ClassNotFoundException {
        return 0;
    }
}
