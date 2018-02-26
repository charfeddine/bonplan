/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bonplan;

import Services.ServiceUser;
import entity.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Mahdi
 */
public class AdminController implements Initializable {

    @FXML
    private Button esPerso;
    @FXML
    private Button deconnect;
    @FXML
    private TextField nomC;
    @FXML
    private TextField prenomC;
    @FXML
    private TableView<User> Tab_User;
    @FXML
    private TableColumn<User, String> NomC;
    @FXML
    private TableColumn<User, String> PrénomC;
    @FXML
    private TextField idC;
    @FXML
    private Button supprimerAd;
    @FXML
    private Button DébloquerU;
    @FXML
    private Button BloquerU;
    @FXML
    private Button ajoutAd;
    @FXML
    private TableColumn<User, String> TypeC;
    @FXML
    private TableColumn<User, String> etatCompte;
    @FXML
    private TableColumn<User, String> ID;
    @FXML
    private TextField nomP;
    @FXML
    private PieChart statistique;
    @FXML
    private Button ValidePlan;
    @FXML
    private VBox VBoxMain;
    @FXML
    private Label contentheaderv0;
    @FXML
    private StackPane Anchorstac;
    @FXML
    private TableView<User> tableView;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonEdit;
    @FXML
    private Label contentheaderb1;
    @FXML
    private Button buttonDel;
    @FXML
    private Button buttonRefresh;

    @FXML
    private TableColumn<User, Integer> id;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, String> telephone;
    @FXML
    private TableColumn<User, String> adr;
    @FXML
    private TableColumn<User, String> sexe;
    @FXML
    private TableColumn<User, String> type;
    @FXML
    private TableColumn<User, String> nom;
    @FXML
    private TableColumn<User, String> Prenom;
    @FXML
    private TableColumn<User, String> etatcpt;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ServiceUser us = new ServiceUser();

        ObservableList<PieChart.Data> D;
        try {
            D = FXCollections.observableArrayList(
                    new PieChart.Data("Bloquer", us.getEtatBloquer()),
                    new PieChart.Data("Verifier", us.getEtatVerifier())
            );

            statistique.setData(D);

        } catch (Exception ex) {
            System.out.println(ex);

        }
        setNomC(LoginController.u.getNom() + LoginController.u.getPrenom());
        System.out.println("Affichage du tableau utilisateur !");
        ServiceUser se = new ServiceUser();
        //se.selectPub();
        NomC.setCellValueFactory(new PropertyValueFactory<>("nom"));
        PrénomC.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        TypeC.setCellValueFactory(new PropertyValueFactory<>("type"));
        etatCompte.setCellValueFactory(new PropertyValueFactory<>("etat_compte"));
        ID.setCellValueFactory(new PropertyValueFactory<>("id_u"));
        ObservableList<User> data = FXCollections.observableArrayList(se.selectUser());
        Tab_User.setEditable(true);
        Tab_User.setItems(data);
        // TODO

        id.setCellValueFactory(new PropertyValueFactory<>("id_u"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        Prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        adr.setCellValueFactory(new PropertyValueFactory<>("adress"));
        telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        etatcpt.setCellValueFactory(new PropertyValueFactory<>("etat_compte"));

        tableView.setEditable(true);
        tableView.setItems(data);
        //tableView.getItems().setAll(dataps);
    }

    public void setNomC(String user) {
        this.nomP.setText(user);
    }

    @FXML
    private void supprimer_ad(ActionEvent event) throws SQLException {
        ServiceUser se = new ServiceUser();
        se.supprimerA(nomC.getText());

        NomC.setCellValueFactory(new PropertyValueFactory<>("nom"));
        PrénomC.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        TypeC.setCellValueFactory(new PropertyValueFactory<>("type"));
        ObservableList<User> data = FXCollections.observableArrayList(se.selectUser());
        Tab_User.setItems(data);
    }

    @FXML
    private void désigner_ad(ActionEvent event) throws SQLException {
        ServiceUser se = new ServiceUser();
        se.ajouterA(nomC.getText());

        NomC.setCellValueFactory(new PropertyValueFactory<>("nom"));
        PrénomC.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        TypeC.setCellValueFactory(new PropertyValueFactory<>("type"));
        ObservableList<User> data = FXCollections.observableArrayList(se.selectUser());
        Tab_User.setItems(data);
    }

    @FXML
    private void débloquer_click(ActionEvent event) throws SQLException {
        ServiceUser se = new ServiceUser();
        se.débloquerA(nomC.getText());
        NomC.setCellValueFactory(new PropertyValueFactory<>("nom"));
        PrénomC.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        TypeC.setCellValueFactory(new PropertyValueFactory<>("type"));
        ObservableList<User> data = FXCollections.observableArrayList(se.selectUser());
        Tab_User.setItems(data);
    }

    @FXML
    private void bloquer_click(ActionEvent event) throws SQLException {
        ServiceUser se = new ServiceUser();
        se.bloquerA(nomC.getText());

        NomC.setCellValueFactory(new PropertyValueFactory<>("nom"));
        PrénomC.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        TypeC.setCellValueFactory(new PropertyValueFactory<>("type"));
        ObservableList<User> data = FXCollections.observableArrayList(se.selectUser());
        Tab_User.setItems(data);
    }

    @FXML
    private void OnValidePlan(ActionEvent event) throws IOException {
        try {

            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("ValidationPlan.fxml"));

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
            stage.setMaximized(true);

//        Stage stage2 = (Stage) ValidePlan.getScene().getWindow(); 
//        stage2.close();
        } catch (Exception e) {
        }
    }

    @FXML
    private void klikTableData(MouseEvent event) {
    }

    @FXML
    private void addUtil(ActionEvent event) {
         try {
             FicheUtilisateurController.id_Ut = -1;
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("FicheUtilisateur.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                         RefresGrid();
                }
            });
        } catch (Exception e) {
        }
    }

    @FXML
    private void EditUtil(ActionEvent event) {
        try {
            User UserGrid = tableView.getSelectionModel().getSelectedItem();
            if (UserGrid == null) {
                AlertHelper.showAlert(Alert.AlertType.WARNING, tableView.getScene().getWindow(), "erreur!", " Veuillez selectionner un Utlisateur!");
            }
            int idu = UserGrid.getId_u();
            if (idu != -1) {
                FicheUtilisateurController.id_Ut = idu;
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("FicheUtilisateur.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    public void handle(WindowEvent we) {
                        RefresGrid();
                    }
                });
            } else {
                AlertHelper.showAlert(Alert.AlertType.WARNING, tableView.getScene().getWindow(), "erreur!", " Veuillez selectionner un Utlisateur!");
            }
            //stage.setMaximized(true); 

        } catch (Exception e) {
        }
    }

    @FXML
    private void SupprimerUil(ActionEvent event) {
         
        try {
            User UserGrid = tableView.getSelectionModel().getSelectedItem();
            if (UserGrid == null) {
                AlertHelper.showAlert(Alert.AlertType.WARNING, tableView.getScene().getWindow(), "erreur!", " Veuillez selectionner un plan!");
            } else {
                // AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, tableView.getScene().getWindow(), "Supprimer plan!", " Voulez vous supprimer un plan?"); 
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Suppression Utilisateur!");
                alert.setContentText("Voulez vous supprimer un Utilisateur?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {

                   ServiceUser se = new ServiceUser();
                    se.supprimerUser(UserGrid.getId_u());
                RefresGrid();
                } else {
                    // ... user chose CANCEL or closed the dialog
                }
            }
        } catch (Exception e) {

        }
    } 
    @FXML
    private void RefreshUtil(ActionEvent event) {
        try {
            ServiceUser se = new ServiceUser();
            ObservableList<User> data = FXCollections.observableArrayList(se.selectUser());
            id.setCellValueFactory(new PropertyValueFactory<>("id_u"));
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            Prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
            adr.setCellValueFactory(new PropertyValueFactory<>("adress"));
            telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
            sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
            type.setCellValueFactory(new PropertyValueFactory<>("type"));
            etatcpt.setCellValueFactory(new PropertyValueFactory<>("etat_compte"));
            tableView.setEditable(true);
            tableView.setItems(data);
        } catch (Exception e) {
        }
    }

    private void RefresGrid() {
        try {
            ServiceUser se = new ServiceUser();
            ObservableList<User> data = FXCollections.observableArrayList(se.selectUser());
            id.setCellValueFactory(new PropertyValueFactory<>("id_u"));
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            Prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
            adr.setCellValueFactory(new PropertyValueFactory<>("adress"));
            telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
            sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
            type.setCellValueFactory(new PropertyValueFactory<>("type"));
            etatcpt.setCellValueFactory(new PropertyValueFactory<>("etat_compte"));
            tableView.setEditable(true);
            tableView.setItems(data);
        } catch (Exception e) {
        }
    }
}
