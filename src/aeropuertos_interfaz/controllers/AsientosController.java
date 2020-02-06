/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeropuertos_interfaz.controllers;

import aeropuertos_interfaz.helpers.*;
//import aeropuertos_interfaz.objetos.Asiento;
//import aeropuertos_interfaz.objetos.Avion;
import Objetos.*;
import aeropuertos_interfaz.NewHibernateUtil;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * FXML Controller class
 *
 * @author a18danielmr
 */
public class AsientosController implements Initializable {
    private String item;
    private int indice;

    private ListView<String> listaAsientos;
    @FXML
    private Text txtContador_Aeropuertos;
    @FXML
    private Text txtContador_Aerolineas;
    @FXML
    private Text txtContador_Aviones;
    @FXML
    private Text txtContador_Vuelos;
    @FXML
    private TableView<Asiento> tablaAsientos;
    @FXML
    private TableColumn<Asiento, Integer> fila;
    @FXML
    private TableColumn<Asiento, String> letra;
    @FXML
    private TableColumn<Asiento, Boolean> ocupado;
    @FXML
    private ComboBox<String> cbVuelos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarAsientos();
        fillCombo();
    }

    public void fillCombo() {
        ArrayList<String> codigos = new ArrayList<String>();
        codigos = CargarDatos.listaVuelos();
        cbVuelos.getItems().addAll(codigos);
    }

    @FXML
    private void show_table_aeropuertos(MouseEvent event) {
    }

    @FXML
    private void show_table_aerolineas(MouseEvent event) {
    }

    @FXML
    private void show_table_aviones(MouseEvent event) {
    }

    @FXML
    private void show_table_vuelos(MouseEvent event) {
    }

    @FXML
    private void buscar(MouseEvent event) {
        String busqueda = cbVuelos.getSelectionModel().getSelectedItem();
        Vuelo resultado = Busqueda.Vuelo(busqueda);
        tablaAsientos.setItems(getAsiento(resultado));
    }
    
    public void cargarAsientos() {
        fila.setCellValueFactory(new PropertyValueFactory<Asiento, Integer>("fila"));
        letra.setCellValueFactory(new PropertyValueFactory<Asiento, String>("letra"));
        ocupado.setCellValueFactory(new PropertyValueFactory<Asiento, Boolean>("ocupado"));
    }
    
    public ObservableList<Asiento> getAsiento(Vuelo v) {
        ObservableList<Asiento> asiento = FXCollections.observableArrayList();
        List<Asiento> asientos = new ArrayList<>(v.getAsientos());
        asiento.addAll(asientos);
        return asiento;
    }

    
    
}
