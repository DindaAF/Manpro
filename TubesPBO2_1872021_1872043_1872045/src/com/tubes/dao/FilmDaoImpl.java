package com.tubes.dao;

import com.tubes.Entity.Film;

import com.tubes.util.DaoService;
import com.tubes.util.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmDaoImpl implements DaoService<Film> {
    @Override
    public List<Film> fetchAll() throws SQLException, ClassNotFoundException {
        List<Film> films = new ArrayList<>();
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "SELECT IdFilm,Judul,Rilis,Deskripsi FROM Film";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Film film = new Film();
                        film.setIdFilm(rs.getString("IdFilm"));
                        film.setJudul(rs.getString("Judul"));
                        film.setRilis(rs.getString("Rilis"));
                        film.setDeskripsi(rs.getString("Deskripsi"));
                        films.add(film);
                    }
                }
            }
        }
        return films;
    }

    @Override
    public int addData(Film object) throws SQLException, ClassNotFoundException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "INSERT INTO Film (IdFilm,Judul,Rilis,Deskripsi) VALUES (?,?,?,?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, object.getIdFilm());
                ps.setString(2, object.getJudul());
                ps.setString(3, object.getRilis());
                ps.setString(4, object.getDeskripsi());
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
    public int updateData(Film object) throws SQLException, ClassNotFoundException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "UPDATE Film SET  Judul=?, Rilis=? , Deskripsi=? WHERE IdFilm=?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, object.getJudul());
                ps.setString(2, object.getRilis());
                ps.setString(3, object.getDeskripsi());
                ps.setString(4, object.getIdFilm());
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
    public int deleteData(Film object) throws SQLException, ClassNotFoundException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "DELETE FROM film WHERE IdFilm = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, object.getIdFilm());
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
