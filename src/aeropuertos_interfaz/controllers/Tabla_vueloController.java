/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeropuertos_interfaz.controllers;

import aeropuertos_interfaz.NewHibernateUtil;
//import aeropuertos_interfaz.objetos.Aeropuerto;
//import aeropuertos_interfaz.objetos.Avion;
//import aeropuertos_interfaz.objetos.Vuelo;
import Objetos.*;
import java.net.URL;
import java.util.Date;
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
public class Tabla_vueloController implements Initializable {

    @FXML
    private TableView<Vuelo> tablaVuelo;
    @FXML
    private TableColumn<Vuelo, String> cod;
    @FXML
    private TableColumn<Vuelo, Float> precio;
    @FXML
    private TableColumn<Vuelo, String> origen;
    @FXML
    private TableColumn<Vuelo, String> destino;
    @FXML
    private TableColumn<Vuelo, Date> fecha;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cod.setCellValueFactory(new PropertyValueFactory<Vuelo, String>("codigoVuelo"));
        precio.setCellValueFactory(new PropertyValueFactory<Vuelo, Float>("precio"));
        origen.setCellValueFactory(new PropertyValueFactory<Vuelo, String>("origen"));
        destino.setCellValueFactory(new PropertyValueFactory<Vuelo, String>("destino"));
        fecha.setCellValueFactory(new PropertyValueFactory<Vuelo, Date>("fechaSalida"));
        tablaVuelo.setItems(getVuelo());
    }    
    
    public ObservableList<Vuelo> getVuelo() {
        ObservableList<Vuelo> vuelo = FXCollections.observableArrayList();
        Session sesion;
        List<Vuelo> vuelos = null;
        try {
            sesion = NewHibernateUtil.getSession();
            vuelos = sesion.createCriteria(Vuelo.class).list();
            sesion.close();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        vuelo.addAll(vuelos);
        return vuelo;
    }
}
