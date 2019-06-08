/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wbdapplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 *
 * @author Saelic
 */
public class Employee 
{
    private Integer employeeId;
    private Integer salePointId;
    private String name;
    private String surname;
    private String phoneNumber;
    private String eMailAdress;
    private String bankAccountNumber;

    //Zwraca całą zawartość tabeli Pracownik
    public ObservableList<Employee> getAll(Connection connection)
    {
        ObservableList<Employee> employeeList = FXCollections.observableArrayList();
        
        String sqlCommand = "SELECT \"ID_Pracownik\", \"ID_Punkt_Sprzedazy\", \"Imie\", "
                + "\"Nazwisko\", \"Numer_telefonu\", \"Adre_e-mail\", \"Numer_konta_bankowego\" "
                + "FROM \"Pracownicy\" ORDER BY \"ID_Pracownik\"";
        
        Statement s;
        ResultSet rS;
        
        try
        {
            s = connection.createStatement();
            rS = s.executeQuery(sqlCommand);
            
            while(rS.next())
            {
                Employee employee = new Employee();
                employee.employeeId = rS.getInt(1);
                employee.salePointId = rS.getInt(2);
                employee.name = rS.getString(3);
                employee.surname = rS.getString(4);
                employee.phoneNumber = rS.getString(5);
                employee.eMailAdress = rS.getString(6);
                employee.bankAccountNumber = rS.getString(7);
                employeeList.add(employee);
            }
        }
        catch(SQLException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error with data access");
            alert.setContentText("Details: " + e.getMessage());
            alert.showAndWait();
        }
        return employeeList;     
    }
    
    public ObservableList<Employee> getRestrictedList(Connection connection, String word)
    {
        ObservableList<Employee> employeeList = FXCollections.observableArrayList();
        String sqlCommand = "SELECT \"ID_Pracownik\", \"ID_Punkt_Sprzedazy\", \"Imie\", "
                + "\"Nazwisko\", \"Numer_telefonu\", \"Adre_e-mail\", \"Numer_konta_bankowego\" "
                + "FROM \"Pracownicy\" WHERE upper(\"Imie\") LIKE ? OR "
                + "upper(\"Nazwisko\") LIKE ? "
                + "ORDER BY \"ID_Pracownik\"";
        
        PreparedStatement pS;
        ResultSet rS;
        
        try
        {
            pS = connection.prepareStatement(sqlCommand);
            pS.setString(1, word.toUpperCase() + "%");
            pS.setString(2, word.toUpperCase() + "%");
            
            rS = pS.executeQuery();
            
            while(rS.next())
            {
                Employee employee = new Employee();
                employee.employeeId = rS.getInt(1);
                employee.salePointId = rS.getInt(2);
                employee.name = rS.getString(3);
                employee.surname = rS.getString(4);
                employee.phoneNumber = rS.getString(5);
                employee.eMailAdress = rS.getString(6);
                employee.bankAccountNumber = rS.getString(7);
                employeeList.add(employee);
            }
        }
        catch(SQLException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error with data access");
            alert.setContentText("Details: " + e.getMessage());
            alert.showAndWait();
        }
        return employeeList;
    }
    
    //Getters and Setters
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getSalePointId() {
        return salePointId;
    }

    public void setSalePointId(int salePointId) {
        this.salePointId = salePointId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String geteMail() {
        return eMailAdress;
    }

    public void seteMailAdress(String eMailAdress) {
        this.eMailAdress = eMailAdress;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }
    
    
}
