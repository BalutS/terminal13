
package org.unimag.controlador.bus;

import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.unimag.vista.bus.VistaBusAdministrar;
import org.unimag.vista.bus.VistaBusCrear;
import org.unimag.vista.bus.VistaBusListar;

public class BusVistasControlador {

    public static StackPane CrearBus(Stage esce, double anchito, double altito) {
        return new VistaBusCrear(esce, anchito, altito);
    }
   
    public static StackPane listarBus(Stage esce, double anchito, double altito) {
        return new VistaBusListar(esce, anchito, altito);
    }
  
    public static StackPane administrarBus(Stage esce, double anchito, double altito) {
        return new VistaBusAdministrar(esce, anchito, altito);
    }
}
