package org.unimag.controlador.ruta;

import org.unimag.servicio.RutaServicio;

public class RutaControladorEliminar {
    
    public static Boolean borrar(int codigo) {
        Boolean correcto;
        RutaServicio rutaServicio = new RutaServicio();
        correcto = rutaServicio.deleteFrom(codigo);
        return correcto;
    }
    
}
