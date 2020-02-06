package Objetos;

import java.io.Serializable;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Asiento implements Serializable,PropertyChangeListener{
    
    private char letra;
    private int fila;
    private boolean ocupado;
    private Vuelo vuelo;
    
    public Asiento()
    {}

    public Asiento(char letra, int fila, boolean ocupado, Vuelo vuelo)
    {
        this.letra = letra;
        this.fila = fila;
        this.ocupado = ocupado;
        this.vuelo = vuelo;
    }

    public char getLetra() {
        return letra;
    }

    public void setLetra(char letra) {
        this.letra = letra;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Asiento reservado satisfactoriamente para el pasajero" + ((Pasajero)evt.getNewValue()).getNombre());
        setOcupado(true);
    }
    
}