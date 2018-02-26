/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bonplan;

import Services.ServiceUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * FXML Controller class
 *
 * @author Mahdi
 */
public class InscriptionController implements Initializable {

    @FXML
    private JFXButton Retour;
    @FXML
    private JFXTextField nomC;
    @FXML
    private JFXTextField prenomC;
    @FXML
    private JFXTextField mailC;
    @FXML
    private JFXTextField usernameC;
    @FXML
    private JFXPasswordField mdpC;
    @FXML
    private JFXTextField codePosC;
    @FXML
    private JFXTextField addresseC;
    @FXML
    private JFXTextField telC;
    @FXML
    private JFXButton valider;
    @FXML
    private JFXCheckBox homme;
    @FXML
    private JFXCheckBox femme;
    @FXML
    private Label msg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void retour_click(ActionEvent event) throws IOException {
        Stage stage = new Stage();
       Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
      
        Scene scene = new Scene(root);
      
      stage.setScene(scene);  
        stage.show();
        stage.setMaximized(true);
        
        Stage stage2 = (Stage) Retour.getScene().getWindow(); 
        stage2.close();
    }

    @FXML
    private void valider_click(ActionEvent event) throws SQLException, IOException {
        if(homme.isSelected()){
            String sexe="Homme";
        }else if (femme.isSelected()){
            String sexe="Femme";
            
        }
        
        int tel=Integer.parseInt(telC.getText());
if ((nomC.getText().isEmpty())||(prenomC.getText().isEmpty())||(mailC.getText().isEmpty())|| (usernameC.getText().isEmpty())|| (mdpC.getText().isEmpty())){
            msg.setText("Remplir tous les champs");
        }else{
            
            ServiceUser se= new ServiceUser();
            se.ajouterUserAns(nomC.getText(),prenomC.getText(),mailC.getText(),tel,usernameC.getText(),mdpC.getText());
            //api sms
            /*try {
			// Construct data
			/*String apiKey = "apikey=Yalfd/Icifs-mQQi9nC9UEURWhz2aANcHQpxvtN5Ab" ;
			String message = "&message=Vous etes inscrit avec succes" ;
			String sender = "&sender=BonPlans" ;
			String numbers = "&numbers=+21654427038"  ;
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
			
			//return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			//return "Error "+e;
		}*/

            //
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("AcceuilClient.fxml"));
            
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
            stage.setMaximized(true);
            
            Stage stage2 = (Stage) valider.getScene().getWindow();
            stage2.close();
            
          
        }
    }

    @FXML
    private void hommeSel() {
        if(homme.isSelected()){
            femme.setSelected(false);
            String sexe="Femme";
        }
    }

    @FXML
    private void femmeSel() {
        if(femme.isSelected()){
            homme.setSelected(false);
        }
    }
    
}
