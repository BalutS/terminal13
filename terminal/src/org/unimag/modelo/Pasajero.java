
package org.unimag.modelo;

public class Pasajero {
    private int idPasajero;
    private String nombrePasajero;
    private Short edadPasajero;
    private Boolean generoPasajero;
    private String nombreImagenPublicoPasajero;
    private String nombreImagenPrivadoPasajero;
    private int cantidadEquipajePasajero;
    private int cantidadTiquetePasajero;

    public int getCantidadEquipajePasajero() {
        return cantidadEquipajePasajero;
    }

    public void setCantidadEquipajePasajero(int cantidadEquipajePasajero) {
        this.cantidadEquipajePasajero = cantidadEquipajePasajero;
    }

    public int getCantidadTiquetePasajero() {
        return cantidadTiquetePasajero;
    }

    public void setCantidadTiquetePasajero(int cantidadTiquetePasajero) {
        this.cantidadTiquetePasajero = cantidadTiquetePasajero;
    }

    /**
     * @return the idPasajero
     */
    public int getIdPasajero() {
        return idPasajero;
    }

    /**
     * @param idPasajero the idPasajero to set
     */
    public void setIdPasajero(int idPasajero) {
        this.idPasajero = idPasajero;
    }

    /**
     * @return the nombrePasajero
     */
    public String getNombrePasajero() {
        return nombrePasajero;
    }

    /**
     * @param nombrePasajero the nombrePasajero to set
     */
    public void setNombrePasajero(String nombrePasajero) {
        this.nombrePasajero = nombrePasajero;
    }

    /**
     * @return the edadPasajero
     */
    public Short getEdadPasajero() {
        return edadPasajero;
    }

    /**
     * @param edadPasajero the edadPasajero to set
     */
    public void setEdadPasajero(Short edadPasajero) {
        this.edadPasajero = edadPasajero;
    }

    /**
     * @return the nombreImagenPublicoPasajero
     */
    public String getNombreImagenPublicoPasajero() {
        return nombreImagenPublicoPasajero;
    }

    /**
     * @param nombreImagenPublicoPasajero the nombreImagenPublicoPasajero to set
     */
    public void setNombreImagenPublicoPasajero(String nombreImagenPublicoPasajero) {
        this.nombreImagenPublicoPasajero = nombreImagenPublicoPasajero;
    }

    /**
     * @return the nombreImagenPrivadoPasajero
     */
    public String getNombreImagenPrivadoPasajero() {
        return nombreImagenPrivadoPasajero;
    }

    /**
     * @param nombreImagenPrivadoPasajero the nombreImagenPrivadoPasajero to set
     */
    public void setNombreImagenPrivadoPasajero(String nombreImagenPrivadoPasajero) {
        this.nombreImagenPrivadoPasajero = nombreImagenPrivadoPasajero;
    }

    public Boolean getGeneroPasajero() {
        return generoPasajero;
    }

    public void setGeneroPasajero(Boolean generoPasajero) {
        this.generoPasajero = generoPasajero;
    }

    public Pasajero(int cedulaPasajero, String nombrePasajero, Short edadPasajero, Boolean generoPasajero, String nombreImagenPublicoPasajero, String nombreImagenPrivadoPasajero, int cantidadEquipajePasajero, int cantidadTiquetePasajero) {
        this.idPasajero = cedulaPasajero;
        this.nombrePasajero = nombrePasajero;
        this.edadPasajero = edadPasajero;
        this.generoPasajero = generoPasajero;
        this.nombreImagenPublicoPasajero = nombreImagenPublicoPasajero;
        this.nombreImagenPrivadoPasajero = nombreImagenPrivadoPasajero;
        this.cantidadEquipajePasajero = cantidadEquipajePasajero;
        this.cantidadTiquetePasajero = cantidadTiquetePasajero;
    }

    

    public Pasajero() {
    }

    @Override
    public String toString() {
        return "Pasajero{" + "cedulaPasajero=" + idPasajero + ", nombrePasajero=" + nombrePasajero + ", edadPasajero=" + edadPasajero + ", generoPasajero=" + generoPasajero + ", nombreImagenPublicoPasajero=" + nombreImagenPublicoPasajero + ", nombreImagenPrivadoPasajero=" + nombreImagenPrivadoPasajero + '}';
    }

    
    
}
