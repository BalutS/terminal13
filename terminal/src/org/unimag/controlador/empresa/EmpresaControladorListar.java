
package org.unimag.controlador.empresa;

import java.util.List;
import org.unimag.dto.EmpresaDto;
import org.unimag.servicio.EmpresaServicio;

public class EmpresaControladorListar {

    public static List<EmpresaDto> obtenerEmpresas(){
        EmpresaServicio miDao = new EmpresaServicio();
        List<EmpresaDto> arreglo = miDao.selectFrom();

        return arreglo;
    }

    public static int obtenerCantidadEmpresa(){
        EmpresaServicio miDao = new EmpresaServicio();
        int cantidad = miDao.numRows();
        return cantidad;
    }
}
