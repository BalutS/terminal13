package org.unimag.vista.gestor;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import org.unimag.recurso.constante.Configuracion;

public class VistaAdmin {

    private final Scene miEscena;
    private final Stage miEscenario;

    private final Pane miPanelCuerpo;
    private final HBox miPanelCabecera;
    private final BorderPane miPanelPrincial;

    public VistaAdmin() {
        miEscenario = new Stage();
        miPanelCuerpo = new Pane();
        miPanelPrincial = new BorderPane();

        VistaCabecera cabeceraVista = new VistaCabecera(miEscenario, miPanelPrincial, miPanelCuerpo, Configuracion.ANCHO_APP, Configuracion.ALTO_CABECERA);

        miPanelCabecera = cabeceraVista.getMiPanelCabecera();
        miPanelCuerpo.setPrefHeight(50);

        miPanelPrincial.setTop(miPanelCabecera);
        miPanelPrincial.setCenter(miPanelCuerpo);

        miEscena = new Scene(miPanelPrincial, Configuracion.ANCHO_APP, Configuracion.ALTO_APP);
        miEscenario.setScene(miEscena);
    }
}