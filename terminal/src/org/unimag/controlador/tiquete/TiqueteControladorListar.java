
package org.unimag.controlador.tiquete;

import java.util.List;
import org.unimag.dto.TiqueteDto;
import org.unimag.servicio.TiqueteServicio;

public class TiqueteControladorListar {

    public static List<TiqueteDto> obtenerTiquete(){
        TiqueteServicio miDao = new TiqueteServicio();
        List<TiqueteDto> arreglo = miDao.selectFrom();

        return arreglo;
    }

    public static int obtenerCantidadTiquete(){
        TiqueteServicio miDao = new TiqueteServicio();
        int cantidad = miDao.numRows();
        return cantidad;
    }
}
