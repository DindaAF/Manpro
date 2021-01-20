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

public class MemberController {
    public TextField txtRole;
    public Label lblRole;
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
    private Button btnSaveMember;
    @FXML
    private Button btnUpdateMember;
    @FXML
    private Button btnResetMember;

    private MemberDaoImpl memberDao;
    private Member selectedMember;
    private AdminController adminController;
    private MemberController memberController;
    private AdminController main;

    @FXML
    private void ActionSaveMember(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(txtIdMember.getText().trim().isEmpty() || txtNama.getText().trim().isEmpty() || txtUsername.getText().trim().isEmpty() || txtPassword.getText().trim().isEmpty() || txtSaldo.getText().trim().isEmpty()||txtRole.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill");
            alert.showAndWait();
        }
        else {
            String id = txtIdMember.getText();
            String namauser = txtNama.getText();
            String username = txtUsername.getText();
            String password = txtPassword.getText();
            String Role = txtRole.getText();
            int saldo = Integer.parseInt(txtSaldo.getText());
            Member member = new Member();
            member.setIdUser(id);
            member.setNamaUser(namauser);
            member.setUsername(username);
            member.setPassword(password);
            member.setSaldo(saldo);
            member.setRole(Role);
            MemberDaoImpl memberDao = new MemberDaoImpl();
            memberDao.addData(member);
            ObservableList<Member> mList = (ObservableList<Member>) memberDao.fetchAll();
            adminController.tabelMember.setItems(mList);
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
        txtRole.clear();
        selectedMember = null;
        btnSaveMember.setDisable(true);
    }

    public void setmain(AdminController main) {
        this.main = main;
    }
}
