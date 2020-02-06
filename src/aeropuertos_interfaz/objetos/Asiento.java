package aeropuertos_interfaz.objetos;

import java.io.Serializable;

public class Asiento implements Serializable{
    
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
    
}