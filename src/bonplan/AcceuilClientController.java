/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bonplan;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author a7med
 */
public class AcceuilClientController implements Initializable {



    @FXML
    private Button deconnect;
    @FXML
    private Button esPerso;
 
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void sedeConnecter(ActionEvent event) {
    }

    @FXML
    private void esPerso(ActionEvent event) throws IOException {
              Stage stage = new Stage();
       Parent root = FXMLLoader.load(getClass().getResource("EsPerso.fxml"));
      
        Scene scene = new Scene(root);
      
      stage.setScene(scene);  
        stage.show();
        stage.setMaximized(true);
        
        Stage stage2 = (Stage) esPerso.getScene().getWindow(); 
        stage2.close();
    }
    
}
