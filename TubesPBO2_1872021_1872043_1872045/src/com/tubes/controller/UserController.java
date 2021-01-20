package com.tubes.controller;

import com.tubes.Entity.Film;
import com.tubes.Entity.Member;
import com.tubes.Entity.Studio;
import com.tubes.Entity.User;
import com.tubes.Main;
import com.tubes.dao.FilmDaoImpl;
import com.tubes.dao.MemberDaoImpl;
import com.tubes.dao.StudioDaoImpl;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserController implements Initializable {
    @FXML
    private Menu menuFile;
    @FXML
    private Menu menuHelp;
    @FXML
    private TableView<Studio> tabelStudio;
    @FXML
    private TableColumn<Studio,Integer> colStudio;
    @FXML
    private TableColumn<Studio,Integer> colHarga;
    @FXML
    private TableColumn<Studio, String> colJam;
    @FXML
    private TableColumn<Studio, String> colFilm;
    @FXML
    private TableView<Film> tabelFilm;
    @FXML
    private TableColumn<Film, String> colJudul;
    @FXML
    private TableColumn<Film, String> colDeskripsi;
    @FXML
    private TableColumn <Film, String>colRelease;
    @FXML
    private Label lblHaloAdmin;
    @FXML
    private Button btnLogOut;
    @FXML
    private Button btnBeliTicket;
    @FXML
    private Label lblUser ;
    @FXML
    private Label lblSaldo;
    private ObservableList<Film> films;
    private ObservableList<Studio> studios;
    private StudioDaoImpl studioDao;
    private FilmDaoImpl filmDao;
    public Film UpFilm;
    public Studio UpStudio;
    private Studio selectedStudio;
    private Film selectedFilm;
    private LoginController main;


    @FXML
    private void ActionLogOut(ActionEvent actionEvent) throws IOException {
        btnLogOut.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/login_form.fxml"));
        Parent root = loader.load();
        Stage logout = new Stage();
        logout.setTitle("Login");
        logout.setScene(new Scene(root));
        logout.initModality(Modality.APPLICATION_MODAL);
        logout.showAndWait();
    }

    @FXML
    private void ActionBeliTicket(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/BeliTicket.fxml"));
        Parent root = loader.load();
        tiketController ticketController= loader.getController();
        ticketController.setMain(this);
        Stage secondPage = new Stage();
        secondPage.setTitle("Beli Tiket");
        secondPage.setScene(new Scene(root));
        secondPage.initModality(Modality.WINDOW_MODAL);
        secondPage.show();
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
        tabelFilm.setItems(films);
        tabelStudio.setItems(studios);

        colJudul.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getJudul()));
        colRelease.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRilis()));
        colDeskripsi.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDeskripsi()));
        colJam.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getJamTayang()));
        colHarga.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getHarga()).asObject());
        colStudio.setCellValueFactory(data-> new SimpleIntegerProperty(data.getValue().getIdStudio()).asObject());
        colFilm.setCellValueFactory(data-> new SimpleObjectProperty(data.getValue().getFilm_idFilm()));

    }

    public void closeAction(ActionEvent actionEvent) {
        System.exit(1);
    }

    public void aboutAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Created by Hosea,Dinda,Yolanda");

        alert.showAndWait();
    }


    public void setMain(LoginController main) {
        this.main=main;
        lblUser.setText(main.user1.getNamaUser());
        lblSaldo.setText(String.valueOf(main.user1.getSaldo()));


    }
}
