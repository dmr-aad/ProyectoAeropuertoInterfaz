/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeropuertos_interfaz.helpers;

import aeropuertos_interfaz.NewHibernateUtil;
//import aeropuertos_interfaz.objetos.*;
import Objetos.*;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;



/**
 *
 * @author Mirotic
 */
public class CargarDatos {
    
    public static ArrayList<String> listaAeropuertos() {
        Session sesion;
        ArrayList<String> codigos = new ArrayList<>();
        try {
            sesion = NewHibernateUtil.getSession();
            List<Aeropuerto> aeropuertos = sesion.createCriteria(Aeropuerto.class).list();
            sesion.close();
            if (!aeropuertos.isEmpty()) {
                for (Aeropuerto aeropuerto : aeropuertos) {
                    codigos.add(aeropuerto.getCodigoAeropuerto());
                }
            }
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return codigos;
    }
    
    public static ArrayList<String> listaAerolineas() {
        Session sesion;
        ArrayList<String> codigos = new ArrayList<>();
        try {
            sesion = NewHibernateUtil.getSession();
            List<Aerolinea> aerolineas = sesion.createCriteria(Aerolinea.class).list();
            sesion.close();
            if (!aerolineas.isEmpty()) {
                for (Aerolinea aerolinea : aerolineas) {
                    codigos.add(aerolinea.getCodigoAerolinea());
                }
            }
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return codigos;
    }
    
    public static ArrayList<String> listaAsientos() {
        Session sesion;
        ArrayList<String> codigos = new ArrayList<>();
        try {
            sesion = NewHibernateUtil.getSession();
            List<Asiento> asientos = sesion.createCriteria(Asiento.class).list();
            sesion.close();
            if (!asientos.isEmpty()) {
                for (Asiento asiento : asientos) {
                    String nuevoAsiento = Integer.toString(asiento.getFila()).concat(String.valueOf(asiento.getLetra()));
                    codigos.add(nuevoAsiento);
                }
            }
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return codigos;
    }
    
    public static ArrayList<String> listaAviones() {
        Session sesion;
        ArrayList<String> ids = new ArrayList<>();
        try {
            sesion = NewHibernateUtil.getSession();
            List<Avion> aviones = sesion.createCriteria(Avion.class).list();
            sesion.close();
            if (!aviones.isEmpty()) {
                for (Avion avion : aviones) {
                    ids.add(Integer.toString(avion.getIdAvion()));
                }
            }
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return ids;
    }
    
    public static ArrayList<String> listaVuelos() {
        Session sesion;
        ArrayList<String> codigos = new ArrayList<>();
        try {
            sesion = NewHibernateUtil.getSession();
            List<Vuelo> vuelos = sesion.createCriteria(Vuelo.class).list();
            sesion.close();
            if (!vuelos.isEmpty()) {
                for (Vuelo vuelo : vuelos) {
                    codigos.add(vuelo.getCodigoVuelo());
                }
            }
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return codigos;
    }
}
