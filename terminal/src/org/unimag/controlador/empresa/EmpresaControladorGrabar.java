package org.unimag.controlador.empresa;

import org.unimag.dto.EmpresaDto;
import org.unimag.servicio.EmpresaServicio;

public class EmpresaControladorGrabar {
    
    public static Boolean crearEmpresa(EmpresaDto dto,String rutaDeLaImagen) {
        
        Boolean correcto = false;
        EmpresaServicio empresaServicio = new EmpresaServicio();
        EmpresaDto dtoRespuesta;
        dtoRespuesta = empresaServicio.inserInto(dto, rutaDeLaImagen);

        if (dtoRespuesta != null) {
            correcto = true;
        }

        return correcto;
    }
    
}
