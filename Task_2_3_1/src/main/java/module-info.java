module ru.nsu.krasnikov.task_2_3_1 {
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

    opens ru.nsu.krasnikov.task_2_3_1 to javafx.fxml;
    exports ru.nsu.krasnikov.task_2_3_1;
}