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
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author a18danielmr
 */
public class AsientosController implements Initializable {
    private String item;
    private int indice;

    @FXML
    private TextField filaAsiento;
    @FXML
    private TextField letraAsiento;
    @FXML
    private RadioButton rbSiAsiento;
    @FXML
    private ToggleGroup rgOcupado;
    @FXML
    private RadioButton rbNoAsiento;
    @FXML
    private ListView<String> listaAsientos;
    @FXML
    private TextField avionAsiento;
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
    private void modificar(MouseEvent event) {
        String fila = filaAsiento.getText();
        String letra = letraAsiento.getText();
        String asiento = fila.concat(letra);
        actualizarLista();
        actualizarContador();
        limpiarEntry();
    }

    @FXML
    private void borrar(MouseEvent event) {
        String fila = filaAsiento.getText();
        String letra = letraAsiento.getText();
        Bajas.asiento(Integer.parseInt(fila), letra.charAt(0));
        String asiento = fila.concat(letra);
        actualizarLista();
        actualizarContador();
        limpiarEntry();
    }

    @FXML
    private void seleccion_item(MouseEvent event) {
        item = listaAsientos.getSelectionModel().getSelectedItem();
        indice = listaAsientos.getSelectionModel().getSelectedIndex();
        Asiento a = Recuperar.asiento(Character.getNumericValue(item.charAt(0)), item.charAt(1));
        filaAsiento.setText(String.valueOf(a.getFila()));
        letraAsiento.setText(String.valueOf(a.getLetra()));
        if (a.isOcupado()) {
            rbSiAsiento.setSelected(true);
        } else {
            rbNoAsiento.setSelected(true);
        }
    }
    
    public void limpiarEntry() {
        filaAsiento.setText("");
        letraAsiento.setText("");
        rbNoAsiento.setSelected(true);
    }
    
    public void actualizarLista() {
        listaAsientos.getItems().clear();
        listaAsientos.getItems().addAll(CargarDatos.listaAsientos());
    }

    public void actualizarContador() {
        txtContador_Aeropuertos.setText(Contar.Aeropuertos());
        txtContador_Aerolineas.setText(Contar.Aerolineas());
        txtContador_Aviones.setText(Contar.Aviones());
        txtContador_Vuelos.setText(Contar.Vuelos());
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
            resultado = Busqueda.Asientos(busqueda);
            if (!resultado.isEmpty()) {
                listaAsientos.getItems().clear();
                listaAsientos.getItems().addAll(resultado);
            } else {
                listaAsientos.getItems().clear();
            }
        } else {
            listaAsientos.getItems().clear();
            listaAsientos.getItems().addAll(CargarDatos.listaAsientos());
        }
    }
    
}
