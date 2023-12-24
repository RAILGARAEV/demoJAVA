package com.example.database;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.example.database.DbFunctions.DbFunctions;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class UpdateTickets {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bdel;

    @FXML
    private Button bref;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private DatePicker tdata;

    @FXML
    private TextField tfirst;

    @FXML
    private TextField tlast;

    @FXML
    private TextField tphone;

    @FXML
    private TextField tsecond;

    @FXML
    private ComboBox<String> tservices;

    @FXML
    private ComboBox<String> tstatus;

    @FXML
    private ComboBox<String> ttime;

    @FXML
    private ImageView nazad;

    String idTicket = "";

    private final DbFunctions dbFunctions = new DbFunctions();

    @FXML
    void initialize() {
        tservices.getItems().add("Массаж");
        tservices.getItems().add("Маникюр");
        tservices.getItems().add("Наращивание ресниц");
        tservices.getItems().add("Коррекция бровей");
        tservices.getItems().add("Увеличение губ");
        tservices.getItems().add("Окрашивание");
        tservices.getItems().add("Стрижка");

        ttime.getItems().add("11:00");
        ttime.getItems().add("12:00");
        ttime.getItems().add("13:00");
        ttime.getItems().add("14:00");
        ttime.getItems().add("15:00");
        ttime.getItems().add("16:00");
        ttime.getItems().add("17:00");
        ttime.getItems().add("18:00");

        tstatus.getItems().add("Отказ");
        tstatus.getItems().add("Принят");
        tstatus.getItems().add("Ожидание");
        tstatus.getItems().add("Выполнено");

        bref.setOnAction(e -> {

            updateDataTicket();

        });
        bdel.setOnAction(e -> deleteDataTicket());


    }

    public void setData(String fn, String sn, String ln, String ph, String dt, String vr, String ser, String stat, String id) {
        tfirst.setText(fn);
        tsecond.setText(sn);
        tlast.setText(ln);
        tphone.setText(ph);
        tdata.setValue(LocalDate.parse(dt));
        ttime.setValue(vr);
        tservices.setValue(ser);
        tstatus.setValue(stat);
        idTicket = id;
    }


    private void deleteDataTicket() {
        if (idTicket.equals("")) {
//            ErrorLabel.setText("Повторите попытку позже");
        } else {
            dbFunctions.deleteDataTicket(idTicket);
            bdel.getScene().getWindow().hide();

        }

    }

    private void updateDataTicket() {

        String firstname = tfirst.getText();
        String secondname = tsecond.getText();
        String lastname = tlast.getText();
        String phone = tphone.getText();
        String date = tdata.getValue().toString();
        String time = ttime.getValue();
        String services = tservices.getValue();
        String status = tstatus.getValue();

        if (firstname.isEmpty() || secondname.isEmpty() || lastname.isEmpty() || phone.isEmpty() || date.isEmpty() || time.isEmpty() || services.isEmpty() || status.isEmpty()) {
//            ErrorLabel.setText("Логин пустой");
        } else {

            dbFunctions.updateDataTickets(firstname, secondname, lastname, phone, date, time, services, status, idTicket);
            bref.getScene().getWindow().hide();


        }


    }

    @FXML
    public void Exit(MouseEvent event) {
        Stage stage = (Stage) nazad.getScene().getWindow();
        stage.close();
    }
}