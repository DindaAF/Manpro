package com.tubes.dao;

import com.tubes.Entity.Film;
import com.tubes.Entity.Studio;
import com.tubes.util.DaoService;
import com.tubes.util.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudioDaoImpl implements DaoService<Studio> {
    @Override
    public List<Studio> fetchAll() throws SQLException, ClassNotFoundException {
        List<Studio> studios = new ArrayList<>();
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "SELECT s.IdStudio,s.NamaStudio, s.JamTayang,s.Harga,f.IdFilm,f.Judul FROM Studio s JOIN Film f ON f.IdFilm=s.Film_idFilm";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Film film = new Film();
                        film.setIdFilm(rs.getString("IdFilm"));
                        film.setJudul(rs.getString("Judul"));
                        Studio studio = new Studio();
                        studio.setIdStudio(rs.getInt("IdStudio"));
                        studio.setNamaStudio(rs.getString("NamaStudio"));
                        studio.setJamTayang(rs.getString("JamTayang"));
                        studio.setHarga(rs.getInt("Harga"));
                        studio.setFilm_idFilm(film);
                        studios.add(studio);
                    }
                }
            }
        }
        return studios;
    }

    @Override
    public int addData(Studio object) throws SQLException, ClassNotFoundException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "INSERT INTO Studio(IdStudio,JamTayang,Harga,NamaStudio,Film_IdFilm) values (?,?,?,?,?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, object.getIdStudio());
                ps.setString(2, object.getJamTayang());
                ps.setInt(3, object.getHarga());
                ps.setString(4, object.getNamaStudio());
                ps.setObject(5, object.getFilm_idFilm().getIdFilm());
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
    public int updateData(Studio object) throws SQLException, ClassNotFoundException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "UPDATE  Studio SET NamaStudio=?,Harga=?,JamTayang=?,Film_idFilm=? WHERE IdStudio=?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, object.getNamaStudio());
                ps.setInt(2, object.getHarga());
                ps.setString(3, object.getJamTayang());
                ps.setObject(4, object.getFilm_idFilm().getIdFilm());
                ps.setInt(5, object.getIdStudio());
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
    public int deleteData(Studio object) throws SQLException, ClassNotFoundException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "DELETE FROM studio WHERE IdStudio = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, String.valueOf(object.getIdStudio()));
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