/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeropuertos_interfaz.helpers;

import Objetos.*;
import aeropuertos_interfaz.NewHibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author a18danielmr
 */
public class Contar {
    
    public static String Aeropuertos() {
        List<Object> tuplas = null;
        Session sesion;
        
        try
        {
            sesion = NewHibernateUtil.getSession();
            tuplas = sesion.createCriteria(Aeropuerto.class).list();
            sesion.close();
                        
        }catch(HibernateException e)
        {
            System.out.println(e.getMessage());
        }
        
        return String.valueOf(tuplas.size());
    } 
    
    public static String Aerolineas() {
        List<Object> tuplas = null;
        Session sesion;
        
        try
        {
            sesion = NewHibernateUtil.getSession();
            tuplas = sesion.createCriteria(Aerolinea.class).list();
            sesion.close();
                        
        }catch(HibernateException e)
        {
            System.out.println(e.getMessage());
        }
        
        return String.valueOf(tuplas.size());
    } 
    
    public static String Aviones() {
        List<Object> tuplas = null;
        Session sesion;
        
        try
        {
            sesion = NewHibernateUtil.getSession();
            tuplas = sesion.createCriteria(Avion.class).list();
            sesion.close();
                        
        }catch(HibernateException e)
        {
            System.out.println(e.getMessage());
        }
        
        return String.valueOf(tuplas.size());
    } 
    
    public static String Vuelos() {
        List<Object> tuplas = null;
        Session sesion;
        
        try
        {
            sesion = NewHibernateUtil.getSession();
            tuplas = sesion.createCriteria(Vuelo.class).list();
            sesion.close();
                        
        }catch(HibernateException e)
        {
            System.out.println(e.getMessage());
        }
        
        return String.valueOf(tuplas.size());
    } 
}
