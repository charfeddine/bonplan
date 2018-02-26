package bonplan;

import DBAccess.DataSource;
import Services.ServiceUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import com.jfoenix.controls.JFXButton;
import entity.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController implements Initializable {
static int id_u=-1 ; 
static User u ;
    @FXML
    private JFXButton inscrit;
    @FXML
     private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Label check;
    @FXML
    private JFXButton Retour;
    @FXML
    private JFXButton connect;
    @FXML
    private JFXButton admin;
    @FXML
    private Label vérifier;
    @FXML
    private AnchorPane chargement;
    @FXML
    private JFXButton mdpOub;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void inscrit_click(ActionEvent event) throws IOException {
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
    private void retour_click(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        stage.setMaximized(true);

        Stage stage2 = (Stage) Retour.getScene().getWindow();
        stage2.close();
    }

    @FXML
    private User connect_click(ActionEvent event) throws IOException {
        DataSource ds = DataSource.getInstance();

        Services.ServiceUser uService = new ServiceUser();
          u = uService.getUtilisateurByLoginPassword(username.getText(), password.getText());
        if (u != null) {
            System.out.println(u.getType());
             if (u.getType().equals("1")) {
               if (u.getEtat_compte().equals("vérifier")) {
                   id_u=u.getId_u();
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("AcceuilClient.fxml"));

                    Scene scene = new Scene(root);

                    stage.setScene(scene);
                    stage.show();
                    stage.setMaximized(true);

                    Stage stage2 = (Stage) connect.getScene().getWindow();
                    stage2.close();
               } else {
                    vérifier.setText("compte désactivé");
                }
                } 
            else {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("Admin.fxml"));

                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.show();
                stage.setMaximized(true);

                Stage stage2 = (Stage) connect.getScene().getWindow();
                stage2.close();
            }
        } else {
            vérifier.setText("Username ou mot de passe incorrecte");

        }
        return u;
    }

    @FXML
    private void mdpOub_click(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("MdpOub.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        stage.setMaximized(true);

        Stage stage2 = (Stage) mdpOub.getScene().getWindow();
        stage2.close();
    }

}
