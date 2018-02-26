/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bonplan;

import entity.Evenement;
import Services.ServiceCategorieEvent;
import Services.ServiceEvent;
import entity.CategorieEvent;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author ben-h
 */
public class EvenementController implements Initializable {

    @FXML
    private Button Btn_New_Event;
    @FXML
    private Button Btn_Clear_Event;
    @FXML
    private Button Btn_Modif_Event;
    @FXML
    private TextField Event_txt_Titre;
    @FXML
    private TextField Event_txt_Lieu;
    @FXML
    private TextField Event_txt_Prix;
    @FXML
    private TextArea Event_txt_Description;
    @FXML
    private DatePicker Event_txt_Date;
    @FXML
    private ComboBox<CategorieEvent> Event_cbx_categorie;
    @FXML
    private TextField Event_txt_Tel;
    @FXML
    private DatePicker Event_txt_Time;
    @FXML
    private TextField Event_txt_image;

    public static int add_modif;
    public static int id_event;
    public Evenement e = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (add_modif == 1)
            Btn_Modif_Event.setVisible(false);
        if (add_modif == 2) {
            Btn_New_Event.setVisible(false);
            if(id_event != -1){
                ServiceEvent se =new ServiceEvent();
                e  = se.selectEventItem(id_event);
                         /*    
                     CategorieService ps =new   CategorieService();
                     List<Categorie> ListCategorie=ps.select_categorie(); 
                    cmb_categorie.getItems().setAll(ListCategorie); 
                    Categorie cat=new Categorie(p.getId_c(), p.getLibCat(), "");
                   // cmb_categorie.setItems(cat);
                    cmb_categorie.setValue(cat);
                    
             Sous_categorie sccat=new Sous_categorie(p.getId_sc(),p.getId_c(), p.getLibSousCat() ); 
                    cmb_SC.setValue(sccat);
                    */
                 Event_txt_Titre.setText(e.getTitre()); 
                 Event_txt_Lieu.setText(e.getAdresse());
                 Event_txt_Description.setText(e.getDescription());
                 Event_txt_Prix.setText(String.valueOf(e.getPrix()));
                 Event_txt_Tel.setText(e.getTel());
                 Event_txt_image.setText(e.getImage());
                 
           }
        }
        
        ServiceCategorieEvent ce = new ServiceCategorieEvent();
        List<CategorieEvent> ListCategorieEvent = ce.getAllCategorieEvent();
        Event_cbx_categorie.getItems().setAll(ListCategorieEvent);
    }    

    @FXML
    private void NewEvent_onClick(ActionEvent event) {
        try {
            if (Event_txt_Titre.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, Btn_New_Event.getScene().getWindow(), "Form Error!", "Verifier le champ Titre !!");
                return;
            }
            if (Event_txt_Lieu.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, Btn_New_Event.getScene().getWindow(), "Form Error!", "Verifier le champ lieu !!");
                return;
            }
            if (Event_txt_Prix.getText().isEmpty() || !validNum(Event_txt_Prix.getText())) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, Btn_New_Event.getScene().getWindow(), "Form Error!", "Entrer un prix correcte");
                return;
            }
            CategorieEvent boxCatEvent = (CategorieEvent) Event_cbx_categorie.getValue();
            int id_cat_event = boxCatEvent.getId_cat();
            Date date_event = java.sql.Date.valueOf(Event_txt_Date.getValue());
            int prix = Integer.parseInt(Event_txt_Prix.getText());
            
            ServiceEvent se = new ServiceEvent();
            Evenement e = new Evenement(0, Event_txt_Titre.getText(), Event_txt_Lieu.getText(), Event_txt_Description.getText(), Event_txt_image.getText(), date_event,
                    prix, Event_txt_Tel.getText(), 3, id_cat_event);
            
            System.out.println(e.toString());
            int i = se.ajouterEvent(e);
            System.out.println(i);
            if (i != -1) {
                Effacer();
                AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, Btn_New_Event.getScene().getWindow(),"Welcome ", "Evenement Ajouté avec Succée!" );
            } else {
                AlertHelper.showAlert(Alert.AlertType.ERROR, Btn_New_Event.getScene().getWindow(), "Form Error!", "Probleme d'ajout!");
            }
        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, Btn_New_Event.getScene().getWindow(), "Form Error 2 !", e.getStackTrace().toString());

        }
    }

    @FXML
    private void AnnulerEvent_onClick(ActionEvent event) {
        Effacer();
    }

    @FXML
    private void ValidModifEvent_onClick(ActionEvent event) {
        try {
            if (Event_txt_Titre.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, Btn_New_Event.getScene().getWindow(), "Form Error!", "Verifier le champ Titre !!");
                return;
            }
            if (Event_txt_Lieu.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, Btn_New_Event.getScene().getWindow(), "Form Error!", "Verifier le champ lieu !!");
                return;
            }
            if (Event_txt_Prix.getText().isEmpty() || !validNum(Event_txt_Prix.getText())) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, Btn_New_Event.getScene().getWindow(), "Form Error!", "Entrer un prix correcte");
                return;
            }
            CategorieEvent boxCatEvent = (CategorieEvent) Event_cbx_categorie.getValue();
            int id_cat_event = boxCatEvent.getId_cat();
            Date date_event = java.sql.Date.valueOf(Event_txt_Date.getValue());
            int prix = Integer.parseInt(Event_txt_Prix.getText());
            
            ServiceEvent se = new ServiceEvent();
            Evenement e = new Evenement(id_event, Event_txt_Titre.getText(), Event_txt_Lieu.getText(), Event_txt_Description.getText(), Event_txt_image.getText(), date_event,
                    prix, Event_txt_Tel.getText(), 3, id_cat_event);
            
            System.out.println(e.toString());
            int i = se.modifierEvent(e);
            System.out.println(i);
            if (i != -1) {
                Effacer();
                AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, Btn_New_Event.getScene().getWindow(),"Welcome ", "Evenement modifié avec Succée!" );
            } else {
                AlertHelper.showAlert(Alert.AlertType.ERROR, Btn_New_Event.getScene().getWindow(), "Form Error!", "Probleme de modification !!");
            }
        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, Btn_New_Event.getScene().getWindow(), "Form Error 2 !", e.getStackTrace().toString());

        }
    }
    
    private boolean validNum(String text) {
        return text.matches("[0-9]*");
    }
    
    private void Effacer() {
        try {
            Event_txt_Titre.setText("");
            Event_txt_Lieu.setText("");
            Event_txt_Prix.setText("");
            Event_txt_Description.setText("");
            Event_txt_Tel.setText("");

        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, Btn_Clear_Event.getScene().getWindow(), "Form Error!", e.getStackTrace().toString());

        }
    }
}
