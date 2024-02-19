package com.example.database;

import com.example.database.DbFunctions.DbFunctions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Statistika implements Initializable {
    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label col;

    @FXML
    private Label time;

    @FXML
    private PieChart piechart;

    private DbFunctions dbFunctions = new DbFunctions();

    @FXML
    void nazad(MouseEvent event) {
        new Loader().openNewScene(rootPane, "/com/example/database/AdminProfile-screen.fxml", "Профиль");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showFaultStatistics();
        showCompletedRequestCount();
        showAverageTime();
    }

    private void showFaultStatistics() {
        ObservableMap<String, Integer> statistics = dbFunctions.getFaultStatistics();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        for (String fault : statistics.keySet()) {
            int count = statistics.get(fault);
            PieChart.Data data = new PieChart.Data(fault, count);
            pieChartData.add(data);
        }

        piechart.setData(pieChartData);
    }

    private void showCompletedRequestCount() {
        int completedCount = dbFunctions.getCompletedRequestCount();
        col.setText(String.valueOf(completedCount));
    }
    private void showAverageTime() {
        double averageTime = Double.parseDouble(dbFunctions.getAverageRequestTime());
        time.setText(String.valueOf(averageTime));
    }
}


