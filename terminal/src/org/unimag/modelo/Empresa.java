
package org.unimag.modelo;

public class Empresa {
    private int idEmpresa;
    private String nombreEmpresa;
    private String nombreImagenPublicoEmpresa;
    private String nombreImagenPrivadoEmpresa;
    private int cantidadBusEmpresa;

    public int getCantidadBusEmpresa() {
        return cantidadBusEmpresa;
    }

    public void setCantidadBusEmpresa(int cantidadBusEmpresa) {
        this.cantidadBusEmpresa = cantidadBusEmpresa;
    }

    /**
     * @return the idEmpresa
     */
    public int getIdEmpresa() {
        return idEmpresa;
    }

    /**
     * @param idEmpresa the idEmpresa to set
     */
    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    /**
     * @return the nombreEmpresa
     */
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    /**
     * @param nombreEmpresa the nombreEmpresa to set
     */
    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    /**
     * @return the nombreImagenPublicoEmpresa
     */
    public String getNombreImagenPublicoEmpresa() {
        return nombreImagenPublicoEmpresa;
    }

    /**
     * @param nombreImagenPublicoEmpresa the nombreImagenPublicoEmpresa to set
     */
    public void setNombreImagenPublicoEmpresa(String nombreImagenPublicoEmpresa) {
        this.nombreImagenPublicoEmpresa = nombreImagenPublicoEmpresa;
    }

    /**
     * @return the nombreImagenPrivadoEmpresa
     */
    public String getNombreImagenPrivadoEmpresa() {
        return nombreImagenPrivadoEmpresa;
    }

    /**
     * @param nombreImagenPrivadoEmpresa the nombreImagenPrivadoEmpresa to set
     */
    public void setNombreImagenPrivadoEmpresa(String nombreImagenPrivadoEmpresa) {
        this.nombreImagenPrivadoEmpresa = nombreImagenPrivadoEmpresa;
    }

    public Empresa(int idEmpresa, String nombreEmpresa, String nombreImagenPublicoEmpresa, String nombreImagenPrivadoEmpresa, int cantidadBusEmpresa) {
        this.idEmpresa = idEmpresa;
        this.nombreEmpresa = nombreEmpresa;
        this.nombreImagenPublicoEmpresa = nombreImagenPublicoEmpresa;
        this.nombreImagenPrivadoEmpresa = nombreImagenPrivadoEmpresa;
        this.cantidadBusEmpresa = cantidadBusEmpresa;
    }

    public Empresa() {
    }

    @Override
    public String toString() {
        return "Empresa{" + "idEmpresa=" + idEmpresa + ", nombreEmpresa=" + nombreEmpresa + ", nombreImagenPublicoEmpresa=" + nombreImagenPublicoEmpresa + ", nombreImagenPrivadoEmpresa=" + nombreImagenPrivadoEmpresa + '}';
    }
    
}
