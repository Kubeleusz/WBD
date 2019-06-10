/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import wbdapplication.DBConnection;
import wbdapplication.Employee;

/**
 * FXML Controller class
 *
 * @author admina
 */
public class FXMLUserAddController implements Initializable {
    
    
    @FXML
    private Label name_label;

    @FXML
    private Label surname_label;

    @FXML
    private Label id_label;

    @FXML
    private Label street_label;

    @FXML
    private Label house_number_label;

    @FXML
    private Label post_code_label;

    @FXML
    private Label city_label;

    @FXML
    private Label e_mail_label;

    @FXML
    private Label phone_label;

    @FXML
    private TextField client_name_text;

    @FXML
    private TextField client_surname_text;

    @FXML
    private TextField client_ID_text;

    @FXML
    private TextField client_street_text;

    @FXML
    private TextField client_house_text;

    @FXML
    private TextField client_post_code_text;

    @FXML
    private TextField client_city_text;

    @FXML
    private TextField client_e_mail_text;

    @FXML
    private TextField client_phone_text;

    @FXML
    private Button add_button;

    @FXML
    private Button modify_button;

    @FXML
    private ImageView add_png;
    
    private Connection connection;
    private Stage stage;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connection = DBConnection.getEstablishedConnection();
        stage = FXMLUserController.stage;
        add_button.setVisible(FXMLUserController.add_status);
        modify_button.setVisible(FXMLUserController.edit_status);
    }    
    
    public void buttonAddOnAction(ActionEvent action)
    {
        Client client = new Client();
        client.setName(client_name_text.getText().trim());
        client.setSurname(client_surname_text.getText().trim());
        client.setIDNumber(client_ID_text.getText().trim());
        client.setStreet(client_street_text.getText().trim());
        client.setHouseNumber(Integer.parseInt(client_house_text.getText().trim()));
        client.setPostCode(client_post_code_text.getText().trim());
        client.setCity(client_city_text.getText().trim());
        client.setEmail(client_e_mail_text.getText().trim());
        client.setPhone(client_phone_text.getText().trim());
        
        if(client.getName().length() == 0 || client.getSurname().length()== 0 || client.getIDNumber().length() == 0
                || client.getStreet().length() == 0 || client.getHouseNumber() == 0 || client.getPostCode().length() == 0 
                || client.getPostCode().length() == 0 || client.getCity().length() == 0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Text fields cannot be empty!");
            alert.showAndWait();
            return;
        }
        
        try
        {/////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
           new Client().addClient(connection,3, client.getName(), client.getSurname(), 
                client.getIDNumber(), client.getStreet(), client.getHouseNumber(), client.getPostCode(),
                client.getCity(), client.getEmail(), client.getPhone());
            
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setContentText("Employee has been added");
           alert.showAndWait();
           stage.close();
        }
        catch(NumberFormatException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error during adding new employee");
            alert.showAndWait();
        }
    } 
    
}
