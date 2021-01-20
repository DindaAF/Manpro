package com.tubes.controller;

import com.tubes.Entity.Film;
import com.tubes.Entity.Member;
import com.tubes.Entity.Studio;
import com.tubes.dao.FilmDaoImpl;
import com.tubes.dao.MemberDaoImpl;
import com.tubes.dao.StudioDaoImpl;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StudioController implements Initializable {
    public TableView<Film> tabelFilm;
    public TableView<Studio> tabelStudio;
    public Label lblStudio;
    public Label lblHarga;
    public Label lblJam;
    public Label lblFilm;
    public TextField txtStudio;
    public TextField txtHarga;
    public TextField txtJam;
    public Button btnSaveStudio;
    public Button btnResetStudio;
    public ComboBox<Film> comboFilm;
    public TextField txtNamaStudio;
    public Label lblNamaStudio;
    private Studio selectedStudio;
    private AdminController adminController;
    private ObservableList<Studio> studios;


    private ObservableList<Member> members;
    private ObservableList<Film> films;

    private MemberDaoImpl memberDao;
    private Member selectedMember;
    private StudioDaoImpl studioDao;
    private FilmDaoImpl filmDao;
    public Member UpMember;
    public Film UpFilm;
    public Studio UpStudio;
    private TableColumn<Studio,Integer> colStudio;
    @FXML
    private TableColumn<Studio,Integer> colHarga;
    @FXML
    private TableColumn<Studio, String> colJam;
    @FXML
    private TableColumn<Studio, String> colFilm;
    @FXML
    private TableColumn<Film, String> colIdFilm;
    @FXML
    private TableColumn<Film, String> colJudul;
    @FXML
    private TableColumn<Film, String> colReleasedate;
    @FXML
    private TableColumn<Film, String> colDeskripsi;
    @FXML
    public TableView<Member> tabelMember;
    @FXML
    private TableColumn<Member, String> colIdMember;
    @FXML
    private TableColumn<Member, String> colNama;
    @FXML
    private TableColumn<Member, String> colUsername;
    @FXML
    private TableColumn<Member, String> colPassword;
    @FXML
    private TableColumn<Member, String> colSaldo;

    private Film selectedFilm;






    public void ActionSaveStudio(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (txtStudio.getText().trim().isEmpty() || txtJam.getText().trim().isEmpty() || txtHarga.getText().trim().isEmpty()||comboFilm.getValue()==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill");
            alert.showAndWait();
        } else {
            Studio studio = new Studio();
            studio.setIdStudio(Integer.parseInt(txtStudio.getText()));
            studio.setNamaStudio(txtNamaStudio.getText());
            studio.setJamTayang(txtJam.getText());
            studio.setHarga(Integer.parseInt(txtHarga.getText()));
            studio.setFilm_idFilm(comboFilm.getValue());
            StudioDaoImpl studioDao = new StudioDaoImpl();
            studioDao.addData(studio);
            ObservableList<Studio> mList = (ObservableList<Studio>) studioDao.fetchAll();
            adminController.tabelStudio.setItems(mList);
            adminController.tabelStudio.refresh();
        }
    }



    private void resetStudio() {
        txtStudio.clear();
        txtHarga.clear();
        txtJam.clear();
        selectedStudio = null;
        btnSaveStudio.setDisable(false);
        comboFilm.getSelectionModel().clearSelection();
    }

    public void ActionResetStudio(ActionEvent actionEvent) {
        resetStudio();
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
        comboFilm.setItems(films);
    }
}
