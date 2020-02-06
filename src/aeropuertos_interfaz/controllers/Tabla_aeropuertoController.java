/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeropuertos_interfaz.controllers;

import aeropuertos_interfaz.NewHibernateUtil;
//import aeropuertos_interfaz.objetos.Aeropuerto;
import Objetos.*;
import java.net.URL;
import java.util.ArrayList;
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
public class Tabla_aeropuertoController implements Initializable {

    @FXML
    private TableView<Aeropuerto> tablaAeropuerto;
    @FXML
    private TableColumn<Aeropuerto, String> cod;
    @FXML
    private TableColumn<Aeropuerto, String> pais;
    @FXML
    private TableColumn<Aeropuerto, String> ciudad;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cod.setCellValueFactory(new PropertyValueFactory<Aeropuerto, String>("codigoAeropuerto"));
        pais.setCellValueFactory(new PropertyValueFactory<Aeropuerto, String>("pais"));
        ciudad.setCellValueFactory(new PropertyValueFactory<Aeropuerto, String>("ciudad"));
        tablaAeropuerto.setItems(getAeropuerto());
    }

    public ObservableList<Aeropuerto> getAeropuerto() {
        ObservableList<Aeropuerto> aeropuerto = FXCollections.observableArrayList();
        Session sesion;
        List<Aeropuerto> aeropuertos = null;
        try {
            sesion = NewHibernateUtil.getSession();
            aeropuertos = sesion.createCriteria(Aeropuerto.class).list();
            sesion.close();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        aeropuerto.addAll(aeropuertos);
        return aeropuerto;
    }

}
