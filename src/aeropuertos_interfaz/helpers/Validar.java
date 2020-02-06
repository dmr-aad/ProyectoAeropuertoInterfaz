/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeropuertos_interfaz.helpers;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author Mirotic
 */
public class Validar {

    private static Alert alert = new Alert(AlertType.WARNING);

    public static int validarCodigoIda(String codigo) {
        int c = 0;

        try {

            if (!codigo.substring(0, 1).matches("[Ii]")) {
                c = 1;

                throw new MisExcepciones("\nError: La primera letra tiene que ser una ' I '");
            } else {
                if (!codigo.substring(1, 2).matches("[-]")) {
                    c = 1;
                    throw new MisExcepciones("\nError: el segundo caracter debe ser -");
                } else {
                    if (codigo.length() != 5) {
                        c = 1;

                        throw new MisExcepciones("\nError: La longitud debe ser de 5 digitos");
                    } else {
                        if (!codigo.substring(2).matches("[0-9]*")) {
                            c = 1;

                            throw new MisExcepciones("\nError: Los ultimos 3 digitos deben ser numeros");
                        }

                    }
                }

            }

        } catch (MisExcepciones ex) {
            alert.setTitle("Advertencia");
            alert.setHeaderText("Error en el Codigo de Vuelo");
            alert.setContentText(ex.getMessage());

            alert.showAndWait();
        }

        return c;
    }

    public static int validarCodigoVuelta(String codigo) {
        int c = 0;

        try {

            if (!codigo.substring(0, 1).matches("[Vv]")) {
                c = 1;

                throw new MisExcepciones("\nError: La primera letra tiene que ser una ' V '");
            } else {
                if (codigo.length() != 5) {
                    c = 1;

                    throw new MisExcepciones("\nError: La longitud debe ser de 5 digitos");
                } else {
                    if (!codigo.substring(2).matches("[0-9]*")) {
                        c = 1;

                        throw new MisExcepciones("\nError: Los ultimos 3 digitos deben ser numeros");
                    }

                }
            }

        } catch (MisExcepciones ex) {
            alert.setTitle("Advertencia");
            alert.setHeaderText("Error en el Codigo de Vuelo");
            alert.setContentText(ex.getMessage());

            alert.showAndWait();
        }

        return c;
    }

    public static int validarCodigoAeropuerto(String codigo) {
        int c = 0;

        try {

            if (!codigo.substring(0).matches("[A-Za-z]*")) {
                c = 1;

                throw new MisExcepciones("\nError el codigo aeropuerto debe estar compuesto por letras");
            } else {
                if (codigo.length() != 3) {
                    c = 1;

                    throw new MisExcepciones("\nError el codigo debe tener una longitud de 3 letras");
                }
            }

        } catch (MisExcepciones ex) {
            alert.setTitle("Advertencia");
            alert.setHeaderText("Error en el Codigo de Aeropuerto");
            alert.setContentText(ex.getMessage());

            alert.showAndWait();
        }

        return c;
    }

    public static int validarCodigoAerolinea(String codigo) {
        int c = 0;

        try {

            if (!codigo.substring(0).matches("[A-Za-z]*")) {
                c = 1;

                throw new MisExcepciones("\nError el codigo aerolinea debe estar compuesto por letras");
            } else {
                if (codigo.length() != 3) {
                    c = 1;

                    throw new MisExcepciones("\nError el codigo debe tener una longitud de 3 letras");
                }
            }

        } catch (MisExcepciones ex) {
            alert.setTitle("Advertencia");
            alert.setHeaderText("Error en el Codigo de Aerolinea");
            alert.setContentText(ex.getMessage());

            alert.showAndWait();
        }

        return c;
    }
}
