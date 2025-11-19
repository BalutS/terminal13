package org.unimag.dto;

public class EquipajeDto {
    
    private int idEquipaje;
    private Double pesoEquipaje;
    private PasajeroDto duenioEquipaje;
    private String nombreImagenPublicoEquipaje;
    private String nombreImagenPrivadoEquipaje;

    public void setIdEquipaje(int idEquipaje) {
        this.idEquipaje = idEquipaje;
    }

    public void setPesoEquipaje(Double pesoEquipaje) {
        this.pesoEquipaje = pesoEquipaje;
    }

    public void setDuenioEquipaje(PasajeroDto duenioEquipaje) {
        this.duenioEquipaje = duenioEquipaje;
    }

    public void setNombreImagenPublicoEquipaje(String nombreImagenPublicoEquipaje) {
        this.nombreImagenPublicoEquipaje = nombreImagenPublicoEquipaje;
    }

    public void setNombreImagenPrivadoEquipaje(String nombreImagenPrivadoEquipaje) {
        this.nombreImagenPrivadoEquipaje = nombreImagenPrivadoEquipaje;
    }

    public int getIdEquipaje() {
        return idEquipaje;
    }

    public Double getPesoEquipaje() {
        return pesoEquipaje;
    }

    public PasajeroDto getDuenioEquipaje() {
        return duenioEquipaje;
    }

    public String getNombreImagenPublicoEquipaje() {
        return nombreImagenPublicoEquipaje;
    }

    public String getNombreImagenPrivadoEquipaje() {
        return nombreImagenPrivadoEquipaje;
    }

    public EquipajeDto() {
    }

    public EquipajeDto(int idEquipaje, Double pesoEquipaje, PasajeroDto duenioEquipaje, String nombreImagenPublicoEquipaje, String nombreImagenPrivadoEquipaje) {
        this.idEquipaje = idEquipaje;
        this.pesoEquipaje = pesoEquipaje;
        this.duenioEquipaje = duenioEquipaje;
        this.nombreImagenPublicoEquipaje = nombreImagenPublicoEquipaje;
        this.nombreImagenPrivadoEquipaje = nombreImagenPrivadoEquipaje;
    }

    @Override
    public String toString() {
        return "Equipaje:" + "ID=" + idEquipaje;
    }

    
}
