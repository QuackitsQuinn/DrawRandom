module com.quackiq.drawrandom {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;
    requires org.apache.httpcomponents.httpclient.fluent;
    requires java.desktop;
    requires javafx.swing;


    opens com.quackiq.drawrandom to javafx.fxml;
    exports com.quackiq.drawrandom;
}