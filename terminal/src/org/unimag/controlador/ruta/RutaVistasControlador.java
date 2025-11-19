
package org.unimag.controlador.ruta;

import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.unimag.vista.ruta.VistaRutaAdministrar;
import org.unimag.vista.ruta.VistaRutaCrear;
import org.unimag.vista.ruta.VistaRutaListar;

public class RutaVistasControlador {

    public static StackPane CrearRuta(Stage esce, double anchito, double altito) {
        return new VistaRutaCrear(esce, anchito, altito);
    }
    
    public static StackPane listarRuta(Stage esce, double anchito, double altito) {
        return new VistaRutaListar(esce, anchito, altito);
    }

    public static StackPane administrarRuta(Stage esce, double anchito, double altito) {
        return new VistaRutaAdministrar(esce, anchito, altito);
    }
}
