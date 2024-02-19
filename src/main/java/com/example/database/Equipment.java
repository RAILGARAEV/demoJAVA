package com.example.database;

import com.example.database.DbFunctions.DbFunctions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Equipment {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;

    @FXML
    private TextField naimenovanie;

    @FXML
    private TextField price;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button createTicket;

    @FXML
    private ImageView ImgBack;

    DbFunctions dbFunctions = new DbFunctions();

    @FXML
    void backScreen(MouseEvent event) {
        new Loader().openNewScene(rootPane, "/com/example/database/AdminProfile-screen.fxml", "Профиль");
    }

    @FXML
    void initialize() {


        createTicket.setOnAction(e -> {
            String TextFieldNaimenovanie = naimenovanie.getText();
            String TextFieldPrice = price.getText();
            if (TextFieldNaimenovanie.isEmpty() || TextFieldPrice.isEmpty()) {
                System.out.println("Заполните все строки");
            } else {
                dbFunctions.createEquipment(TextFieldNaimenovanie, TextFieldPrice);
                new Loader().openNewScene(rootPane, "/com/example/database/AdminProfile-screen.fxml", "Профиль");
            }
        });
    }
}