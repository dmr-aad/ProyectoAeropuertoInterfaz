package Objetos;

import java.util.*;
import java.io.Serializable;

public class Avion implements Serializable{

    private int idAvion;
    private String modelo;
    private int cantidadPasajeros;
    private Aerolinea aerolinea;
    private Set<Vuelo> vuelos;

    public Avion()
    {}

    public Avion(String modelo, int cantidadPasajeros, Aerolinea aerolinea)
    {
        this.modelo = modelo;
        this.cantidadPasajeros = cantidadPasajeros;
        this.aerolinea = aerolinea;
        this.vuelos = new HashSet<>();
    }

    public int getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(int idAvion) {
        this.idAvion = idAvion;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCantidadPasajeros() {
        return cantidadPasajeros;
    }

    public void setCantidadPasajeros(int cantidadPasajeros) {
        this.cantidadPasajeros = cantidadPasajeros;
    }

    public Aerolinea getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(Aerolinea aerolinea) {
        this.aerolinea = aerolinea;
    }
    
    public Set<Vuelo> getVuelos() {
        return vuelos;
    }

    public void setVuelos(Set<Vuelo> vuelos) {
        this.vuelos = vuelos;
    }  
}
