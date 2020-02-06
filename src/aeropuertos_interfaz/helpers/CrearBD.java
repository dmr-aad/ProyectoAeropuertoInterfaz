/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
*/
package aeropuertos_interfaz.helpers;

import java.sql.SQLException;
import java.sql.Statement;

public class CrearBD {
    public static void aeropuerto(Statement s) {
        try {
            s.execute("CREATE DATABASE IF NOT EXISTS AeropuertosBD");
            
            s.execute("USE AeropuertosBD");
            
            s.execute("CREATE TABLE IF NOT EXISTS aeropuertos("
                    + " codigoAeropuerto CHAR(3) NOT NULL, "
                    + " pais VARCHAR(15) NOT NULL,"
                    + " ciudad VARCHAR(9) NOT NULL,"
                    + " PRIMARY KEY(codigoAeropuerto)"
                    + ")"
                    + "ENGINE INNODB;");
            
            s.execute("CREATE TABLE IF NOT EXISTS aerolineas("
                    + " codigoAerolinea CHAR(3) NOT NULL,"
                    + " nombre VARCHAR(20) NOT NULL,"
                    + " PRIMARY KEY(codigoAerolinea)"
                    + ")"
                    + "ENGINE INNODB;");
            
            s.execute("CREATE TABLE IF NOT EXISTS Aeropuerto_AeroLinea("
                    + " aeropuerto CHAR(3) NOT NULL,"
                    + " aerolinea CHAR(3) NOT NULL,"
                    + " PRIMARY KEY(aeropuerto, aerolinea),"
                    + " FOREIGN KEY (aeropuerto) REFERENCES aeropuertos(codigoAeropuerto)"
                    + "     ON DELETE CASCADE"
                    + "     ON UPDATE CASCADE,"
                    + " FOREIGN KEY (aerolinea) REFERENCES aerolineas(codigoAerolinea)"
                    + "     ON DELETE CASCADE"
                    + "     ON UPDATE CASCADE"
                    + ")"
                    + "ENGINE INNODB");
            
            s.execute("CREATE TABLE IF NOT EXISTS aviones("
                    + " idAvion INT(4) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,"
                    + " modelo VARCHAR(15) NOT NULL,"
                    + " cantidadPasajeros INT NOT NULL,"
                    + " codigoAerolinea CHAR(3) NOT NULL,"
                    + " PRIMARY KEY(idAvion),"
                    + " FOREIGN KEY (codigoAerolinea) REFERENCES aerolineas(codigoAerolinea)"
                    + "     ON DELETE CASCADE"
                    + "     ON UPDATE CASCADE"
                    + ")"
                    + "ENGINE INNODB");
                        
            s.execute("CREATE TABLE IF NOT EXISTS vuelos("
                    + " codigoVuelo CHAR(6) NOT NULL,"
                    + " precio FLOAT NOT NULL,"
                    + " origen VARCHAR(20) NOT NULL,"
                    + " destino VARCHAR(20) NOT NULL,"
                    + " fechaSalida Date not null,"
                    + " horaSalida Time not null,"
                    + " horaLlegada Time not null,"
                    + " Embarque Time not null,"
                    + " oferta BOOLEAN NOT NULL,"
                    + " idAvion INT(4) UNSIGNED ZEROFILL NOT NULL,"
                    + " PRIMARY KEY(codigoVuelo),"
                    + " FOREIGN KEY (idAvion) REFERENCES aviones(idAvion)"
                    + "     ON DELETE CASCADE"
                    + "     ON UPDATE CASCADE"
                    + ")"
                    + "ENGINE INNODB");
            
            s.execute("CREATE TABLE IF NOT EXISTS vuelosIda("
                    + " codigoVuelo CHAR(6) NOT NULL,"
                    + " tarifa FLOAT NOT NULL,"
                    + " impuesto FLOAT NOT NULL,"
                    + " PRIMARY KEY(codigoVuelo),"
                    + " FOREIGN KEY (codigoVuelo) REFERENCES vuelos(codigoVuelo)"
                    + "     ON DELETE CASCADE"
                    + "     ON UPDATE CASCADE"
                    + ")"
                    + "ENGINE INNODB");
            
            s.execute("CREATE TABLE IF NOT EXISTS vuelosVuelta("
                    + " codigoVuelo CHAR(6) NOT NULL,"
                    + " tarifa FLOAT NOT NULL,"
                    + " impuesto FLOAT NOT NULL,"
                    + " PRIMARY KEY(codigoVuelo),"
                    + " FOREIGN KEY (codigoVuelo) REFERENCES vuelos(codigoVuelo)"
                    + "     ON DELETE CASCADE"
                    + "     ON UPDATE CASCADE"
                    + ")"
                    + "ENGINE INNODB");
            
            s.execute("CREATE TABLE IF NOT EXISTS asientos("
                    + " fila INT(2) NOT NULL,"
                    + " letra CHAR(1) NOT NULL,"
                    + " ocupado BOOLEAN NOT NULL,"
                    + " vuelo CHAR(6) NOT NULL,"
                    + " PRIMARY KEY(vuelo, fila, letra),"
                    + " FOREIGN KEY (vuelo) REFERENCES vuelos(codigoVuelo)"
                    + "     ON DELETE CASCADE"
                    + "     ON UPDATE CASCADE"
                    + ")"
                    + "ENGINE INNODB");
            
            s.execute("CREATE TABLE IF NOT EXISTS PASAJEROS"
                    + "(DNI CHAR(9) NOT NULL,"
                    + " NOMBRE VARCHAR(15) NOT NULL,"
                    + " APELLIDOS VARCHAR(20) NOT NULL,"
                    + " PAIS VARCHAR(20) NOT NULL,"
                    + " EMAIL VARCHAR (20) NOT NULL,"
                    + " TELEFONO CHAR(9) NOT NULL,"
                    + " VUELO CHAR(6) NOT NULL,"
                    + " PRIMARY KEY(DNI),"
                    + " FOREIGN KEY (VUELO) REFERENCES VUELOS (CODIGOVUELO)"
                    + "                 ON DELETE CASCADE"
                    + "                 ON UPDATE CASCADE"
                    + ")"
                    + "ENGINE INNODB");
            
            s.execute("CREATE TABLE IF NOT EXISTS RESERVAS"
                    + "(CLASE VARCHAR(15) NOT NULL,"
                    + " PASAJERO CHAR(9) NOT NULL,"
                    + " VUELO CHAR(6) NOT NULL,"
                    + " ASIENTO CHAR(3) not null,"
                    + " PRIMARY KEY (PASAJERO,VUELO),"
                    + " FOREIGN KEY (PASAJERO) REFERENCES PASAJEROS(DNI)"
                    + "             ON DELETE CASCADE"
                    + "             ON UPDATE CASCADE,"
                    + " FOREIGN KEY (VUELO) REFERENCES VUELOS(CODIGOVUELO)"
                    + "             ON DELETE CASCADE"
                    + "             ON UPDATE CASCADE"
                    + ")ENGINE INNODB;");
            
            System.out.println("TABLAS CREADAS");
        } catch (SQLException e) {
            System.out.println("Error generando las tablas\n" + e);
        }
    }
}