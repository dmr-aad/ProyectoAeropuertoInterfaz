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
import java.net.URL;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
    private TextField diaSalidaVuelo;
    @FXML
    private TextField mesSalidaVuelo;
    @FXML
    private TextField añoSalidaVuelo;
    @FXML
    private TextField horaSalidaVuelo;
    @FXML
    private TextField minutoSalidaVuelo;
    @FXML
    private TextField horaLlegadaVuelo;
    @FXML
    private TextField minutoLlegadaVuelo;
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
        String fechaSalida = diaSalidaVuelo.getText() + "/"
                + mesSalidaVuelo.getText() + "/"
                + añoSalidaVuelo.getText();
        Date fSalida = ParseFecha(fechaSalida);
        String horaSalida = horaSalidaVuelo.getText() + ":"
                + minutoSalidaVuelo.getText();
        Time hSalida = ParseTime(horaSalida);
        String horaLlegada = horaLlegadaVuelo.getText() + ":"
                + minutoLlegadaVuelo.getText();
        Time hLlegada = ParseTime(horaLlegada);
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
            Altas.altasVuelosIda(vi, avion);
        } else {
            VueloIdaVuelta vv = new VueloIdaVuelta(Float.parseFloat(tarifa),
                    Float.parseFloat(impuesto), cod, origen, destino, fSalida,
                    hSalida, hLlegada, hEmbarque, oferta,
                    Float.parseFloat(precio), avion);
            Altas.altasVuelosVuelta(vv, avion);
        }
        actualizarLista();
        actualizarContador();
        limpiarEntry();
    }

    @FXML
    private void modificar(MouseEvent event) {
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
        String fechaSalida = diaSalidaVuelo.getText() + "/"
                + mesSalidaVuelo.getText() + "/"
                + añoSalidaVuelo.getText();
        Date fSalida = ParseFecha(fechaSalida);
        String horaSalida = horaSalidaVuelo.getText() + ":"
                + minutoSalidaVuelo.getText();
        Time hSalida = ParseTime(horaSalida);
        String horaLlegada = horaLlegadaVuelo.getText() + ":"
                + minutoLlegadaVuelo.getText();
        Time hLlegada = ParseTime(horaLlegada);
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

    @SuppressWarnings("deprecation")
    public void cargarEntry(VueloIda v) {
        rbIda.setSelected(true);
        codVuelo.setText(v.getCodigoVuelo());
        precioVuelo.setText(String.valueOf(v.getPrecio()));
        if(v.isOferta()) {
            rbSi.setSelected(true);
        } else {
            rbNo.setSelected(true);
        }
        origenVuelo.setText(v.getOrigen());
        destinoVuelo.setText(v.getDestino());
        Calendar c = Calendar.getInstance();
        c.setTime(v.getFechaSalida());
        diaSalidaVuelo.setText(String.valueOf(c.get(Calendar.DAY_OF_MONTH)));
        mesSalidaVuelo.setText(String.valueOf(c.get(Calendar.MONTH)));
        añoSalidaVuelo.setText(String.valueOf(c.get(Calendar.YEAR)));
        horaSalidaVuelo.setText(String.valueOf(v.getHoraSalida().getHours()));
        minutoSalidaVuelo.setText(String.valueOf(v.getHoraSalida().getMinutes()));
        horaLlegadaVuelo.setText(String.valueOf(v.getHoraLlegada().getHours()));
        minutoLlegadaVuelo.setText(String.valueOf(v.getHoraLlegada().getMinutes()));
        tarifaVuelo.setText(String.valueOf(v.getTarifa()));
        impuestoVuelo.setText(String.valueOf(v.getImpuesto()));
    }

    @SuppressWarnings("deprecation")
    public void cargarEntry(VueloIdaVuelta v) {
        rbIdayVuelta.setSelected(true);
        codVuelo.setText(v.getCodigoVuelo());
        precioVuelo.setText(String.valueOf(v.getPrecio()));
        if(v.isOferta()) {
            rbSi.setSelected(true);
        } else {
            rbNo.setSelected(true);
        }
        origenVuelo.setText(v.getOrigen());
        destinoVuelo.setText(v.getDestino());
        Calendar c = Calendar.getInstance();
        c.setTime(v.getFechaSalida());
        diaSalidaVuelo.setText(String.valueOf(c.get(Calendar.DAY_OF_MONTH)));
        mesSalidaVuelo.setText(String.valueOf(c.get(Calendar.MONTH)));
        añoSalidaVuelo.setText(String.valueOf(c.get(Calendar.YEAR)));
        horaSalidaVuelo.setText(String.valueOf(v.getHoraSalida().getHours()));
        minutoSalidaVuelo.setText(String.valueOf(v.getHoraSalida().getMinutes()));
        horaLlegadaVuelo.setText(String.valueOf(v.getHoraLlegada().getHours()));
        minutoLlegadaVuelo.setText(String.valueOf(v.getHoraLlegada().getMinutes()));
        tarifaVuelo.setText(String.valueOf(v.getTarifa()));
        impuestoVuelo.setText(String.valueOf(v.getImpuesto()));
    }
    
    public void limpiarEntry() {
        rbIda.setSelected(true);
        codVuelo.setText("");
        precioVuelo.setText("");
        rbNo.setSelected(true);
        origenVuelo.setText("");
        destinoVuelo.setText("");
        diaSalidaVuelo.setText("");
        mesSalidaVuelo.setText("");
        añoSalidaVuelo.setText("");
        horaSalidaVuelo.setText("");
        minutoSalidaVuelo.setText("");
        horaLlegadaVuelo.setText("");
        minutoLlegadaVuelo.setText("");
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
