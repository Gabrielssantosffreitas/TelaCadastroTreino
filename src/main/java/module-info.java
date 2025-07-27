module com.gabriel.tela {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.gabriel.tela to javafx.fxml;
    opens com.gabriel.tela.controller to javafx.fxml;
    opens com.gabriel.tela.entity to javafx.base;
    exports com.gabriel.tela to javafx.graphics;
}