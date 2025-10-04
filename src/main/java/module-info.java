module com.example.parquesdigital {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;

    // 👇 Paquete raíz abierto a FXML
    opens com.example.parquesdigital to javafx.fxml;

    // 👇 Paquete de controladores abierto a FXML
    opens com.example.parquesdigital.controller to javafx.fxml;

    // Exporta los paquetes que quieras usar fuera del módulo
    exports com.example.parquesdigital;
    exports com.example.parquesdigital.controller;
}
