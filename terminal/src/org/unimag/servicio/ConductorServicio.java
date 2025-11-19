package org.unimag.servicio;

import com.poo.persistence.NioFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.unimag.api.ApiOperacionBD;
import org.unimag.dto.ConductorDto;
import org.unimag.modelo.Conductor;
import org.unimag.recurso.constante.Persistencia;
import org.unimag.recurso.utilidad.GestorImagen;

public class ConductorServicio implements ApiOperacionBD<ConductorDto, Integer> {

    private NioFile miArchivo;
    private String nombrePersistencia;

    public ConductorServicio() {
        nombrePersistencia = Persistencia.NOMBRE_CONDUCTOR;
        try {
            miArchivo = new NioFile(nombrePersistencia);
        } catch (IOException ex) {
            Logger.getLogger(ConductorServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getSerial() {
        int id = 0;
        try {
            id = miArchivo.ultimoCodigo() + 1;
        } catch (IOException ex) {
            Logger.getLogger(ConductorServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    @Override
    public ConductorDto inserInto(ConductorDto dto, String ruta) {
        Conductor objConductor = new Conductor();
        objConductor.setIdConductor(getSerial());
        objConductor.setNombreConductor(dto.getNombreConductor());
        objConductor.setEdadConductor(dto.getEdadConductor());
        objConductor.setGeneroConductor(dto.getGeneroConductor());
        
        // Limpiar nombres de archivo para evitar conflictos
        String nombrePublico = dto.getNombreImagenPublicoConductor();
        if (nombrePublico != null) {
            nombrePublico = nombrePublico.replace("@", "_").replace(";", "_");
        }
        
        String nombrePrivado = ruta != null ? GestorImagen.grabarLaImagen(ruta) : "";
        if (nombrePrivado != null) {
            nombrePrivado = nombrePrivado.replace("@", "_").replace(";", "_");
        }
        
        objConductor.setNombreImagenPublicoConductor(nombrePublico);
        objConductor.setNombreImagenPrivadoConductor(nombrePrivado);

        String filaGrabar = objConductor.getIdConductor() + Persistencia.SEPARADOR_COLUMNAS
                + objConductor.getNombreConductor() + Persistencia.SEPARADOR_COLUMNAS
                + objConductor.getEdadConductor() + Persistencia.SEPARADOR_COLUMNAS
                + objConductor.getGeneroConductor() + Persistencia.SEPARADOR_COLUMNAS
                + objConductor.getNombreImagenPublicoConductor() + Persistencia.SEPARADOR_COLUMNAS
                + objConductor.getNombreImagenPrivadoConductor();

        if (miArchivo.agregarRegistro(filaGrabar)) {
            dto.setIdConductor(objConductor.getIdConductor());
            return dto;
        }

        return null;
    }

    @Override
    public List<ConductorDto> selectFrom() {
        ViajeServicio viajeServicio = new ViajeServicio();
        Map<Integer, Integer> arrCantidades = viajeServicio.viajesPorConductor();

        List<ConductorDto> arregloConductorDtos = new ArrayList<>();
        List<String> arregloDatos = miArchivo.obtenerDatos();

        for (String cadena : arregloDatos) {
            try {
                cadena = cadena.replace("@", "");
                String[] columnas = cadena.split(Persistencia.SEPARADOR_COLUMNAS);
                
                // Validar que la fila tenga el número correcto de columnas
                if (columnas.length < 6) {
                    continue;
                }

                ConductorDto objConductorDto = new ConductorDto();
                objConductorDto.setIdConductor(Integer.parseInt(columnas[0].trim()));
                objConductorDto.setNombreConductor(columnas[1].trim());
                objConductorDto.setEdadConductor(Short.parseShort(columnas[2].trim()));
                objConductorDto.setGeneroConductor(Boolean.parseBoolean(columnas[3].trim()));
                objConductorDto.setNombreImagenPublicoConductor(columnas[4].trim());
                objConductorDto.setNombreImagenPrivadoConductor(columnas[5].trim());
                objConductorDto.setCantidadViajeConductor(arrCantidades.getOrDefault(objConductorDto.getIdConductor(), 0));

                arregloConductorDtos.add(objConductorDto);

            } catch (NumberFormatException | ArrayIndexOutOfBoundsException error) {
                Logger.getLogger(ConductorServicio.class.getName()).log(Level.SEVERE, null, error);
            }
        }
        return arregloConductorDtos;
    }

    @Override
    public int numRows() {
        int cantidad = 0;
        try {
            cantidad = miArchivo.cantidadFilas();
        } catch (IOException ex) {
            Logger.getLogger(ConductorServicio.class.getName()).log(Level.SEVERE, null, ex);
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
    public ConductorDto getOne(Integer codigo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ConductorDto updateSet(Integer codigo, ConductorDto objeto, String ruta) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
