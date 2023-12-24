package com.example.database;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.database.DbFunctions.Variables;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class RegistrationForEmployees {
    @FXML
    private AnchorPane rootPane;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button nazad;


    @FXML
    void initialize() {
        nazad.setOnAction(e -> {
            new Loader().openNewScene(rootPane, "/com/example/database/come-screen.fxml", "Салон красоты Ирина");
        });
    }
}
