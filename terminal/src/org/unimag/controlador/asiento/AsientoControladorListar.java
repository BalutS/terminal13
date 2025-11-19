
package org.unimag.controlador.asiento;

import java.util.List;
import org.unimag.dto.AsientoDto;
import org.unimag.servicio.AsientoServicio;

public class AsientoControladorListar {
    public static List<AsientoDto> obtenerAsientos() {
        AsientoServicio miDao = new AsientoServicio();
        List<AsientoDto> arreglo = miDao.selectFrom();

        return arreglo;
    }
    
    public static List<AsientoDto> obtenerAsientosDesocupados() {
        AsientoServicio miDao = new AsientoServicio();
        List<AsientoDto> arreglo = miDao.selectFromWhereDesocupados();

        return arreglo;
    }

    public static int obtenerCantidadAsientos() {
        AsientoServicio miDao = new AsientoServicio();
        int cantidad = miDao.numRows();
        return cantidad;
    }
}
