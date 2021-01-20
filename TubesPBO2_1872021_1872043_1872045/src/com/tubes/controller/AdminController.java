package com.tubes.controller;

import com.tubes.Entity.Film;
import com.tubes.Entity.Member;
import com.tubes.Entity.Studio;
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

public class AdminController implements Initializable {

    public Menu menuFile;
    public Menu menuHelp;
    public TableView<Member> tabelMember;
    public TableColumn<Member, String> colIdMember;
    public TableColumn<Member, String> colNama;
    public TableColumn<Member, String> colUsername;
    public TableColumn<Member, String> colPassword;
    public TableColumn<Member, String> colSaldo;

    public TableView<Film> tabelFilm;
    public TableColumn<Film, String> colIdFilm;
    public TableColumn<Film, String> colJudul;
    public TableColumn<Film, String> colReleasedate;
    public TableColumn<Film, String> colDeskripsi;
    public TableView<Studio> tabelStudio;
    public TableColumn<Studio,String> colStudio;
    public TableColumn<Studio,Integer> colHarga;
    public TableColumn<Studio, String>  colJam;
    public TableColumn<Studio, String>  colFilm;

    ////////Top Up
    public Label lblIdMember;
    public Label lblNama;
    public Label lblUsername;
    public Label lblPassword;
    public TextField txtPassword;

    public ComboBox<Member>comboName;
    public DatePicker tanggal;
    public Button btnTopUp2;
    public Button btnReset;
    public Button btnClose;



    //////////
    @FXML
    private Label lblStudio;
    @FXML
    private Label lblFilm;
    @FXML
    private Label lblMember;
    @FXML
    private Label lblHaloAdmin;
    @FXML
    private Button btnTopUp;
    @FXML
    private Button btnLogOut;
    @FXML
    private Button btnAddStudio;
    @FXML
    private Button btnUpdateStudio;
    @FXML
    private Button btnDeleteStudio;
    @FXML
    private Button btnAddFilm;
    @FXML
    private Button btnUpdateFilm;
    @FXML
    private Button btnAddMember;
    @FXML
    private Button btnDeleteFilm;
    @FXML
    private Button btnUpdateMember;
    @FXML
    private Button btnDeleteMember;
    @FXML

    private ComboBox<Film> comboFilm;
    private ObservableList<Member> members;
    private ObservableList<Film> films;
    private ObservableList<Studio> studios;
    private MemberDaoImpl memberDao;
    private Member selectedMember;
    private StudioDaoImpl studioDao;
    private FilmDaoImpl filmDao;
    public Member UpMember;
    public Film UpFilm;
    public Studio UpStudio;
    private Studio selectedStudio;
    private Film selectedFilm;


    private LoginController main;

    public void setMain(LoginController main) {
        this.main=main;
    }


    @FXML
    private void ActionTopUp(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException  {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/TopUp.fxml"));
        Parent root = loader.load();
        TopUpController Con= loader.getController();
        Con.setMain(this);
        Stage topupPage = new Stage();
        topupPage.setTitle("Top Up");
        topupPage.setScene(new Scene(root));
        topupPage.initModality(Modality.WINDOW_MODAL);
        topupPage.showAndWait();
        MemberDaoImpl dao = new MemberDaoImpl();
        members = FXCollections.observableArrayList();
        members.addAll(dao.fetchAll());
        tabelMember.setItems(members);
    }

    @FXML
    private void ActionLogOut(ActionEvent actionEvent) throws IOException {
        btnLogOut.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/login_form.fxml"));
        Parent root = loader.load();
        Stage adminStage = new Stage();
        adminStage.setTitle("Login");
        adminStage.setScene(new Scene(root));
        adminStage.initModality(Modality.APPLICATION_MODAL);
        adminStage.show();
    }

    @FXML
    private void ActionAddStudio(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/addStudio.fxml"));
        Parent root = loader.load();
        Stage studioPage = new Stage();
        studioPage.setTitle("Tambah Studio Data");
        studioPage.setScene(new Scene(root));
        studioPage.initModality(Modality.WINDOW_MODAL);
        studioPage.showAndWait();
        StudioDaoImpl studioDao = new StudioDaoImpl();
        studios = FXCollections.observableArrayList();
        studios.addAll(studioDao.fetchAll());
        tabelStudio.setItems(studios);
    }

    @FXML
    private void ActionUpdateStudio(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        UpStudio = (Studio) tabelStudio.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/UpdateStudio.fxml"));
        Parent root = loader.load();
        UpdateStudio updateStudio = loader.getController();
        updateStudio.setMain(this);
        Stage studioUpPage = new Stage();
        studioUpPage.setTitle("Update Studio Data");
        studioUpPage.setScene(new Scene(root));
        studioUpPage.initModality(Modality.WINDOW_MODAL);
        studioUpPage.showAndWait();

        StudioDaoImpl studioDao= new StudioDaoImpl();
        studios = FXCollections.observableArrayList();
        studios.addAll(studioDao.fetchAll());
        tabelStudio.setItems(studios);
    }

    @FXML
    private void ActionDeleteStudio(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        selectedStudio = tabelStudio.getSelectionModel().getSelectedItem();
        System.out.println(selectedStudio);
        StudioDaoImpl studioDao = new StudioDaoImpl();
        if (studioDao.deleteData(selectedStudio) == 1) {
            studios.clear();
            studios.addAll(studioDao.fetchAll());
        }
    }



    @FXML
    private void ActionUpdateFilm(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        UpFilm = (Film) tabelFilm.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/UpdateFilm.fxml"));
        Parent root = loader.load();
        UpdateFilm updateFilm = loader.getController();
        updateFilm.setMain(this);
        Stage FilmUpPage = new Stage();
        FilmUpPage.setTitle("Update Film Data");
        FilmUpPage.setScene(new Scene(root));
        FilmUpPage.initModality(Modality.WINDOW_MODAL);
        FilmUpPage.showAndWait();
        FilmDaoImpl filmDao= new FilmDaoImpl();
        films = FXCollections.observableArrayList();
        films.addAll(filmDao.fetchAll());
        tabelFilm.setItems(films);
    }



    @FXML
    private void ActionAddMember(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/addMember.fxml"));
        Parent root = loader.load();
        Stage secondPage = new Stage();
        secondPage.setTitle("Tambah Member Data");
        secondPage.setScene(new Scene(root));
        secondPage.initModality(Modality.WINDOW_MODAL);
        secondPage.showAndWait();
        MemberDaoImpl dao = new MemberDaoImpl();
        members = FXCollections.observableArrayList();
        members.addAll(dao.fetchAll());
        tabelMember.setItems(members);
    }

    @FXML
    private void ActionUpdateMember(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        UpMember = (Member) tabelMember.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/UpdateMember.fxml"));
        Parent root = loader.load();
        UpdateMember updateMember = loader.getController();
        updateMember.setMain(this);
        Stage secondPage = new Stage();
        secondPage.setTitle("Update Member Data");
        secondPage.setScene(new Scene(root));
        secondPage.initModality(Modality.WINDOW_MODAL);
        secondPage.showAndWait();
        MemberDaoImpl dao = new MemberDaoImpl();
        members = FXCollections.observableArrayList();
        members.addAll(dao.fetchAll());
        tabelMember.setItems(members);
    }

    @FXML
    private void ActionDeleteMember(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        selectedMember = tabelMember.getSelectionModel().getSelectedItem();
        System.out.println(selectedMember);
        MemberDaoImpl memberDao = new MemberDaoImpl();
        if (memberDao.deleteData(selectedMember) == 1) {
            members.clear();
            members.addAll(memberDao.fetchAll());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        studioDao = new StudioDaoImpl();
        studios = FXCollections.observableArrayList();
        filmDao = new FilmDaoImpl();
        films = FXCollections.observableArrayList();
        memberDao = new MemberDaoImpl();
        members = FXCollections.observableArrayList();
        try {
            studios.addAll(studioDao.fetchAll());
            films.addAll(filmDao.fetchAll());
            members.addAll(memberDao.fetchAll());
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        tabelStudio.setItems(studios);
        colStudio.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getNamaStudio()));
        colHarga.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getHarga()).asObject());
        colJam.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getJamTayang()));
        colFilm.setCellValueFactory(data-> new SimpleObjectProperty(data.getValue().getFilm_idFilm()));

        tabelMember.setItems(members);
        colIdFilm.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getIdFilm()));
        colJudul.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getJudul()));
        colReleasedate.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRilis()));
        colDeskripsi.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDeskripsi()));

        tabelFilm.setItems(films);
        colIdMember.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getIdUser()));
        colNama.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNamaUser()));
        colUsername.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUsername()));
        colPassword.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPassword()));
        colSaldo.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getSaldo())));

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


    /////// ACTION TOP UP


    public void ActionReset(ActionEvent actionEvent) {

    }

    public void ActionClose(ActionEvent actionEvent) {
    }

    public void ActionDeleteFilm(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        selectedFilm = tabelFilm.getSelectionModel().getSelectedItem();
        System.out.println(selectedFilm);
        FilmDaoImpl filmDao = new FilmDaoImpl();
        if (filmDao.deleteData(selectedFilm)==1){
            films.clear();
            films.addAll(filmDao.fetchAll());
        }
    }

    public void ActionAddFilm(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/addFilm.fxml"));
        Parent root = loader.load();
        Stage filmPage = new Stage();
        filmPage.setTitle("Tambah Film Data");
        filmPage.setScene(new Scene(root));
        filmPage.initModality(Modality.WINDOW_MODAL);
        filmPage.showAndWait();
        FilmDaoImpl filmDao = new FilmDaoImpl();
        films = FXCollections.observableArrayList();
        films.addAll(filmDao.fetchAll());
        tabelFilm.setItems(films);
    }
}