module com.example.observablelists {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.observablelists to javafx.fxml;
    exports com.example.observablelists;
}