package org.unimag.recurso.utilidad;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import org.unimag.recurso.constante.Persistencia;

public class GestorImagen {
    public static boolean esUnaImagen(Path miArchivo) {
        boolean correcto = false;
        try {
            String tipoImagen = Files.probeContentType(miArchivo);
            if (tipoImagen.equals("image/png")
                    || tipoImagen.equals("image/jpeg")) {
                correcto = true;
            }
        } catch (IOException ex) {
            Logger.getLogger(GestorImagen.class.getName()).log(Level.SEVERE, null, ex);
        }
        return correcto;
    }

    public static String identificadorUnico() {
        UUID identificador = UUID.randomUUID();
        return identificador.toString();
    }

    public static String obtenerRutaImagen(TextField caja, FileChooser objSeleccionar) {
        String rutaCompleta;
        File archivoSeleccionado = objSeleccionar.showOpenDialog(null);

        if (archivoSeleccionado != null) {
            rutaCompleta = archivoSeleccionado.getAbsolutePath();
            Path archivoOrigen = Paths.get(rutaCompleta);
            if (esUnaImagen(archivoOrigen)) {
                caja.setText(archivoOrigen.getFileName().toString());
            } else {
                rutaCompleta = "";
                caja.setText("ERROR: formato incorrecto");
            }
        } else {
            rutaCompleta = "";
            caja.setText("ERROR: No seleccionó una imagen");
        }
        return rutaCompleta;
    }

    public static String grabarLaImagen(String rutaCompleta) {
        String nocu;
        Path archivoOrigen = Paths.get(rutaCompleta);
        nocu = identificadorUnico() + "_" + archivoOrigen.getFileName();
        String aca = Persistencia.RUTA_FOTOS + Persistencia.SEPARADOR_CARPETAS
                + nocu;
        Path verificarCarpeta = Paths.get(Persistencia.RUTA_FOTOS);
        if (!Files.isDirectory(verificarCarpeta)) {
            try {
                Files.createDirectories(verificarCarpeta);
            } catch (IOException ex) {
                Logger.getLogger(GestorImagen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Path archivoDestino = Paths.get(aca);
        try {
            Files.copy(archivoOrigen, archivoDestino);
        } catch (IOException ex) {
            Logger.getLogger(GestorImagen.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nocu;
    }
}
