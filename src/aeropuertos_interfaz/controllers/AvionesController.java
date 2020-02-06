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
import aeropuertos_interfaz.NewHibernateUtil;
import aeropuertos_interfaz.helpers.Busqueda;
import aeropuertos_interfaz.helpers.Contar;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * FXML Controller class
 *
 * @author a18danielmr
 */
public class AvionesController implements Initializable {

    private String item;
    private int indice;
    private Avion avionRecuperado;
    private Alert alert = new Alert(Alert.AlertType.WARNING);

    @FXML
    private TextField idAvion;
    @FXML
    private TextField modeloAvion;
    @FXML
    private TextField npasajerosAvion;
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
    @FXML
    private TableView<Avion> tableAvion;
    @FXML
    private TableColumn<Avion, Integer> id;
    @FXML
    private TableColumn<Avion, String> modelo;
    @FXML
    private TableColumn<Avion, String> aerolinea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actualizarTabla();
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
        if (cbAvion.getSelectionModel().getSelectedIndex() != -1) {
            String codAerolinea = cbAvion.getSelectionModel().getSelectedItem();
            if (!modelo.isEmpty() && !npasajeros.isEmpty() && !codAerolinea.isEmpty()) {
                Aerolinea aerolinea = Recuperar.aerolinea(codAerolinea);
                Avion a = new Avion(modelo, Integer.parseInt(npasajeros), aerolinea);
                Altas.altasAviones(a, aerolinea);
                actualizarTabla();
                actualizarContador();
                limpiarEntry();
            } else {
                alert.setTitle("Advertencia");
                alert.setHeaderText("Error en el Alta del Avion");
                alert.setContentText("Faltan campos por rellenar");

                alert.showAndWait();
            }
        }  else {
            alert.setTitle("Advertencia");
            alert.setHeaderText("Error en el Alta del Avion");
            alert.setContentText("Debes seleccionar una aerolinea");

            alert.showAndWait();
        }
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
        actualizarTabla();
        actualizarContador();
        limpiarEntry();
    }

    public void limpiarEntry() {
        idAvion.setText("");
        modeloAvion.setText("");
        npasajerosAvion.setText("");
        cbAvion.getSelectionModel().select(-1);
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

    public void actualizarTabla() {
        id.setCellValueFactory(new PropertyValueFactory<Avion, Integer>("idAvion"));
        modelo.setCellValueFactory(new PropertyValueFactory<Avion, String>("modelo"));
        aerolinea.setCellValueFactory(new Callback<CellDataFeatures<Avion, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(CellDataFeatures<Avion, String> param) {
                return new SimpleStringProperty(param.getValue().getAerolinea().getCodigoAerolinea());
            }
        });
        tableAvion.setItems(getAvion());
    }

    public ObservableList<Avion> getAvion() {
        ObservableList<Avion> avion = FXCollections.observableArrayList();
        Session sesion;
        List<Avion> aviones = null;
        try {
            sesion = NewHibernateUtil.getSession();
            aviones = sesion.createCriteria(Avion.class).list();
            sesion.close();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        avion.addAll(aviones);
        return avion;
    }

    public ObservableList<Avion> getAvion(Avion a) {
        ObservableList<Avion> avion = FXCollections.observableArrayList();
        avion.add(a);
        return avion;
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
            Avion resultado;
            resultado = Busqueda.AvionesObjeto(busqueda);
            if (resultado != null) {
                tableAvion.getItems().clear();
                tableAvion.setItems(getAvion(resultado));
            } else {
                tableAvion.getItems().clear();
            }
        } else {
            tableAvion.getItems().clear();
            tableAvion.setItems(getAvion());
        }
    }

    @FXML
    private void cargar_item(MouseEvent event) {
        if (event.getClickCount() == 2) {
            Avion a = tableAvion.getSelectionModel().getSelectedItem();
            idAvion.setText(String.valueOf(a.getIdAvion()));
            modeloAvion.setText(a.getModelo());
            npasajerosAvion.setText(String.valueOf(a.getCantidadPasajeros()));
            cbAvion.getSelectionModel().select(a.getAerolinea().getCodigoAerolinea());
        }
    }

}
