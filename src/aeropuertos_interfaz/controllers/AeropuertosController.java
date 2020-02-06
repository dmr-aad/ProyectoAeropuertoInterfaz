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
//import aeropuertos_interfaz.objetos.Aeropuerto;
import Objetos.*;
import aeropuertos_interfaz.helpers.Busqueda;
import aeropuertos_interfaz.helpers.Contar;
import aeropuertos_interfaz.helpers.Validar;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author a18danielmr
 */
public class AeropuertosController implements Initializable {

    private String item;
    private int indice;
    private Alert alert = new Alert(Alert.AlertType.WARNING);

    @FXML
    private TextField codAeropuerto;
    @FXML
    private TextField paisAeropuerto;
    @FXML
    private TextField ciudadAeropuerto;
    @FXML
    private ListView<String> listaAeropuertos;
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
    }

    @FXML
    private void añadir(MouseEvent event) {
        String cod = codAeropuerto.getText();
        String pais = paisAeropuerto.getText();
        String ciudad = ciudadAeropuerto.getText();
        if (!cod.isEmpty() && !pais.isEmpty() && !ciudad.isEmpty()) {
            Aeropuerto a = new Aeropuerto(cod, pais, ciudad);
            if (Validar.validarCodigoAeropuerto(cod) == 0) {
                Altas.altasAeropuertos(a);
                actualizarLista();
                actualizarContador();
                limpiarEntry();
            }
        } else {
            alert.setTitle("Advertencia");
            alert.setHeaderText("Error en el Alta del Aeropuerto");
            alert.setContentText("Faltan campos por rellenar");

            alert.showAndWait();
        }
    }

    @FXML
    private void modificar(MouseEvent event) {
        String cod = codAeropuerto.getText();
        String pais = paisAeropuerto.getText();
        String ciudad = ciudadAeropuerto.getText();
        Aeropuerto a = new Aeropuerto(cod, pais, ciudad);
        Modificar.Aeropuertos(a);
        limpiarEntry();
    }

    @FXML
    private void borrar(MouseEvent event) {
        String cod = codAeropuerto.getText();
        Bajas.aeropuerto(cod);
        actualizarLista();
        actualizarContador();
        limpiarEntry();
    }

    @FXML
    private void seleccion_item(MouseEvent event) {
        item = listaAeropuertos.getSelectionModel().getSelectedItem();
        indice = listaAeropuertos.getSelectionModel().getSelectedIndex();
        Aeropuerto a = Recuperar.aeropuerto(item);
        codAeropuerto.setText(a.getCodigoAeropuerto());
        paisAeropuerto.setText(a.getPais());
        ciudadAeropuerto.setText(a.getCiudad());
    }

    public void limpiarEntry() {
        codAeropuerto.setText("");
        paisAeropuerto.setText("");
        ciudadAeropuerto.setText("");
    }

    public void actualizarContador() {
        txtContador_Aeropuertos.setText(Contar.Aeropuertos());
        txtContador_Aerolineas.setText(Contar.Aerolineas());
        txtContador_Aviones.setText(Contar.Aviones());
        txtContador_Vuelos.setText(Contar.Vuelos());
    }

    public void actualizarLista() {
        listaAeropuertos.getItems().clear();
        listaAeropuertos.getItems().addAll(CargarDatos.listaAeropuertos());
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
            resultado = Busqueda.Aeropuertos(busqueda);
            if (!resultado.isEmpty()) {
                listaAeropuertos.getItems().clear();
                listaAeropuertos.getItems().addAll(resultado);
            } else {
                listaAeropuertos.getItems().clear();
            }
        } else {
            listaAeropuertos.getItems().clear();
            listaAeropuertos.getItems().addAll(CargarDatos.listaAeropuertos());
        }
    }
}
