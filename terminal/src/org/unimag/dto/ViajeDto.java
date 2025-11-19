/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.unimag.dto;

/**
 *
 * @author Juan
 */
public class ViajeDto {
    
    private int idViaje;
    private RutaDto rutaViaje;
    private ConductorDto conductorViaje;
    private BusDto busViaje;
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

    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

    public Boolean getEstadoViaje() {
        return estadoViaje;
    }

    public void setEstadoViaje(Boolean estadoViaje) {
        this.estadoViaje = estadoViaje;
    }

    public void setRutaViaje(RutaDto rutaViaje) {
        this.rutaViaje = rutaViaje;
    }

    public void setConductorViaje(ConductorDto conductorViaje) {
        this.conductorViaje = conductorViaje;
    }

    public void setBusViaje(BusDto busViaje) {
        this.busViaje = busViaje;
    }

    public void setFechaViaje(String fechaViaje) {
        this.fechaViaje = fechaViaje;
    }

    public void setHoraViaje(int horaViaje) {
        this.horaViaje = horaViaje;
    }

    public void setNombreImagenPublicoViaje(String nombreImagenPublicoViaje) {
        this.nombreImagenPublicoViaje = nombreImagenPublicoViaje;
    }

    public void setNombreImagenPrivadoViaje(String nombreImagenPrivadoViaje) {
        this.nombreImagenPrivadoViaje = nombreImagenPrivadoViaje;
    }

    public int getIdViaje() {
        return idViaje;
    }

    public RutaDto getRutaViaje() {
        return rutaViaje;
    }

    public ConductorDto getConductorViaje() {
        return conductorViaje;
    }

    public BusDto getBusViaje() {
        return busViaje;
    }

    public String getFechaViaje() {
        return fechaViaje;
    }

    public int getHoraViaje() {
        return horaViaje;
    }

    public String getNombreImagenPublicoViaje() {
        return nombreImagenPublicoViaje;
    }

    public String getNombreImagenPrivadoViaje() {
        return nombreImagenPrivadoViaje;
    }

    public ViajeDto() {
    }

    public ViajeDto(int idViaje, RutaDto rutaViaje, ConductorDto conductorViaje, BusDto busViaje, String fechaViaje, int horaViaje, Boolean estadoViaje, String nombreImagenPublicoViaje, String nombreImagenPrivadoViaje, int cantidadTiqueteViaje) {
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

    @Override
    public String toString() {
        if (rutaViaje == null) {
            return "Seleccione el Viaje";
        }
        return "ID="+idViaje+"\n"+rutaViaje.getCiudadOrigenRuta() + "-" + rutaViaje.getCiudadDestinoRuta();
    }
  
}
