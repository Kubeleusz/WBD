package wbdapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.scene.control.Alert;

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
