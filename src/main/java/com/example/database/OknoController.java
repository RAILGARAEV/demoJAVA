package com.example.database;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.example.database.DbFunctions.DbFunctions;
import com.example.database.Models.Singleton;

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

public class OknoController {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;

    @FXML
    private ComboBox<String> equipment;

    @FXML
    private ComboBox<String> priority;

    @FXML
    private ComboBox<String> ispolnitel;

    @FXML
    private ComboBox<String> tip;

    @FXML
    private TextField problem;

    @FXML
    private DatePicker date;

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
        ObservableList<String> prioritet = dbFunctions.getAllPrioritet();
        priority.setItems(prioritet);

        ObservableList<String> ispolniteli = FXCollections.observableArrayList(dbFunctions.getIspolniteli());
        ispolnitel.setItems(ispolniteli);

        ObservableList<String> equipments = FXCollections.observableArrayList(dbFunctions.getEquipments());
        equipment.setItems(equipments);

        ObservableList<String> tipni = FXCollections.observableArrayList(dbFunctions.getTip());
        tip.setItems(tipni);


        createTicket.setOnAction(e -> {
            String DatePicker = String.valueOf(date.getValue());
            String TextFieldEquipment = equipment.getValue();
            String TextFieldTip = tip.getValue();
            String ComboBoxPriority = priority.getValue();
            String ComboBoxIspolnitel = ispolnitel.getValue();
            String TextFieldProblem = problem.getText();
            String status = "Зарегистрировано";
            if (DatePicker.isEmpty() || TextFieldEquipment.isEmpty() || TextFieldTip.isEmpty() || TextFieldProblem.isEmpty()) {
                System.out.println("Заполните все строки");
            } else {
                dbFunctions.createTickets(DatePicker, TextFieldEquipment, TextFieldTip, ComboBoxPriority, ComboBoxIspolnitel, TextFieldProblem, status);
                new Loader().openNewScene(rootPane, "/com/example/database/AdminProfile-screen.fxml", "Профиль");
            }
        });


    }
}