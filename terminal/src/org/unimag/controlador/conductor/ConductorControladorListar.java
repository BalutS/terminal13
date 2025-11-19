
package org.unimag.controlador.conductor;

import java.util.List;
import org.unimag.dto.ConductorDto;
import org.unimag.servicio.ConductorServicio;

public class ConductorControladorListar {

    public static List<ConductorDto> obtenerConductores(){
        ConductorServicio miDao = new ConductorServicio();
        List<ConductorDto> arreglo = miDao.selectFrom();

        return arreglo;
    }

    public static int obtenerCantidadConductor(){
        ConductorServicio miDao = new ConductorServicio();
        int cantidad = miDao.numRows();
        return cantidad;
    }
}
