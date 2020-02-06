package aeropuertos_interfaz.objetos;

import java.util.*;
import java.io.*;

public class Pasajero implements Serializable
{
    private String dni;
    private String nombre;
    private String apellidos;
    private String pais;
    private String email;
    private String telefono;
    private Set<Reserva> reservas;
    private Vuelo vuelo;

    public Pasajero()
    {}

    public Pasajero(String dni, String nombre, String apellidos, String pais, String email, String telefono,Vuelo vuelo)
    {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.pais = pais;
        this.email = email;
        this.telefono = telefono;
        this.reservas = new HashSet<>();
        this.vuelo = vuelo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Set<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(Set<Reserva> reservas) {
        this.reservas = reservas;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }
    
    
    
}
