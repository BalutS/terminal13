package org.unimag.controlador.equipaje;

import org.unimag.dto.EquipajeDto;
import org.unimag.servicio.EquipajeServicio;

public class EquipajeControladorGrabar {
    public static Boolean crearEquipaje(EquipajeDto dto,String rutaDeLaImagen) {

            Boolean correcto = false;
            EquipajeServicio equipajeServicio = new EquipajeServicio();
            EquipajeDto dtoRespuesta;
            dtoRespuesta = equipajeServicio.inserInto(dto, rutaDeLaImagen);

            if (dtoRespuesta != null) {
                correcto = true;
            }

            return correcto;
    }
}
