package org.unimag.controlador.ruta;

import org.unimag.dto.RutaDto;
import org.unimag.servicio.RutaServicio;

public class RutaControladorGrabar {
    public static Boolean crearRuta(RutaDto dto,String rutaDeLaImagen) {

            Boolean correcto = false;
            RutaServicio rutaServicio = new RutaServicio();
            RutaDto dtoRespuesta;
            dtoRespuesta = rutaServicio.inserInto(dto, rutaDeLaImagen);

            if (dtoRespuesta != null) {
                correcto = true;
            }

            return correcto;
    }
}
