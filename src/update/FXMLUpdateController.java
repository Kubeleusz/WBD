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

    @FXML
    private TextField surnameTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private TextField eMailAddressTextField;

    @FXML
    private TextField bankAccountNumberTextField;
    
    private Connection connection;
    
    int employeeId;
    String name;
    String surname;
    String phoneNumber;
    String eMailAddress;
    String bankAccountNumber;
    
    public void buttonUpdateOnAction(ActionEvent action)
    {
        Integer result;
    
        name = nameTextField.getText().trim();
        surname = surnameTextField.getText().trim();
        phoneNumber = phoneNumberTextField.getText().trim();
        eMailAddress = eMailAddressTextField.getText().trim();
        bankAccountNumber = bankAccountNumberTextField.getText().trim();
        
        String command = "\"Imie\" = '" + name + "', \"Nazwisko\" = '" + surname + "', \"Numer_telefonu\" = '" + phoneNumber + "', \"Adre_e-mail\" = '"
                + eMailAddress + "', \"Numer_konta_bankowego\" = '" + bankAccountNumber + "'";
        
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
        employeeId = wbdapplication.FXMLDocumentController.e.getEmployeeId();
        name = wbdapplication.FXMLDocumentController.e.getName();
        surname = wbdapplication.FXMLDocumentController.e.getSurname();
        phoneNumber = wbdapplication.FXMLDocumentController.e.getPhoneNumber();
        eMailAddress = wbdapplication.FXMLDocumentController.e.geteMail();
        bankAccountNumber = wbdapplication.FXMLDocumentController.e.getBankAccountNumber();
        
        nameTextField.setText(name);
        surnameTextField.setText(surname);
        phoneNumberTextField.setText(phoneNumber);
        eMailAddressTextField.setText(eMailAddress);
        bankAccountNumberTextField.setText(bankAccountNumber);
    }  
}
