module ik.ku.ecommercesystem.ecommercefx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;

    opens ik.ku.ecommercesystem.ecommercefx to javafx.fxml;
    exports ik.ku.ecommercesystem.ecommercefx;
}