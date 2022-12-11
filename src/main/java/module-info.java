module com.quackiq.drawrandom {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;
    requires org.apache.httpcomponents.httpclient.fluent;



    opens com.quackiq.drawrandom to javafx.fxml;
    exports com.quackiq.drawrandom;
    opens com.quackiq.drawrandom.log4j2 to javafx.fxml;
}