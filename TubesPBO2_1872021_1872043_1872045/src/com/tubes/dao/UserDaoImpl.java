package com.tubes.dao;

import com.tubes.Entity.User;
import com.tubes.util.DaoService;
import com.tubes.util.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements DaoService<User> {
    public List<User> fetchAll() throws SQLException, ClassNotFoundException {
        List<User> users = new ArrayList<>();
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "SELECT IdUser, NamaUser, Username, Password, Saldo, Role FROM User";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        User user = new User();
                        user.setIdUser(rs.getString("IdUser"));
                        user.setNamaUser(rs.getString("NamaUser"));
                        user.setUsername(rs.getString("Username"));
                        user.setPassword(rs.getString("Password"));
                        user.setSaldo(rs.getInt("Saldo"));
                        user.setRole(rs.getString("Role"));
                        users.add(user);
                    }
                }
            }
        }
        return users;
    }

    @Override
    public int addData(User object) throws SQLException, ClassNotFoundException {
        return 0;
    }

    @Override
    public int updateData(User object) throws SQLException, ClassNotFoundException {
        return 0;
    }

    @Override
    public int deleteData(User object) throws SQLException, ClassNotFoundException {
        return 0;
    }

    public User Login(User data) throws ClassNotFoundException {
        User u = new User();
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "SELECT * FROM user Where username=? and password=?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1,data.getUsername());
                ps.setString(2,data.getPassword());
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        u.setIdUser(rs.getString("IdUser"));
                        u.setNamaUser(rs.getString("NamaUser"));
                        u.setUsername(rs.getString("Username"));
                        u.setPassword(rs.getString("Password"));
                        u.setSaldo(rs.getInt("Saldo"));
                        u.setRole(rs.getString("Role"));
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return u;
    }


}

