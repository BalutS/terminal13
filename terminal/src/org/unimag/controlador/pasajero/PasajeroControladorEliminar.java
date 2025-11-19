package org.unimag.controlador.pasajero;

import org.unimag.servicio.PasajeroServicio;

public class PasajeroControladorEliminar {
    
    public static Boolean borrar(int codigo) {
        Boolean correcto;
        PasajeroServicio pasajeroServicio = new PasajeroServicio();
        correcto = pasajeroServicio.deleteFrom(codigo);
        return correcto;
    }
    
}
