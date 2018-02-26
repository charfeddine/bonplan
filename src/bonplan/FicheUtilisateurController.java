/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bonplan;

import java.time.*;
import java.util.Date;
import Services.ServiceUser;
import entity.User;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
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
import javafx.scene.control.TextField;
import javafx.stage.Window;
import javax.swing.text.DateFormatter;

/**
 * FXML Controller class
 *
 * @author chaima
 */
public class FicheUtilisateurController implements Initializable {

    public static int id_Ut = -1;
    @FXML
    private Label lbltitre;
    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtpre;
    @FXML
    private TextField txtphone;
    @FXML
    private Button Btn_ajouter;
    @FXML
    private ComboBox<String> cmb_Type;
    @FXML
    private TextField txtlogin;
    @FXML
    private TextField txtpsd;
    @FXML
    private TextField txtsexe;
    @FXML
    private TextField txtemail;
    @FXML
    private DatePicker txtnaiss;
    @FXML
    private CheckBox etat;

    /**
     * Initializes the controller class.
     */
    public entity.User u = null;
    @FXML
    private TextField txtadr;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            // Plan p=new Plan();
            if (id_Ut != -1) {
                lbltitre.setText("Modification Utilsateur");
                ServiceUser se = new ServiceUser();
                u = se.selectUtilsateurItem(id_Ut);
                List<String> Listtype = new ArrayList<String>();
                Listtype.add("Admin");
                Listtype.add("User");
                cmb_Type.getItems().setAll(Listtype);
                cmb_Type.setValue(u.getType());

                txtadr.setText(u.getAdress());
                txtnom.setText(u.getNom());
                txtpre.setText(u.getPrenom());
                txtsexe.setText(u.getSexe());
                txtemail.setText(u.getEmail());
                txtphone.setText(String.valueOf(u.getTelephone()));
                // txtnaiss.setValue((u.getDateNaissance())); 

                txtlogin.setText((u.getLogin()));
                txtpsd.setText((u.getPassword()));
                if ("1".equals(u.getEtat_compte())) {
                    etat.setSelected(true);
                } else {
                    etat.setSelected(false);

                    ZoneId defaultZoneId = ZoneId.systemDefault();
                    Instant instant = u.getDateNaissance().toInstant();
                    LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();
      
                    txtnaiss.setValue(localDate);

                }
            } else {
                lbltitre.setText("Ajout Utilsateur");
                List<String> Listtype = new ArrayList<String>();
                Listtype.add("Admin");
                Listtype.add("Utilisateur");
                cmb_Type.getItems().setAll(Listtype);;
                cmb_Type.setValue("Utilisateur");
            }
        } catch (Exception e) {
        }
    }
    String pattern = "yyyy-MM-dd";
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

    public LocalDate fromString(String string) {
        if (string != null && !string.isEmpty()) {
            return LocalDate.parse(string, dateFormatter);
        } else {
            return null;
        }
    }

    @FXML
    private void OnclikEnregistrer(ActionEvent event) {

        Window owner = Btn_ajouter.getScene().getWindow();
        try {

            String output = (String) cmb_Type.getValue();
            if (output == null) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Type utilisateur!!!!");
                return;
            }
            if (txtnom.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Nom!!!!");
                return;
            }
            if (txtpre.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Prenom!!!!");
                return;
            }
            if (txtsexe.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Sexe!!!!");
                return;
            }
            if (txtemail.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Email!!!!");
                return;
            }
            if (txtadr.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Adresse !!!!");
                return;
            }
            if (txtphone.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Phone!!!!");
                return;
            }
            if (txtlogin.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Login!!!!");
                return;
            }
            if (txtpsd.getText().isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Password!!!!");
                return;
            }

            String etat_compte = "0";
            if (etat.isSelected()) {
                etat_compte = "1";
            }
            LocalDate ld = txtnaiss.getValue();
             Date date =null;
            if(ld!=null){
            Calendar c = Calendar.getInstance();
            c.set(ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth());
              date = c.getTime();
            }
            ServiceUser se = new ServiceUser();
            u = new entity.User(id_Ut,txtnom.getText(), txtpre.getText(), txtsexe.getText(), txtemail.getText(), txtadr.getText(),
                     Integer.parseInt(txtphone.getText()), date, txtlogin.getText(), txtpsd.getText(), output,
                    etat_compte);
            int i = -1;
            if (id_Ut != -1) {

                i = se.ModifUser(u);

                if (i != -1) {
                    // Clean();
                    AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Utilisateur Ajouter avec succées", u.getNom().toString());
 
                } else {
                    AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Probleme d'ajout!");
                }

            } else {
                i = se.ajouterUser(u);
                if (i != -1) {
                    // Clean();
                    AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Utilisateur Ajouter avec succées", u.getNom().toString());

                } else {
                    AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Probleme d'ajout!");
                }
            }
        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", e.getStackTrace().toString());

        }
    }
}
