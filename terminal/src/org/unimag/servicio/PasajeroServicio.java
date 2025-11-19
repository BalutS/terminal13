package org.unimag.servicio;

import com.poo.persistence.NioFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.unimag.api.ApiOperacionBD;
import org.unimag.dto.PasajeroDto;
import org.unimag.modelo.Pasajero;
import org.unimag.recurso.constante.Persistencia;
import org.unimag.recurso.utilidad.GestorImagen;

public class PasajeroServicio implements ApiOperacionBD<PasajeroDto, Integer> {

    private NioFile miArchivo;
    private String nombrePersistencia;

    public PasajeroServicio() {
        nombrePersistencia = Persistencia.NOMBRE_PASAJERO;
        try {
            miArchivo = new NioFile(nombrePersistencia);
        } catch (IOException ex) {
            Logger.getLogger(PasajeroServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getSerial() {
        int id = 0;
        try {
            id = miArchivo.ultimoCodigo() + 1;
        } catch (IOException ex) {
            Logger.getLogger(PasajeroServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    @Override
    public PasajeroDto inserInto(PasajeroDto dto, String ruta) {
        Pasajero objPasajero = new Pasajero();
        objPasajero.setIdPasajero(getSerial());
        objPasajero.setNombrePasajero(dto.getNombrePasajero());
        objPasajero.setEdadPasajero(dto.getEdadPasajero());
        objPasajero.setGeneroPasajero(dto.getGeneroPasajero());
        
        String nombrePublico = dto.getNombreImagenPublicoPasajero();
        if (nombrePublico != null) {
            nombrePublico = nombrePublico.replace("@", "_").replace(";", "_");
        }
        
        String nombrePrivado = ruta != null ? GestorImagen.grabarLaImagen(ruta) : "";
        if (nombrePrivado != null) {
            nombrePrivado = nombrePrivado.replace("@", "_").replace(";", "_");
        }
        
        objPasajero.setNombreImagenPublicoPasajero(nombrePublico);
        objPasajero.setNombreImagenPrivadoPasajero(nombrePrivado);

        String filaGrabar = objPasajero.getIdPasajero() + Persistencia.SEPARADOR_COLUMNAS
                + objPasajero.getNombrePasajero() + Persistencia.SEPARADOR_COLUMNAS
                + objPasajero.getEdadPasajero() + Persistencia.SEPARADOR_COLUMNAS
                + objPasajero.getGeneroPasajero() + Persistencia.SEPARADOR_COLUMNAS
                + objPasajero.getNombreImagenPublicoPasajero() + Persistencia.SEPARADOR_COLUMNAS
                + objPasajero.getNombreImagenPrivadoPasajero();

        if (miArchivo.agregarRegistro(filaGrabar)) {
            dto.setIdPasajero(objPasajero.getIdPasajero());
            return dto;
        }

        return null;
    }

    @Override
    public List<PasajeroDto> selectFrom() {
        EquipajeServicio equipajeServicio = new EquipajeServicio();
        Map<Integer, Integer> arrCantEquipaje = equipajeServicio.equipajePorPasajero();

        TiqueteServicio tiqueteServicio = new TiqueteServicio();
        Map<Integer, Integer> arrCantTiquetes = tiqueteServicio.tiquetesPorPasajero();

        List<PasajeroDto> arregloPasajeroDtos = new ArrayList<>();
        List<String> arregloDatos = miArchivo.obtenerDatos();

        for (String cadena : arregloDatos) {
            try {
                cadena = cadena.replace("@", "");
                String[] columnas = cadena.split(Persistencia.SEPARADOR_COLUMNAS);
                
                if (columnas.length < 6) {
                    continue;
                }

                PasajeroDto objPasajeroDto = new PasajeroDto();
                objPasajeroDto.setIdPasajero(Integer.parseInt(columnas[0].trim()));
                objPasajeroDto.setNombrePasajero(columnas[1].trim());
                objPasajeroDto.setEdadPasajero(Short.parseShort(columnas[2].trim()));
                objPasajeroDto.setGeneroPasajero(Boolean.parseBoolean(columnas[3].trim()));
                objPasajeroDto.setNombreImagenPublicoPasajero(columnas[4].trim());
                objPasajeroDto.setNombreImagenPrivadoPasajero(columnas[5].trim());
                objPasajeroDto.setCantidadEquipajePasajero(arrCantEquipaje.getOrDefault(objPasajeroDto.getIdPasajero(), 0));
                objPasajeroDto.setCantidadTiquetePasajero(arrCantTiquetes.getOrDefault(objPasajeroDto.getIdPasajero(), 0));

                arregloPasajeroDtos.add(objPasajeroDto);
                
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException error) {
                Logger.getLogger(PasajeroServicio.class.getName()).log(Level.SEVERE, null, error);
            }
        }
        return arregloPasajeroDtos;
    }

    @Override
    public int numRows() {
        int cantidad = 0;
        try {
            cantidad = miArchivo.cantidadFilas();
        } catch (IOException ex) {
            Logger.getLogger(PasajeroServicio.class.getName()).log(Level.SEVERE, null, ex);
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

    @Override
    public PasajeroDto getOne(Integer codigo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PasajeroDto updateSet(Integer codigo, PasajeroDto objeto, String ruta) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
