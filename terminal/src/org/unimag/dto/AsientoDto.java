package org.unimag.dto;

public class AsientoDto {
    
    private int idAsiento;
    private BusDto busAsiento;
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

    @Override
    public String toString() {
        if (idAsiento == 0) {
            return "Seleccione el Asiento";
        }
        return "Asiento N#" + idAsiento + " de Bus " + busAsiento.getIdBus();
    }


    public void setIdAsiento(int idAsiento) {
        this.idAsiento = idAsiento;
    }

    public void setBusAsiento(BusDto busAsiento) {
        this.busAsiento = busAsiento;
    }

    public void setEstadoAsiento(Boolean estadoAsiento) {
        this.estadoAsiento = estadoAsiento;
    }

    public void setNombreImagenPublicoAsiento(String nombreImagenPublicoAsiento) {
        this.nombreImagenPublicoAsiento = nombreImagenPublicoAsiento;
    }

    public void setNombreImagenPrivadoAsiento(String nombreImagenPrivadoAsiento) {
        this.nombreImagenPrivadoAsiento = nombreImagenPrivadoAsiento;
    }

    public int getIdAsiento() {
        return idAsiento;
    }

    public BusDto getBusAsiento() {
        return busAsiento;
    }

    public Boolean getEstadoAsiento() {
        return estadoAsiento;
    }

    public String getNombreImagenPublicoAsiento() {
        return nombreImagenPublicoAsiento;
    }

    public String getNombreImagenPrivadoAsiento() {
        return nombreImagenPrivadoAsiento;
    }

    public AsientoDto() {
    }

    public AsientoDto(int idAsiento, BusDto busAsiento, Boolean estadoAsiento, String nombreImagenPublicoAsiento, String nombreImagenPrivadoAsiento, int cantidadTiqueteAsiento) {
        this.idAsiento = idAsiento;
        this.busAsiento = busAsiento;
        this.estadoAsiento = estadoAsiento;
        this.nombreImagenPublicoAsiento = nombreImagenPublicoAsiento;
        this.nombreImagenPrivadoAsiento = nombreImagenPrivadoAsiento;
        this.cantidadTiqueteAsiento = cantidadTiqueteAsiento;
    }

    
}
