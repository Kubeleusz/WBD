package wbdapplication;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

public class Remuneration 
{
    private Integer remunerationId;
    public String cash;
    ObservableList<Remuneration> remunarationList = FXCollections.observableArrayList();

    //Zwraca ID i kwoty wynagrodzeń
    public ObservableList<Remuneration> getRemunerations(Connection connection)
    {
        String sqlCommand = "SELECT \"ID_Wynagrodzenie\", \"Kwota\" FROM \"Wynagrodzenia\"";
        
        Statement s;
        ResultSet rS;
        
        try
        {
            s = connection.prepareStatement(sqlCommand);
            
            rS = s.executeQuery(sqlCommand);
            
            while(rS.next())
            {
                Remuneration remuneration = new Remuneration();
                remuneration.remunerationId = rS.getInt(1);
                remuneration.cash = rS.getString(2);
                
                remunarationList.add(remuneration);
            }
        }
        catch(SQLException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error with data access");
            alert.setContentText("Details: " + e.getMessage());
            alert.showAndWait();
        }
        
        return remunarationList;  
    }
    
    public String getCash()
    {
        return cash;
    }
    
    public static ObservableList<String> getCashList(ObservableList<Remuneration> list)
    {
        ObservableList<String> cashList = FXCollections.observableArrayList();
        for(Remuneration r : list)
        {
            cashList.add(r.getCash());
        }
        return cashList;
    }
}
