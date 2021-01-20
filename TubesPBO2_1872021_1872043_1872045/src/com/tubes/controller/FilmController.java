package com.tubes.controller;

import com.tubes.Entity.Film;
import com.tubes.dao.FilmDaoImpl;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.sql.SQLException;

public class FilmController {
    public TableView<Film> tabelFilm;
    public Label lblIdFilm;
    public Label lblJudulFilm;
    public Label lblReleaseFilm;
    public Label lblDeskripsiFilm;
    public TextField txtIdFilm;
    public TextField txtJudulFilm;
    public TextField txtReleaseFilm;
    public TextField txtDeskripsiFilm;
    public Button btnSaveFilm;
    public Button btnUpdateFilm2;
    public Button btnResetFilm;
    private Film selectedFilm;
    private AdminController adminController;
    private ObservableList<Film> films;

    public void ActionSaveFilm(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(txtIdFilm.getText().trim().isEmpty() || txtJudulFilm.getText().trim().isEmpty() || txtDeskripsiFilm.getText().trim().isEmpty() || txtReleaseFilm.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill");
            alert.showAndWait();
        }
        else {

            Film film = new Film();
            film.setIdFilm(txtIdFilm.getText());
            film.setJudul(txtJudulFilm.getText());
            film.setRilis(txtReleaseFilm.getText());
            film.setDeskripsi(txtDeskripsiFilm.getText());
            FilmDaoImpl filmDao = new FilmDaoImpl();
            filmDao.addData(film);
            ObservableList<Film> mList = (ObservableList<Film>) filmDao.fetchAll();
            adminController.tabelFilm.setItems(mList);
            adminController.tabelFilm.refresh();
        }
    }

    public void ActionUpdateFilm2(ActionEvent actionEvent) {

    }
    private void resetFilm(){
        txtIdFilm.clear();
        txtDeskripsiFilm.clear();
        txtJudulFilm.clear();
        txtReleaseFilm.clear();
        selectedFilm = null;
        btnSaveFilm.setDisable(false);
        btnUpdateFilm2.setDisable(true);
    }

    public void ActionResetFilm(ActionEvent actionEvent) {
        resetFilm();
    }
}
