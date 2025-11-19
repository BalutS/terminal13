package org.unimag.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.unimag.vista.gestor.VistaCabecera;

public class Principal extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Pane body = new Pane();
        Scene scene = new Scene(root, 1200, 600);

        VistaCabecera cabecera = new VistaCabecera(primaryStage, root, body, scene.getWidth(), 50);
        root.setTop(cabecera.getMiPanelCabecera());

        primaryStage.setTitle("Terminal de Transporte");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
