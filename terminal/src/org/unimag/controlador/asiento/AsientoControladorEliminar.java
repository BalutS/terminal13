package org.unimag.controlador.asiento;

import org.unimag.servicio.AsientoServicio;

public class AsientoControladorEliminar {
    
    public static Boolean borrar(int codigo) {
        Boolean correcto;
        AsientoServicio asientoServicio = new AsientoServicio();
        correcto = asientoServicio.deleteFrom(codigo);
        return correcto;
    }
    
}
