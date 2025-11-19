package org.unimag.controlador.pasajero;

import org.unimag.dto.PasajeroDto;
import org.unimag.servicio.PasajeroServicio;

public class PasajeroControladorGrabar {
    public static Boolean crearPasajero(PasajeroDto dto,String rutaDeLaImagen) {
        
        Boolean correcto = false;
        PasajeroServicio busServicio = new PasajeroServicio();
        PasajeroDto dtoRespuesta;
        dtoRespuesta = busServicio.inserInto(dto, rutaDeLaImagen);

        if (dtoRespuesta != null) {
            correcto = true;
        }

        return correcto;
    }
}
