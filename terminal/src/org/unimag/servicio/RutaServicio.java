package org.unimag.servicio;

import com.poo.persistence.NioFile;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.unimag.api.ApiOperacionBD;
import org.unimag.dto.RutaDto;
import org.unimag.modelo.Ruta;
import org.unimag.recurso.constante.Persistencia;
import org.unimag.recurso.utilidad.GestorImagen;

public class RutaServicio implements ApiOperacionBD<RutaDto, Integer> {

    private NioFile miArchivo;
    private String nombrePersistencia;

    public RutaServicio() {
        nombrePersistencia = Persistencia.NOMBRE_RUTA;
        try {
            miArchivo = new NioFile(nombrePersistencia);
        } catch (IOException ex) {
            Logger.getLogger(RutaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getSerial() {
        int id = 0;

        try {
            id = miArchivo.ultimoCodigo() + 1;
        } catch (IOException ex) {
            Logger.getLogger(RutaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id;
    }

    @Override
    public RutaDto inserInto(RutaDto dto, String ruta) {
        Ruta objRuta = new Ruta();
        objRuta.setIdRuta(getSerial());
        objRuta.setCiudadOrigenRuta(dto.getCiudadOrigenRuta());
        objRuta.setCiudadDestinoRuta(dto.getCiudadDestinoRuta());
        objRuta.setTarifaRuta(dto.getTarifaRuta());
        
        String nombrePublico = dto.getNombreImagenPublicoRuta();
        if (nombrePublico != null) {
            nombrePublico = nombrePublico.replace("@", "_").replace(";", "_");
        }
        
        String nombrePrivado = ruta != null ? GestorImagen.grabarLaImagen(ruta) : "";
        if (nombrePrivado != null) {
            nombrePrivado = nombrePrivado.replace("@", "_").replace(";", "_");
        }
        
        objRuta.setNombreImagenPublicoRuta(nombrePublico);
        objRuta.setNombreImagenPrivadoRuta(nombrePrivado);

        String filaGrabar = objRuta.getIdRuta() + Persistencia.SEPARADOR_COLUMNAS
                + objRuta.getCiudadOrigenRuta() + Persistencia.SEPARADOR_COLUMNAS
                + objRuta.getCiudadDestinoRuta() + Persistencia.SEPARADOR_COLUMNAS
                + BigDecimal.valueOf(objRuta.getTarifaRuta()).toPlainString() + Persistencia.SEPARADOR_COLUMNAS
                + objRuta.getNombreImagenPublicoRuta() + Persistencia.SEPARADOR_COLUMNAS
                + objRuta.getNombreImagenPrivadoRuta();

        if (miArchivo.agregarRegistro(filaGrabar)) {
            dto.setIdRuta(objRuta.getIdRuta());
            return dto;
        }

        return null;
    }

    @Override
    public List<RutaDto> selectFrom() {
        ViajeServicio viajeServicio = new ViajeServicio();
        Map<Integer, Integer> arrCantidades = viajeServicio.viajesPorRuta();

        List<RutaDto> arregloRutaDtos = new ArrayList<>();
        List<String> arregloDatos = miArchivo.obtenerDatos();

        for (String cadena : arregloDatos) {
            try {
                cadena = cadena.replace("@", "");
                String[] columnas = cadena.split(Persistencia.SEPARADOR_COLUMNAS);
                
                if (columnas.length < 6) {
                    continue;
                }

                RutaDto objRutaDto = new RutaDto();
                objRutaDto.setIdRuta(Integer.parseInt(columnas[0].trim()));
                objRutaDto.setCiudadOrigenRuta(columnas[1].trim());
                objRutaDto.setCiudadDestinoRuta(columnas[2].trim());
                objRutaDto.setTarifaRuta(Double.valueOf(columnas[3].trim()));
                objRutaDto.setNombreImagenPublicoRuta(columnas[4].trim());
                objRutaDto.setNombreImagenPrivadoRuta(columnas[5].trim());
                objRutaDto.setCantidadViajeRuta(arrCantidades.getOrDefault(objRutaDto.getIdRuta(), 0));

                arregloRutaDtos.add(objRutaDto);
                
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException error) {
                Logger.getLogger(RutaServicio.class.getName()).log(Level.SEVERE, null, error);
            }
        }
        return arregloRutaDtos;
    }

    @Override
    public int numRows() {
        int cantidad = 0;
        try {
            cantidad = miArchivo.cantidadFilas();
        } catch (IOException ex) {
            Logger.getLogger(RutaServicio.class.getName()).log(Level.SEVERE, null, ex);
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
    public RutaDto getOne(Integer codigo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public RutaDto updateSet(Integer codigo, RutaDto objeto, String ruta) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
