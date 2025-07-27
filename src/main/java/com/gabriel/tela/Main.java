package com.gabriel.tela;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private Stage janela;

    @Override
    public void start(Stage stage) throws Exception {
        this.janela = stage;
        Parent tela = FXMLLoader.load(getClass().getResource("/com/gabriel/tela/Home.fxml"));

        Scene scene = new Scene(tela);

        janela.setScene(scene);
        janela.show();

    }


}
