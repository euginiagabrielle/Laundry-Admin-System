module com.example.percobaan2baru {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires io.github.cdimascio.dotenv.java;


    opens com.example.percobaan2baru to javafx.fxml;
    exports com.example.percobaan2baru;
}