module com.example.kursinis2048 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.kursinis2048 to javafx.fxml;
    exports com.example.kursinis2048;
}