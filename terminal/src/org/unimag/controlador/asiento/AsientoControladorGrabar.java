package org.unimag.controlador.asiento;

import org.unimag.dto.AsientoDto;
import org.unimag.servicio.AsientoServicio;

public class AsientoControladorGrabar {
    public static Boolean crearAsiento(AsientoDto dto,String rutaDeLaImagen) {
        
        Boolean correcto = false;
        AsientoServicio  asientoServicio = new AsientoServicio();
        AsientoDto dtoRespuesta;
        dtoRespuesta = asientoServicio.inserInto(dto, rutaDeLaImagen);

        if (dtoRespuesta != null) {
            correcto = true;
        }

        return correcto;
    }
}
