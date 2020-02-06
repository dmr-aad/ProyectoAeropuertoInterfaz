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
public class Bajas {
    
    public static void aerolinea(String cod) {
        Aerolinea a;
        a = Recuperar.aerolinea(cod);
        if (a != null) {
            eliminar(a);
        }
    }
    
    public static void aeropuerto(String cod) {
        Aeropuerto a;
        a = Recuperar.aeropuerto(cod);
        if (a != null) {
            eliminar(a);
        }
    }
    
    public static void asiento(int fila, char letra) {
        Asiento a;
        a = Recuperar.asiento(fila, letra);
        if (a != null) {
            eliminar(a);
        }
    }
    
    public static void avion(int id) {
        Avion a;
        a = Recuperar.avion(id);
        if (a != null) {
            eliminar(a);
        }
    }
    
    public static void vuelo(String cod) {
        Vuelo v;
        v = Recuperar.vuelo(cod);
        if (v != null) {
            eliminar(v);
        }
    }
    
    public static void eliminar(Object objeto) {
        Session sesion;
        try {
            sesion = NewHibernateUtil.getSession();
            sesion.beginTransaction();
            sesion.delete(objeto);
            sesion.getTransaction().commit();
            sesion.close();
        }catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }
}
