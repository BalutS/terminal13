package org.unimag.controlador.conductor;

import org.unimag.dto.ConductorDto;
import org.unimag.servicio.ConductorServicio;

public class ConductorControladorGrabar {
    
    public static Boolean crearConductor(ConductorDto dto,String rutaDeLaImagen) {

            Boolean correcto = false;
            ConductorServicio conductorServicio = new ConductorServicio();
            ConductorDto dtoRespuesta;
            dtoRespuesta = conductorServicio.inserInto(dto, rutaDeLaImagen);

            if (dtoRespuesta != null) {
                correcto = true;
            }

            return correcto;
    }
}
