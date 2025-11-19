package org.unimag.modelo;

public class Ruta {

    private int idRuta;
    private String ciudadOrigenRuta;
    private String ciudadDestinoRuta;
    private Double tarifaRuta;
    private String nombreImagenPublicoRuta;
    private String nombreImagenPrivadoRuta;
    private int cantidadViajeRuta;

    public int getCantidadViajeRuta() {
        return cantidadViajeRuta;
    }

    public void setCantidadViajeRuta(int cantidadViajeRuta) {
        this.cantidadViajeRuta = cantidadViajeRuta;
    }

    /**
     * @return the idRuta
     */
    public int getIdRuta() {
        return idRuta;
    }

    /**
     * @param idRuta the idRuta to set
     */
    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }

    /**
     * @return the ciudadOrigenRuta
     */
    public String getCiudadOrigenRuta() {
        return ciudadOrigenRuta;
    }

    /**
     * @param ciudadOrigenRuta the ciudadOrigenRuta to set
     */
    public void setCiudadOrigenRuta(String ciudadOrigenRuta) {
        this.ciudadOrigenRuta = ciudadOrigenRuta;
    }

    /**
     * @return the ciudadDestinoRuta
     */
    public String getCiudadDestinoRuta() {
        return ciudadDestinoRuta;
    }

    /**
     * @param ciudadDestinoRuta the ciudadDestinoRuta to set
     */
    public void setCiudadDestinoRuta(String ciudadDestinoRuta) {
        this.ciudadDestinoRuta = ciudadDestinoRuta;
    }

    /**
     * @return the tarifaRuta
     */
    public Double getTarifaRuta() {
        return tarifaRuta;
    }

    /**
     * @param tarifaRuta the tarifaRuta to set
     */
    public void setTarifaRuta(Double tarifaRuta) {
        this.tarifaRuta = tarifaRuta;
    }

    /**
     * @return the nombreImagenPublicoRuta
     */
    public String getNombreImagenPublicoRuta() {
        return nombreImagenPublicoRuta;
    }

    /**
     * @param nombreImagenPublicoRuta the nombreImagenPublicoRuta to set
     */
    public void setNombreImagenPublicoRuta(String nombreImagenPublicoRuta) {
        this.nombreImagenPublicoRuta = nombreImagenPublicoRuta;
    }

    /**
     * @return the nombreImagenPrivadoRuta
     */
    public String getNombreImagenPrivadoRuta() {
        return nombreImagenPrivadoRuta;
    }

    /**
     * @param nombreImagenPrivadoRuta the nombreImagenPrivadoRuta to set
     */
    public void setNombreImagenPrivadoRuta(String nombreImagenPrivadoRuta) {
        this.nombreImagenPrivadoRuta = nombreImagenPrivadoRuta;
    }

    public Ruta(int idRuta, String ciudadOrigenRuta, String ciudadDestinoRuta, Double tarifaRuta, String nombreImagenPublicoRuta, String nombreImagenPrivadoRuta, int cantidadViajeRuta) {
        this.idRuta = idRuta;
        this.ciudadOrigenRuta = ciudadOrigenRuta;
        this.ciudadDestinoRuta = ciudadDestinoRuta;
        this.tarifaRuta = tarifaRuta;
        this.nombreImagenPublicoRuta = nombreImagenPublicoRuta;
        this.nombreImagenPrivadoRuta = nombreImagenPrivadoRuta;
        this.cantidadViajeRuta = cantidadViajeRuta;
    }

    public Ruta() {
    }

    @Override
    public String toString() {
        return "Ruta{" + "idRuta=" + idRuta + ", ciudadOrigenRuta=" + ciudadOrigenRuta + ", ciudadDestinoRuta=" + ciudadDestinoRuta + ", tarifaRuta=" + tarifaRuta + ", nombreImagenPublicoRuta=" + nombreImagenPublicoRuta + ", nombreImagenPrivadoRuta=" + nombreImagenPrivadoRuta + '}';
    }
    
    
}
