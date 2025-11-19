package org.unimag.modelo;

public class Viaje {

    private int idViaje;
    private Ruta rutaViaje;
    private Conductor conductorViaje;
    private Bus busViaje;
    private String fechaViaje;
    private int horaViaje;
    private Boolean estadoViaje;
    private String nombreImagenPublicoViaje;
    private String nombreImagenPrivadoViaje;
    private int cantidadTiqueteViaje;

    public int getCantidadTiqueteViaje() {
        return cantidadTiqueteViaje;
    }

    public void setCantidadTiqueteViaje(int cantidadTiqueteViaje) {
        this.cantidadTiqueteViaje = cantidadTiqueteViaje;
    }

    /**
     * @return the idViaje
     */
    public int getIdViaje() {
        return idViaje;
    }

    public Boolean getEstadoViaje() {
        return estadoViaje;
    }

    public void setEstadoViaje(Boolean estadoViaje) {
        this.estadoViaje = estadoViaje;
    }

    /**
     * @param idViaje the idViaje to set
     */
    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

    /**
     * @return the rutaViaje
     */
    public Ruta getRutaViaje() {
        return rutaViaje;
    }

    /**
     * @param rutaViaje the rutaViaje to set
     */
    public void setRutaViaje(Ruta rutaViaje) {
        this.rutaViaje = rutaViaje;
    }

    /**
     * @return the conductorViaje
     */
    public Conductor getConductorViaje() {
        return conductorViaje;
    }

    /**
     * @param conductorViaje the conductorViaje to set
     */
    public void setConductorViaje(Conductor conductorViaje) {
        this.conductorViaje = conductorViaje;
    }

    /**
     * @return the busViaje
     */
    public Bus getBusViaje() {
        return busViaje;
    }

    /**
     * @param busViaje the busViaje to set
     */
    public void setBusViaje(Bus busViaje) {
        this.busViaje = busViaje;
    }

    /**
     * @return the fechaViaje
     */
    public String getFechaViaje() {
        return fechaViaje;
    }

    /**
     * @param fechaViaje the fechaViaje to set
     */
    public void setFechaViaje(String fechaViaje) {
        this.fechaViaje = fechaViaje;
    }

    /**
     * @return the horaViaje
     */
    public int getHoraViaje() {
        return horaViaje;
    }

    /**
     * @param horaViaje the horaViaje to set
     */
    public void setHoraViaje(int horaViaje) {
        this.horaViaje = horaViaje;
    }

    /**
     * @return the nombreImagenPublicoViaje
     */
    public String getNombreImagenPublicoViaje() {
        return nombreImagenPublicoViaje;
    }

    /**
     * @param nombreImagenPublicoViaje the nombreImagenPublicoViaje to set
     */
    public void setNombreImagenPublicoViaje(String nombreImagenPublicoViaje) {
        this.nombreImagenPublicoViaje = nombreImagenPublicoViaje;
    }

    /**
     * @return the nombreImagenPrivadoViaje
     */
    public String getNombreImagenPrivadoViaje() {
        return nombreImagenPrivadoViaje;
    }

    /**
     * @param nombreImagenPrivadoViaje the nombreImagenPrivadoViaje to set
     */
    public void setNombreImagenPrivadoViaje(String nombreImagenPrivadoViaje) {
        this.nombreImagenPrivadoViaje = nombreImagenPrivadoViaje;
    }

    public Viaje(int idViaje, Ruta rutaViaje, Conductor conductorViaje, Bus busViaje, String fechaViaje, int horaViaje, Boolean estadoViaje, String nombreImagenPublicoViaje, String nombreImagenPrivadoViaje, int cantidadTiqueteViaje) {
        this.idViaje = idViaje;
        this.rutaViaje = rutaViaje;
        this.conductorViaje = conductorViaje;
        this.busViaje = busViaje;
        this.fechaViaje = fechaViaje;
        this.horaViaje = horaViaje;
        this.estadoViaje = estadoViaje;
        this.nombreImagenPublicoViaje = nombreImagenPublicoViaje;
        this.nombreImagenPrivadoViaje = nombreImagenPrivadoViaje;
        this.cantidadTiqueteViaje = cantidadTiqueteViaje;
    }

    public Viaje() {
    }

    @Override
    public String toString() {
        return "Viaje{" + "idViaje=" + idViaje + ", rutaViaje=" + rutaViaje + ", conductorViaje=" + conductorViaje + ", busViaje=" + busViaje + ", fechaViaje=" + fechaViaje + ", horaViaje=" + horaViaje + ", estadoViaje=" + estadoViaje + ", nombreImagenPublicoViaje=" + nombreImagenPublicoViaje + ", nombreImagenPrivadoViaje=" + nombreImagenPrivadoViaje + '}';
    }

    

}
