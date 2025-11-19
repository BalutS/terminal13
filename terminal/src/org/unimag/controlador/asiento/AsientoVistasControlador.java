
package org.unimag.controlador.asiento;

import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.unimag.vista.asiento.VistaAsientoAdministrar;
import org.unimag.vista.asiento.VistaAsientoCrear;
import org.unimag.vista.asiento.VistaAsientoListar;

public class AsientoVistasControlador {
    
    public static StackPane crearAsiento(Stage esce, double anchito, double altito) {
        return new VistaAsientoCrear(esce, anchito, altito);
    }
    
    public static StackPane listarAsiento(Stage esce, double anchito, double altito) {
        return new VistaAsientoListar(esce, anchito, altito);
    }

    public static StackPane administrarAsiento(Stage esce, double anchito, double altito) {
        return new VistaAsientoAdministrar(esce, anchito, altito);
    }
    
}
