module com.example.lab4_j120 {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.lab4_j120 to javafx.fxml;
    exports com.example.lab4_j120;
}