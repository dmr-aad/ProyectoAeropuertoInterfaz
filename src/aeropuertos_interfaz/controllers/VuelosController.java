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
//import aeropuertos_interfaz.objetos.Avion;
//import aeropuertos_interfaz.objetos.Vuelo;
//import aeropuertos_interfaz.objetos.VueloIda;
//import aeropuertos_interfaz.objetos.VueloIdaVuelta;
import Objetos.*;
import aeropuertos_interfaz.helpers.Busqueda;
import aeropuertos_interfaz.helpers.Contar;
import aeropuertos_interfaz.helpers.Validar;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
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
public class VuelosController implements Initializable {

    private String item;
    private int indice;
    private Alert alert = new Alert(Alert.AlertType.WARNING);

    @FXML
    private TextField codVuelo;
    @FXML
    private TextField precioVuelo;
    @FXML
    private TextField origenVuelo;
    @FXML
    private TextField destinoVuelo;
    @FXML
    private ListView<String> listaVuelos;
    @FXML
    private ToggleGroup rgOferta;
    @FXML
    private RadioButton rbSi;
    @FXML
    private RadioButton rbNo;
    @FXML
    private TextField tarifaVuelo;
    @FXML
    private TextField impuestoVuelo;
    @FXML
    private RadioButton rbIda;
    @FXML
    private ToggleGroup rgTipo;
    @FXML
    private RadioButton rbIdayVuelta;
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
    private Text txtLetraCod;
    @FXML
    private JFXDatePicker pickerFSalida;
    @FXML
    private JFXTimePicker pickerHSalida;

    private String letraCod = "I-";
    @FXML
    private JFXTimePicker pickerHLlegada;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actualizarLista();
        actualizarContador();
        limpiarEntry();
        fillCombo();
        txtLetraCod.setText(letraCod);
        rbIda.selectedProperty().addListener((ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) -> {
            if (isNowSelected) {
                letraCod = "I-";
                txtLetraCod.setText(letraCod);
            } else {
                // ...
            }
        });

        rbIdayVuelta.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    letraCod = "V-";
                    txtLetraCod.setText(letraCod);
                } else {
                    // ...
                }
            }
        });
    }

    @FXML
    private void añadir(MouseEvent event) throws ParseException {
        String cod = txtLetraCod.getText().concat(codVuelo.getText());
        String precio = precioVuelo.getText();
        boolean oferta;
        if (rbSi.isSelected()) {
            oferta = true;
        } else {
            oferta = false;
        }
        String origen = origenVuelo.getText();
        String destino = destinoVuelo.getText();
        Date fSalida = new SimpleDateFormat("yyyy-MM-dd").parse(pickerFSalida.getValue().toString());
        Time hSalida = Time.valueOf(pickerHSalida.getValue());
        Time hLlegada = Time.valueOf(pickerHLlegada.getValue());
        Time hEmbarque = CalcularEmbarque(hSalida);
        String idAvion = cbAvion.getSelectionModel().getSelectedItem();
        String impuesto = impuestoVuelo.getText();
        String tarifa = tarifaVuelo.getText();
        if (!cod.isEmpty() && !precio.isEmpty() && !origen.isEmpty()
                && !destino.isEmpty() && !idAvion.isEmpty() && !impuesto.isEmpty()
                && !tarifa.isEmpty()) {
            Avion avion = Recuperar.avion(Integer.parseInt(idAvion));
            boolean valido = true;
            if (rbIda.isSelected()) {
                if (Validar.validarCodigoIda(cod) == 0) {
                    VueloIda vi = new VueloIda(Float.parseFloat(tarifa),
                    Float.parseFloat(impuesto), cod, origen, destino, fSalida,
                    hSalida, hLlegada, hEmbarque, oferta,
                    Float.parseFloat(precio), avion);
                    Altas.altasVuelosIda(vi, avion);
                } else {
                    valido = false;
                }
            } else {
                if (Validar.validarCodigoVuelta(cod) == 0) {
                    VueloIdaVuelta vv = new VueloIdaVuelta(Float.parseFloat(tarifa),
                    Float.parseFloat(impuesto), cod, origen, destino, fSalida,
                    hSalida, hLlegada, hEmbarque, oferta,
                    Float.parseFloat(precio), avion);
                    Altas.altasVuelosVuelta(vv, avion);
                } else {
                    valido = false;
                }
            }
            if (valido) {
                actualizarLista();
                actualizarContador();
                limpiarEntry();
            }
        } else {
            alert.setTitle("Advertencia");
            alert.setHeaderText("Error en el Alta de la Aerolinea");
            alert.setContentText("Faltan campos por rellenar");

            alert.showAndWait();
        }
    }

    @FXML
    private void modificar(MouseEvent event) throws ParseException {
        String cod = codVuelo.getText();
        String precio = precioVuelo.getText();
        boolean oferta;
        if (rbSi.isSelected()) {
            oferta = true;
        } else {
            oferta = false;
        }
        String origen = origenVuelo.getText();
        String destino = destinoVuelo.getText();
        Date fSalida = new SimpleDateFormat("yyyy-MM-dd").parse(pickerFSalida.getValue().toString());
        Time hSalida = Time.valueOf(pickerHSalida.getValue());
        Time hLlegada = Time.valueOf(pickerHLlegada.getValue());
        Time hEmbarque = CalcularEmbarque(hSalida);
        String idAvion = cbAvion.getSelectionModel().getSelectedItem();
        String impuesto = impuestoVuelo.getText();
        String tarifa = tarifaVuelo.getText();
        Avion avion = Recuperar.avion(Integer.parseInt(idAvion));
        if (rbIda.isSelected()) {
            VueloIda vi = new VueloIda(Float.parseFloat(tarifa),
                    Float.parseFloat(impuesto), cod, origen, destino, fSalida,
                    hSalida, hLlegada, hEmbarque, oferta,
                    Float.parseFloat(precio), avion);
            Modificar.VuelosIda(vi);
        } else {
            VueloIdaVuelta vv = new VueloIdaVuelta(Float.parseFloat(tarifa),
                    Float.parseFloat(impuesto), cod, origen, destino, fSalida,
                    hSalida, hLlegada, hEmbarque, oferta,
                    Float.parseFloat(precio), avion);
            Modificar.VuelosVuelta(vv);
        }
        limpiarEntry();
    }

    @FXML
    private void borrar(MouseEvent event) {
        String cod = codVuelo.getText();
        Bajas.vuelo(cod);
        actualizarLista();
        actualizarContador();
        limpiarEntry();
    }

    @FXML
    private void seleccion_item(MouseEvent event) {
        item = listaVuelos.getSelectionModel().getSelectedItem();
        indice = listaVuelos.getSelectionModel().getSelectedIndex();
        switch (item.charAt(0)) {
            case 'I':
                VueloIda vi = Recuperar.vueloIda(item);
                cargarEntry(vi);
                break;
            case 'V':
                VueloIdaVuelta vv = Recuperar.vueloIdaVuelta(item);
                cargarEntry(vv);
                break;
        }
    }

    public void cargarEntry(VueloIda v) {
        rbIda.setSelected(true);
        codVuelo.setText(v.getCodigoVuelo());
        precioVuelo.setText(String.valueOf(v.getPrecio()));
        if (v.isOferta()) {
            rbSi.setSelected(true);
        } else {
            rbNo.setSelected(true);
        }
        origenVuelo.setText(v.getOrigen());
        destinoVuelo.setText(v.getDestino());
        pickerFSalida.setValue(v.getFechaSalida().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate());
        pickerHLlegada.setValue(v.getHoraLlegada().toLocalTime());
        pickerHSalida.setValue(v.getHoraSalida().toLocalTime());
        tarifaVuelo.setText(String.valueOf(v.getTarifa()));
        impuestoVuelo.setText(String.valueOf(v.getImpuesto()));
        cbAvion.getSelectionModel().select(String.valueOf(v.getAvion().getIdAvion()));
    }

    public void cargarEntry(VueloIdaVuelta v) {
        rbIdayVuelta.setSelected(true);
        codVuelo.setText(v.getCodigoVuelo());
        precioVuelo.setText(String.valueOf(v.getPrecio()));
        if (v.isOferta()) {
            rbSi.setSelected(true);
        } else {
            rbNo.setSelected(true);
        }
        origenVuelo.setText(v.getOrigen());
        destinoVuelo.setText(v.getDestino());
        pickerFSalida.setValue(v.getFechaSalida().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate());
        pickerHLlegada.setValue(v.getHoraLlegada().toLocalTime());
        pickerHSalida.setValue(v.getHoraSalida().toLocalTime());
        tarifaVuelo.setText(String.valueOf(v.getTarifa()));
        impuestoVuelo.setText(String.valueOf(v.getImpuesto()));
        cbAvion.getSelectionModel().select(String.valueOf(v.getAvion().getIdAvion()));
    }

    public void limpiarEntry() {
        rbIda.setSelected(true);
        codVuelo.setText("");
        precioVuelo.setText("");
        rbNo.setSelected(true);
        origenVuelo.setText("");
        destinoVuelo.setText("");
        pickerFSalida.setValue(LocalDate.now());
        pickerHLlegada.setValue(LocalTime.now());
        pickerHSalida.setValue(LocalTime.now());
        tarifaVuelo.setText("");
        impuestoVuelo.setText("");
    }

    public void actualizarContador() {
        txtContador_Aeropuertos.setText(Contar.Aeropuertos());
        txtContador_Aerolineas.setText(Contar.Aerolineas());
        txtContador_Aviones.setText(Contar.Aviones());
        txtContador_Vuelos.setText(Contar.Vuelos());
    }

    public void actualizarLista() {
        listaVuelos.getItems().clear();
        listaVuelos.getItems().addAll(CargarDatos.listaVuelos());
    }

    public static Date ParseFecha(String fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fecha);
        } catch (ParseException ex) {
            System.out.println(ex);
        }
        return fechaDate;
    }

    public static Time ParseTime(String hora) {
        DateFormat df = new SimpleDateFormat("HH:mm");
        java.sql.Time horaTime = null;
        try {
            horaTime = new java.sql.Time(df.parse(hora).getTime());
        } catch (ParseException ex) {
            System.out.println(ex);
        }
        return horaTime;
    }

    public static Time CalcularEmbarque(Time hora) {
        long horaMilis = hora.getTime();
        horaMilis = horaMilis - 1800000;
        Time horaEmbarque = new Time(horaMilis);
        return horaEmbarque;
    }

    public void fillCombo() {
        ArrayList<String> codigos = new ArrayList<String>();
        codigos = CargarDatos.listaAviones();
        cbAvion.getItems().addAll(codigos);
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
            resultado = Busqueda.Vuelos(busqueda);
            if (!resultado.isEmpty()) {
                listaVuelos.getItems().clear();
                listaVuelos.getItems().addAll(resultado);
            } else {
                listaVuelos.getItems().clear();
            }
        } else {
            listaVuelos.getItems().clear();
            listaVuelos.getItems().addAll(CargarDatos.listaVuelos());
        }
    }

}
