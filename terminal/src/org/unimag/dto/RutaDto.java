package org.unimag.dto;

public class RutaDto {
    
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

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }

    public void setCiudadOrigenRuta(String ciudadOrigenRuta) {
        this.ciudadOrigenRuta = ciudadOrigenRuta;
    }

    public void setCiudadDestinoRuta(String ciudadDestinoRuta) {
        this.ciudadDestinoRuta = ciudadDestinoRuta;
    }

    public void setTarifaRuta(Double tarifaRuta) {
        this.tarifaRuta = tarifaRuta;
    }

    public void setNombreImagenPublicoRuta(String nombreImagenPublicoRuta) {
        this.nombreImagenPublicoRuta = nombreImagenPublicoRuta;
    }

    public void setNombreImagenPrivadoRuta(String nombreImagenPrivadoRuta) {
        this.nombreImagenPrivadoRuta = nombreImagenPrivadoRuta;
    }

    public int getIdRuta() {
        return idRuta;
    }

    public String getCiudadOrigenRuta() {
        return ciudadOrigenRuta;
    }

    public String getCiudadDestinoRuta() {
        return ciudadDestinoRuta;
    }

    public Double getTarifaRuta() {
        return tarifaRuta;
    }

    public String getNombreImagenPublicoRuta() {
        return nombreImagenPublicoRuta;
    }

    public String getNombreImagenPrivadoRuta() {
        return nombreImagenPrivadoRuta;
    }

    public RutaDto() {
    }

    public RutaDto(int idRuta, String ciudadOrigenRuta, String ciudadDestinoRuta, Double tarifaRuta, String nombreImagenPublicoRuta, String nombreImagenPrivadoRuta, int cantidadViajeRuta) {
        this.idRuta = idRuta;
        this.ciudadOrigenRuta = ciudadOrigenRuta;
        this.ciudadDestinoRuta = ciudadDestinoRuta;
        this.tarifaRuta = tarifaRuta;
        this.nombreImagenPublicoRuta = nombreImagenPublicoRuta;
        this.nombreImagenPrivadoRuta = nombreImagenPrivadoRuta;
        this.cantidadViajeRuta = cantidadViajeRuta;
    }

    @Override
    public String toString() {
        if (idRuta == 0) {
            return "Seleccione La ruta";
        }
        return getCiudadOrigenRuta() + "-" + getCiudadDestinoRuta();
    }
    
}
