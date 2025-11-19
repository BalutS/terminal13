
package org.unimag.modelo;

public class Asiento {
    private int idAsiento;
    private Bus busAsiento;
    private Boolean estadoAsiento;
    private String nombreImagenPublicoAsiento;
    private String nombreImagenPrivadoAsiento;
    private int cantidadTiqueteAsiento;

    public int getCantidadTiqueteAsiento() {
        return cantidadTiqueteAsiento;
    }

    public void setCantidadTiqueteAsiento(int cantidadTiqueteAsiento) {
        this.cantidadTiqueteAsiento = cantidadTiqueteAsiento;
    }

    /**
     * @return the idAsiento
     */
    public int getIdAsiento() {
        return idAsiento;
    }

    /**
     * @param idAsiento the idAsiento to set
     */
    public void setIdAsiento(int idAsiento) {
        this.idAsiento = idAsiento;
    }

    /**
     * @return the busAsiento
     */
    public Bus getBusAsiento() {
        return busAsiento;
    }

    /**
     * @param busAsiento the busAsiento to set
     */
    public void setBusAsiento(Bus busAsiento) {
        this.busAsiento = busAsiento;
    }

    /**
     * @return the estadoAsiento
     */
    public Boolean getEstadoAsiento() {
        return estadoAsiento;
    }

    /**
     * @param estadoAsiento the estadoAsiento to set
     */
    public void setEstadoAsiento(Boolean estadoAsiento) {
        this.estadoAsiento = estadoAsiento;
    }

    /**
     * @return the nombreImagenPublicoAsiento
     */
    public String getNombreImagenPublicoAsiento() {
        return nombreImagenPublicoAsiento;
    }

    /**
     * @param nombreImagenPublicoAsiento the nombreImagenPublicoAsiento to set
     */
    public void setNombreImagenPublicoAsiento(String nombreImagenPublicoAsiento) {
        this.nombreImagenPublicoAsiento = nombreImagenPublicoAsiento;
    }

    /**
     * @return the nombreImagenPrivadoAsiento
     */
    public String getNombreImagenPrivadoAsiento() {
        return nombreImagenPrivadoAsiento;
    }

    /**
     * @param nombreImagenPrivadoAsiento the nombreImagenPrivadoAsiento to set
     */
    public void setNombreImagenPrivadoAsiento(String nombreImagenPrivadoAsiento) {
        this.nombreImagenPrivadoAsiento = nombreImagenPrivadoAsiento;
    }

    public Asiento(int idAsiento, Bus busAsiento, Boolean estadoAsiento, String nombreImagenPublicoAsiento, String nombreImagenPrivadoAsiento, int cantidadTiqueteAsiento) {
        this.idAsiento = idAsiento;
        this.busAsiento = busAsiento;
        this.estadoAsiento = estadoAsiento;
        this.nombreImagenPublicoAsiento = nombreImagenPublicoAsiento;
        this.nombreImagenPrivadoAsiento = nombreImagenPrivadoAsiento;
        this.cantidadTiqueteAsiento = cantidadTiqueteAsiento;
    }

    public Asiento() {
    }

    @Override
    public String toString() {
        return "Asiento{" + "idAsiento=" + idAsiento + ", busAsiento=" + busAsiento + ", estadoAsiento=" + estadoAsiento + ", nombreImagenPublicoAsiento=" + nombreImagenPublicoAsiento + ", nombreImagenPrivadoAsiento=" + nombreImagenPrivadoAsiento + '}';
    }
    
}
