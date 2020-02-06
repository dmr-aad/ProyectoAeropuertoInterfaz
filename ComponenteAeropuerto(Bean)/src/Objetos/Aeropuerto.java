package Objetos;

import java.util.*;
import java.io.Serializable;

public class Aeropuerto implements Serializable{

    private String codigoAeropuerto;
    private String pais;
    private String ciudad;
    private Set<Aerolinea> aerolineas;

    public Aeropuerto()
    {}

    public Aeropuerto(String codigoAeropuerto, String pais, String ciudad)
    {
        this.codigoAeropuerto = codigoAeropuerto;
        this.pais = pais;
        this.ciudad = ciudad;
        this.aerolineas = new HashSet<>();
    }

    public String getCodigoAeropuerto() {
        return codigoAeropuerto;
    }

    public void setCodigoAeropuerto(String codigoAeropuerto) {
        this.codigoAeropuerto = codigoAeropuerto;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Set<Aerolinea> getAerolineas() {
        return aerolineas;
    }

    public void setAerolineas(Set<Aerolinea> aerolineas) {
        this.aerolineas = aerolineas;
    }
    
    
}
