package com.example.database.uchet;

import com.example.database.Loader;
import javafx.fxml.FXML;
import javafx.print.PageLayout;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Scale;

public class PribController {
    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();

    @FXML
    private AnchorPane AnchorPane;

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private Button nazad;

    @FXML
    private Button printBtn;

    @FXML
    private void initialize() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        series.getData().add(new XYChart.Data<>("Аренда", 70000));
        series.getData().add(new XYChart.Data<>("Зарплата персонала", 205000));
        series.getData().add(new XYChart.Data<>("Моющие средства", 30000));
        series.getData().add(new XYChart.Data<>("Хозинвентарь", 15000));
        series.getData().add(new XYChart.Data<>("Коммунальные платежи", 23000));
        series.getData().add(new XYChart.Data<>("Вывоз мусора", 1500));
        series.getData().add(new XYChart.Data<>("Реклама в соцсетях", 55000));
        series.getData().add(new XYChart.Data<>("Программное обеспечение", 40000));
        series.getData().add(new XYChart.Data<>("Охрана", 45000));
        series.getData().add(new XYChart.Data<>("Налоги и взносы", 110000));
        series.getData().add(new XYChart.Data<>("Чистая прибыль", 112000));





        barChart.setLegendVisible(false);
        barChart.getData().add(series);

        for (Node n : barChart.lookupAll(".default-color0.chart-bar")) {
            n.setStyle("-fx-bar-fill: red;");

            nazad.setOnAction(event -> {
                new Loader().openNewScene(AnchorPane, "/com/example/database/uchet-screen.fxml", "Учет деятельности");
            });

            printBtn.setOnAction( aEvent -> {
                print(AnchorPane);
            });



        }

    }

    @FXML
    public void print(Node node) {
        printBtn.setVisible(false);
        nazad.setVisible(false);
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null && job.showPrintDialog(null)) {
            PageLayout pageLayout = job.getJobSettings().getPageLayout();
            double scaleX = 1.0;
            if (pageLayout.getPrintableWidth() < node.getBoundsInParent().getWidth()){
                scaleX = pageLayout.getPrintableWidth() / node.getBoundsInParent().getWidth();
            }
            double scaleY = 1.0;
            if (pageLayout.getPrintableHeight() < node.getBoundsInParent().getHeight()){
                scaleY = pageLayout.getPrintableHeight() / node.getBoundsInParent().getHeight();
            }
            double scaleXY = Double.min(scaleX, scaleY);
            Scale scale = new Scale(scaleXY, scaleXY);
            node.getTransforms().add(scale);
            boolean success = job.printPage(node);
            node.getTransforms().remove(scale);
            if (success){
                job.endJob();
                nazad.setVisible(true);
                printBtn.setVisible(true);
            }
        }
    }
}
