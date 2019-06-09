/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
/**
 *
 * @author admina
 */
public class FXMLLoginController implements Initializable{
    @FXML
    private TextField login_field;

    @FXML
    private ImageView login_picture;

    @FXML
    private Label login_label;

    @FXML
    private Label password_label;

    @FXML
    private PasswordField password_field;

    @FXML
    private Button login_button;

    @FXML
    private Label error_label;

    @FXML
    private Label welcome_label;

    @FXML
    private Label please_label;
    
    Connection conn;
    
    @Override
    public void initialize(URL url, ResourceBundle bundle){
      conn = wbdapplication.DBConnection.getConnection();
    }
    
    private String doLogin(Connection conn, String login, String password){
        String sql = "SELECT user_access from LOGINS where login='" + login +"' and password='" + password + "'";
        Statement stms;
        ResultSet rs;
        
        try{
        stms = conn.createStatement();
        rs = stms.executeQuery(sql);
        
        return rs.getString(1);
        }
        catch(SQLException exc){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error with login");
            alert.setContentText("Detail: "+exc.getMessage());
            alert.showAndWait();
        }
        return "";
    } 
    
    public void buttonLoginOnAction(ActionEvent event){
        String login;
        String password;
        String access;
        
        login = login_field.getText().trim();
        if(login.length() == 0){
            error_label.setText("Login can't be empty!!!");
            error_label.setVisible(true);
            return;
        }
        
        password = password_field.getText().trim();
        if(password.length() == 0){
            error_label.setText("Password can't be empty!!!");
            error_label.setVisible(true);
            return;
        }
        
        try{
        if(!(access=doLogin(conn, login, password)).equals("")){
                if (access.equals("admin")){
                    wbdapplication.WBDApplication.setPermission(true);
                    Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                    Stage stage = wbdapplication.WBDApplication.getStage();
                    stage.hide();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                else if(access.equals("user")){
                    wbdapplication.WBDApplication.setPermission(false);
                    Parent root = FXMLLoader.load(getClass().getResource(""));
                    Stage stage = wbdapplication.WBDApplication.getStage();
                    stage.hide();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
        }
        else{
            error_label.setText("Wrong credentials!!!");
            error_label.setVisible(true);
        }
        } catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error during login");
            alert.setContentText("Undefinied error. Please try again");
        }
    }

}
