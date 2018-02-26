/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bonplan;

import Services.ServicePlan; 
import entity.PlanGrid;
import entity.plan;
import entity.publicite;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author a7med
 */
public class EsPersoController implements Initializable {

    @FXML
    private Button deconnect;

    private TableColumn<publicite, String> titre;
    private TableColumn<publicite, String> site;
    private TableColumn<publicite, String> desc;
    private TableView<publicite> Tab_pub;
    @FXML
    private Button esPerso;
    private TableView<PlanGrid> tableView;
    @FXML
    private MenuItem btn_hotel;
    @FXML
    private AnchorPane container;
    @FXML
    private MenuItem btn_parc;
    @FXML
    private Button Validadmin;
    @FXML
    private Button maps;
    @FXML
    private MenuItem btn_rest;
    @FXML
    private MenuItem btn_ice;
    @FXML
    private MenuItem btn_cafe;
    @FXML
    private MenuItem btn_Coif;
    @FXML
    private MenuItem btn_SpaEsth;
    @FXML
    private MenuItem btn_Ssport;
    @FXML
    private MenuButton btn_div;
    public String type = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Timeline timeline = new Timeline(new KeyFrame(
                    javafx.util.Duration.millis(15000),
                    ae -> showNotification()));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
            ServicePlan ps = new ServicePlan();
            type = ps.selectTypeCompte(LoginController.id_u);

            //            if ("2".equals(type)) {
            //                Validadmin.setVisible(true);
            //            } else {
            //                Validadmin.setVisible(false);
            //            }
            Validadmin.setVisible(false);
            Node node = null;
            FXMLLoader loader = new FXMLLoader();
            node = (Parent) loader.load(getClass().getResourceAsStream("gestionPlans.fxml"));
            container.getChildren().clear();
            container.getChildren().add(node);

        } catch (Exception ex) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, tableView.getScene().getWindow(), "erreur!", ex.getMessage());
        }
        //chargé les données a partir de la base  
//            ServicePlan ps = new ServicePlan(); 
//            List<PlanGrid> dataps = ps.selectPlanGrid(AcceuilClientController.id_u);
//            tableView.getItems().setAll(dataps);

    }

    private void showNotification() {
        try {
            ServicePlan ps = new ServicePlan();
            if ("2".equals(type)) {
            } else {
                //notification
                List<plan> dataps = ps.selectPlanGridByTypeForNotif(LoginController.id_u);
                for (plan p : dataps) {

                    if (p.getEtat() == 1 && p.getEtatnotif() == 0) {
                        String title = "Merci , pour votre fidelite , votre plan est valide";
                        String message = p.getLibSousCat() + ":" + p.getLibelle();
                        //"You've successfully created your first Tray Notification";
                        NotificationType notification = NotificationType.SUCCESS ;
                        TrayNotification tray = new TrayNotification();
                        tray.setTitle(title);
                        tray.setMessage(message);
                        tray.setNotificationType(notification);
                        //tray.showAndWait();
                        tray.showAndDismiss(Duration.seconds(10));
                        //update notif
                        ps.UpdatenotifPlan(p.getId_p(), 1);
                    }
                    if (p.getEtatnotif() == 2) {
                        String title = "Plan von validé";
                        String message = p.getLibSousCat() + ":" + p.getLibelle();
                        //"You've successfully created your first Tray Notification";
                         NotificationType notification = NotificationType.WARNING ;
                        TrayNotification tray = new TrayNotification();
                        tray.setTitle(title);
                        tray.setMessage(message);
                        tray.setNotificationType(notification);
                        // tray.showAndWait();
                        tray.showAndDismiss(Duration.seconds(10));
                        //update notif
                        ps.UpdatenotifPlan(p.getId_p(), 0);
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    @FXML
    private void sedeConnecter(ActionEvent event) {

    }

    @FXML
    private void esPerso(ActionEvent event) {
        try {
            Node node = null;
            FXMLLoader loader = new FXMLLoader();
            node = (Parent) loader.load(getClass().getResourceAsStream("gestionPlans.fxml"));
            container.getChildren().clear();
            container.getChildren().add(node);

        } catch (Exception ex) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, tableView.getScene().getWindow(), "erreur!", ex.getMessage());
        }
    }

    @FXML
    private void ShowListeHotels(ActionEvent event) {

        try {
            consultation_ImgController.id_Sc = 2;
            consultation_ImgController.titre = "Liste des Hotels";
            Node node = null;
            FXMLLoader loader = new FXMLLoader();
            node = (Parent) loader.load(getClass().getResourceAsStream("consultation_Img.fxml"));
            container.getChildren().clear();
            container.getChildren().add(node);

        } catch (Exception ex) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, tableView.getScene().getWindow(), "erreur!", ex.getMessage());
        }
    }

    @FXML
    private void ShowListeParc(ActionEvent event) {
        try {
            consultation_ImgController.id_Sc = 3;
            consultation_ImgController.titre = "Liste des Parc";
            Node node = null;
            FXMLLoader loader = new FXMLLoader();
            node = (Parent) loader.load(getClass().getResourceAsStream("consultation_Img.fxml"));
            container.getChildren().clear();
            container.getChildren().add(node);

        } catch (Exception ex) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, tableView.getScene().getWindow(), "erreur!", ex.getMessage());
        }
    }

    @FXML
    private void onValidadmin(ActionEvent event) {
        try {
            Node node = null;
            FXMLLoader loader = new FXMLLoader();
            node = (Parent) loader.load(getClass().getResourceAsStream("ValidationPlan.fxml"));
            container.getChildren().clear();
            container.getChildren().add(node);

        } catch (Exception ex) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, tableView.getScene().getWindow(), "erreur!", ex.getMessage());
        }
    }

    @FXML
    private void showmaps(ActionEvent event) {
        try {
            Node node = null;
            FXMLLoader loader = new FXMLLoader();
           node = (Parent) loader.load(getClass().getResourceAsStream("/map/GMapsGlobalePlan.fxml"));
            // node = (Parent) loader.load(getClass().getResourceAsStream("/map/Map.fxml"));
            container.getChildren().clear();
            container.getChildren().add(node);

        } catch (Exception ex) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, tableView.getScene().getWindow(), "erreur!", ex.getMessage());
        }
    }

    @FXML
    private void ShowListeRestaurants(ActionEvent event) {
        try {
            consultation_ImgController.titre = "Liste des Restaurants";
            consultation_ImgController.id_Sc = 9;
            Node node = null;
            FXMLLoader loader = new FXMLLoader();
            node = (Parent) loader.load(getClass().getResourceAsStream("consultation_Img.fxml"));
            container.getChildren().clear();
            container.getChildren().add(node);

        } catch (Exception ex) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, tableView.getScene().getWindow(), "erreur!", ex.getMessage());
        }
    }

    @FXML
    private void ShowListeGelaterie(ActionEvent event) {
        try {
            consultation_ImgController.titre = "Liste des Gelateries";
            consultation_ImgController.id_Sc = 11;
            Node node = null;
            FXMLLoader loader = new FXMLLoader();
            node = (Parent) loader.load(getClass().getResourceAsStream("consultation_Img.fxml"));
            container.getChildren().clear();
            container.getChildren().add(node);

        } catch (Exception ex) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, tableView.getScene().getWindow(), "erreur!", ex.getMessage());
        }
    }

    @FXML
    private void ShowListeCafe(ActionEvent event) {
        try {
            consultation_ImgController.titre = "Liste des Cafés";
            consultation_ImgController.id_Sc = 10;
            Node node = null;
            FXMLLoader loader = new FXMLLoader();
            node = (Parent) loader.load(getClass().getResourceAsStream("consultation_Img.fxml"));
            container.getChildren().clear();
            container.getChildren().add(node);

        } catch (Exception ex) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, tableView.getScene().getWindow(), "erreur!", ex.getMessage());
        }
    }

    @FXML
    private void ShowListeCoif(ActionEvent event) {
        try {
            consultation_ImgController.titre = "Liste des Coiffures";
            consultation_ImgController.id_Sc = 6;
            Node node = null;
            FXMLLoader loader = new FXMLLoader();
            node = (Parent) loader.load(getClass().getResourceAsStream("consultation_Img.fxml"));
            container.getChildren().clear();
            container.getChildren().add(node);

        } catch (Exception ex) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, tableView.getScene().getWindow(), "erreur!", ex.getMessage());
        }

    }

    @FXML
    private void ShowListe(ActionEvent event) {

    }

    @FXML
    private void ShowListeSsport(ActionEvent event) {
        try {
            consultation_ImgController.titre = "Liste des Salles de Sports";
            consultation_ImgController.id_Sc = 7;
            Node node = null;
            FXMLLoader loader = new FXMLLoader();
            node = (Parent) loader.load(getClass().getResourceAsStream("consultation_Img.fxml"));
            container.getChildren().clear();
            container.getChildren().add(node);

        } catch (Exception ex) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, tableView.getScene().getWindow(), "erreur!", ex.getMessage());
        }
    }

    @FXML
    private void ShowListeAllDivertissement(ActionEvent event) {
        try {
            consultation_ImgController.id_c = 1;
            consultation_ImgController.id_Sc = -1;
            consultation_ImgController.titre = "Menu Divertissement";
            Node node = null;
            FXMLLoader loader = new FXMLLoader();
            node = (Parent) loader.load(getClass().getResourceAsStream("consultation_Img.fxml"));
            container.getChildren().clear();
            container.getChildren().add(node);

        } catch (Exception ex) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, tableView.getScene().getWindow(), "erreur!", ex.getMessage());
        }
    }

    @FXML
    private void ShowListeAllDivertissement(MouseEvent event) {
        try {
            consultation_ImgController.titre = "Menu Divertissement";
            consultation_ImgController.id_c = 1;
            consultation_ImgController.id_Sc = -1;
            Node node = null;
            FXMLLoader loader = new FXMLLoader();
            node = (Parent) loader.load(getClass().getResourceAsStream("consultation_Img.fxml"));
            container.getChildren().clear();
            container.getChildren().add(node);

        } catch (Exception ex) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, tableView.getScene().getWindow(), "erreur!", ex.getMessage());
        }
    }

}
