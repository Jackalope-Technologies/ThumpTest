module com.jackalope.thumptest {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires com.github.oshi;
    requires org.slf4j;
    requires static lombok;
    requires java.desktop;

    opens com.jackalope.thumptest to javafx.fxml;
    exports com.jackalope.thumptest;
    exports com.jackalope.thumptest.controller;
    opens com.jackalope.thumptest.controller to javafx.fxml;
}