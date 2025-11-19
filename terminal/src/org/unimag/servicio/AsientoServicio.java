package org.unimag.servicio;

import com.poo.persistence.NioFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.unimag.api.ApiOperacionBD;
import org.unimag.dto.AsientoDto;
import org.unimag.dto.BusDto;
import org.unimag.modelo.Asiento;
import org.unimag.modelo.Bus;
import org.unimag.recurso.constante.Persistencia;
import org.unimag.recurso.utilidad.GestorImagen;
import org.unimag.servicio.TiqueteServicio;

public class AsientoServicio implements ApiOperacionBD<AsientoDto, Integer> {

    private NioFile miArchivo;
    private String nombrePersistencia;

    public AsientoServicio() {
        nombrePersistencia = Persistencia.NOMBRE_ASIENTO;
        try {
            miArchivo = new NioFile(nombrePersistencia);
        } catch (IOException ex) {
            Logger.getLogger(AsientoServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getSerial() {
        int id = 0;
        try {
            id = miArchivo.ultimoCodigo() + 1;
        } catch (IOException ex) {
            Logger.getLogger(AsientoServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    @Override
    public AsientoDto inserInto(AsientoDto dto, String ruta) {
        Bus objBus = new Bus(dto.getBusAsiento().getIdBus(), "", null, "", "", 0,0);

        Asiento objAsiento = new Asiento();
        objAsiento.setIdAsiento(getSerial());
        objAsiento.setBusAsiento(objBus);
        objAsiento.setEstadoAsiento(dto.getEstadoAsiento());

        String nombrePublico = dto.getNombreImagenPublicoAsiento();
        if (nombrePublico != null) {
            nombrePublico = nombrePublico.replace("@", "_").replace(";", "_");
        }

        String nombrePrivado = ruta != null ? GestorImagen.grabarLaImagen(ruta) : "";
        if (nombrePrivado != null) {
            nombrePrivado = nombrePrivado.replace("@", "_").replace(";", "_");
        }

        objAsiento.setNombreImagenPublicoAsiento(nombrePublico);
        objAsiento.setNombreImagenPrivadoAsiento(nombrePrivado);

        String filaGrabar = objAsiento.getIdAsiento() + Persistencia.SEPARADOR_COLUMNAS
                + objAsiento.getBusAsiento().getIdBus() + Persistencia.SEPARADOR_COLUMNAS
                + objAsiento.getEstadoAsiento() + Persistencia.SEPARADOR_COLUMNAS
                + objAsiento.getNombreImagenPublicoAsiento() + Persistencia.SEPARADOR_COLUMNAS
                + objAsiento.getNombreImagenPrivadoAsiento();

        if (miArchivo.agregarRegistro(filaGrabar)) {
            dto.setIdAsiento(objAsiento.getIdAsiento());
            return dto;
        }

        return null;
    }

    @Override
    public List<AsientoDto> selectFrom() {
        BusServicio busServicio = new BusServicio();
        List<BusDto> arrBuses = busServicio.selectFrom();

        TiqueteServicio tiqueteServicio = new TiqueteServicio();
        Map<Integer, Integer> arrCantidades = tiqueteServicio.tiquetesActivosPorAsiento();

        // Crear MAP para acceso rápido
        Map<Integer, BusDto> mapBuses = new HashMap<>();
        for (BusDto bus : arrBuses) {
            mapBuses.put(bus.getIdBus(), bus);
        }

        List<AsientoDto> arregloAsientoDtos = new ArrayList<>();
        List<String> arregloDatos = miArchivo.obtenerDatos();

        for (String cadena : arregloDatos) {
            try {
                cadena = cadena.replace("@", "");
                String[] columnas = cadena.split(Persistencia.SEPARADOR_COLUMNAS);

                AsientoDto objAsientoDto = new AsientoDto();
                objAsientoDto.setIdAsiento(Integer.parseInt(columnas[0].trim()));

                int idBus = Integer.parseInt(columnas[1].trim());
                objAsientoDto.setBusAsiento(mapBuses.getOrDefault(idBus, null));

                objAsientoDto.setEstadoAsiento(Boolean.parseBoolean(columnas[2].trim()));
                objAsientoDto.setNombreImagenPublicoAsiento(columnas[3].trim());
                objAsientoDto.setNombreImagenPrivadoAsiento(columnas[4].trim());
                objAsientoDto.setCantidadTiqueteAsiento(arrCantidades.getOrDefault(objAsientoDto.getIdAsiento(), 0));

                arregloAsientoDtos.add(objAsientoDto);

            } catch (NumberFormatException error) {
                Logger.getLogger(AsientoServicio.class.getName()).log(Level.SEVERE, null, error);
            }
        }

        return arregloAsientoDtos;
    }

    public List<AsientoDto> selectFromWhereDesocupados() {
        List<AsientoDto> arreglo = new ArrayList<>();
        List<String> arregloDatos = miArchivo.obtenerDatos();

        TiqueteServicio tiqueteServicio = new TiqueteServicio();
        Map<Integer, Integer> arrCantTiquetesActivos = tiqueteServicio.tiquetesActivosPorAsiento();

        for (String cadena : arregloDatos) {
            try {
                cadena = cadena.replace("@", "");
                String[] columnas = cadena.split(Persistencia.SEPARADOR_COLUMNAS);

                int idAsiento = Integer.parseInt(columnas[0].trim());
                int idBus = Integer.parseInt(columnas[1].trim());
                boolean estadoAsiento = Boolean.valueOf(columnas[2].trim());
                String imagenPublica = columnas[3].trim();
                String imagenPrivada = columnas[4].trim();

                // Si no tiene tiquetes activos → el asiento está desocupado
                if (arrCantTiquetesActivos.getOrDefault(idAsiento, 0) == 0) {

                    // Aquí se usa DTO para devolver a la vista
                    BusDto busDto = new BusDto(idBus, "", null, "", "", 0,0);

                    arreglo.add(new AsientoDto(
                            idAsiento,
                            busDto,
                            estadoAsiento,
                            imagenPublica,
                            imagenPrivada,
                            0
                    ));
                }

            } catch (NumberFormatException error) {
                Logger.getLogger(AsientoServicio.class.getName())
                        .log(Level.SEVERE, null, error);
            }
        }

        return arreglo;
    }

    @Override
    public int numRows() {
        int cantidad = 0;
        try {
            cantidad = miArchivo.cantidadFilas();
        } catch (IOException ex) {
            Logger.getLogger(AsientoServicio.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AsientoServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return correcto;
    }

    @Override
    public AsientoDto getOne(Integer codigo) {
        BusServicio busServicio = new BusServicio();
        Map<Integer, BusDto> mapBuses = new HashMap<>();

        for (BusDto bus : busServicio.selectFrom()) {
            mapBuses.put(bus.getIdBus(), bus);
        }

        List<String> arregloDatos = miArchivo.obtenerDatos();

        for (String cadena : arregloDatos) {
            try {
                cadena = cadena.replace("@", "");
                String[] columnas = cadena.split(Persistencia.SEPARADOR_COLUMNAS);

                int idAsiento = Integer.parseInt(columnas[0].trim());

                if (idAsiento == codigo) {
                    AsientoDto objAsientoDto = new AsientoDto();
                    objAsientoDto.setIdAsiento(idAsiento);

                    int idBus = Integer.parseInt(columnas[1].trim());
                    objAsientoDto.setBusAsiento(mapBuses.getOrDefault(idBus, null));

                    objAsientoDto.setEstadoAsiento(Boolean.parseBoolean(columnas[2].trim()));
                    objAsientoDto.setNombreImagenPublicoAsiento(columnas[3].trim());
                    objAsientoDto.setNombreImagenPrivadoAsiento(columnas[4].trim());

                    return objAsientoDto;
                }

            } catch (NumberFormatException error) {
                Logger.getLogger(AsientoServicio.class.getName()).log(Level.SEVERE, null, error);
            }
        }

        return null;
    }

    @Override
    public AsientoDto updateSet(Integer codigo, AsientoDto objeto, String ruta) {
        try {
            List<String> lineas = miArchivo.obtenerDatos();
            int indice = -1;

            // Buscar el índice del registro
            for (int i = 0; i < lineas.size(); i++) {
                String lineaLimpia = lineas.get(i).replace("@", "");
                String[] columnas = lineaLimpia.split(Persistencia.SEPARADOR_COLUMNAS);
                if (Integer.parseInt(columnas[0].trim()) == codigo) {
                    indice = i;
                    break;
                }
            }

            if (indice != -1) {
                // Construir la nueva línea con los datos actualizados
                String lineaActual = lineas.get(indice).replace("@", "");
                String[] columnas = lineaActual.split(Persistencia.SEPARADOR_COLUMNAS);

                // Actualizar solo el estado (columna 2)
                columnas[2] = String.valueOf(objeto.getEstadoAsiento());

                // Reconstruir la línea completa
                String nuevaLinea = String.join(Persistencia.SEPARADOR_COLUMNAS, columnas);

                // Usar el método actualizaFilaPosicion de NioFile
                if (miArchivo.actualizaFilaPosicion(indice, nuevaLinea)) {
                    return objeto;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(AsientoServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Map<Integer, Integer> asientosPorBus() {
    Map<Integer, Integer> conteo = new HashMap<>();
    List<String> arregloDatos = miArchivo.obtenerDatos();

    for (String cadena : arregloDatos) {
        try {
            cadena = cadena.replace("@", "");
            String[] columnas = cadena.split(Persistencia.SEPARADOR_COLUMNAS);
            
            int idBus = Integer.parseInt(columnas[1].trim());
            conteo.put(idBus, conteo.getOrDefault(idBus, 0) + 1);
            
        } catch (NumberFormatException error) {
            Logger.getLogger(AsientoServicio.class.getName()).log(Level.SEVERE, null, error);
        }
    }

    return conteo;
}
}
