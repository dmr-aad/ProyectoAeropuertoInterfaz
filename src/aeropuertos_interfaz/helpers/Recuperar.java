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
public class Recuperar {
    
    public static Aerolinea aerolinea(String cod) {
        Session sesion;
        Aerolinea a = null;
        try {
            sesion = NewHibernateUtil.getSession();
            a = (Aerolinea) sesion.get(Aerolinea.class, cod);
            sesion.close();
        } catch(HibernateException e) {
            System.out.println(e.getMessage());
        }
        return a;
    }
    
    public static Aeropuerto aeropuerto(String cod) {
        Session sesion;
        Aeropuerto a = null;
        try {
            sesion = NewHibernateUtil.getSession();
            a = (Aeropuerto) sesion.get(Aeropuerto.class, cod);
            sesion.close();
        } catch(HibernateException e) {
            System.out.println(e.getMessage());
        }
        return a;
    }
    
    public static Asiento asiento(int fila, char letra) {
        Session sesion;
        Asiento a = null;
        try {
            sesion = NewHibernateUtil.getSession();
            List<Asiento> asientos = sesion.createCriteria(Asiento.class).list();
            sesion.close();
            if (!asientos.isEmpty()) {
                for (Asiento asiento : asientos) {
                    if (fila == asiento.getFila()
                            && letra == asiento.getLetra()) {
                        a = asiento;
                    }
                }
            }
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return a;
    }
    
    public static Avion avion(int id) {
        Session sesion;
        Avion a = null;
        try {
            sesion = NewHibernateUtil.getSession();
            a = (Avion) sesion.get(Avion.class, id);
            sesion.close();
        }catch(HibernateException e) {
            System.out.println(e.getMessage());
        }
        return a;
    }
    
    public static Vuelo vuelo(String cod) {
        Session sesion;
        Vuelo v = null;
        try {
            sesion = NewHibernateUtil.getSession();
            v = (Vuelo) sesion.get(Vuelo.class, cod);
            sesion.close();
        } catch(HibernateException e) {
            System.out.println(e.getMessage());
        }
        return v;
    }
    
    public static VueloIda vueloIda(String cod) {
        Session sesion;
        VueloIda v = null;
        try {
            sesion = NewHibernateUtil.getSession();
            v = (VueloIda) sesion.get(VueloIda.class, cod);
            sesion.close();
        } catch(HibernateException e) {
            System.out.println(e.getMessage());
        }
        return v;
    }
    
    public static VueloIdaVuelta vueloIdaVuelta(String cod) {
        Session sesion;
        VueloIdaVuelta v = null;
        try {
            sesion = NewHibernateUtil.getSession();
            v = (VueloIdaVuelta) sesion.get(VueloIdaVuelta.class, cod);
            sesion.close();
        } catch(HibernateException e) {
            System.out.println(e.getMessage());
        }
        return v;
    }
}
