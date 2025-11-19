package org.unimag.servicio;

import com.poo.persistence.NioFile;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.unimag.api.ApiOperacionBD;
import org.unimag.dto.EquipajeDto;
import org.unimag.dto.PasajeroDto;
import org.unimag.modelo.Equipaje;
import org.unimag.modelo.Pasajero;
import org.unimag.recurso.constante.Persistencia;
import org.unimag.recurso.utilidad.GestorImagen;

public class EquipajeServicio implements ApiOperacionBD<EquipajeDto, Integer> {

    private NioFile miArchivo;
    private String nombrePersistencia;

    public EquipajeServicio() {
        nombrePersistencia = Persistencia.NOMBRE_EQUIPAJE;
        try {
            miArchivo = new NioFile(nombrePersistencia);
        } catch (IOException ex) {
            Logger.getLogger(EquipajeServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getSerial() {
        int id = 0;
        try {
            id = miArchivo.ultimoCodigo() + 1;
        } catch (IOException ex) {
            Logger.getLogger(EquipajeServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    @Override
    public EquipajeDto inserInto(EquipajeDto dto, String ruta) {
        Pasajero objPasajero = new Pasajero(dto.getDuenioEquipaje().getIdPasajero(), "", (short) 0, false, "", "", 0, 0);

        Equipaje objEquipaje = new Equipaje();
        objEquipaje.setIdEquipaje(getSerial());
        objEquipaje.setPesoEquipaje(dto.getPesoEquipaje());
        objEquipaje.setDuenioEquipaje(objPasajero);
        
        String nombrePublico = dto.getNombreImagenPublicoEquipaje();
        if (nombrePublico != null) {
            nombrePublico = nombrePublico.replace("@", "_").replace(";", "_");
        }
        
        String nombrePrivado = ruta != null ? GestorImagen.grabarLaImagen(ruta) : "";
        if (nombrePrivado != null) {
            nombrePrivado = nombrePrivado.replace("@", "_").replace(";", "_");
        }
        
        objEquipaje.setNombreImagenPublicoEquipaje(nombrePublico);
        objEquipaje.setNombreImagenPrivadoEquipaje(nombrePrivado);

        String filaGrabar = objEquipaje.getIdEquipaje() + Persistencia.SEPARADOR_COLUMNAS
                + BigDecimal.valueOf(objEquipaje.getPesoEquipaje()).toPlainString() + Persistencia.SEPARADOR_COLUMNAS
                + objEquipaje.getDuenioEquipaje().getIdPasajero() + Persistencia.SEPARADOR_COLUMNAS
                + objEquipaje.getNombreImagenPublicoEquipaje() + Persistencia.SEPARADOR_COLUMNAS
                + objEquipaje.getNombreImagenPrivadoEquipaje();

        if (miArchivo.agregarRegistro(filaGrabar)) {
            dto.setIdEquipaje(objEquipaje.getIdEquipaje());
            return dto;
        }

        return null;
    }

    @Override
    public List<EquipajeDto> selectFrom() {
        PasajeroServicio pasajeroServicio = new PasajeroServicio();
        List<PasajeroDto> arrPasajeros = pasajeroServicio.selectFrom();

        Map<Integer, PasajeroDto> mapPasajeros = new HashMap<>();
        for (PasajeroDto p : arrPasajeros) {
            mapPasajeros.put(p.getIdPasajero(), p);
        }

        List<EquipajeDto> arregloEquipajeDtos = new ArrayList<>();
        List<String> arregloDatos = miArchivo.obtenerDatos();

        for (String cadena : arregloDatos) {
            try {
                cadena = cadena.replace("@", "");
                String[] columnas = cadena.split(Persistencia.SEPARADOR_COLUMNAS);
                
                if (columnas.length < 5) {
                    continue;
                }

                EquipajeDto objEquipajeDto = new EquipajeDto();
                objEquipajeDto.setIdEquipaje(Integer.parseInt(columnas[0].trim()));
                objEquipajeDto.setPesoEquipaje(Double.valueOf(columnas[1].trim()));

                int ced = Integer.parseInt(columnas[2].trim());
                objEquipajeDto.setDuenioEquipaje(mapPasajeros.getOrDefault(ced, null));

                objEquipajeDto.setNombreImagenPublicoEquipaje(columnas[3].trim());
                objEquipajeDto.setNombreImagenPrivadoEquipaje(columnas[4].trim());

                arregloEquipajeDtos.add(objEquipajeDto);

            } catch (NumberFormatException | ArrayIndexOutOfBoundsException error) {
                Logger.getLogger(EquipajeServicio.class.getName()).log(Level.SEVERE, null, error);
            }
        }
        return arregloEquipajeDtos;
    }

    @Override
    public int numRows() {
        int cantidad = 0;
        try {
            cantidad = miArchivo.cantidadFilas();
        } catch (IOException ex) {
            Logger.getLogger(EquipajeServicio.class.getName()).log(Level.SEVERE, null, ex);
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

    public Map<Integer, Integer> equipajePorPasajero() {
        Map<Integer, Integer> arrCantidades = new HashMap<>();
        List<String> arregloDatos = miArchivo.obtenerDatos();

        for (String cadena : arregloDatos) {
            try {
                cadena = cadena.replace("@", "");
                String[] columnas = cadena.split(Persistencia.SEPARADOR_COLUMNAS);

                if (columnas.length < 3) {
                    continue;
                }

                int idPasajero = Integer.parseInt(columnas[2].trim());
                arrCantidades.put(idPasajero, arrCantidades.getOrDefault(idPasajero, 0) + 1);

            } catch (NumberFormatException | ArrayIndexOutOfBoundsException error) {
                Logger.getLogger(EquipajeServicio.class.getName()).log(Level.SEVERE, null, error);
            }
        }
        return arrCantidades;
    }

    @Override
    public EquipajeDto getOne(Integer codigo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public EquipajeDto updateSet(Integer codigo, EquipajeDto objeto, String ruta) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
