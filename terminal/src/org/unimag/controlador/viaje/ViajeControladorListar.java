
package org.unimag.controlador.viaje;

import java.util.List;
import org.unimag.dto.ViajeDto;
import org.unimag.servicio.ViajeServicio;

public class ViajeControladorListar {

    public static List<ViajeDto> obtenerViajes(){
        ViajeServicio miDao = new ViajeServicio();
        List<ViajeDto> arreglo = miDao.selectFrom();

        return arreglo;
    }

    public static int obtenerCantidadViaje(){
        ViajeServicio miDao = new ViajeServicio();
        int cantidad = miDao.numRows();
        return cantidad;
    }
}
