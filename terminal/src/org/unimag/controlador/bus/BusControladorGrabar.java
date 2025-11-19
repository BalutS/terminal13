package org.unimag.controlador.bus;

import org.unimag.dto.BusDto;
import org.unimag.servicio.BusServicio;

public class BusControladorGrabar {

    public static Boolean crearBus(BusDto dto,String rutaDeLaImagen) {
        
        Boolean correcto = false;
        BusServicio busServicio = new BusServicio();
        BusDto dtoRespuesta;
        dtoRespuesta = busServicio.inserInto(dto, rutaDeLaImagen);

        if (dtoRespuesta != null) {
            correcto = true;
        }

        return correcto;
    }
}
