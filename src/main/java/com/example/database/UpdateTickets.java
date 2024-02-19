package com.example.database;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

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
    private DatePicker tdate;

    @FXML
    private ComboBox<String> tequipment;

    @FXML
    private ComboBox<String> ttip;

    @FXML
    private ComboBox<String> tmaterials;

    @FXML
    private TextField tproblem;

    @FXML
    private TextField ttime;

    @FXML
    private TextField tcomment;

    @FXML
    private TextField tcolmaterials;

    @FXML
    private TextField tcost;

    @FXML
    private ComboBox<String> tispolnitel;

    @FXML
    private ComboBox<String> tremont;

    @FXML
    private ComboBox<String> tpriority;

    @FXML
    private ComboBox<String> tstatus;


    @FXML
    private ImageView nazad;

    String idRequest = "";

    private final DbFunctions dbFunctions = new DbFunctions();

    @FXML
    void initialize() {

        ObservableList<String> status = dbFunctions.getAllStatus();
        tstatus.setItems(status);

        ObservableList<String> prioritet = dbFunctions.getAllPrioritet();
        tpriority.setItems(prioritet);

        ObservableList<String> ispolniteli = FXCollections.observableArrayList(dbFunctions.getIspolniteli());
        tispolnitel.setItems(ispolniteli);

        ObservableList<String> equipments = FXCollections.observableArrayList(dbFunctions.getEquipments());
        tequipment.setItems(equipments);

        ObservableList<String> tipni = FXCollections.observableArrayList(dbFunctions.getTip());
        ttip.setItems(tipni);

        ObservableList<String> tcomm = FXCollections.observableArrayList(dbFunctions.getComment());
        tremont.setItems(tcomm);

        ObservableList<String> tmat = FXCollections.observableArrayList(dbFunctions.getMaterials());
        tmaterials.setItems(tmat);

        bref.setOnAction(e -> {

            updateDataTicket();

        });
        bdel.setOnAction(e -> deleteDataTicket());


    }

    public void setData(String dt, String eq, String tp, String prior, String is, String pr, String stat, String rm, String tm, String mt, String cmat, String ct, String cm, String id) {
        tdate.setValue(LocalDate.parse(dt));
        tequipment.setValue(eq);
        ttip.setValue(tp);
        tpriority.setValue(prior);
        tispolnitel.setValue(is);
        tproblem.setText(pr);
        tstatus.setValue(stat);
        tremont.setValue(rm);
        ttime.setText(tm);
        tmaterials.setValue(mt);
        tcolmaterials.setText(cmat);
        tcost.setText(ct);
        tcomment.setText(cm);
        idRequest = id;
    }


    private void deleteDataTicket() {
        if (idRequest.equals("")) {
//            ErrorLabel.setText("Повторите попытку позже");
        } else {
            dbFunctions.deleteDataTicket(idRequest);
            bdel.getScene().getWindow().hide();

        }

    }

    private void updateDataTicket() {

        String date = tdate.getValue().toString();
        String equipment = tequipment.getValue();
        String tip = ttip.getValue();
        String priority = tpriority.getValue();
        String ispolnitel = tispolnitel.getValue();
        String problem = tproblem.getText();
        String status = tstatus.getValue();
        String remont = tremont.getValue();
        String time = ttime.getText();
        String materials = tmaterials.getValue();
        String colmaterials = tcolmaterials.getText();
        String cost = tcost.getText();
        String comment = tcomment.getText();
        if (date.isEmpty() || equipment.isEmpty() || tip.isEmpty() || priority.isEmpty() || ispolnitel.isEmpty() ||
                problem.isEmpty() || status.isEmpty() || remont.isEmpty() || time.isEmpty() || materials.isEmpty() || colmaterials.isEmpty() || cost.isEmpty() || comment.isEmpty()) {
        } else {

            dbFunctions.updateDataTickets(date, equipment, tip, priority, ispolnitel, problem, status, remont, time, materials, colmaterials, cost, comment, idRequest);
            bref.getScene().getWindow().hide();

        }


    }

    @FXML
    public void Exit(MouseEvent event) {
        Stage stage = (Stage) nazad.getScene().getWindow();
        stage.close();
    }
}