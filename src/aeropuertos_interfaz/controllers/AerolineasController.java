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
//import aeropuertos_interfaz.objetos.Aeropuerto;
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
public class AerolineasController implements Initializable {

    private String item;
    private int indice;

    @FXML
    private ListView<String> listaAerolineas;
    @FXML
    private TextField codAerolinea;
    @FXML
    private TextField nomAerolinea;
    @FXML
    private ComboBox<String> cbAeropuerto;
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

    @FXML
    private void añadir(MouseEvent event) {
        String cod = codAerolinea.getText();
        String nom = nomAerolinea.getText();
        String codigoAeropuerto = cbAeropuerto.getSelectionModel().getSelectedItem();
        Aeropuerto aeropuerto = Recuperar.aeropuerto(codigoAeropuerto);
        Aerolinea a = new Aerolinea(cod, nom);
        Altas.altasAerolineas(a, aeropuerto);
        actualizarLista();
        actualizarContador();
        limpiarEntry();
    }

    @FXML
    private void modificar(MouseEvent event) {
        String cod = codAerolinea.getText();
        String nom = nomAerolinea.getText();
        Aerolinea a = new Aerolinea(cod, nom);
        Modificar.Aerolineas(a);
        limpiarEntry();
    }

    @FXML
    private void borrar(MouseEvent event) {
        String cod = codAerolinea.getText();
        String nom = nomAerolinea.getText();
        Bajas.aerolinea(cod);
        actualizarLista();
        actualizarContador();
        limpiarEntry();
    }

    @FXML
    private void seleccion_item(MouseEvent event) {
        item = listaAerolineas.getSelectionModel().getSelectedItem();
        indice = listaAerolineas.getSelectionModel().getSelectedIndex();
        Aerolinea a = Recuperar.aerolinea(item);
        codAerolinea.setText(a.getCodigoAerolinea());
        nomAerolinea.setText(a.getNombre());
    }

    public void limpiarEntry() {
        codAerolinea.setText("");
        nomAerolinea.setText("");
    }

    public void fillCombo() {
        ArrayList<String> codigos = new ArrayList<String>();
        codigos = CargarDatos.listaAeropuertos();
        cbAeropuerto.getItems().addAll(codigos);
    }
    
    public void actualizarContador() {
        txtContador_Aeropuertos.setText(Contar.Aeropuertos());
        txtContador_Aerolineas.setText(Contar.Aerolineas());
        txtContador_Aviones.setText(Contar.Aviones());
        txtContador_Vuelos.setText(Contar.Vuelos());
    }
    
    public void actualizarLista() {
        listaAerolineas.getItems().clear();
        listaAerolineas.getItems().addAll(CargarDatos.listaAerolineas());
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
            resultado = Busqueda.Aerolineas(busqueda);
            if (!resultado.isEmpty()) {
                listaAerolineas.getItems().clear();
                listaAerolineas.getItems().addAll(resultado);
            } else {
                listaAerolineas.getItems().clear();
            }
        } else {
            listaAerolineas.getItems().clear();
            listaAerolineas.getItems().addAll(CargarDatos.listaAerolineas());
        }
    }
}
