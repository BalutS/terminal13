
package org.unimag.modelo;

public class Tiquete {
    private int idTiquete;
    private Pasajero pasajeroTiquete;
    private Viaje viajeTiquete;
    private Asiento asientoTiquete;
    private Boolean estadoTiquete;
    private String nombreImagenPublicoTiquete;
    private String nombreImagenPrivadoTiquete;

    /**
     * @return the idTiquete
     */
    public int getIdTiquete() {
        return idTiquete;
    }

    public void setEstadoTiquete(Boolean estadoTiquete) {
        this.estadoTiquete = estadoTiquete;
    }

    public Boolean getEstadoTiquete() {
        return estadoTiquete;
    }

    /**
     * @param idTiquete the idTiquete to set
     */
    public void setIdTiquete(int idTiquete) {
        this.idTiquete = idTiquete;
    }

    /**
     * @return the pasajeroTiquete
     */
    public Pasajero getPasajeroTiquete() {
        return pasajeroTiquete;
    }

    /**
     * @param pasajeroTiquete the pasajeroTiquete to set
     */
    public void setPasajeroTiquete(Pasajero pasajeroTiquete) {
        this.pasajeroTiquete = pasajeroTiquete;
    }

    /**
     * @return the viajeTiquete
     */
    public Viaje getViajeTiquete() {
        return viajeTiquete;
    }

    /**
     * @param viajeTiquete the viajeTiquete to set
     */
    public void setViajeTiquete(Viaje viajeTiquete) {
        this.viajeTiquete = viajeTiquete;
    }

    /**
     * @return the asientoTiquete
     */
    public Asiento getAsientoTiquete() {
        return asientoTiquete;
    }

    public void setAsientoTiquete(Asiento asientoTiquete) {
        this.asientoTiquete = asientoTiquete;
    }

    /**
     * @return the nombreImagenPublicoTiquete
     */
    public String getNombreImagenPublicoTiquete() {
        return nombreImagenPublicoTiquete;
    }

    /**
     * @param nombreImagenPublicoTiquete the nombreImagenPublicoTiquete to set
     */
    public void setNombreImagenPublicoTiquete(String nombreImagenPublicoTiquete) {
        this.nombreImagenPublicoTiquete = nombreImagenPublicoTiquete;
    }

    /**
     * @return the nombreImagenPrivadoTiquete
     */
    public String getNombreImagenPrivadoTiquete() {
        return nombreImagenPrivadoTiquete;
    }

    /**
     * @param nombreImagenPrivadoTiquete the nombreImagenPrivadoTiquete to set
     */
    public void setNombreImagenPrivadoTiquete(String nombreImagenPrivadoTiquete) {
        this.nombreImagenPrivadoTiquete = nombreImagenPrivadoTiquete;
    }

    public Tiquete(int idTiquete, Pasajero pasajeroTiquete, Viaje viajeTiquete, Asiento asientoTiquete, Boolean estadoTiquete, String nombreImagenPublicoTiquete, String nombreImagenPrivadoTiquete) {
        this.idTiquete = idTiquete;
        this.pasajeroTiquete = pasajeroTiquete;
        this.viajeTiquete = viajeTiquete;
        this.asientoTiquete = asientoTiquete;
        this.estadoTiquete = estadoTiquete;
        this.nombreImagenPublicoTiquete = nombreImagenPublicoTiquete;
        this.nombreImagenPrivadoTiquete = nombreImagenPrivadoTiquete;
    }

    

    public Tiquete() {
    }

    @Override
    public String toString() {
        return "Tiquete{" + "idTiquete=" + idTiquete + ", pasajeroTiquete=" + pasajeroTiquete + ", viajeTiquete=" + viajeTiquete + ", asientoTiquete=" + asientoTiquete + ", estadoTiquete=" + estadoTiquete + ", nombreImagenPublicoTiquete=" + nombreImagenPublicoTiquete + ", nombreImagenPrivadoTiquete=" + nombreImagenPrivadoTiquete + '}';
    }

    
    
    
}
