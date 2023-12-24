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

public class OtchetController {
    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();

    @FXML private BarChart<String, Number> barChart;

    @FXML
    private Button nazad;
    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button printBtn;


    @FXML private void initialize() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        series.getData().add( new XYChart.Data<>( "Июль 2023", 120000 ) );
        series.getData().add( new XYChart.Data<>( "Август 2023", 110000 ) );
        series.getData().add( new XYChart.Data<>( "Сентябрь 2023", 140000 ) );
        series.getData().add( new XYChart.Data<>( "Октябрь", 125000 ) );
        series.getData().add( new XYChart.Data<>( "Ноябрь 2023", 100000 ) );
        series.getData().add( new XYChart.Data<>( "Декабрь 2023", 112000 ) );

        barChart.setLegendVisible(false);
        barChart.getData().add( series );

        for(Node n:barChart.lookupAll(".default-color0.chart-bar")) {
            n.setStyle("-fx-bar-fill: green;");

            nazad.setOnAction(event -> {
                new Loader().openNewScene(rootPane, "/com/example/database/uchet-screen.fxml", "Учет деятельности");
            });

        }

        printBtn.setOnAction( aEvent -> {
            print(rootPane);
        });
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