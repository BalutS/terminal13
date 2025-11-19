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
import org.unimag.dto.AsientoDto;
import org.unimag.dto.BusDto;
import org.unimag.dto.ConductorDto;
import org.unimag.dto.PasajeroDto;
import org.unimag.dto.RutaDto;
import org.unimag.dto.TiqueteDto;
import org.unimag.dto.ViajeDto;
import org.unimag.modelo.Asiento;
import org.unimag.modelo.Bus;
import org.unimag.modelo.Conductor;
import org.unimag.modelo.Pasajero;
import org.unimag.modelo.Ruta;
import org.unimag.modelo.Tiquete;
import org.unimag.modelo.Viaje;
import org.unimag.recurso.constante.Persistencia;
import org.unimag.recurso.utilidad.GestorImagen;

public class TiqueteServicio implements ApiOperacionBD<TiqueteDto, Integer> {

    private NioFile miArchivo;
    private String nombrePersistencia;
    private AsientoServicio asientoServicio;

    public TiqueteServicio(AsientoServicio asientoServicio) {
        this.asientoServicio = asientoServicio;
        nombrePersistencia = Persistencia.NOMBRE_TIQUETE;
        try {
            miArchivo = new NioFile(nombrePersistencia);
        } catch (IOException ex) {
            Logger.getLogger(TiqueteServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public TiqueteServicio() {
        nombrePersistencia = Persistencia.NOMBRE_TIQUETE;
        asientoServicio = new AsientoServicio();
        try {
            miArchivo = new NioFile(nombrePersistencia);
        } catch (IOException ex) {
            Logger.getLogger(TiqueteServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getSerial() {
        int id = 0;
        try {
            id = miArchivo.ultimoCodigo() + 1;
        } catch (IOException ex) {
            Logger.getLogger(TiqueteServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    @Override
    public TiqueteDto inserInto(TiqueteDto dto, String ruta) {
        Pasajero objPasajero = new Pasajero(dto.getPasajeroTiquete().getIdPasajero(), "", (short) 0, false, "", "", 0, 0);
        Viaje objViaje = new Viaje(dto.getViajeTiquete().getIdViaje(), null, null, null, "", 0, false, "", "", 0);
        Asiento objAsiento = new Asiento(dto.getAsientoTiquete().getIdAsiento(), null, false, "", "", 0);

        Tiquete objTiquete = new Tiquete();
        objTiquete.setIdTiquete(getSerial());
        objTiquete.setPasajeroTiquete(objPasajero);
        objTiquete.setViajeTiquete(objViaje);
        objTiquete.setAsientoTiquete(objAsiento);
        objTiquete.setEstadoTiquete(dto.getEstadoTiquete());
        
        String nombrePublico = dto.getNombreImagenPublicoTiquete();
        if (nombrePublico != null) {
            nombrePublico = nombrePublico.replace("@", "_").replace(";", "_");
        }
        
        String nombrePrivado = ruta != null ? GestorImagen.grabarLaImagen(ruta) : "";
        if (nombrePrivado != null) {
            nombrePrivado = nombrePrivado.replace("@", "_").replace(";", "_");
        }
        
        objTiquete.setNombreImagenPublicoTiquete(nombrePublico);
        objTiquete.setNombreImagenPrivadoTiquete(nombrePrivado);

        String filaGrabar = objTiquete.getIdTiquete() + Persistencia.SEPARADOR_COLUMNAS
                + objTiquete.getPasajeroTiquete().getIdPasajero() + Persistencia.SEPARADOR_COLUMNAS
                + objTiquete.getViajeTiquete().getIdViaje() + Persistencia.SEPARADOR_COLUMNAS
                + objTiquete.getAsientoTiquete().getIdAsiento() + Persistencia.SEPARADOR_COLUMNAS
                + objTiquete.getEstadoTiquete() + Persistencia.SEPARADOR_COLUMNAS
                + objTiquete.getNombreImagenPublicoTiquete() + Persistencia.SEPARADOR_COLUMNAS
                + objTiquete.getNombreImagenPrivadoTiquete();

        if (miArchivo.agregarRegistro(filaGrabar)) {
            dto.setIdTiquete(objTiquete.getIdTiquete());
            AsientoDto asientoDto = new AsientoDto();
            asientoDto.setEstadoAsiento(true);
            if (asientoServicio.updateSet(objAsiento.getIdAsiento(), asientoDto, "") != null) {
                return dto;
            }
        }

        return null;
    }

    @Override
    public List<TiqueteDto> selectFrom() {
        PasajeroServicio pasajeroServicio = new PasajeroServicio();
        List<PasajeroDto> arrPasajeros = pasajeroServicio.selectFrom();

        ViajeServicio viajeServicio = new ViajeServicio();
        List<ViajeDto> arrViajes = viajeServicio.selectFrom();

        AsientoServicio asientoServicio = new AsientoServicio();
        List<AsientoDto> arrAsientos = asientoServicio.selectFrom();

        Map<Integer, PasajeroDto> mapPasajeros = new HashMap<>();
        for (PasajeroDto p : arrPasajeros) mapPasajeros.put(p.getIdPasajero(), p);

        Map<Integer, ViajeDto> mapViajes = new HashMap<>();
        for (ViajeDto v : arrViajes) mapViajes.put(v.getIdViaje(), v);

        Map<Integer, AsientoDto> mapAsientos = new HashMap<>();
        for (AsientoDto a : arrAsientos) mapAsientos.put(a.getIdAsiento(), a);

        List<TiqueteDto> arregloTiqueteDtos = new ArrayList<>();
        List<String> arregloDatos = miArchivo.obtenerDatos();

        for (String cadena : arregloDatos) {
            try {
                cadena = cadena.replace("@", "");
                String[] columnas = cadena.split(Persistencia.SEPARADOR_COLUMNAS);
                
                if (columnas.length < 7) {
                    continue;
                }

                TiqueteDto obj = new TiqueteDto();
                obj.setIdTiquete(Integer.parseInt(columnas[0].trim()));

                obj.setPasajeroTiquete(mapPasajeros.getOrDefault(Integer.parseInt(columnas[1].trim()), null));
                obj.setViajeTiquete(mapViajes.getOrDefault(Integer.parseInt(columnas[2].trim()), null));
                obj.setAsientoTiquete(mapAsientos.getOrDefault(Integer.parseInt(columnas[3].trim()), null));
                obj.setEstadoTiquete(Boolean.parseBoolean(columnas[4].trim()));
                obj.setNombreImagenPublicoTiquete(columnas[5].trim());
                obj.setNombreImagenPrivadoTiquete(columnas[6].trim());

                arregloTiqueteDtos.add(obj);

            } catch (NumberFormatException | ArrayIndexOutOfBoundsException error) {
                Logger.getLogger(TiqueteServicio.class.getName()).log(Level.SEVERE, null, error);
            }
        }
        return arregloTiqueteDtos;
    }

    @Override
    public int numRows() {
        int cantidad = 0;
        try {
            cantidad = miArchivo.cantidadFilas();
        } catch (IOException ex) {
            Logger.getLogger(TiqueteServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cantidad;
    }

    @Override
    public Boolean deleteFrom(Integer codigo) {
        try {
            String[] tiqueteInfo = miArchivo.obtenerDatos().get(codigo).split(Persistencia.SEPARADOR_COLUMNAS);
            int idAsiento = Integer.parseInt(tiqueteInfo[3].trim());

            if (!miArchivo.borrarFilaPosicion(codigo).isEmpty()) {
                AsientoDto asientoDto = asientoServicio.getOne(idAsiento);
                if (asientoDto != null) {
                    asientoDto.setEstadoAsiento(false);
                    asientoServicio.updateSet(idAsiento, asientoDto, "");
                }
                return true;
            }
        } catch (IOException | NumberFormatException | IndexOutOfBoundsException ex) {
            Logger.getLogger(TiqueteServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public Map<Integer, Integer> tiquetesActivosPorAsiento() {
        Map<Integer, Integer> arrCantidades = new HashMap<>();
        List<String> arregloDatos = miArchivo.obtenerDatos();

        for (String cadena : arregloDatos) {
            try {
                cadena = cadena.replace("@", "");
                String[] columnas = cadena.split(Persistencia.SEPARADOR_COLUMNAS);
                
                if (columnas.length < 5) {
                    continue;
                }

                int idAsiento = Integer.parseInt(columnas[3].trim());
                boolean estadoTiquete = Boolean.valueOf(columnas[4].trim());

                if (estadoTiquete) {
                    arrCantidades.put(idAsiento, arrCantidades.getOrDefault(idAsiento, 0) + 1);
                }

            } catch (NumberFormatException | ArrayIndexOutOfBoundsException error) {
                Logger.getLogger(TiqueteServicio.class.getName()).log(Level.SEVERE, null, error);
            }
        }
        return arrCantidades;
    }

    public Map<Integer, Integer> tiquetesPorPasajero() {
        Map<Integer, Integer> arrCantidades = new HashMap<>();
        List<String> arregloDatos = miArchivo.obtenerDatos();

        for (String cadena : arregloDatos) {
            try {
                cadena = cadena.replace("@", "");
                String[] columnas = cadena.split(Persistencia.SEPARADOR_COLUMNAS);

                if (columnas.length < 2) {
                    continue;
                }

                int idPasajero = Integer.parseInt(columnas[1].trim());
                arrCantidades.put(idPasajero, arrCantidades.getOrDefault(idPasajero, 0) + 1);

            } catch (NumberFormatException | ArrayIndexOutOfBoundsException error) {
                Logger.getLogger(TiqueteServicio.class.getName()).log(Level.SEVERE, null, error);
            }
        }
        return arrCantidades;
    }

    public Map<Integer, Integer> tiquetesPorViaje() {
        Map<Integer, Integer> arrCantidades = new HashMap<>();
        List<String> arregloDatos = miArchivo.obtenerDatos();

        for (String cadena : arregloDatos) {
            try {
                cadena = cadena.replace("@", "");
                String[] columnas = cadena.split(Persistencia.SEPARADOR_COLUMNAS);

                if (columnas.length < 3) {
                    continue;
                }

                int idViaje = Integer.parseInt(columnas[2].trim());
                arrCantidades.put(idViaje, arrCantidades.getOrDefault(idViaje, 0) + 1);

            } catch (NumberFormatException | ArrayIndexOutOfBoundsException error) {
                Logger.getLogger(TiqueteServicio.class.getName()).log(Level.SEVERE, null, error);
            }
        }
        return arrCantidades;
    }

    @Override
    public TiqueteDto getOne(Integer codigo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TiqueteDto updateSet(Integer codigo, TiqueteDto objeto, String ruta) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
