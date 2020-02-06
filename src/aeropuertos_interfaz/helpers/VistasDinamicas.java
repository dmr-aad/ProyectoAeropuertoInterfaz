/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeropuertos_interfaz.helpers;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author a18danielmr
 */
public class VistasDinamicas {
    
    private VistasDinamicas() {
        
    }
    /*Metodo que carga las vistas en el panel principal*/
    public static void load_border_center(BorderPane borderPane, String resource){
        try {
            Parent dashboard = FXMLLoader.load(new VistasDinamicas().getClass().getResource("/aeropuertos_interfaz/views/" + resource + ".fxml"));
            borderPane.setCenter(dashboard);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
