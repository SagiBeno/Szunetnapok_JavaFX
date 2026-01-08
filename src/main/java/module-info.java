module com.example.szunetnapok {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires java.xml;


    opens com.example.szunetnapok to javafx.fxml;
    exports com.example.szunetnapok;
}