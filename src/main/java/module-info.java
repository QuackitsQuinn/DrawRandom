module com.quackiq.drawrandom {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;
    requires org.apache.httpcomponents.httpclient.fluent;
    requires org.apache.logging.log4j.core;
    requires org.apache.logging.log4j;



    opens com.quackiq.drawrandom to javafx.fxml;
    exports com.quackiq.drawrandom;
    exports com.quackiq.drawrandom.log4j2;
    opens com.quackiq.drawrandom.log4j2 to javafx.fxml;
}