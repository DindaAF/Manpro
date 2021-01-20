package com.tubes.controller;

import com.tubes.Entity.Film;
import com.tubes.Entity.Member;
import com.tubes.Entity.Studio;
import com.tubes.dao.FilmDaoImpl;
import com.tubes.dao.MemberDaoImpl;
import com.tubes.dao.StudioDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateStudio implements Initializable {
    public TableView<Film> tabelFilm;
    public TableView<Studio> tabelStudio;
    public Label lblStudio;
    public Label lblHarga;
    public Label lblJam;
    public Label lblFilm;
    public TextField txtStudio;
    public TextField txtHarga;
    public TextField txtJam;
    public TextField txtFilm;
    public Button btnUpdateStudio;
    public Button btnResetStudio;
    public ComboBox<Film> comboUpStudio;
    public TextField txtIdStudio;
    public Label IdStudio;
    private AdminController adminController;
    private Studio selectedStudio;


    private ObservableList<Studio> studios;

    private ObservableList<Film> films;

    private StudioDaoImpl studioDao;
    private FilmDaoImpl filmDao;


    public void ActionUpdateStudio(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (txtStudio.getText().trim().isEmpty() || txtHarga.getText().trim().isEmpty() || txtJam.getText().trim().isEmpty()||comboUpStudio.getValue()==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill");
            alert.showAndWait();
        } else {
            Studio studio = new Studio();
            studio.setIdStudio(Integer.parseInt(txtIdStudio.getText()));
            studio.setNamaStudio(txtStudio.getText());
            studio.setJamTayang(txtJam.getText());
            studio.setHarga(Integer.parseInt(txtHarga.getText()));
            studio.setFilm_idFilm(comboUpStudio.getValue());
            StudioDaoImpl studioDao = new StudioDaoImpl();
            studioDao.updateData(studio);
            ObservableList<Studio> mList = (ObservableList<Studio>) studioDao.fetchAll();
            adminController.tabelStudio.setItems(mList);
            adminController.tabelStudio.refresh();
        }
    }

    public void ActionResetStudio(ActionEvent actionEvent) { resetStudio();
    }
    private void resetStudio() {
        txtStudio.clear();
        txtHarga.clear();
        txtJam.clear();
        selectedStudio = null;
        comboUpStudio.getSelectionModel().clearSelection();
        btnUpdateStudio.setDisable(false);
    }



    public void setMain(AdminController main){
        this.adminController = main;
        txtIdStudio.setText(String.valueOf(adminController.UpStudio.getIdStudio()));
        txtStudio.setText(adminController.UpStudio.getNamaStudio());
        txtHarga.setText(String.valueOf(adminController.UpStudio.getHarga()));
        //txtFilm.setText(String.valueOf(adminController.UpStudio.getFilm_idFilm()));
        txtJam.setText(adminController.UpStudio.getJamTayang());
        System.out.println(adminController.UpStudio.getIdStudio());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        studioDao = new StudioDaoImpl();
        studios = FXCollections.observableArrayList();
        filmDao = new FilmDaoImpl();
        films = FXCollections.observableArrayList();
        try {
            studios.addAll(studioDao.fetchAll());
            films.addAll(filmDao.fetchAll());
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        comboUpStudio.setItems(films);
    }
}
