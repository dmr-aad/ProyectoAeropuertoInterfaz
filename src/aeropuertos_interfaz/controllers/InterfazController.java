package aeropuertos_interfaz.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import aeropuertos_interfaz.helpers.VistasDinamicas;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author a18danielmr
 */
public class InterfazController implements Initializable {

    @FXML
    private BorderPane border_pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        VistasDinamicas.load_border_center(border_pane, "dashboard");
    }    

    @FXML
    private void show_dashboard(MouseEvent event) throws IOException {
        VistasDinamicas.load_border_center(border_pane, "dashboard");
    }

    @FXML
    private void show_airports(MouseEvent event) {
        VistasDinamicas.load_border_center(border_pane, "aeropuertos");
    }

    @FXML
    private void show_airlines(MouseEvent event) {
        VistasDinamicas.load_border_center(border_pane, "aerolineas");
    }

    @FXML
    private void show_airplanes(MouseEvent event) {
        VistasDinamicas.load_border_center(border_pane, "aviones");
    }

    @FXML
    private void show_seats(MouseEvent event) {
        VistasDinamicas.load_border_center(border_pane, "asientos");
    }

    @FXML
    private void show_flights(MouseEvent event) {
        VistasDinamicas.load_border_center(border_pane, "vuelos");
    }

    @FXML
    private void show_reservation(MouseEvent event) {
        VistasDinamicas.load_border_center(border_pane, "reservas");
    }
    
}
