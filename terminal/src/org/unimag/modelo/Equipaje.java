
package org.unimag.modelo;

public class Equipaje {
    private int idEquipaje;
    private Double pesoEquipaje;
    private Pasajero duenioEquipaje;
    private String nombreImagenPublicoEquipaje;
    private String nombreImagenPrivadoEquipaje;

    /**
     * @return the idEquipaje
     */
    public int getIdEquipaje() {
        return idEquipaje;
    }

    /**
     * @param idEquipaje the idEquipaje to set
     */
    public void setIdEquipaje(int idEquipaje) {
        this.idEquipaje = idEquipaje;
    }

    /**
     * @return the pesoEquipaje
     */
    public Double getPesoEquipaje() {
        return pesoEquipaje;
    }

    /**
     * @param pesoEquipaje the pesoEquipaje to set
     */
    public void setPesoEquipaje(Double pesoEquipaje) {
        this.pesoEquipaje = pesoEquipaje;
    }

    /**
     * @return the duenioEquipaje
     */
    public Pasajero getDuenioEquipaje() {
        return duenioEquipaje;
    }

    /**
     * @param duenioEquipaje the duenioEquipaje to set
     */
    public void setDuenioEquipaje(Pasajero duenioEquipaje) {
        this.duenioEquipaje = duenioEquipaje;
    }

    /**
     * @return the nombreImagenPublicoEquipaje
     */
    public String getNombreImagenPublicoEquipaje() {
        return nombreImagenPublicoEquipaje;
    }

    /**
     * @param nombreImagenPublicoEquipaje the nombreImagenPublicoEquipaje to set
     */
    public void setNombreImagenPublicoEquipaje(String nombreImagenPublicoEquipaje) {
        this.nombreImagenPublicoEquipaje = nombreImagenPublicoEquipaje;
    }

    /**
     * @return the nombreImagenPrivadoEquipaje
     */
    public String getNombreImagenPrivadoEquipaje() {
        return nombreImagenPrivadoEquipaje;
    }

    /**
     * @param nombreImagenPrivadoEquipaje the nombreImagenPrivadoEquipaje to set
     */
    public void setNombreImagenPrivadoEquipaje(String nombreImagenPrivadoEquipaje) {
        this.nombreImagenPrivadoEquipaje = nombreImagenPrivadoEquipaje;
    }

    public Equipaje(int idEquipaje, Double pesoEquipaje, Pasajero duenioEquipaje, String nombreImagenPublicoEquipaje, String nombreImagenPrivadoEquipaje) {
        this.idEquipaje = idEquipaje;
        this.pesoEquipaje = pesoEquipaje;
        this.duenioEquipaje = duenioEquipaje;
        this.nombreImagenPublicoEquipaje = nombreImagenPublicoEquipaje;
        this.nombreImagenPrivadoEquipaje = nombreImagenPrivadoEquipaje;
    }

    public Equipaje() {
    }

    @Override
    public String toString() {
        return "Equipaje{" + "idEquipaje=" + idEquipaje + ", pesoEquipaje=" + pesoEquipaje + ", duenioEquipaje=" + duenioEquipaje + ", nombreImagenPublicoEquipaje=" + nombreImagenPublicoEquipaje + ", nombreImagenPrivadoEquipaje=" + nombreImagenPrivadoEquipaje + '}';
    }
    
}
