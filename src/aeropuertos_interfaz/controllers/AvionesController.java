/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeropuertos_interfaz.controllers;

import aeropuertos_interfaz.helpers.Altas;
import aeropuertos_interfaz.helpers.Bajas;
import aeropuertos_interfaz.helpers.CargarDatos;
import aeropuertos_interfaz.helpers.HelperResourcesLoader;
import aeropuertos_interfaz.helpers.Modificar;
import aeropuertos_interfaz.helpers.Recuperar;
//import aeropuertos_interfaz.objetos.Aerolinea;
//import aeropuertos_interfaz.objetos.Avion;
import Objetos.*;
import aeropuertos_interfaz.helpers.Busqueda;
import aeropuertos_interfaz.helpers.Contar;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author a18danielmr
 */
public class AvionesController implements Initializable {
    private String item;
    private int indice;
    private Avion avionRecuperado;

    @FXML
    private TextField idAvion;
    @FXML
    private TextField modeloAvion;
    @FXML
    private TextField npasajerosAvion;
    @FXML
    private ListView<String> listaAviones;
    @FXML
    private ComboBox<String> cbAvion;
    @FXML
    private Text txtContador_Aeropuertos;
    @FXML
    private Text txtContador_Aerolineas;
    @FXML
    private Text txtContador_Aviones;
    @FXML
    private Text txtContador_Vuelos;
    @FXML
    private TextField txtBuscar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actualizarLista();
        actualizarContador();
        limpiarEntry();
        fillCombo();
    }    

    private void añadir_avion(MouseEvent event) {
        Dialog dialog = new Dialog();
        Parent root = HelperResourcesLoader.loadFXML("/dialogs/añadir_avion");
        dialog.getDialogPane().setContent(root);
        
        root.setStyle("-fx-background-color:transparent");
        
        dialog.initStyle(StageStyle.TRANSPARENT);
        dialog.show();
    }

    @FXML
    private void añadir(MouseEvent event) {
        String modelo = modeloAvion.getText();
        String npasajeros = npasajerosAvion.getText();
        String codAerolinea = cbAvion.getSelectionModel().getSelectedItem();
        Aerolinea aerolinea = Recuperar.aerolinea(codAerolinea);
        Avion a = new Avion(modelo, Integer.parseInt(npasajeros), aerolinea);
        Altas.altasAviones(a, aerolinea);
        actualizarLista();
        actualizarContador();
        limpiarEntry();
    }

    @FXML
    private void modificar(MouseEvent event) {
        String modelo = modeloAvion.getText();
        String npasajeros = npasajerosAvion.getText();
        String codAerolinea = cbAvion.getSelectionModel().getSelectedItem();
        Aerolinea aerolinea = Recuperar.aerolinea(codAerolinea);
        avionRecuperado.setModelo(modelo);
        avionRecuperado.setCantidadPasajeros(Integer.parseInt(npasajeros));
        avionRecuperado.setAerolinea(aerolinea);
        Modificar.Aviones(avionRecuperado);
        limpiarEntry();
    }

    @FXML
    private void borrar(MouseEvent event) {
        String id = idAvion.getText();
        Bajas.avion(Integer.parseInt(id));
        actualizarLista();
        actualizarContador();
        limpiarEntry();
    }

    @FXML
    private void seleccion_item(MouseEvent event) {
        item = listaAviones.getSelectionModel().getSelectedItem();
        indice = listaAviones.getSelectionModel().getSelectedIndex();
        avionRecuperado = Recuperar.avion(Integer.parseInt(item));
        idAvion.setText(String.valueOf(avionRecuperado.getIdAvion()));
        modeloAvion.setText(avionRecuperado.getModelo());
        npasajerosAvion.setText(String.valueOf(avionRecuperado.getCantidadPasajeros()));
    }
    
    public void limpiarEntry() {
        idAvion.setText("");
        modeloAvion.setText("");
        npasajerosAvion.setText("");
    }
    
    public void fillCombo() {
        ArrayList<String> codigos = new ArrayList<String>();
        codigos = CargarDatos.listaAerolineas();
        cbAvion.getItems().addAll(codigos);
    }
    
    public void actualizarContador() {
        txtContador_Aeropuertos.setText(Contar.Aeropuertos());
        txtContador_Aerolineas.setText(Contar.Aerolineas());
        txtContador_Aviones.setText(Contar.Aviones());
        txtContador_Vuelos.setText(Contar.Vuelos());
    }
    
    public void actualizarLista() {
        listaAviones.getItems().clear();
        listaAviones.getItems().addAll(CargarDatos.listaAviones());
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
        String busqueda = txtBuscar.getText();
        if (!busqueda.isEmpty()) {
            ArrayList<String> resultado = new ArrayList<>();
            resultado = Busqueda.Aviones(busqueda);
            if (!resultado.isEmpty()) {
                listaAviones.getItems().clear();
                listaAviones.getItems().addAll(resultado);
            } else {
                listaAviones.getItems().clear();
            }
        } else {
            listaAviones.getItems().clear();
            listaAviones.getItems().addAll(CargarDatos.listaAviones());
        }
    }
    
}
