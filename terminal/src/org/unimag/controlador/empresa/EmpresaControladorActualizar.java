package org.unimag.controlador.empresa;

import org.unimag.dto.EmpresaDto;
import org.unimag.servicio.EmpresaServicio;

public class EmpresaControladorActualizar {
    
    public static Boolean actualizarEmpresa(int posicion, EmpresaDto empresa, String rutaImagen) {
        EmpresaServicio servicio = new EmpresaServicio();
        EmpresaDto resultado = servicio.updateSet(posicion, empresa, rutaImagen);
        return resultado != null;
    }
}