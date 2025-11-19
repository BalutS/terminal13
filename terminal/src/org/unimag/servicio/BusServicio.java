package org.unimag.servicio;

import com.poo.persistence.NioFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.unimag.api.ApiOperacionBD;
import org.unimag.dto.BusDto;
import org.unimag.dto.EmpresaDto;
import org.unimag.modelo.Bus;
import org.unimag.modelo.Empresa;
import org.unimag.recurso.constante.Persistencia;
import org.unimag.recurso.utilidad.GestorImagen;

public class BusServicio implements ApiOperacionBD<BusDto, Integer> {

    private NioFile miArchivo;
    private String nombrePersistencia;

    public BusServicio() {
        nombrePersistencia = Persistencia.NOMBRE_BUS;
        try {
            miArchivo = new NioFile(nombrePersistencia);
        } catch (IOException ex) {
            Logger.getLogger(BusServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getSerial() {
        int id = 0;
        try {
            id = miArchivo.ultimoCodigo() + 1;
        } catch (IOException ex) {
            Logger.getLogger(BusServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    @Override
    public BusDto inserInto(BusDto dto, String ruta) {
        Empresa objEmpresa = new Empresa(dto.getEmpresaBus().getIdEmpresa(),
                dto.getEmpresaBus().getNombreEmpresa(),
                "", "", 0
        );

        Bus objBus = new Bus();
        objBus.setIdBus(getSerial());
        objBus.setModeloBus(dto.getModeloBus());
        objBus.setEmpresaBus(objEmpresa);
        
        String nombrePublico = dto.getNombreImagenPublicoBus();
        if (nombrePublico != null) {
            nombrePublico = nombrePublico.replace("@", "_").replace(";", "_");
        }
        
        String nombrePrivado = ruta != null ? GestorImagen.grabarLaImagen(ruta) : "";
        if (nombrePrivado != null) {
            nombrePrivado = nombrePrivado.replace("@", "_").replace(";", "_");
        }
        
        objBus.setNombreImagenPublicoBus(nombrePublico);
        objBus.setNombreImagenPrivadoBus(nombrePrivado);

        String filaGrabar = objBus.getIdBus() + Persistencia.SEPARADOR_COLUMNAS
                + objBus.getModeloBus() + Persistencia.SEPARADOR_COLUMNAS
                + objBus.getEmpresaBus().getIdEmpresa() + Persistencia.SEPARADOR_COLUMNAS
                + objBus.getNombreImagenPublicoBus() + Persistencia.SEPARADOR_COLUMNAS
                + objBus.getNombreImagenPrivadoBus();

        if (miArchivo.agregarRegistro(filaGrabar)) {
            dto.setIdBus(objBus.getIdBus());
            return dto;
        }

        return null;
    }

    @Override
public List<BusDto> selectFrom() {
    EmpresaServicio empresaServicio = new EmpresaServicio();
    List<EmpresaDto> arrEmpresas = empresaServicio.selectFrom();

    ViajeServicio viajeServicio = new ViajeServicio();
    Map<Integer, Integer> arrCantidades = viajeServicio.viajesPorBus();

    AsientoServicio asientoServicio = new AsientoServicio();
    Map<Integer, Integer> arrCantidadAsientos = asientoServicio.asientosPorBus();

    Map<Integer, EmpresaDto> mapEmpresas = new HashMap<>();
    for (EmpresaDto empresa : arrEmpresas) {
        mapEmpresas.put(empresa.getIdEmpresa(), empresa);
    }

    List<BusDto> arregloBusDtos = new ArrayList<>();
    List<String> arregloDatos = miArchivo.obtenerDatos();

    for (String cadena : arregloDatos) {
        try {
            cadena = cadena.replace("@", "");
            String[] columnas = cadena.split(Persistencia.SEPARADOR_COLUMNAS);

            BusDto objBusDto = new BusDto();
            objBusDto.setIdBus(Integer.parseInt(columnas[0].trim()));
            objBusDto.setModeloBus(columnas[1].trim());

            int idEmpresa = Integer.parseInt(columnas[2].trim());
            objBusDto.setEmpresaBus(mapEmpresas.getOrDefault(idEmpresa, null));

            objBusDto.setNombreImagenPublicoBus(columnas[3].trim());
            objBusDto.setNombreImagenPrivadoBus(columnas[4].trim());
            objBusDto.setCantidadViajeBus(arrCantidades.getOrDefault(objBusDto.getIdBus(), 0));
            
            objBusDto.setCantidadAsientoBus(arrCantidadAsientos.getOrDefault(objBusDto.getIdBus(), 0));

            arregloBusDtos.add(objBusDto);

        } catch (NumberFormatException error) {
            Logger.getLogger(BusServicio.class.getName()).log(Level.SEVERE, null, error);
        }
    }

    return arregloBusDtos;
}

    @Override
    public int numRows() {
        int cantidad = 0;
        try {
            cantidad = miArchivo.cantidadFilas();
        } catch (IOException ex) {
            Logger.getLogger(BusServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cantidad;
    }

    @Override
    public Boolean deleteFrom(Integer codigo) {
        Boolean correcto = false;
        try {
            List<String> arreglo;

            arreglo = miArchivo.borrarFilaPosicion(codigo);
            if (!arreglo.isEmpty()) {
                correcto = true;
            }
        } catch (IOException ex) {
            Logger.getLogger(BusServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return correcto;
    }

    public Map<Integer, Integer> busesPorEmpresa() {
        Map<Integer, Integer> arrCantidades = new HashMap<>();
        List<String> arregloDatos = miArchivo.obtenerDatos();

        for (String cadena : arregloDatos) {
            try {
                cadena = cadena.replace("@", "");
                String[] columnas = cadena.split(Persistencia.SEPARADOR_COLUMNAS);

                if (columnas.length < 3) {
                    continue;
                }

                int idEmpresa = Integer.parseInt(columnas[2].trim());
                arrCantidades.put(idEmpresa, arrCantidades.getOrDefault(idEmpresa, 0) + 1);

            } catch (NumberFormatException | ArrayIndexOutOfBoundsException error) {
                Logger.getLogger(BusServicio.class.getName()).log(Level.SEVERE, null, error);
            }
        }
        return arrCantidades;
    }

    @Override
    public BusDto getOne(Integer codigo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BusDto updateSet(Integer codigo, BusDto objeto, String ruta) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
