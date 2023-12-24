package com.example.database.uchet;


import java.net.URL;
import java.util.ResourceBundle;

import com.example.database.DbFunctions.DbFunctions;
import com.example.database.Loader;
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
    private ComboBox<String> comboBoxServices;

    @FXML
    private ComboBox<String> comboBoxTime;

    @FXML
    private DatePicker dataPicker;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField textFieldFirstName;

    @FXML
    private TextField textFieldLastName;

    @FXML
    private TextField textFieldPhone;

    @FXML
    private TextField textFieldSecondName;

    @FXML
    private Button createTicket;

    DbFunctions dbFunctions = new DbFunctions();

    @FXML
    private ImageView ImgBack;

    @FXML
    void backScreen(MouseEvent event) {
        new Loader().openNewScene(rootPane, "/com/example/database/Client-screen.fxml", "Профиль");
    }

    @FXML
    void initialize() {

        comboBoxServices.getItems().add("Массаж");
        comboBoxServices.getItems().add("Маникюр");
        comboBoxServices.getItems().add("Наращивание ресниц");
        comboBoxServices.getItems().add("Коррекция бровей");
        comboBoxServices.getItems().add("Увеличение губ");
        comboBoxServices.getItems().add("Окрашивание");
        comboBoxServices.getItems().add("Стрижка");
        comboBoxTime.getItems().add("11:00");
        comboBoxTime.getItems().add("12:00");
        comboBoxTime.getItems().add("13:00");
        comboBoxTime.getItems().add("14:00");
        comboBoxTime.getItems().add("15:00");
        comboBoxTime.getItems().add("16:00");
        comboBoxTime.getItems().add("17:00");
        comboBoxTime.getItems().add("18:00");





        createTicket.setOnAction(e->{



            String FirstName = textFieldFirstName.getText();
            String SecondName = textFieldSecondName.getText();
            String LastName = textFieldLastName.getText();
            String Phone = textFieldPhone.getText();
            String Data = String.valueOf(dataPicker.getValue());
            String Time = String.valueOf(comboBoxTime.getValue());
            String Services = String.valueOf(comboBoxServices.getValue());
            String Status = "Ожидание";


            if (FirstName.isEmpty() || SecondName.isEmpty() || LastName.isEmpty() || Phone.isEmpty() || Data.isEmpty() || Time.isEmpty() || Services.isEmpty()){
                System.out.println("Заполните все строки");

            } else {
                dbFunctions.createTickets(FirstName,SecondName,LastName,Phone,Data,Time,Services,Status);
                new Loader().openNewScene(rootPane, "/com/example/database/auth-screen.fxml", "Авторизация");
            }



        });





    }

}
