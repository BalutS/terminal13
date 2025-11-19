package org.unimag.controlador.tiquete;

import org.unimag.dto.TiqueteDto;
import org.unimag.servicio.TiqueteServicio;


import org.unimag.servicio.AsientoServicio;

public class TiqueteControladorGrabar {
    public static Boolean crearTiquete(TiqueteDto dto,String rutaDeLaImagen) {

            Boolean correcto = false;
            AsientoServicio asientoServicio = new AsientoServicio();
            TiqueteServicio tiqueteServicio = new TiqueteServicio(asientoServicio);
            TiqueteDto dtoRespuesta;
            dtoRespuesta = tiqueteServicio.inserInto(dto, rutaDeLaImagen);

            if (dtoRespuesta != null) {
                correcto = true;
            }

            return correcto;
    }
}
