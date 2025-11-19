package org.unimag.controlador.equipaje;

import org.unimag.servicio.EquipajeServicio;

public class EquipajeControladorEliminar {
    
    public static Boolean borrar(int codigo) {
        Boolean correcto;
        EquipajeServicio equipajeServicio = new EquipajeServicio();
        correcto = equipajeServicio.deleteFrom(codigo);
        return correcto;
    }
    
}