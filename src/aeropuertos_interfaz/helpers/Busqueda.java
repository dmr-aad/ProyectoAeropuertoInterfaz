/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeropuertos_interfaz.helpers;

import Objetos.Aerolinea;
import Objetos.Aeropuerto;
import Objetos.Asiento;
import Objetos.Avion;
import Objetos.Vuelo;
import aeropuertos_interfaz.NewHibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author a18danielmr
 */
public class Busqueda {
    
    public static ArrayList<String> Aerolineas(String busqueda) {
        Session sesion;
        ArrayList<String> codigos = new ArrayList<>();
        try {
            sesion = NewHibernateUtil.getSession();
            List<Aerolinea> aerolineas = sesion.createCriteria(Aerolinea.class).list();
            sesion.close();
            if (!aerolineas.isEmpty()) {
                for (Aerolinea aerolinea : aerolineas) {
                    if (aerolinea.getCodigoAerolinea().contains(busqueda.toUpperCase())) {
                        codigos.add(aerolinea.getCodigoAerolinea());
                    }
                }
            }
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return codigos;
    }
    
    public static ArrayList<String> Aeropuertos(String busqueda) {
        Session sesion;
        ArrayList<String> codigos = new ArrayList<>();
        try {
            sesion = NewHibernateUtil.getSession();
            List<Aeropuerto> aeropuertos = sesion.createCriteria(Aeropuerto.class).list();
            sesion.close();
            if (!aeropuertos.isEmpty()) {
                for (Aeropuerto aeropuerto : aeropuertos) {
                    if (aeropuerto.getCodigoAeropuerto().contains(busqueda.toUpperCase())) {
                        codigos.add(aeropuerto.getCodigoAeropuerto());
                    }
                }
            }
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return codigos;
    }
    
    public static ArrayList<String> Asientos(String busqueda) {
        Session sesion;
        ArrayList<String> codigos = new ArrayList<>();
        try {
            sesion = NewHibernateUtil.getSession();
            List<Asiento> asientos = sesion.createCriteria(Asiento.class).list();
            sesion.close();
            if (!asientos.isEmpty()) {
                for (Asiento asiento : asientos) {
                    String nuevoAsiento = Integer.toString(asiento.getFila()).concat(String.valueOf(asiento.getLetra()));
                    if (nuevoAsiento.contains(busqueda.toUpperCase())) {
                        codigos.add(nuevoAsiento);
                    }
                }
            }
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return codigos;
    }
    
    public static ArrayList<String> Aviones(String busqueda) {
        Session sesion;
        ArrayList<String> ids = new ArrayList<>();
        try {
            sesion = NewHibernateUtil.getSession();
            List<Avion> aviones = sesion.createCriteria(Avion.class).list();
            sesion.close();
            if (!aviones.isEmpty()) {
                for (Avion avion : aviones) {
                    if (String.valueOf(avion.getIdAvion()).contains(busqueda)) {
                        ids.add(Integer.toString(avion.getIdAvion()));
                    }
                }
            }
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return ids;
    }
    
    public static ArrayList<String> Vuelos(String busqueda) {
        Session sesion;
        ArrayList<String> codigos = new ArrayList<>();
        try {
            sesion = NewHibernateUtil.getSession();
            List<Vuelo> vuelos = sesion.createCriteria(Vuelo.class).list();
            sesion.close();
            if (!vuelos.isEmpty()) {
                for (Vuelo vuelo : vuelos) {
                    if (vuelo.getCodigoVuelo().contains(busqueda.toUpperCase())) {
                        codigos.add(vuelo.getCodigoVuelo());
                    }
                }
            }
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return codigos;
    }
}
