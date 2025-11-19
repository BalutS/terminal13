package org.unimag.controlador.tiquete;

import org.unimag.servicio.TiqueteServicio;

public class TiqueteControladorEliminar {
    
    public static Boolean borrar(int codigo) {
        Boolean correcto;
        TiqueteServicio tiqueteServicio = new TiqueteServicio();
        correcto = tiqueteServicio.deleteFrom(codigo);
        return correcto;
    }
    
}
