module com.example.laboratornaya2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.laboratornaya2 to javafx.fxml;
    exports com.example.laboratornaya2;
    exports com.example.laboratornaya2.controller;
    opens com.example.laboratornaya2.controller to javafx.fxml;
    exports com.example.laboratornaya2.model;
    opens com.example.laboratornaya2.model to javafx.fxml;
    exports com.example.laboratornaya2.factory;
    opens com.example.laboratornaya2.factory to javafx.fxml;
    exports com.example.laboratornaya2.composite;
    opens com.example.laboratornaya2.composite to javafx.fxml;
}