package org.unimag.recurso.utilidad;

import java.io.FileInputStream;
import java.io.IOException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.InputStream;
import org.unimag.recurso.constante.Persistencia;

public class Icono {
    public static ImageView obtenerIcono(String nombreIcono, int alto) {
        String rutaIcono = Persistencia.NOMBRE_CARPETA_IMAGENES_INTERNAS + nombreIcono;//se usa la persistencia
        InputStream iconoSalirStream = Icono.class.getResourceAsStream(rutaIcono);
        if (iconoSalirStream == null) {
            System.err.println("No se pudo encontrar el recurso: " + rutaIcono);
            return null;
        }
        Image iconoBasico = new Image(iconoSalirStream);
        ImageView iconoMostrar = new ImageView(iconoBasico);
        if (alto != 0) {
            iconoMostrar.setFitHeight(alto);
        }

        iconoMostrar.setPreserveRatio(true);
        iconoMostrar.setSmooth(true);
        return iconoMostrar;
    }
    
    public static ImageView previsualizar(String rutaImagen, int dimensionMaxima) {
        ImageView imgMostrar = null;

        try (FileInputStream archivo = new FileInputStream(rutaImagen)) {
            Image imgBasica = new Image(archivo);
            imgMostrar = new ImageView(imgBasica);

            double ancho = imgBasica.getWidth();
            double alto = imgBasica.getHeight();

            if (ancho > alto) {
                imgMostrar.setFitWidth(dimensionMaxima);
            } else {
                imgMostrar.setFitHeight(dimensionMaxima);
            }

            imgMostrar.setPreserveRatio(true);
            imgMostrar.setSmooth(true);

        } catch (IOException miError) {
            System.out.println("Error al cargar la foto externa: " + miError.getMessage());
        }

        return imgMostrar;
    }
}
