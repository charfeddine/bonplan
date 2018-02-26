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
import javafx.fxml.FXML;
import javafx.fxml.Initializable; 
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView; 
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author chaima
 */
public class ConsultationPlanController implements Initializable {
    
    public static int id_c = -1;
     public static int id_Sc = -1;
    @FXML
    private TableView<PlanGrid> tableView; 
    @FXML
    private VBox VBoxMain;
    @FXML
    private StackPane Anchorstac; 
    @FXML
    private TableColumn<?, ?> id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            
            ServicePlan ps = new ServicePlan();
            List<PlanGrid> dataps = ps.selectPlanGridByType(id_c,id_Sc);
            tableView.getItems().setAll(dataps);
        } catch (Exception e) {
        }
        // TODO
    }    

     
    
}
