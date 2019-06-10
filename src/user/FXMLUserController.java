/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.net.URL;
import java.sql.Connection;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import wbdapplication.DBConnection;
import wbdapplication.Employee;

/**
 * FXML Controller class
 *
 * @author admina
 */
public class FXMLUserController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        connection = DBConnection.getEstablishedConnection();
        clientList = client.getAll(connection);
        setTableVievClient(clientList);
    }    
    
    public static Stage stage;
    private Connection connection;
    Client client = new Client();
    private ObservableList<Client> clientList = FXCollections.observableArrayList();
    public static boolean add_status;
    public static boolean edit_status;
    
      @FXML
    private MenuBar menu_bar;

    @FXML
    private Menu menu_options;

    @FXML
    private MenuItem menu_item_account;

    @FXML
    private TableView<Client> table_view;

    @FXML
    private TableColumn<Client, Integer> tableColumnID;

    @FXML
    private TableColumn<Client, String> tableColumnName;

    @FXML
    private TableColumn<Client, String> tableColumnSurname;

    @FXML
    private TableColumn<Client, String> tableColumnIDnumber;

    @FXML
    private TableColumn<Client, String> tableColumnStreet;

    @FXML
    private TableColumn<Client, Integer> tableColumnHouse;

    @FXML
    private TableColumn<Client, String> tableColumnPost;

    @FXML
    private TableColumn<Client, String> tableColumnCity;

    @FXML
    private TableColumn<Client, String> tableColumnMail;

    @FXML
    private TableColumn<Client, String> tableColumnPhone;

    @FXML
    private TextField search_field;

    @FXML
    private Button search_button;

    @FXML
    private Label label_enter;

    @FXML
    private Button edit_button;

    @FXML
    private Button delete_button;

    @FXML
    private Label modify_label;

    @FXML
    private Button add_button;

    @FXML
    private Label add_label;
    
    private void setTableVievClient(ObservableList<Client> eL)
    {
        tableColumnID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumnSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        tableColumnIDnumber.setCellValueFactory(new PropertyValueFactory<>("IDNumber"));
        tableColumnStreet.setCellValueFactory(new PropertyValueFactory<>("street"));
        tableColumnHouse.setCellValueFactory(new PropertyValueFactory<>("houseNumber"));
        tableColumnPost.setCellValueFactory(new PropertyValueFactory<>("postCode"));
        tableColumnCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        tableColumnMail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tableColumnPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        
        table_view.setItems(eL);
    }
    
    //Definiuje zachowanie przycisku Add
    public void buttonAddOnAction(ActionEvent action) throws Exception
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/user/FXMLUserAdd.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        add_status = true;
        edit_status = false;
        stage = new Stage();
        stage.setTitle("Add new client");
        stage.setScene(new Scene(root));
        stage.showAndWait();
        
        clientList= new Client().getRestrictedList(connection, "");
        setTableVievClient(clientList);
    }
    
    //Definiuje zachowanie przycisku Delete
    public void buttonDeleteOnAction(ActionEvent action)
    {
        Integer rowIndex = table_view.getSelectionModel().getSelectedIndex();
        
        if(rowIndex < -1)
        {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setContentText("There is no proper record selected!");
            alert1.showAndWait();
        }
        
        Integer clientId = table_view.getSelectionModel().getSelectedItem().getID();
        
        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Confirmation");
        alert2.setContentText("Are you sure you want to delete this item?");
        Optional<ButtonType> res1 = alert2.showAndWait();
                
        if(res1.get() == ButtonType.OK)
        {
            Integer result = new Client().removeClient(connection, clientId);
            
            if(result > 0)
            {
                Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                alert1.setContentText("Record has been removed.");
                alert1.showAndWait();
            }
        }
        
        //Autoodświeżanie listy pracowników
        clientList = new Client().getRestrictedList(connection, "");
        setTableVievClient(clientList);
    }
    

    public void buttonSearchOnAction(ActionEvent action)
    {   
        clientList = new Client().getRestrictedList(connection, search_field.getText().trim());
        
        setTableVievClient(clientList);
    }
    
}
