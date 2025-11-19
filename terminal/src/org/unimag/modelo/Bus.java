
package org.unimag.modelo;

public class Bus {
    private int idBus;
    private String modeloBus;
    private Empresa empresaBus;
    private String nombreImagenPublicoBus;
    private String nombreImagenPrivadoBus;
    private int cantidadViajeBus;
    private int cantidadAsientoBus;

    public int getCantidadViajeBus() {
        return cantidadViajeBus;
    }

    public void setCantidadViajeBus(int cantidadViajeBus) {
        this.cantidadViajeBus = cantidadViajeBus;
    }


    /**
     * @return the idBus
     */
    public int getIdBus() {
        return idBus;
    }

    /**
     * @param idBus the idBus to set
     */
    public void setIdBus(int idBus) {
        this.idBus = idBus;
    }

    /**
     * @return the modeloBus
     */
    public String getModeloBus() {
        return modeloBus;
    }

    /**
     * @param modeloBus the modeloBus to set
     */
    public void setModeloBus(String modeloBus) {
        this.modeloBus = modeloBus;
    }

    /**
     * @return the empresaBus
     */
    public Empresa getEmpresaBus() {
        return empresaBus;
    }

    /**
     * @param empresaBus the empresaBus to set
     */
    public void setEmpresaBus(Empresa empresaBus) {
        this.empresaBus = empresaBus;
    }

    /**
     * @return the nombreImagenPublicoBus
     */
    public String getNombreImagenPublicoBus() {
        return nombreImagenPublicoBus;
    }

    /**
     * @param nombreImagenPublicoBus the nombreImagenPublicoBus to set
     */
    public void setNombreImagenPublicoBus(String nombreImagenPublicoBus) {
        this.nombreImagenPublicoBus = nombreImagenPublicoBus;
    }

    /**
     * @return the nombreImagenPrivadoBus
     */
    public String getNombreImagenPrivadoBus() {
        return nombreImagenPrivadoBus;
    }

    /**
     * @param nombreImagenPrivadoBus the nombreImagenPrivadoBus to set
     */
    public void setNombreImagenPrivadoBus(String nombreImagenPrivadoBus) {
        this.nombreImagenPrivadoBus = nombreImagenPrivadoBus;
    }

    public int getCantidadAsientoBus() {
        return cantidadAsientoBus;
    }

    public void setCantidadAsientoBus(int cantidadAsientoBus) {
        this.cantidadAsientoBus = cantidadAsientoBus;
    }

    public Bus(int idBus, String modeloBus, Empresa empresaBus, String nombreImagenPublicoBus, String nombreImagenPrivadoBus, int cantidadViajeBus, int cantidadAsientoBus) {
        this.idBus = idBus;
        this.modeloBus = modeloBus;
        this.empresaBus = empresaBus;
        this.nombreImagenPublicoBus = nombreImagenPublicoBus;
        this.nombreImagenPrivadoBus = nombreImagenPrivadoBus;
        this.cantidadViajeBus = cantidadViajeBus;
        this.cantidadAsientoBus = cantidadAsientoBus;
    }

    

    public Bus() {
    }

    @Override
    public String toString() {
        return "Bus{" + "idBus=" + idBus + ", modeloBus=" + modeloBus + ", empresaBus=" + empresaBus + ", nombreImagenPublicoBus=" + nombreImagenPublicoBus + ", nombreImagenPrivadoBus=" + nombreImagenPrivadoBus + '}';
    }
    
}
