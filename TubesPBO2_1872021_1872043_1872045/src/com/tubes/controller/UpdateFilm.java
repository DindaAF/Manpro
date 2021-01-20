package com.tubes.controller;

import com.tubes.Entity.Film;
import com.tubes.dao.FilmDaoImpl;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class UpdateFilm {
    public Label lblIdFilm;
    public Label lblJudulFilm;
    public Label lblReleaseFilm;
    public Label lblDeskripsiFilm;
    public TextField txtIdFilm;
    public TextField txtJudulFilm;
    public TextField txtReleaseFilm;
    public TextField txtDeskripsiFilm;
    public Button btnUpdateFilm2;
    public Button btnResetFilm;
    private AdminController adminController;
    private Film selectedFilm;

    public void ActionUpdateFilm2(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (txtIdFilm.getText().trim().isEmpty() || txtJudulFilm.getText().trim().isEmpty() || txtReleaseFilm.getText().trim().isEmpty() || txtDeskripsiFilm.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill");
            alert.showAndWait();
        } else {
            String id = txtIdFilm.getText();
            String judulFilm = txtJudulFilm.getText();
            String releaseFilm = txtReleaseFilm.getText();
            String deskripsiFilm = txtDeskripsiFilm.getText();
            Film film = new Film();
            film.setIdFilm(id);
            film.setJudul(judulFilm);
            film.setRilis(releaseFilm);
            film.setDeskripsi(deskripsiFilm);
            FilmDaoImpl filmDao = new FilmDaoImpl();
            filmDao.updateData(film);
            ObservableList<Film> List = (ObservableList<Film>) filmDao.fetchAll();
            adminController.tabelFilm.setItems(List);
            adminController.tabelFilm.refresh();
        }
    }

    public void ActionResetFilm(ActionEvent actionEvent) { resetFilm();
    }
    private void resetFilm(){
        txtIdFilm.clear();
        txtJudulFilm.clear();
        txtReleaseFilm.clear();
        txtDeskripsiFilm.clear();
        selectedFilm = null;
        btnUpdateFilm2.setDisable(true);
    }
    public void setMain(AdminController main){
        this.adminController = main;
        txtIdFilm.setText(adminController.UpFilm.getIdFilm());
        txtJudulFilm.setText(adminController.UpFilm.getJudul());
        txtDeskripsiFilm.setText(adminController.UpFilm.getDeskripsi());
        txtReleaseFilm.setText(adminController.UpFilm.getJudul());
        System.out.println(adminController.UpFilm.getIdFilm());
    }
}
