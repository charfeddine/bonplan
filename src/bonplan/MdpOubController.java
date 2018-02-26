package bonplan;

import DBAccess.DataSource;
import Services.ServiceUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entity.User;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class MdpOubController implements Initializable{
    
    @FXML
    private JFXButton inscrit;
    Label check;    
    @FXML
    private JFXButton Retour;
    @FXML
    private JFXButton connect;
    private JFXButton admin;
    @FXML
    private Label vérifier;
    @FXML
    private JFXTextField mail;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void inscrit2_click(ActionEvent event) throws IOException {
        Stage stage = new Stage();
       Parent root = FXMLLoader.load(getClass().getResource("Inscription.fxml"));
      
        Scene scene = new Scene(root);
      
      stage.setScene(scene);  
        stage.show();
        stage.setMaximized(true);
        
        Stage stage2 = (Stage) inscrit.getScene().getWindow(); 
        stage2.close();
    }

    @FXML
    private void retour_à_la_login_click(ActionEvent event) throws IOException {
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
    private User connect2_click(ActionEvent event) throws IOException, AddressException, MessagingException  {
        DataSource ds =DataSource.getInstance();
    
         Services.ServiceUser uService = new ServiceUser();
          User u=uService.getUtilisateurByMail(mail.getText());
          if (u!=null ){     
                vérifier.setText("Mail correcte");
                String host ="smtp.gmail.com";
                String user ="znaidimahdi93@gmail.com";
                String pass ="zapabenarous";
                String from ="znaidimahdi93@gmail.com";
                String to =mail.getText();
                String subject ="Consultation du mot de passe ";
                String messageText ="Ton mot de passe est : "+u.getPassword()+"\n Ton username est :"+u.getLogin()+"";
                boolean sessionDebug =false ;
                
                Properties props = System.getProperties();
                
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", host);
                props.put("mail.smtp.port", "587");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.required", "true");
                
                java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
                Session mailSession =Session.getDefaultInstance(props, null);
                mailSession.setDebug(sessionDebug);
                Message msg = new MimeMessage(mailSession);
                msg.setFrom(new InternetAddress(from));
                InternetAddress[] address = {new InternetAddress(to)};
                msg.setRecipients(Message.RecipientType.TO, address);
                msg.setSubject(subject);
                msg.setSentDate(new Date());
                msg.setText(messageText);
                
                Transport transport =mailSession.getTransport("smtp");
                transport.connect(host, user , pass);
                transport.sendMessage(msg, msg.getAllRecipients());
                transport.close();
                System.out.println("message envoyé");
                
                
                
          }else {
                  vérifier.setText("Mail incorrecte");

          }
          return u;
    }

}
