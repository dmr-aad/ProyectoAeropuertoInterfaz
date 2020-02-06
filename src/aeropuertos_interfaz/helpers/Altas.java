/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeropuertos_interfaz.helpers;

import aeropuertos_interfaz.NewHibernateUtil;
//import aeropuertos_interfaz.objetos.*;
import Objetos.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Mirotic
 */
public class Altas {

    public static void altasAeropuertos(Aeropuerto a) {
        guardarModificar(a);
    }

    public static void altasAerolineas(Aerolinea a, Aeropuerto ap) {
        if (ap != null) {
            ap.getAerolineas().add(a);
            a.getAeropuertos().add(ap);
            guardarModificar(ap);
        } else {
            System.out.println("No existe ningún aeropuerto con ese codigo");
        }
    }

    public static void altasAviones(Avion a, Aerolinea aerolinea) {
        if (aerolinea != null) {
            aerolinea.getAviones().add(a);
            guardarModificar(aerolinea);
        } else {
            System.out.println("No existe esa aerolinea");
        }
    }

    public static void altasVuelosIda(VueloIda v, Avion a) {
        if (a != null) {
            v = (VueloIda) newAsiento(v);
            a.getVuelos().add(v);
            guardarModificar(a);
        } else {
            System.out.println("No existe ese avión");
        }
    }

    public static void altasVuelosVuelta(VueloIdaVuelta v, Avion a) {
        if (a != null) {
            v = (VueloIdaVuelta) newAsiento(v);
            a.getVuelos().add(v);
            guardarModificar(a);
        } else {
            System.out.println("No existe ese avión");
        }
    }
    
    public static Vuelo newAsiento(Vuelo vuelo) {
        char letra;
        int fila = 0;
        boolean ocupado = false;

        String letras = "ABCDEF";

        Avion avion = vuelo.getAvion();
        Asiento asiento = null;

        for (int i = 1; i < avion.getCantidadPasajeros() / 6 + 1; i++) {

            fila = i;

            for (int j = 0; j < 6; j++) {

                letra = letras.charAt(j);

                asiento = new Asiento(letra, fila, ocupado, vuelo);
                vuelo.getAsientos().add(asiento);
            }

        }
        
        return vuelo;
    }

    public static void guardarModificar(Object objeto) {
        Session sesion;
        try {
            sesion = NewHibernateUtil.getSession();
            sesion.beginTransaction();
            sesion.saveOrUpdate(objeto);
            sesion.getTransaction().commit();
            sesion.close();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }
}
