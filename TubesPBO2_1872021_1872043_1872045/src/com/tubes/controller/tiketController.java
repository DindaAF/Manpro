package com.tubes.controller;

import com.tubes.Entity.Film;
import com.tubes.Entity.Member;
import com.tubes.Entity.Studio;
import com.tubes.Entity.Ticket;
import com.tubes.dao.FilmDaoImpl;
import com.tubes.dao.MemberDaoImpl;
import com.tubes.dao.StudioDaoImpl;
import com.tubes.dao.TicketDaoImpl;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class tiketController implements Initializable {
    public Label lblStudio;
    public Label lblHarga;
    public Label lblJam;
    public Label lblFilm;
    public TextField txtFilm;
    public ComboBox<Ticket> comboStudio;
    public Label lblJumlahPesan;
    public TextField txtPesan;
    public Label lblTotalBayar;
    public TextField txtTotalBayar;
    public Button btnBeliTicket;
    public Button btnReset;
    public Button btnClose;
    public TextField txtHarga;
    public TextField txtJam;
    private StudioDaoImpl studioDao;
    private FilmDaoImpl filmDao;
    private TicketDaoImpl ticketDao;
    private ObservableList<Studio> studios;
    private ObservableList<Film> films;
    private ObservableList<Ticket> tickets;
    private ObservableList<Member> members;
    private AdminController adminController;
    private UserController UserController;

    public void ActionBeliTicket(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if ( comboStudio.getValue()==null|| txtFilm.getText().trim().isEmpty() || txtTotalBayar.getText().trim().isEmpty() || txtPesan.getText().trim().isEmpty()||txtJam.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill");
            alert.showAndWait();
        } else {
            Ticket ticket = new Ticket();
            ticket.setStudio_IdStudio(comboStudio.getValue().getStudio_IdStudio());
            ticket.setStudio_Film_IdFilm(comboStudio.getValue().getStudio_Film_IdFilm());
            ticket.setHarga(comboStudio.getValue().getHarga());
            ticket.setJam(comboStudio.getValue().getJam());
            ticket.setJumlahpesan(Integer.parseInt(txtPesan.getText()));
            ticket.setTotalbayar(Integer.parseInt(txtTotalBayar.getText()));
            Member member = new Member();
            //member.setIdUser();
            //member.setSaldo();
            member.kurangSaldo(Integer.parseInt(txtTotalBayar.getText()));;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Berhasil Top Up user ");
            alert.showAndWait();
            TicketDaoImpl dao = new TicketDaoImpl();
            dao.addData(ticket);
            MemberDaoImpl memberdao = new MemberDaoImpl();
            memberdao.updateSaldo(member);
            members = FXCollections.observableArrayList();
            members.addAll(memberdao.fetchAll());
            adminController.tabelMember.setItems(members);
        }
    }

    public void ActionReset(ActionEvent actionEvent) {
        resetTiket();
    }

    private void resetTiket() {
        txtJam.clear();
        comboStudio.getSelectionModel().clearSelection();
        txtFilm.clear();
        txtPesan.clear();
        txtTotalBayar.clear();
        btnBeliTicket.setDisable(false);
    }

    public void ActionClose(ActionEvent actionEvent) {
        Platform.exit();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        studioDao = new StudioDaoImpl();
        ticketDao = new TicketDaoImpl();
        studios = FXCollections.observableArrayList();
        filmDao = new FilmDaoImpl();
        tickets= FXCollections.observableArrayList();
        films = FXCollections.observableArrayList();
        try {
            studios.addAll(studioDao.fetchAll());
            films.addAll(filmDao.fetchAll());
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        comboStudio.setItems(tickets);

    }
    public void setMain(UserController main){
        this.UserController = main;



    }
}
