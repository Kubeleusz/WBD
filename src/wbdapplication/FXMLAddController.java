package wbdapplication;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class FXMLAddController  {

    @FXML
    private AnchorPane root;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField surnameTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private TextField eMailAddressTextField;

    @FXML
    private TextField bankAccountNumberTextField;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    @FXML
    private ChoiceBox<String> remunerationChoiceBox;
    
    Remuneration remuneration = new Remuneration();
    
    //DO CHOICE BOXA
    private ObservableList<Remuneration> remunerationList = FXCollections.observableArrayList();
    
    private Connection connection;
    
    
    public void buttonAddOnAction(ActionEvent action)
    {
        Integer remunerationId;
        String name;
        String surname;
        String phoneNumber;
        String eMailAddress;
        String bankAccountNumber;
                
        Integer result;
        
        name = nameTextField.getText().trim();
        surname = surnameTextField.getText().trim();
        phoneNumber = phoneNumberTextField.getText().trim();
        eMailAddress = eMailAddressTextField.getText().trim();
        bankAccountNumber = bankAccountNumberTextField.getText().trim();
        
        if(name.length() == 0 || surname.length() == 0 || phoneNumber.length() == 0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Text fields cannot be empty!");
            alert.showAndWait();
            return;
        }
        
        try
        {
            connection = DBConnection.getConnection();
            result = new Employee().addEmployee(connection, 5, 1, 1, 1, name, surname, phoneNumber, eMailAddress, bankAccountNumber);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Employee has been added");
            alert.showAndWait();
            
        }
        catch(NumberFormatException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error during adding new employee");
            alert.showAndWait();
        }
    } 
    
    //DO CHOICE BOXA
    @FXML
    private void initialize()
    {
        connection = DBConnection.getConnection();
        
        remunerationList = remuneration.getRemunerations(connection);
        remunerationChoiceBox.setItems(Remuneration.getCashList(remunerationList));
    }
}
