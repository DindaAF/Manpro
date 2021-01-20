package com.tubes.dao;

import com.tubes.Entity.Admin;

import com.tubes.util.DaoService;
import com.tubes.util.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl implements DaoService<Admin> {
    @Override
    public List<Admin> fetchAll() throws SQLException, ClassNotFoundException {
        List<Admin> admins = new ArrayList<>();
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "SELECT IdAdmin, Username, Password FROM Admin";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Admin admin = new Admin();
                        admin.setIdAdmin(rs.getString("IdAdmin"));
                        admin.setUsername(rs.getString("Username"));
                        admin.setPassword(rs.getString("Password"));
                        admins.add(admin);
                    }
                }
            }
        }
        return admins ;
    }

    @Override
    public int addData(Admin object) throws SQLException, ClassNotFoundException {
        return 0;
    }

    @Override
    public int updateData(Admin object) throws SQLException, ClassNotFoundException {
        return 0;
    }

    @Override
    public int deleteData(Admin object) throws SQLException, ClassNotFoundException {
        return 0;
    }
}
