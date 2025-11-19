package org.unimag.controlador.varios;

import javafx.stage.Stage;
import org.unimag.recurso.utilidad.Mensaje;

public class ControladorSalida {

    public static void verificar(Stage stage){
        Mensaje.salir(stage);
    }//se cambio para tener una buena logica de negocios y no tener un metodo haciendose desde el controlador
    //como estaba anteriormente

}