package aeropuertos_interfaz.objetos;

import java.io.Serializable;
import java.util.*;
import java.sql.Time;
import java.time.LocalTime;

public class Vuelo implements Serializable{
    
    private String codigoVuelo;
    private float precio;
    private String origen;
    private String destino;
    private Date fechaSalida;
    private Time horaSalida;
    private Time horaLlegada;
    private Time embarque;
    private boolean oferta;
    private Avion avion;
    private Set<Pasajero> pasajeros;
    private Set<Asiento> asientos;

    /*
        El administrador va a cargar los datos de los aeropuertos y todo lo que tenga
        que ver con la gestion de dicho aeropuerto.

        El precio del vuelo va a variar segun la eleccion del usuario a la hora de 
        hacer la reserva.
     */

    public Vuelo()
    {}

    public Vuelo(String codigoVuelo, String origen, String destino,Date fechaSalida, Time horaSalida, Time horaLlegada, Time embarque, boolean oferta, float precio, Avion avion)
    {
        this.codigoVuelo = codigoVuelo;
        this.origen = origen;
        this.destino = destino;
        this.fechaSalida = fechaSalida;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.embarque = embarque;
        this.oferta = oferta;
        this.precio = precio;
        this.avion = avion;
        this.pasajeros = new HashSet<>();
        this.asientos = new HashSet<>();
    }

    public String getCodigoVuelo() {
        return codigoVuelo;
    }

    public void setCodigoVuelo(String codigoVuelo) {
        this.codigoVuelo = codigoVuelo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public boolean isOferta() {
        return oferta;
    }

    public void setOferta(boolean oferta) {
        this.oferta = oferta;
    } 

    public Time getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Time horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Time getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(Time horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public Time getEmbarque() {
        return embarque;
    }

    public void setEmbarque(Time embarque) {
        this.embarque = embarque;
    }    
        
    public Set<Asiento> getAsientos() {
        return asientos;
    }

    public void setAsientos(Set<Asiento> asientos) {
        this.asientos = asientos;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public Set<Pasajero> getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(Set<Pasajero> pasajeros) {
        this.pasajeros = pasajeros;
    }   
    
}
