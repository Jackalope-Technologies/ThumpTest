module com.jackalope.thumptest {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.jackalope.thumptest to javafx.fxml;
    exports com.jackalope.thumptest;
}