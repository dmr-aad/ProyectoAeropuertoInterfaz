/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeropuertos_interfaz.controllers;

import aeropuertos_interfaz.NewHibernateUtil;
//import aeropuertos_interfaz.objetos.Aerolinea;
//import aeropuertos_interfaz.objetos.Aeropuerto;
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
public class Tabla_aerolineaController implements Initializable {

    @FXML
    private TableView<Aerolinea> tablaAerolinea;
    @FXML
    private TableColumn<Aerolinea, String> cod;
    @FXML
    private TableColumn<Aerolinea, String> nombre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cod.setCellValueFactory(new PropertyValueFactory<Aerolinea, String>("codigoAerolinea"));
        nombre.setCellValueFactory(new PropertyValueFactory<Aerolinea, String>("nombre"));
        tablaAerolinea.setItems(getAerolinea());
    }    
    
    public ObservableList<Aerolinea> getAerolinea() {
        ObservableList<Aerolinea> aerolinea = FXCollections.observableArrayList();
        Session sesion;
        List<Aerolinea> aerolineas = null;
        try {
            sesion = NewHibernateUtil.getSession();
            aerolineas = sesion.createCriteria(Aerolinea.class).list();
            sesion.close();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        aerolinea.addAll(aerolineas);
        return aerolinea;
    }
}
