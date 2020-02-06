/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeropuertos_interfaz.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import Objetos.*;
import aeropuertos_interfaz.NewHibernateUtil;
import aeropuertos_interfaz.helpers.Busqueda;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * FXML Controller class
 *
 * @author Mirotic
 */
public class ReservasController implements Initializable {

    @FXML
    private Text txtContador_Aeropuertos;
    @FXML
    private Text txtContador_Aerolineas;
    @FXML
    private Text txtContador_Aviones;
    @FXML
    private Text txtContador_Vuelos;
    @FXML
    private TextField txtBusqueda;
    @FXML
    private TableView<Reserva> tablaReservas;
    @FXML
    private TableColumn<Reserva, String> dni;
    @FXML
    private TableColumn<Reserva, String> nombre;
    @FXML
    private TableColumn<Reserva, String> apellido;
    @FXML
    private TableColumn<Reserva, String> pais;
    @FXML
    private TableColumn<Reserva, String> email;
    @FXML
    private TableColumn<Reserva, String> telefono;
    @FXML
    private TableColumn<Reserva, String> vuelo;
    @FXML
    private TableColumn<Reserva, String> asiento;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actualizarTabla();
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
        String busqueda = txtBusqueda.getText();
        if (!busqueda.isEmpty()) {
            Reserva resultado;
            resultado = Busqueda.ReservasObjeto(busqueda);
            if (resultado != null) {
                tablaReservas.getItems().clear();
                tablaReservas.setItems(getReserva(resultado));
            } else {
                tablaReservas.getItems().clear();
            }
        } else {
            tablaReservas.getItems().clear();
            tablaReservas.setItems(getReserva());
        }
    }
    
    public void actualizarTabla() {
        dni.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reserva, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Reserva, String> param) {
                return new SimpleStringProperty(param.getValue().getPasajero().getDni());
            }
        });
        nombre.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reserva, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Reserva, String> param) {
                return new SimpleStringProperty(param.getValue().getPasajero().getNombre());
            }
        });
        apellido.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reserva, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Reserva, String> param) {
                return new SimpleStringProperty(param.getValue().getPasajero().getApellidos());
            }
        });
        pais.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reserva, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Reserva, String> param) {
                return new SimpleStringProperty(param.getValue().getPasajero().getPais());
            }
        });
        email.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reserva, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Reserva, String> param) {
                return new SimpleStringProperty(param.getValue().getPasajero().getEmail());
            }
        });
        telefono.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reserva, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Reserva, String> param) {
                return new SimpleStringProperty(param.getValue().getPasajero().getTelefono());
            }
        });
        vuelo.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reserva, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Reserva, String> param) {
                return new SimpleStringProperty(param.getValue().getVuelo().getCodigoVuelo());
            }
        });
        asiento.setCellValueFactory(new PropertyValueFactory<Reserva, String>("asiento"));
        tablaReservas.setItems(getReserva());
    }
    
    public ObservableList<Reserva> getReserva(Reserva r) {
        ObservableList<Reserva> reserva = FXCollections.observableArrayList();
        reserva.add(r);
        return reserva;
    }
    
    public ObservableList<Reserva> getReserva() {
        ObservableList<Reserva> reserva = FXCollections.observableArrayList();
        Session sesion;
        List<Reserva> reservas = null;
        try {
            sesion = NewHibernateUtil.getSession();
            reservas = sesion.createCriteria(Reserva.class).list();
            sesion.close();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        reserva.addAll(reservas);
        return reserva;
    }
    
}
