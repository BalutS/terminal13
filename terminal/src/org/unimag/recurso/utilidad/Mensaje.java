package org.unimag.recurso.utilidad;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.Window;


public class Mensaje {
    private static Alert crearAlerta(
            Alert.AlertType tipo,
            Window ventanaPadre,
            String titulo,
            String contenido
    ) {

        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.initOwner(ventanaPadre);

        return alert;
    }

    public static void salir(Stage miEscenario) {

        Alert msg = new Alert(Alert.AlertType.CONFIRMATION);
        msg.setTitle("Saliendo");
        msg.initOwner(miEscenario);
        msg.setHeaderText(null);
        msg.getDialogPane().setGraphic(Icono.obtenerIcono("iconByeBye.png",40));
        msg.setContentText("¿Desea cerrar la aplicacion?");

        if (msg.showAndWait().get() == ButtonType.OK){
            miEscenario.close();//se usa el metodo que estaba en el controlador
        }
    }

    public static void mostrar(
            Alert.AlertType tipo,
            Window ventanaPadre,
            String titulo,
            String contenido
    ) {

        Alert msg = crearAlerta(tipo, ventanaPadre, titulo, contenido);
        msg.show();
    }
}
