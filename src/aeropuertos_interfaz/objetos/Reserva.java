package aeropuertos_interfaz.objetos;

import java.io.*;

public class Reserva implements Serializable
{
    private String clase;
    private Pasajero pasajero;
    private Vuelo vuelo;
    private String asiento;
    

    public Reserva()
    {}

    public Reserva(String clase, Pasajero pasajero, String asiento, Vuelo vuelo)
    {
        this.clase = clase;
        this.pasajero = pasajero;
        this.vuelo = vuelo;
        this.asiento = asiento;
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
}
