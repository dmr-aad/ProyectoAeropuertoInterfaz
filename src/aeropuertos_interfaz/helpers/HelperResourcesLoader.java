/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeropuertos_interfaz.helpers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 *
 * @author a18danielmr
 */
public class HelperResourcesLoader {
    
    private HelperResourcesLoader() {
        
    }
    /*Metodo para cargar las ventanas*/
    public static Parent loadFXML(String resource) {
        Parent root = null;
        try {
            root = new FXMLLoader().load(new HelperResourcesLoader().getClass().getResource("/aeropuertos_interfaz/views"
                    + resource + ".fxml"));
        } catch (IOException ex) {
            
        }
        return root;
    }
}
