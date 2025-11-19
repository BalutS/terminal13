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
import org.unimag.dto.ConductorDto;
import org.unimag.dto.RutaDto;
import org.unimag.dto.TiqueteDto;
import org.unimag.dto.ViajeDto;
import org.unimag.modelo.Bus;
import org.unimag.modelo.Conductor;
import org.unimag.modelo.Ruta;
import org.unimag.modelo.Tiquete;
import org.unimag.modelo.Viaje;
import org.unimag.recurso.constante.Persistencia;
import org.unimag.recurso.utilidad.GestorImagen;

public class ViajeServicio implements ApiOperacionBD<ViajeDto, Integer> {

    private NioFile miArchivo;
    private String nombrePersistencia;

    public ViajeServicio() {
        nombrePersistencia = Persistencia.NOMBRE_VIAJE;
        try {
            miArchivo = new NioFile(nombrePersistencia);
        } catch (IOException ex) {
            Logger.getLogger(ViajeServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getSerial() {
        int id = 0;
        try {
            id = miArchivo.ultimoCodigo() + 1;
        } catch (IOException ex) {
            Logger.getLogger(ViajeServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    @Override
    public ViajeDto inserInto(ViajeDto dto, String ruta) {
        Ruta objRuta = new Ruta(dto.getRutaViaje().getIdRuta(), "", "", 0.0, "", "", 0);
        Conductor objConductor = new Conductor(dto.getConductorViaje().getIdConductor(), "", (short) 0, false, "", "", 0);
        Bus objBus = new Bus(dto.getBusViaje().getIdBus(), "", null, "", "", 0,0);

        Viaje objViaje = new Viaje();
        objViaje.setIdViaje(getSerial());
        objViaje.setRutaViaje(objRuta);
        objViaje.setConductorViaje(objConductor);
        objViaje.setBusViaje(objBus);
        objViaje.setFechaViaje(dto.getFechaViaje());
        objViaje.setHoraViaje(dto.getHoraViaje());
        objViaje.setEstadoViaje(dto.getEstadoViaje());
        
        String nombrePublico = dto.getNombreImagenPublicoViaje();
        if (nombrePublico != null) {
            nombrePublico = nombrePublico.replace("@", "_").replace(";", "_");
        }
        
        String nombrePrivado = ruta != null ? GestorImagen.grabarLaImagen(ruta) : "";
        if (nombrePrivado != null) {
            nombrePrivado = nombrePrivado.replace("@", "_").replace(";", "_");
        }
        
        objViaje.setNombreImagenPublicoViaje(nombrePublico);
        objViaje.setNombreImagenPrivadoViaje(nombrePrivado);

        String filaGrabar = objViaje.getIdViaje() + Persistencia.SEPARADOR_COLUMNAS
                + objViaje.getRutaViaje().getIdRuta() + Persistencia.SEPARADOR_COLUMNAS
                + objViaje.getConductorViaje().getIdConductor() + Persistencia.SEPARADOR_COLUMNAS
                + objViaje.getBusViaje().getIdBus() + Persistencia.SEPARADOR_COLUMNAS
                + objViaje.getFechaViaje() + Persistencia.SEPARADOR_COLUMNAS
                + objViaje.getHoraViaje() + Persistencia.SEPARADOR_COLUMNAS
                + objViaje.getEstadoViaje()+ Persistencia.SEPARADOR_COLUMNAS
                + objViaje.getNombreImagenPublicoViaje() + Persistencia.SEPARADOR_COLUMNAS
                + objViaje.getNombreImagenPrivadoViaje();

        if (miArchivo.agregarRegistro(filaGrabar)) {
            dto.setIdViaje(objViaje.getIdViaje());
            return dto;
        }

        return null;
    }

    @Override
    public List<ViajeDto> selectFrom() {
        RutaServicio rutaServicio = new RutaServicio();
        List<RutaDto> arrRutas = rutaServicio.selectFrom();

        ConductorServicio conductorServicio = new ConductorServicio();
        List<ConductorDto> arrConductores = conductorServicio.selectFrom();

        BusServicio busServicio = new BusServicio();
        List<BusDto> arrBuses = busServicio.selectFrom();

        TiqueteServicio tiqueteServicio = new TiqueteServicio();
        Map<Integer, Integer> arrCantidades = tiqueteServicio.tiquetesPorViaje();

        Map<Integer, RutaDto> mapRutas = new HashMap<>();
        for (RutaDto r : arrRutas) mapRutas.put(r.getIdRuta(), r);

        Map<Integer, ConductorDto> mapConductores = new HashMap<>();
        for (ConductorDto c : arrConductores) mapConductores.put(c.getIdConductor(), c);

        Map<Integer, BusDto> mapBuses = new HashMap<>();
        for (BusDto b : arrBuses) mapBuses.put(b.getIdBus(), b);

        List<ViajeDto> arregloViajeDtos = new ArrayList<>();
        List<String> arregloDatos = miArchivo.obtenerDatos();

        for (String cadena : arregloDatos) {
            try {
                cadena = cadena.replace("@", "");
                String[] columnas = cadena.split(Persistencia.SEPARADOR_COLUMNAS);
                
                if (columnas.length < 9) {
                    continue;
                }

                ViajeDto obj = new ViajeDto();
                obj.setIdViaje(Integer.parseInt(columnas[0].trim()));

                obj.setRutaViaje(mapRutas.getOrDefault(Integer.parseInt(columnas[1].trim()), null));
                obj.setConductorViaje(mapConductores.getOrDefault(Integer.parseInt(columnas[2].trim()), null));
                obj.setBusViaje(mapBuses.getOrDefault(Integer.parseInt(columnas[3].trim()), null));

                obj.setFechaViaje(columnas[4].trim());
                obj.setHoraViaje(Integer.parseInt(columnas[5].trim()));
                obj.setEstadoViaje(Boolean.parseBoolean(columnas[6].trim()));
                obj.setNombreImagenPublicoViaje(columnas[7].trim());
                obj.setNombreImagenPrivadoViaje(columnas[8].trim());
                obj.setCantidadTiqueteViaje(arrCantidades.getOrDefault(obj.getIdViaje(), 0));

                arregloViajeDtos.add(obj);

            } catch (NumberFormatException | ArrayIndexOutOfBoundsException error) {
                Logger.getLogger(ViajeServicio.class.getName()).log(Level.SEVERE, null, error);
            }
        }

        return arregloViajeDtos;
    }

    @Override
    public int numRows() {
        int cantidad = 0;
        try {
            cantidad = miArchivo.cantidadFilas();
        } catch (IOException ex) {
            Logger.getLogger(ViajeServicio.class.getName()).log(Level.SEVERE, null, ex);
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

    public Map<Integer, Integer> viajesPorRuta() {
        Map<Integer, Integer> arrCantidades = new HashMap<>();
        List<String> arregloDatos = miArchivo.obtenerDatos();

        for (String cadena : arregloDatos) {
            try {
                cadena = cadena.replace("@", "");
                String[] columnas = cadena.split(Persistencia.SEPARADOR_COLUMNAS);

                if (columnas.length < 2) {
                    continue;
                }

                int idRuta = Integer.parseInt(columnas[1].trim());
                arrCantidades.put(idRuta, arrCantidades.getOrDefault(idRuta, 0) + 1);

            } catch (NumberFormatException | ArrayIndexOutOfBoundsException error) {
                Logger.getLogger(ViajeServicio.class.getName()).log(Level.SEVERE, null, error);
            }
        }
        return arrCantidades;
    }

    public Map<Integer, Integer> viajesPorConductor() {
        Map<Integer, Integer> arrCantidades = new HashMap<>();
        List<String> arregloDatos = miArchivo.obtenerDatos();

        for (String cadena : arregloDatos) {
            try {
                cadena = cadena.replace("@", "");
                String[] columnas = cadena.split(Persistencia.SEPARADOR_COLUMNAS);

                if (columnas.length < 3) {
                    continue;
                }

                int idConductor = Integer.parseInt(columnas[2].trim());
                arrCantidades.put(idConductor, arrCantidades.getOrDefault(idConductor, 0) + 1);

            } catch (NumberFormatException | ArrayIndexOutOfBoundsException error) {
                Logger.getLogger(ViajeServicio.class.getName()).log(Level.SEVERE, null, error);
            }
        }
        return arrCantidades;
    }

    public Map<Integer, Integer> viajesPorBus() {
        Map<Integer, Integer> arrCantidades = new HashMap<>();
        List<String> arregloDatos = miArchivo.obtenerDatos();

        for (String cadena : arregloDatos) {
            try {
                cadena = cadena.replace("@", "");
                String[] columnas = cadena.split(Persistencia.SEPARADOR_COLUMNAS);

                if (columnas.length < 4) {
                    continue;
                }

                int idBus = Integer.parseInt(columnas[3].trim());
                arrCantidades.put(idBus, arrCantidades.getOrDefault(idBus, 0) + 1);

            } catch (NumberFormatException | ArrayIndexOutOfBoundsException error) {
                Logger.getLogger(ViajeServicio.class.getName()).log(Level.SEVERE, null, error);
            }
        }
        return arrCantidades;
    }


    @Override
    public ViajeDto getOne(Integer codigo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ViajeDto updateSet(Integer codigo, ViajeDto objeto, String ruta) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
