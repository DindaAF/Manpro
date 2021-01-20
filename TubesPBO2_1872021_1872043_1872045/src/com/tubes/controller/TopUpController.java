package com.tubes.controller;

import com.tubes.Entity.Member;
import com.tubes.dao.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TopUpController implements Initializable {
    public Label lblIdMember;
    public Label lblNama;

    public ComboBox<Member> comboName;
    public Button btnTopUp2;
    public Button btnReset;
    public Button btnClose;
    public TextField txtTopUp;
    public Label lblTopUp;
    private MemberDaoImpl memberDao;
    private ObservableList<Member> members;
    private AdminController adminController;

    public void ActionTopUp2(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (comboName.getValue()==null || txtTopUp.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill");
            alert.showAndWait();
        } else {
            Member member = new Member();
            member.setIdUser(comboName.getValue().getIdUser());
            member.setSaldo(comboName.getValue().getSaldo());
            member.tambahSaldo(Integer.parseInt(txtTopUp.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Berhasil Top Up user "+member.getIdUser());
            alert.showAndWait();
            MemberDaoImpl dao = new MemberDaoImpl();
            dao.updateSaldo(member);
            members = FXCollections.observableArrayList();
            members.addAll(dao.fetchAll());
            adminController.tabelMember.setItems(members);
        }

    }

    public void ActionReset(ActionEvent actionEvent) {
        resetTopUp();
    }

    private void resetTopUp() {
        txtTopUp.clear();

        comboName.getSelectionModel().clearSelection();
        btnTopUp2.setDisable(false);
    }


    public void ActionClose(ActionEvent actionEvent) {
        Platform.exit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        memberDao = new MemberDaoImpl();
        members = FXCollections.observableArrayList();


        try {

            members.addAll(memberDao.fetchAll());
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        comboName.setItems(members);
    }
    public void setMain(AdminController main){
        this.adminController = main;


    }

}
