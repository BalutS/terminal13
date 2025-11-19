package org.unimag.controlador.viaje;

import org.unimag.dto.ViajeDto;
import org.unimag.servicio.ViajeServicio;

public class ViajeControladorGrabar {
    public static Boolean crearViaje(ViajeDto dto,String rutaDeLaImagen) {

            Boolean correcto = false;
            ViajeServicio viajeServicio = new ViajeServicio();
            ViajeDto dtoRespuesta;
            dtoRespuesta = viajeServicio.inserInto(dto, rutaDeLaImagen);

            if (dtoRespuesta != null) {
                correcto = true;
            }

            return correcto;
    }
}
