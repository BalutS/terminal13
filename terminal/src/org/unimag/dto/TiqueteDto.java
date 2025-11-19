package org.unimag.dto;

public class TiqueteDto {
    
    private int idTiquete;
    private PasajeroDto pasajeroTiquete;
    private ViajeDto viajeTiquete;
    private AsientoDto asientoTiquete;
    private Boolean estadoTiquete;
    private String nombreImagenPublicoTiquete;
    private String nombreImagenPrivadoTiquete;

    public void setIdTiquete(int idTiquete) {
        this.idTiquete = idTiquete;
    }

    public Boolean getEstadoTiquete() {
        return estadoTiquete;
    }

    public void setEstadoTiquete(Boolean estadoTiquete) {
        this.estadoTiquete = estadoTiquete;
    }

    public void setPasajeroTiquete(PasajeroDto pasajeroTiquete) {
        this.pasajeroTiquete = pasajeroTiquete;
    }

    public void setViajeTiquete(ViajeDto viajeTiquete) {
        this.viajeTiquete = viajeTiquete;
    }

    public void setAsientoTiquete(AsientoDto asientoTiquete) {
        this.asientoTiquete = asientoTiquete;
    }

    public void setNombreImagenPublicoTiquete(String nombreImagenPublicoTiquete) {
        this.nombreImagenPublicoTiquete = nombreImagenPublicoTiquete;
    }

    public void setNombreImagenPrivadoTiquete(String nombreImagenPrivadoTiquete) {
        this.nombreImagenPrivadoTiquete = nombreImagenPrivadoTiquete;
    }

    public int getIdTiquete() {
        return idTiquete;
    }

    public PasajeroDto getPasajeroTiquete() {
        return pasajeroTiquete;
    }

    public ViajeDto getViajeTiquete() {
        return viajeTiquete;
    }

    public AsientoDto getAsientoTiquete() {
        return asientoTiquete;
    }

    public String getNombreImagenPublicoTiquete() {
        return nombreImagenPublicoTiquete;
    }

    public String getNombreImagenPrivadoTiquete() {
        return nombreImagenPrivadoTiquete;
    }

    public TiqueteDto() {
    }

    public TiqueteDto(int idTiquete, PasajeroDto pasajeroTiquete, ViajeDto viajeTiquete, AsientoDto asientoTiquete, Boolean estadoTiquete, String nombreImagenPublicoTiquete, String nombreImagenPrivadoTiquete) {
        this.idTiquete = idTiquete;
        this.pasajeroTiquete = pasajeroTiquete;
        this.viajeTiquete = viajeTiquete;
        this.asientoTiquete = asientoTiquete;
        this.estadoTiquete = estadoTiquete;
        this.nombreImagenPublicoTiquete = nombreImagenPublicoTiquete;
        this.nombreImagenPrivadoTiquete = nombreImagenPrivadoTiquete;
    }

    @Override
    public String toString() {
        return ""  + idTiquete;
    }
    
    
    
}
