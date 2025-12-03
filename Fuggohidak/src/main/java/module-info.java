module com.example.fuggohidak {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fuggohidak to javafx.fxml;
    exports com.example.fuggohidak;
}