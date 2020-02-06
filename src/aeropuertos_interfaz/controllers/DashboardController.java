/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeropuertos_interfaz.controllers;

import aeropuertos_interfaz.helpers.Contar;
import aeropuertos_interfaz.helpers.VistasDinamicas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author a18danielmr
 */
public class DashboardController implements Initializable {

    @FXML
    private BorderPane border_pane;
    @FXML
    private Text txtContador_Aeropuertos;
    @FXML
    private Text txtContador_Aerolineas;
    @FXML
    private Text txtContador_Aviones;
    @FXML
    private Text txtContador_Vuelos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtContador_Aeropuertos.setText(Contar.Aeropuertos());
        txtContador_Aerolineas.setText(Contar.Aerolineas());
        txtContador_Aviones.setText(Contar.Aviones());
        txtContador_Vuelos.setText(Contar.Vuelos());
    }    

    @FXML
    private void show_table_aeropuertos(MouseEvent event) {
        VistasDinamicas.load_border_center(border_pane, "tabla_aeropuerto");
    }

    @FXML
    private void show_table_aerolineas(MouseEvent event) {
        VistasDinamicas.load_border_center(border_pane, "tabla_aerolinea");
    }

    @FXML
    private void show_table_aviones(MouseEvent event) {
        VistasDinamicas.load_border_center(border_pane, "tabla_avion");
    }

    @FXML
    private void show_table_vuelos(MouseEvent event) {
        VistasDinamicas.load_border_center(border_pane, "tabla_vuelo");
    }
    
}
