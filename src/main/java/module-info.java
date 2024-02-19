module com.example.database {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.postgresql.jdbc;
    requires java.sql;


    opens com.example.database to javafx.fxml;
    exports com.example.database;

    opens com.example.database.Models to javafx.fxml;
    exports com.example.database.Models;
    exports com.example.database.DbFunctions;
    opens com.example.database.DbFunctions to javafx.fxml;
}