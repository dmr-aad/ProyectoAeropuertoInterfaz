/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeropuertos_interfaz.controllers;

import aeropuertos_interfaz.NewHibernateUtil;
//import aeropuertos_interfaz.objetos.Aerolinea;
//import aeropuertos_interfaz.objetos.Aeropuerto;
//import aeropuertos_interfaz.objetos.Avion;
import Objetos.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * FXML Controller class
 *
 * @author Mirotic
 */
public class Tabla_avionController implements Initializable {

    @FXML
    private TableView<Avion> tablaAvion;
    @FXML
    private TableColumn<Avion, Integer> id;
    @FXML
    private TableColumn<Avion, String> modelo;
    @FXML
    private TableColumn<Avion, Integer> pasajeros;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id.setCellValueFactory(new PropertyValueFactory<Avion, Integer>("idAvion"));
        modelo.setCellValueFactory(new PropertyValueFactory<Avion, String>("modelo"));
        pasajeros.setCellValueFactory(new PropertyValueFactory<Avion, Integer>("cantidadPasajeros"));
        tablaAvion.setItems(getAvion());
    }  
    
    public ObservableList<Avion> getAvion() {
        ObservableList<Avion> avion = FXCollections.observableArrayList();
        Session sesion;
        List<Avion> aviones = null;
        try {
            sesion = NewHibernateUtil.getSession();
            aviones = sesion.createCriteria(Avion.class).list();
            sesion.close();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        avion.addAll(aviones);
        return avion;
    }
    
}
