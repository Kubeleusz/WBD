package wbdapplication;

import java.net.URL;
import java.sql.Connection;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLDocumentController implements Initializable 
{
    @FXML
    private Label label;

    @FXML
    private TableView<Employee> employeeTable;

    @FXML
    private TableColumn<Employee, Integer> tableColumnEmployeeId;

    @FXML
    private TableColumn<Employee, Integer> tableColumnSalePointId;

    @FXML
    private TableColumn<Employee, String> tableColumnName;

    @FXML
    private TableColumn<Employee, String> tableColumnSurname;

    @FXML
    private TableColumn<Employee, String> tableColumnPhoneNumber;

    @FXML
    private TableColumn<Employee, String> tableColumnEMailAdress;

    @FXML
    private TableColumn<Employee, String> tableColumnBankAccountNumber;

    @FXML
    private TextField textField;

    @FXML
    private Button searchButton;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button updateButton;
    
    
    
    private Connection connection;
    Employee employee = new Employee();
    private ObservableList<Employee> employeeList = FXCollections.observableArrayList();

    
    //Definiuje zachowanie przycisku Add
    public void buttonAddOnAction(ActionEvent action) throws Exception
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLAdd.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Add new employee");
        stage.setScene(new Scene(root));
        stage.showAndWait();
        
        employeeList = new Employee().getRestrictedList(connection, "");
        setTableVievEmployee(employeeList);
    }
    
    //Definiuje zachowanie przycisku Delete
    public void buttonDeleteOnAction(ActionEvent action)
    {
        Integer rowIndex = employeeTable.getSelectionModel().getSelectedIndex();
        
        if(rowIndex < -1)
        {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setContentText("There is no proper record selected!");
            alert1.showAndWait();
        }
        
        Integer employeeId = employeeTable.getSelectionModel().getSelectedItem().getEmployeeId();
        
        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Confirmation");
        alert2.setContentText("Are you sure you want to delete this item?");
        Optional<ButtonType> res1 = alert2.showAndWait();
                
        if(res1.get() == ButtonType.OK)
        {
            Integer result = new Employee().removeEmployee(connection, employeeId);
            
            if(result > 0)
            {
                Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                alert1.setContentText("Record has been removed.");
                alert1.showAndWait();
            }
        }
        
        //Autoodświeżanie listy pracowników
        employeeList = new Employee().getRestrictedList(connection, "");
        setTableVievEmployee(employeeList);
    }
    
    public void buttonSearchOnAction(ActionEvent action)
    {   
        employeeList = new Employee().getRestrictedList(connection, textField.getText().trim());
        
        setTableVievEmployee(employeeList);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        connection = DBConnection.getConnection();
        
        employeeList = employee.getAll(connection);
        setTableVievEmployee(employeeList);
    }    
    
    private void setTableVievEmployee(ObservableList<Employee> eL)
    {
        tableColumnEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        tableColumnSalePointId.setCellValueFactory(new PropertyValueFactory<>("salePointId"));
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumnSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        tableColumnPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        tableColumnEMailAdress.setCellValueFactory(new PropertyValueFactory<>("eMailAdress"));
        tableColumnBankAccountNumber.setCellValueFactory(new PropertyValueFactory<>("bankAccountNumber"));
        
        employeeTable.setItems(eL);
    }
}
