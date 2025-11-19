package org.unimag.recurso.constante;

import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import javafx.stage.Screen;

public class Configuracion {
    public static final int ALTO_APP = (int) (Screen.getPrimary().getBounds().getHeight() * 0.7);//RESPONSIVE
    public static final int ANCHO_APP = (int) (Screen.getPrimary().getBounds().getWidth() * 0.6);

    private static final double CABECERA_ALTO_PORCENTAJE = 0.1;
    public static final double ALTO_CABECERA = CABECERA_ALTO_PORCENTAJE * ALTO_APP;
////////////////////////////CAMBIAR Y EDITAR COLORES//////////////////////////////////////////////

    public static final String MORADO_MEDIO = "#9120e2";
    public static final String MORADO_OSCURO = "#6a0dad";
    public static final String MORADO_CLARO = "#dd9eff";
    public static final String MORADO_SUPER_CLARO = "#f1d7ff";
    public static final String MORADO_PURPURA = "#e440ff";
    public static final String MORADO_PURPURA_CLARO = "#f3a9ff";

    public static final String CABECERA_ESTILO_FONDO = String.format("-fx-background-color: #C3A7E8;"
            + "-fx-background-color:linear-gradient(#2E2E2E,#C3A7E8, #F7D6E0); ");

    public static final double MARCO_ALTO_PORCENTAJE = 0.75;
    public static final double MARCO_ANCHO_PORCENTAJE = 0.80;

    public static final double GRILLA_ANCHO_PORCENTAJE = 0.7;

    public static final String DEGRADE_BORDE = "#b63eff";

    public static final Stop[] DEGRADE_ARREGLO_GENERO = new Stop[]{
        new Stop(0.0, Color.web(MORADO_SUPER_CLARO, 0.8)),
        new Stop(0.25, Color.web(MORADO_CLARO, 0.7)),
        new Stop(0.50, Color.web(MORADO_CLARO, 0.6)),
        new Stop(0.75, Color.web(MORADO_SUPER_CLARO, 0.5)),};

    public static final Stop[] DEGRADE_ARREGLO_PELICULA = new Stop[]{
        new Stop(0.0, Color.web(MORADO_PURPURA, 0.8)),
        new Stop(0.25, Color.web(MORADO_PURPURA_CLARO, 0.7)),
        new Stop(0.50, Color.web(MORADO_PURPURA_CLARO, 0.6)),
        new Stop(0.75, Color.web(MORADO_PURPURA, 0.5)),};
////////////////////////////CAMBIAR Y EDITAR COLORES//////////////////////////////////////////////
    public static final String ICONO_BORRAR = "iconoBorrar.png";
    public static final String ICONO_EDITAR = "iconoEditar.png";
    public static final String ICONO_CANCELAR = "iconoCancelar.png";
    public static final String ICONO_NO_DISPONIBLE = "imgNoDisponible.png";
}
