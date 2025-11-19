package org.unimag.dto;

public class PasajeroDto {
    
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

    public void setIdPasajero(int idPasajero) {
        this.idPasajero = idPasajero;
    }

    public void setNombrePasajero(String nombrePasajero) {
        this.nombrePasajero = nombrePasajero;
    }

    public void setEdadPasajero(Short edadPasajero) {
        this.edadPasajero = edadPasajero;
    }

    public void setGeneroPasajero(Boolean generoPasajero) {
        this.generoPasajero = generoPasajero;
    }

    public void setNombreImagenPublicoPasajero(String nombreImagenPublicoPasajero) {
        this.nombreImagenPublicoPasajero = nombreImagenPublicoPasajero;
    }

    public void setNombreImagenPrivadoPasajero(String nombreImagenPrivadoPasajero) {
        this.nombreImagenPrivadoPasajero = nombreImagenPrivadoPasajero;
    }

    public int getIdPasajero() {
        return idPasajero;
    }

    public String getNombrePasajero() {
        return nombrePasajero;
    }

    public Short getEdadPasajero() {
        return edadPasajero;
    }

    public Boolean getGeneroPasajero() {
        return generoPasajero;
    }

    public String getNombreImagenPublicoPasajero() {
        return nombreImagenPublicoPasajero;
    }

    public String getNombreImagenPrivadoPasajero() {
        return nombreImagenPrivadoPasajero;
    }

    public PasajeroDto() {
    }

    public PasajeroDto(int cedulaPasajero, String nombrePasajero, Short edadPasajero, Boolean generoPasajero, String nombreImagenPublicoPasajero, String nombreImagenPrivadoPasajero, int cantidadEquipajePasajero, int cantidadTiquetePasajero) {
        this.idPasajero = cedulaPasajero;
        this.nombrePasajero = nombrePasajero;
        this.edadPasajero = edadPasajero;
        this.generoPasajero = generoPasajero;
        this.nombreImagenPublicoPasajero = nombreImagenPublicoPasajero;
        this.nombreImagenPrivadoPasajero = nombreImagenPrivadoPasajero;
        this.cantidadEquipajePasajero = cantidadEquipajePasajero;
        this.cantidadTiquetePasajero = cantidadTiquetePasajero;
    }

    @Override 
    public String toString() {
        if (idPasajero == 0) {
            return "Seleccione el Pasajero";
        }
        return "Nombre=" + nombrePasajero + "\nCedula=" +idPasajero;
    }
}
