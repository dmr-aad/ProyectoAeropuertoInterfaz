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
import aeropuertos_interfaz.NewHibernateUtil;
import aeropuertos_interfaz.helpers.Busqueda;
import aeropuertos_interfaz.helpers.Contar;
import aeropuertos_interfaz.helpers.Validar;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
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
public class AerolineasController implements Initializable {

    private String item;
    private int indice;
    private Alert alert = new Alert(Alert.AlertType.WARNING);

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
    @FXML
    private TreeView<String> treeAerolineas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarTree();
        actualizarContador();
        limpiarEntry();
        fillCombo();
    }

    @FXML
    private void añadir(MouseEvent event) {
        String cod = codAerolinea.getText();
        String nom = nomAerolinea.getText();
        String codigoAeropuerto = null;
        if (cbAeropuerto.getSelectionModel().getSelectedIndex() != -1) {
            codigoAeropuerto = cbAeropuerto.getSelectionModel().getSelectedItem();
            Aeropuerto aeropuerto = Recuperar.aeropuerto(codigoAeropuerto);
            if (!cod.isEmpty() && !nom.isEmpty()) {
                Aerolinea a = new Aerolinea(cod, nom);
                if (Validar.validarCodigoAerolinea(cod) == 0) {
                    Altas.altasAerolineas(a, aeropuerto);
                    cargarTree();
                    actualizarContador();
                    limpiarEntry();
                }
            } else {
                alert.setTitle("Advertencia");
                alert.setHeaderText("Error en el Alta del Aeropuerto");
                alert.setContentText("Faltan campos por rellenar");

                alert.showAndWait();
            }
        } else {
            alert.setTitle("Advertencia");
            alert.setHeaderText("Error en el Alta de la Aerolinea");
            alert.setContentText("Debes seleccionar un aeropuerto");

            alert.showAndWait();
        }
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
        cargarTree();
        actualizarContador();
        limpiarEntry();
    }

    public void limpiarEntry() {
        codAerolinea.setText("");
        nomAerolinea.setText("");
        cbAeropuerto.getSelectionModel().select(-1);
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

    public void cargarTree() {
        TreeItem root = new TreeItem("Aerolineas");
        root.setExpanded(true);
        treeAerolineas.setShowRoot(false);
        treeAerolineas.setRoot(root);
        Session sesion;
        List<Aerolinea> aerolineas = null;
        Set<Aeropuerto> aeropuertos = null;
        try {
            sesion = NewHibernateUtil.getSession();
            aerolineas = sesion.createCriteria(Aerolinea.class).list();
            sesion.close();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        if (!aerolineas.isEmpty()) {
            for (Aerolinea aerolinea : aerolineas) {
                TreeItem item = new TreeItem(aerolinea.getCodigoAerolinea());
                root.getChildren().add(item);
                aeropuertos = aerolinea.getAeropuertos();
                for (Aeropuerto aeropuerto : aeropuertos) {
                    TreeItem hijo = new TreeItem(aeropuerto.getCodigoAeropuerto());
                    item.getChildren().add(hijo);
                }
            }
        }
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
            TreeItem root = new TreeItem("Aerolineas");
            root.setExpanded(true);
            treeAerolineas.setShowRoot(false);
            treeAerolineas.setRoot(root);
            Session sesion;
            List<Aerolinea> aerolineas = null;
            Set<Aeropuerto> aeropuertos = null;
            try {
                sesion = NewHibernateUtil.getSession();
                aerolineas = sesion.createCriteria(Aerolinea.class).list();
                sesion.close();
            } catch (HibernateException e) {
                System.out.println(e.getMessage());
            }
            if (!aerolineas.isEmpty()) {
                for (Aerolinea aerolinea : aerolineas) {
                    if (aerolinea.getCodigoAerolinea().contains(busqueda.toUpperCase())) {
                        TreeItem item = new TreeItem(aerolinea.getCodigoAerolinea());
                        root.getChildren().add(item);
                        aeropuertos = aerolinea.getAeropuertos();
                        for (Aeropuerto aeropuerto : aeropuertos) {
                            TreeItem hijo = new TreeItem(aeropuerto.getCodigoAeropuerto());
                            item.getChildren().add(hijo);
                        }
                    }
                }
            }
        } else {
            cargarTree();
        }
    }

    @FXML
    private void cargarItem(MouseEvent event
    ) {
        if (event.getClickCount() == 2) {
            TreeItem<String> item = treeAerolineas.getSelectionModel().getSelectedItem();
            Aerolinea a = Recuperar.aerolinea(item.getParent().getValue());
            codAerolinea.setText(a.getCodigoAerolinea());
            nomAerolinea.setText(a.getNombre());
            cbAeropuerto.getSelectionModel().select(item.getValue());
        }
    }
}
