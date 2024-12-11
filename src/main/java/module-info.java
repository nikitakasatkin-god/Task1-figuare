module com.example.laboratornaya2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.laboratornaya2 to javafx.fxml;
    exports com.example.laboratornaya2;
}