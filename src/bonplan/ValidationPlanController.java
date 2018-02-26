/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bonplan;

import Services.ServicePlan;
import entity.PlanGrid;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author chaima
 */
public class ValidationPlanController implements Initializable {

    @FXML
    private VBox VBoxMain;
    @FXML
    private StackPane Anchorstac;
    @FXML
    private TableView<PlanGrid> tableView;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private Button validoui;
    @FXML
    private Button ValidNon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            
            ServicePlan ps = new ServicePlan();
            List<PlanGrid> dataps = ps.selectPlanGridValidation();
            tableView.getItems().setAll(dataps);
        } catch (Exception ex) {
             AlertHelper.showAlert(Alert.AlertType.ERROR, tableView.getScene().getWindow(), "erreur!", ex.getMessage());
        }
    }    

    @FXML
    private void onValidOui(ActionEvent event) {
        validate(1);
    }

    @FXML
    private void onValidNon(ActionEvent event) {
         validate(0);
    }
    
    public void validate(int etat){
          try {
              PlanGrid planGrid = tableView.getSelectionModel().getSelectedItem();
            if (planGrid == null) {
                AlertHelper.showAlert(Alert.AlertType.WARNING, tableView.getScene().getWindow(), "erreur!", " Veuillez selectionner un plan!");
            }
            int idp = planGrid.getId_p();
            ServicePlan ps = new ServicePlan();
            int i= ps.UpdateValidationPlan(idp,etat);
               if (i != -1) {
                 int p= ps.UpdatePointfidelite(planGrid.getid_u(),50);
           List<PlanGrid> dataps = ps.selectPlanGridValidation();
            tableView.getItems().setAll(dataps);
               }
        } catch (Exception ex) {
             AlertHelper.showAlert(Alert.AlertType.ERROR, tableView.getScene().getWindow(), "erreur!", ex.getMessage());
        }
    }
}
