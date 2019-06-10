package update;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import wbdapplication.DBConnection;
import wbdapplication.Employee;

public class FXMLUpdateController implements Initializable {

    @FXML
    private TextField nameTextField;

    @FXML
    private Button updateButton;
    
    private Connection connection;
    
    public void buttonUpdateOnAction(ActionEvent action)
    {
        String name;
        
        Integer result;
        
        int employeeId = wbdapplication.FXMLDocumentController.employeeId;

        name = nameTextField.getText().trim();
        
        String command = "\"Imie\" = '" + name + "'";
        
        if(name.length() == 0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Text fields cannot be empty!");
            alert.showAndWait();
            return;
        }
        
        try
        {
            connection = DBConnection.getConnection();
            result = new Employee().updateEmployee(connection, employeeId, command);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Record has been updated.");
            alert.showAndWait();
            
        }
        catch(NumberFormatException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error during adding new employee");
            alert.showAndWait();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
    }  
}
