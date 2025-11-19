
package org.unimag.controlador.pasajero;

import java.util.List;
import org.unimag.dto.PasajeroDto;
import org.unimag.servicio.PasajeroServicio;

public class PasajeroControladorListar {

    public static List<PasajeroDto> obtenerPasajeros(){
        PasajeroServicio miDao = new PasajeroServicio();
        List<PasajeroDto> arreglo = miDao.selectFrom();

        return arreglo;
    }

    public static int obtenerCantidadPasajero(){
        PasajeroServicio miDao = new PasajeroServicio();
        int cantidad = miDao.numRows();
        return cantidad;
    }
}
