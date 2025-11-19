
package org.unimag.controlador.pasajero;

import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.unimag.vista.pasajero.VistaPasajeroAdministrar;
import org.unimag.vista.pasajero.VistaPasajeroCrear;
import org.unimag.vista.pasajero.VistaPasajeroListar;

public class PasajeroVistasControlador {

    public static StackPane CrearPasajero(Stage esce, double anchito, double altito) {
        return new VistaPasajeroCrear(esce, anchito, altito);
    }
    
    public static StackPane listarPasajero(Stage esce, double anchito, double altito) {
        return new VistaPasajeroListar(esce, anchito, altito);
    }

    public static StackPane administrarPasajero(Stage esce, double anchito, double altito) {
        return new VistaPasajeroAdministrar(esce, anchito, altito);
    }
}
