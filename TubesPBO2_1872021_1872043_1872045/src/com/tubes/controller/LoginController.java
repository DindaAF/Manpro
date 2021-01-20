
package com.tubes.controller;

import com.tubes.Entity.User;
import com.tubes.Main;
import com.tubes.dao.UserDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigInteger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class LoginController {
    public Button login;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    public int iduser = 0;
    @FXML
    private GridPane rootGrid;

    private AdminController main;
    public User user1;


    public static String getMd5(String input) {
        try {
            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            // of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    private void loginAction(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        String username = txtUsername.getText();
        String pass = txtPassword.getText();
        txtUsername.setText("");
        txtPassword.setText("");
        User user = new User();
        user.setUsername(username);
        user.setPassword(pass);
        UserDaoImpl userDao = new UserDaoImpl();
        user1 = userDao.Login(user);

        System.out.println(user1.getIdUser());
        System.out.println(user1.getRole());


        if (username==null || pass==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill username and password");
            alert.showAndWait();
        } else {
            if (user1.getRole().equals("admin")) {
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/admin.fxml"));
                Parent root = loader.load();
                rootGrid.getScene().getWindow().hide();
                AdminController adminController = loader.getController();
                adminController.setMain(this);
                Stage adminStage = new Stage();
                adminStage.setTitle("Admin Management");
                adminStage.setScene(new Scene(root));
                adminStage.initOwner(rootGrid.getScene().getWindow());
                adminStage.initModality(Modality.APPLICATION_MODAL);
                adminStage.show();

            } else if (user1.getRole().equals("member")) {
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/user.fxml"));
                Parent root = loader.load();
                rootGrid.getScene().getWindow().hide();
                UserController userController = loader.getController();
                userController.setMain(this);
                Stage userStage = new Stage();
                userStage.setTitle("Bioskop HYD");
                userStage.setScene(new Scene(root));
                userStage.initOwner(rootGrid.getScene().getWindow());
                userStage.initModality(Modality.APPLICATION_MODAL);
                userStage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid username or password");
                alert.showAndWait();
            }
        }
    }
}




