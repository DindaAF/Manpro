package com.tubes.dao;

import com.tubes.Entity.Ticket;
import com.tubes.util.DaoService;
import com.tubes.util.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TicketDaoImpl implements DaoService<Ticket> {
    @Override
    public List<Ticket> fetchAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public int addData(Ticket object) throws SQLException, ClassNotFoundException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "INSERT INTO Ticket(IdTicket,User_IdUser,harga,Jam,jumlahpesan,totalbayar,Studio_IdStudio,Studio_Film_IdFilm) values (?,?,?,?,?,?,?,?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, object.getIdTicket());
                ps.setObject(2, object.getUser_IdUser().getIdUser());
                ps.setObject(3, object.getStudio_IdStudio().getHarga());
                ps.setObject(4, object.getStudio_IdStudio().getJamTayang());
                ps.setInt(5, object.getJumlahpesan());
                ps.setInt(6, object.getTotalbayar());
                ps.setObject(7, object.getStudio_IdStudio().getIdStudio());
                ps.setObject(8, object.getStudio_Film_IdFilm().getFilm_idFilm());
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
    public int updateData(Ticket object) throws SQLException, ClassNotFoundException {
        return 0;
    }

    @Override
    public int deleteData(Ticket object) throws SQLException, ClassNotFoundException {
        return 0;
    }
}
