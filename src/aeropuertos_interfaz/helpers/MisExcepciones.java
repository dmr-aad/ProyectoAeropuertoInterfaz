/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeropuertos_interfaz.helpers;

/**
 *
 * @author Mirotic
 */
public class MisExcepciones extends Exception{
    private String error;

    public MisExcepciones(String message) {
        super(message);
        this.error = message;
    }

    public String getError() {
        return error;
    }

    public MisExcepciones() {
    }
}
