package Objetos;

import java.util.*;
import java.io.*;

public class Aerolinea implements Serializable{

    private String codigoAerolinea;
    private String nombre;
    private Set<Aeropuerto> aeropuertos;
    private Set<Avion> aviones;

    public Aerolinea()
    {}

    public Aerolinea(String codigoAerolinea, String nombre)
    {
        this.codigoAerolinea = codigoAerolinea;
        this.nombre = nombre;
        this.aeropuertos = new HashSet<>();
        this.aviones = new HashSet<>();
    }

    public String getCodigoAerolinea() {
        return codigoAerolinea;
    }

    public void setCodigoAerolinea(String codigoAerolinea) {
        this.codigoAerolinea = codigoAerolinea;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Aeropuerto> getAeropuertos() {
        return aeropuertos;
    }

    public void setAeropuertos(Set<Aeropuerto> aeropuertos) {
        this.aeropuertos = aeropuertos;
    }

    public Set<Avion> getAviones() {
        return aviones;
    }

    public void setAviones(Set<Avion> aviones) {
        this.aviones = aviones;
    }
    
    
}
