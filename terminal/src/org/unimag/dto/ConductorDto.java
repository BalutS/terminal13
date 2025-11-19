package org.unimag.dto;

public class ConductorDto {
    
    private int idConductor;
    private String nombreConductor;
    private Short edadConductor;
    private Boolean generoConductor;
    private String nombreImagenPublicoConductor;
    private String nombreImagenPrivadoConductor;
    private int cantidadViajeConductor;

    public int getCantidadViajeConductor() {
        return cantidadViajeConductor;
    }

    public void setCantidadViajeConductor(int cantidadViajeConductor) {
        this.cantidadViajeConductor = cantidadViajeConductor;
    }

    public int getIdConductor() {
        return idConductor;
    }

    public String getNombreConductor() {
        return nombreConductor;
    }

    public Short getEdadConductor() {
        return edadConductor;
    }

    public Boolean getGeneroConductor() {
        return generoConductor;
    }

    public String getNombreImagenPublicoConductor() {
        return nombreImagenPublicoConductor;
    }

    public String getNombreImagenPrivadoConductor() {
        return nombreImagenPrivadoConductor;
    }

    public void setIdConductor(int idConductor) {
        this.idConductor = idConductor;
    }

    public void setNombreConductor(String nombreConductor) {
        this.nombreConductor = nombreConductor;
    }

    public void setEdadConductor(Short edadConductor) {
        this.edadConductor = edadConductor;
    }

    public void setGeneroConductor(Boolean generoConductor) {
        this.generoConductor = generoConductor;
    }

    public void setNombreImagenPublicoConductor(String nombreImagenPublicoConductor) {
        this.nombreImagenPublicoConductor = nombreImagenPublicoConductor;
    }

    public void setNombreImagenPrivadoConductor(String nombreImagenPrivadoConductor) {
        this.nombreImagenPrivadoConductor = nombreImagenPrivadoConductor;
    }

    public ConductorDto() {
    }

    public ConductorDto(int cedulaConductor, String nombreConductor, Short edadConductor, Boolean generoConductor, String nombreImagenPublicoConductor, String nombreImagenPrivadoConductor, int cantidadViajeConductor) {
        this.idConductor = cedulaConductor;
        this.nombreConductor = nombreConductor;
        this.edadConductor = edadConductor;
        this.generoConductor = generoConductor;
        this.nombreImagenPublicoConductor = nombreImagenPublicoConductor;
        this.nombreImagenPrivadoConductor = nombreImagenPrivadoConductor;
        this.cantidadViajeConductor = cantidadViajeConductor;
    }

    @Override
    public String toString() {
        if (idConductor == 0) {
            return "Seleccione el Conductor";
        }
        return "Nombre=" + nombreConductor + "\nCedula=" +idConductor;
    }
    
    
    
}
