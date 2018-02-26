/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bonplan;

import Services.ServiceCategorie;
import Services.ServicePlan;
import Services.ServiceSous_categorie;
import entity.PlanGrid;
import entity.categorie;
import entity.sous_categorie;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author chaima
 */
public class ModifierPlanController implements Initializable {
   File dataFile = null;
    public static int id_plan = -1;
    public entity.plan p = null;
    @FXML
    private TextField txtlib;
    @FXML
    private TextField txtadr;
    @FXML
    private TextField txtimg;
    @FXML
    private TextField txtpx;
    @FXML
    private TextArea txtdescr;
    @FXML
    private Button Btn_ajouter;
    @FXML
    private ComboBox<sous_categorie> cmb_SC;
    @FXML
    private ComboBox<categorie> cmb_categorie;
    @FXML
    private TextField lng;
    @FXML
    private TextField lat;
    @FXML
    private TextField txtville;
    @FXML
    private Label lbltitre;
    @FXML
    private Button openMI;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // Plan p=new Plan();
            if (id_plan != -1) {
                lbltitre.setText("Modification Plan");
                ServicePlan pserv = new ServicePlan();
                p = pserv.selectPlanItem(id_plan);

                ServiceCategorie ps = new ServiceCategorie();
                List<categorie> ListCategorie = ps.select_categorie();
                cmb_categorie.getItems().setAll(ListCategorie);
                categorie cat = new categorie(p.getId_c(), p.getLibCat(), "");
                // cmb_categorie.setItems(cat);
                cmb_categorie.setValue(cat);
                ServiceSous_categorie psc = new ServiceSous_categorie();
                List<sous_categorie> List_Sous_categorieDiv = psc.select_Sous_categorie(p.getId_c());
                cmb_SC.getItems().setAll(List_Sous_categorieDiv);
                sous_categorie sccat = new sous_categorie(p.getId_sc(), p.getId_c(), p.getLibSousCat());
                cmb_SC.setValue(sccat);

                txtlib.setText(p.getLibelle());
                txtadr.setText(p.getAdresse());
                 txtville.setText(p.getVille());
                txtimg.setText("C:\\bonplans\\"+p.getLibelle()+".jpg");
                txtpx.setText(p.getPrix());
                lat.setText(String.valueOf(p.getLatitude()));
                lng.setText(String.valueOf(p.getLongitude()));
                txtdescr.setText(p.getDescription());
            } else {
                lbltitre.setText("Ajout Plan");
                ServiceCategorie ps = new ServiceCategorie();
                List<categorie> ListCategorie = ps.select_categorie();
                cmb_categorie.getItems().setAll(ListCategorie);
            }
        } catch (Exception e) {
        }
        // TODO
    }

    @FXML
    private void OnclikEnregistrer(ActionEvent event) {

        Window owner = Btn_ajouter.getScene().getWindow();
        try {
            sous_categorie output = (sous_categorie) cmb_SC.getValue();
             if (output==null) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "sous_categorie!!!!");
                return;
            }
            if (txtlib.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Libelle!!!!");
                return;
            }
            if (txtadr.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Adresse!!!!");
                return;
            }
            if (txtimg.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Image!!!!");
                return;
            }
            if (txtpx.getText().isEmpty() || !validate(txtpx.getText())) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Prix!!!!");
                return;
            }
            if (lng.getText().isEmpty() || validatedouble(lng.getText())) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Longitude !!!!");
                return;
            }
            if (lat.getText().isEmpty() || validatedouble(lat.getText())) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Latitude!!!!");
                return;
            }
            
 if (txtimg.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Image!!!!");
                return;
            } 
            int idsc = output.getId_sc();
            ServicePlan ps = new ServicePlan();
              byte[] bytesArray=null;
           if(dataFile!=null){
                 bytesArray = new byte[(int) dataFile.length()];

            FileInputStream fis = new FileInputStream(dataFile);
            fis.read(bytesArray); //read file into bytes[]
            fis.close();}
          
            entity.plan p1 = new entity.plan(id_plan, txtlib.getText(), txtadr.getText(),
                    txtdescr.getText(), txtville.getText(),bytesArray,
                    null, null, txtpx.getText(), Double.parseDouble(lng.getText()), Double.parseDouble(lat.getText()), LoginController.id_u, idsc);

            int i = -1;
            if (id_plan != -1) {

                i = ps.ModifPlan(p1);
                 if (i != -1) {
                Clean();
                AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Plan Ajouter avec succées", p1.getLibelle().toString());
              
            } else {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Probleme d'ajout!");
            }
                
            } else {
                i = ps.ajouterPlan(p1);
                 if (i != -1) {
                Clean();
                AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Plan Ajouter avec succées", p1.getLibelle().toString());
               
            } else {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Probleme d'ajout!");
            }
            }
        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", e.getStackTrace().toString());

        }
    }

    private void Clean() {
        Window owner = Btn_ajouter.getScene().getWindow();
        try {
            txtlib.setText("");
            txtadr.setText("");
            txtville.setText("");
            txtpx.setText("");
            txtimg.setText("");
            lat.setText("");
            lng.setText("");
            txtdescr.setText("");
            ServiceCategorie ps = new ServiceCategorie();
                List<categorie> ListCategorie = ps.select_categorie();
                cmb_categorie.getItems().setAll(ListCategorie);

        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", e.getStackTrace().toString());

        }
    }
    @FXML
    private void initializecmbcat(ActionEvent event) {
        try {
            ServiceSous_categorie psc = new ServiceSous_categorie();
            categorie selectedCategorie = cmb_categorie.getSelectionModel().getSelectedItem();
            List<sous_categorie> List_Sous_categorieDiv = psc.select_Sous_categorie(selectedCategorie.getId_c());
            cmb_SC.getItems().setAll(List_Sous_categorieDiv);
        } catch (Exception e) {
        }
    }

    private boolean validate(String text) {
        return text.matches("[0-9]*");
    }

    private boolean validatedouble(String text) {
        try {
            Double value = Double.parseDouble(text);
            return value.isNaN();
        } catch (Exception e) {
            return true;
        }

    }

    @FXML
    private void openImgage(ActionEvent event) {
          try {
            // handleOpenClick();
            FileChooser fileChooser = new FileChooser();
            dataFile = fileChooser.showOpenDialog(null);

            if (dataFile != null) {

                txtimg.setText("File selected: " + dataFile.getName());
            } else {
                //actionStatus.setText("File selection cancelled.");
            }
        } catch (Exception e) {
        }
    }
}
