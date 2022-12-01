module edu.acg.kio.kiobookingsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires javafx.graphics;
    requires org.apache.commons.io;

    opens edu.acg.kio.kiobookingsystem to javafx.fxml;
    exports edu.acg.kio.kiobookingsystem;
    exports edu.acg.kio.kiobookingsystem.classes;
    opens edu.acg.kio.kiobookingsystem.classes to javafx.fxml;
}