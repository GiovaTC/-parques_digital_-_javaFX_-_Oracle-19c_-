module com.example.parquesdigital {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;

    opens com.example.parquesdigital to javafx.fxml;
    exports com.example.parquesdigital;
}