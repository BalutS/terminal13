package org.unimag.controlador.empresa;

import org.unimag.servicio.EmpresaServicio;

public class EmpresaControladorEliminar {
    
    public static Boolean borrar(int codigo) {
        Boolean correcto;
        EmpresaServicio empresaServicio = new EmpresaServicio();
        correcto = empresaServicio.deleteFrom(codigo);
        return correcto;
    }
    
}
