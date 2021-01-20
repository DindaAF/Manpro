package com.tubes.controller;

import com.tubes.Entity.Film;
import com.tubes.Entity.Member;
import com.tubes.Entity.Studio;
import com.tubes.Entity.Ticket;
import com.tubes.dao.FilmDaoImpl;
import com.tubes.dao.MemberDaoImpl;
import com.tubes.dao.StudioDaoImpl;
import com.tubes.dao.TicketDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class tiketController implements Initializable {
    public Label lblStudio;
    public Label lblHarga;
    public Label lblJam;
    public Label lblFilm;
    public TextField txtFilm;
    public ComboBox comboStudio;
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
    private UserController main;

    public void ActionBeliTicket(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if ( comboStudio.getValue()==null|| txtFilm.getText().trim().isEmpty() || txtTotalBayar.getText().trim().isEmpty() || txtPesan.getText().trim().isEmpty()||txtJam.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill");
            alert.showAndWait();
        } else {
            Ticket ticket = new Ticket();
            ticket.setStudio_IdStudio(main.studioselect);
            ticket.setStudio_Film_IdFilm(main.studioselect);
            ticket.setHarga(main.studioselect.getHarga());
            ticket.setJam(main.studioselect.getJamTayang());
            ticket.setJumlahpesan(Integer.parseInt(txtPesan.getText()));
            ticket.setTotalbayar(Integer.parseInt(txtTotalBayar.getText()));
            Member member = new Member();
//            member.setIdUser(LoginController.user1);
            int s=member.getSaldo();
            System.out.println(s);
//            member.setSaldo();
            member.kurangSaldo(Integer.parseInt(txtTotalBayar.getText()));;

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Berhasil Membeli tiket ");
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
        txtPesan.clear();
        txtTotalBayar.clear();
        btnBeliTicket.setDisable(false);
    }

    public void ActionClose(ActionEvent actionEvent) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    public void setMain(UserController main){
        this.main = main;
        txtPesan.setText("0");
        comboStudio.setDisable(true);
        comboStudio.setValue(main.studioselect.getNamaStudio());
        txtHarga.setText(String.valueOf(main.studioselect.getHarga()));
        txtHarga.setDisable(true);
        txtFilm.setText(String.valueOf(main.studioselect.getFilm_idFilm()));
        txtFilm.setDisable(true);
        txtJam.setText(main.studioselect.getJamTayang());
        txtJam.setDisable(true);
        System.out.println(main.studioselect.getIdStudio());
        txtTotalBayar.setDisable(true);
    }

    public void tes(KeyEvent inputMethodEvent) {
        int harga= Integer.parseInt(txtHarga.getText());
        int pesan= Integer.parseInt(txtPesan.getText());
        if(txtPesan.getText()==null){
            pesan=0;
        }
        txtTotalBayar.setText(String.valueOf(harga*pesan));
    }
}
