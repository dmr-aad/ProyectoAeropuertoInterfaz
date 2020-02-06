/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeropuertos_interfaz.helpers;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author a18danielmr
 */
public class HelperStage {
    
    private HelperStage() {
        
    }
    /*Método que utiliza una imágen para cerrar una ventana*/
    public static void close(Node node) {
        ImageView img = (ImageView) node;
        Stage window = (Stage) img.getScene().getWindow();
        window.close();
    }
}
