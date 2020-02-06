/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeropuertos_interfaz.helpers;

import aeropuertos_interfaz.NewHibernateUtil;
//import aeropuertos_interfaz.objetos.Aerolinea;
//import aeropuertos_interfaz.objetos.Aeropuerto;
//import aeropuertos_interfaz.objetos.Avion;
//import aeropuertos_interfaz.objetos.VueloIda;
//import aeropuertos_interfaz.objetos.VueloIdaVuelta;
import Objetos.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Mirotic
 */
public class Modificar {

    public static void Aeropuertos(Aeropuerto a) {
        guardarModificar(a);
    }

    public static void Aerolineas(Aerolinea a) {
        guardarModificar(a);
    }

    public static void Aviones(Avion a) {
        guardarModificar(a);
    }

    public static void VuelosIda(VueloIda v) {
        guardarModificar(v);
    }

    public static void VuelosVuelta(VueloIdaVuelta v) {
        guardarModificar(v);
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
