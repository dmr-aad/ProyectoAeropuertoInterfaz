/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeropuertos_interfaz;

import aeropuertos_interfaz.helpers.CrearBD;
import java.io.IOException;
import java.sql.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author a18danielmr
 */
public class Aeropuertos_Interfaz extends Application {

    static Connection conex;
    static Statement s;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/aeropuertos_interfaz/views/Interfaz.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("AirManagementBD");
        primaryStage.getIcons().add(new Image("/aeropuertos_interfaz/assets/images/app_icon.png"));
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //Configuracion Hibernate
        String url = "jdbc:mysql://localhost:3307/?user=root&password=usbw";
        try {
            conex = DriverManager.getConnection(url);
            s = conex.createStatement();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
            System.exit(1);
        }

        CrearBD.aeropuerto(s);
        
        NewHibernateUtil.getSessionFactory();

        launch(args);
    }
}
