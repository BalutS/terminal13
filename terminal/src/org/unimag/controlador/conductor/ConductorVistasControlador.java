
package org.unimag.controlador.conductor;

import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.unimag.vista.conductor.VistaConductorAdministrar;
import org.unimag.vista.conductor.VistaConductorCrear;
import org.unimag.vista.conductor.VistaConductorListar;

public class ConductorVistasControlador {
    
    public static StackPane CrearConductor(Stage esce, double anchito, double altito) {
        return new VistaConductorCrear(esce, anchito, altito);
    }
    
    public static StackPane listarConductor(Stage esce, double anchito, double altito) {
        return new VistaConductorListar(esce, anchito, altito);
    }

    public static StackPane administrarConductor(Stage esce, double anchito, double altito) {
        return new VistaConductorAdministrar(esce, anchito, altito);
    }
}
