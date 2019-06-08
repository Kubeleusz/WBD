/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wbdapplication;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Saelic
 */
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

    public void buttonSearchOnAction(ActionEvent action)
    {
        //connection = DBConnection.getConnection();
        
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
