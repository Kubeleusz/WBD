package wbdapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Saelic
 */
public class WBDApplication extends Application {
    private static boolean permission = false;
    private static Stage stage;
    
    @Override
    public void start(Stage stag) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/login/FXMLLogin.fxml"));
        
        stage = stag;
        Scene scene = new Scene(root);
        stag.setResizable(false);
        stag.setScene(scene);
        stag.show();
    }

    public static void main(String[] args) 
    {
        launch(args);
    }
    
    public static void setPermission(boolean perm){
        permission = perm;
    }
    
    public static Stage getStage(){
        return stage;
    }

}
