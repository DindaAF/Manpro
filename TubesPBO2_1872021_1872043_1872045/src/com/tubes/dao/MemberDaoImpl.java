package com.tubes.dao;


import com.tubes.Entity.Member;

import com.tubes.util.DaoService;
import com.tubes.util.MySQLConnection;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDaoImpl implements DaoService<Member> {
    public List<Member> fetchAll() throws SQLException, ClassNotFoundException {
        List<Member> members = new ArrayList<>();
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "SELECT * FROM User";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Member member = new Member();
                        member.setIdUser(rs.getString("IdUser"));
                        member.setNamaUser(rs.getString("NamaUser"));
                        member.setUsername(rs.getString("Username"));
                        member.setPassword(rs.getString("Password"));
                        member.setSaldo(rs.getInt("Saldo"));
                        member.setRole(rs.getString("Role"));
                        members.add(member);
                    }
                }
            }
        }
        return members;
    }

    @Override
    public int addData(Member object) throws SQLException, ClassNotFoundException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "INSERT INTO user(IdUser, NamaUser, Username , Password, Saldo, Role) values (?,?,?,?,?,?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, object.getIdUser());
                ps.setString(2, object.getNamaUser());
                ps.setString(3, object.getUsername());
                ps.setString(4, object.getPassword());
                ps.setInt(5, object.getSaldo());
                ps.setString(6, object.getRole());
                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            }
        }
        return result;
    }

    @Override
    public int updateData(Member object) throws SQLException, ClassNotFoundException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "UPDATE user SET  NamaUser=?, Username=? , Password=?, Saldo=? WHERE IdUser=?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, object.getNamaUser());
                ps.setString(2, object.getUsername());
                ps.setString(3, object.getPassword());
                ps.setInt(4, object.getSaldo());
                ps.setString(5, object.getIdUser());
                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            }
        }
        return result;
    }

    public int updateSaldo(Member object) throws SQLException, ClassNotFoundException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "UPDATE user SET   Saldo=? WHERE IdUser=?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, object.getSaldo());
                ps.setString(2, object.getIdUser());
                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            }
        }
        return result;
    }



    @Override
    public int deleteData(Member object) throws SQLException, ClassNotFoundException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "DELETE FROM user WHERE IdUser = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, object.getIdUser());
                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            }
        }
        return result;
    }
}
