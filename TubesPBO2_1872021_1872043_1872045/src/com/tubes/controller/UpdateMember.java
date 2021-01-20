package com.tubes.controller;

import com.tubes.Entity.Member;
import com.tubes.dao.MemberDaoImpl;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class UpdateMember {
    @FXML
    private Label lblIdMember;
    @FXML
    private Label lblNama;
    @FXML
    private Label lblUsername;
    @FXML
    private Label lblPassword;
    @FXML
    private TextField txtIdMember;
    @FXML
    private TextField txtNama;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private Label lblSaldo;
    @FXML
    private TextField txtSaldo;
    @FXML
    private Button btnUpdateMember;
    @FXML
    private Button btnResetMember;

    private MemberDaoImpl memberDao;
    private Member selectedMember;
    private AdminController adminController;
    private MemberController memberController;

    @FXML
    private void ActionUpdateMember(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (txtIdMember.getText().trim().isEmpty() || txtNama.getText().trim().isEmpty() || txtUsername.getText().trim().isEmpty() || txtPassword.getText().trim().isEmpty() || txtSaldo.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill");
            alert.showAndWait();
        } else {
            String id = txtIdMember.getText();
            String namauser = txtNama.getText();
            String username = txtUsername.getText();
            String password = txtPassword.getText();
            int saldo = Integer.parseInt(txtSaldo.getText());
            Member member = new Member();
            member.setIdUser(id);
            member.setNamaUser(namauser);
            member.setUsername(username);
            member.setPassword(password);
            member.setSaldo(saldo);
            MemberDaoImpl memberDao = new MemberDaoImpl();
            memberDao.updateData(member);
            ObservableList<Member> List = (ObservableList<Member>)memberDao.fetchAll();
            adminController.tabelMember.setItems(List);
            adminController.tabelMember.refresh();
        }
    }

    @FXML
    private void ActionResetMember(ActionEvent actionEvent) {
        resetMember();
    }

    private void resetMember(){
        txtIdMember.clear();
        txtNama.clear();
        txtUsername.clear();
        txtPassword.clear();
        txtSaldo.clear();
        selectedMember = null;
        btnUpdateMember.setDisable(true);
    }

    public void setMain(AdminController main){
        this.adminController = main;
        txtIdMember.setText(adminController.UpMember.getIdUser());
        txtNama.setText(adminController.UpMember.getNamaUser());
        txtUsername.setText(adminController.UpMember.getUsername());
        txtPassword.setText(adminController.UpMember.getPassword());
        txtSaldo.setText(String.valueOf(Integer.valueOf(adminController.UpMember.getSaldo())));
        System.out.println(adminController.UpMember.getIdUser());
    }
}
