package Objetos;

import java.io.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Reserva implements Serializable
{
    private String clase;
    private Pasajero pasajero;
    private Vuelo vuelo;
    private String asiento;
    private PropertyChangeSupport propertySupport;
    

    public Reserva()
    {}

    public Reserva(String clase, Pasajero pasajero, String asiento, Vuelo vuelo)
    {
        this.clase = clase;
        this.pasajero = pasajero;
        this.vuelo = vuelo;
        this.asiento = asiento;
        this.propertySupport=new PropertyChangeSupport(this);
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        propertySupport.firePropertyChange("Ocupado","false",this.pasajero);
        this.asiento = asiento;
    }
    
    public float precioPagado(float impuesto, float tarifa, float precio)
    {
        float pagado = 0f;
        float extra = 0f;
        
        if (clase.equalsIgnoreCase("Primera"))
        {
            extra = 50;
        }
        
        pagado = precio + (precio*impuesto*tarifa) + extra;

        return pagado;
    }
    
    public void addListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }
    
    public void removeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }
}
