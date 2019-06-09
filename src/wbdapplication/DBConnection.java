/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wbdapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.scene.control.Alert;

/**
 *
 * @author Saelic
 */
public class DBConnection 
{
    private static Connection connection;
    
    public static Connection getConnection()
    {
        String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
        String USER = "Kubeleusz";
        String PASSWORD = "user";
        
        try
        {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Alert alert = new Alert((Alert.AlertType.INFORMATION));
            alert.setContentText("You have been succesfully connected to database.");
            alert.show();    
        }
        catch( SQLException e )
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Connection error");
            alert.setContentText("Details: " + e.getMessage());
            alert.show();
        }
        
        return connection;
    }
    
    public static Connection getEstablishedConnection(){
        return connection;
    }
}
