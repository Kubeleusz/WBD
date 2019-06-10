/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import wbdapplication.Employee;

/**
 *
 * @author admina
 */
public class Client {
    private int ID;
    private String name;
    private String surname;
    private String IDNumber;
    private String street;
    private int houseNumber;
    private String postCode;
    private String city;
    private String email;
    private String phone;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public String getIDNumber() {
        return IDNumber;
    }

    public void setIDNumber(String IDNumber) {
        this.IDNumber = IDNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    //Zwraca całą zawartość tabeli Client
    public ObservableList<Client> getAll(Connection connection)
    {
        ObservableList<Client> clients = FXCollections.observableArrayList();
        
        String sqlCommand = "SELECT \"ID_Klient\", \"Imie\", "
                + "\"Nazwisko\", \"Numer_dowodu\", \"Ulica\", \"Numer_domu\", \"Kod_pocztowy\", \"Miasto\", \"Adres_e_mail\", \"Numer_telefonu\" "
                + "FROM \"Kliecnci\" ORDER BY \"ID_Pracownik\"";
        
        Statement s;
        ResultSet rS;
        
        try
        {
            s = connection.createStatement();
            rS = s.executeQuery(sqlCommand);
            
            while(rS.next())
            {
                Client client = new Client();
                client.ID = rS.getInt(1);
                client.name = rS.getString(2);
                client.surname = rS.getString(3);
                client.IDNumber = rS.getString(4);
                client.street = rS.getString(5);
                client.houseNumber = rS.getInt(6);
                client.postCode = rS.getString(7);
                client.city = rS.getString(8);
                client.email = rS.getString(9);
                client.phone = rS.getString(10);
                clients.add(client);
            }
        }
        catch(SQLException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error with data access");
            alert.setContentText("Details: " + e.getMessage());
            alert.showAndWait();
        }
        return clients;     
    }
    
    public ObservableList<Client> getRestrictedList(Connection connection, String word)
    {
        ObservableList<Client> clients = FXCollections.observableArrayList();
        String sqlCommand = "SELECT \"ID_Klient\", \"Imie\", "
                + "\"Nazwisko\", \"Numer_dowodu\", \"Ulica\", \"Numer_domu\", \"Kod_pocztowy\", \"Miasto\", \"Adres_e_mail\", \"Numer_telefonu\" "
                + "FROM \"Kliecnci\" WHERE upper(\"Imie\") LIKE ? OR "
                + "upper(\"Nazwisko\") LIKE ? "
                + "ORDER BY \"ID_Klient\"";
        
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
                Client client = new Client();
                client.ID = rS.getInt(1);
                client.name = rS.getString(2);
                client.surname = rS.getString(3);
                client.IDNumber = rS.getString(4);
                client.street = rS.getString(5);
                client.houseNumber = rS.getInt(6);
                client.postCode = rS.getString(7);
                client.city = rS.getString(8);
                client.email = rS.getString(9);
                client.phone = rS.getString(10);
                clients.add(client);
            }
        }
        catch(SQLException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error with data access");
            alert.setContentText("Details: " + e.getMessage());
            alert.showAndWait();
        }
        return clients;
    }
    
    //Dodaje rekord
    public int addClient(Connection connection, Integer clientId,
                             String name, String surname, String IDnumber, String street, int homeNumber, 
                             String post, String city, String email, String phone)
    {
        String sqlCommand = "INSERT INTO \"Pracownicy\" VALUES (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pS;
        Integer res = 0;
        
        try
        {
            pS = connection.prepareStatement(sqlCommand);
            pS.setInt(1, clientId);
            pS.setString(2, name);
            pS.setString(3, surname);
            pS.setString(4, IDnumber);
            pS.setString(5, street);
            pS.setInt(6, homeNumber);
            pS.setString(7, post);
            pS.setString(8, city);
            pS.setString(9, email);
            pS.setString(10, phone);
            
            res = pS.executeUpdate();
        }
        catch(SQLException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error with data insert");
            alert.setContentText("Details: " + e.getMessage());
            alert.showAndWait();
        } 
        return res;
    }
    
    //Usuwa rekord
    public int removeClient(Connection connection, int clientId)
    {
        String sqlCommand = "DELETE FROM \"Klienci\" WHERE \"ID_Klient\" = ?";
        PreparedStatement pS;
        Integer res = 0;
        
        try
        {
            pS = connection.prepareStatement(sqlCommand);
            pS.setInt(1, clientId);
            
            res = pS.executeUpdate();
        }
        catch(SQLException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error during deleting record");
            alert.setContentText("Details: " + e.getMessage());
            alert.showAndWait();
        }
        
        return res;
    }
    
}
