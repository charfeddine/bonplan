/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bonplan;

import Services.ServiceCategorie;
import Services.ServiceEvent;
import Services.ServicePlan;
import Services.ServicePub;
import Services.ServiceSous_categorie;
import entity.EventGrid;
import entity.PlanGrid;
import entity.categorie;
import entity.publicite;
import entity.sous_categorie;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author chaima
 */
public class gestionPlansController implements Initializable {

    File dataFile = null;
    @FXML
    private ComboBox<categorie> cmb_categorie;
    @FXML
    private ComboBox<sous_categorie> cmb_SC;
    private TextField txtlib;
    private TextField txtadr;
    private TextField txtville;
    @FXML
    private TextField txtimg;
    @FXML
    private Button openMI;
    private TextField txtpx;
    @FXML
    private TextField lng;
    @FXML
    private TextField lat;
    private TextArea txtdescr;
    @FXML
    private Button Btn_ajouter;
    @FXML
    private VBox VBoxMain;
    @FXML
    private Label contentheaderv0;
    @FXML
    private StackPane Anchorstac;
    @FXML
    private TableView<PlanGrid> tableView;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private Label contentheaderv;
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
    private AnchorPane Main;
    @FXML
    private TextField titre_pub;
    @FXML
    private TextField site_pub;
    @FXML
    private TextField desc_pub;
    @FXML
    private TableView<publicite> Tab_pub;
    @FXML
    private TableColumn<publicite, String> titrepub;
    @FXML
    private TableColumn<publicite, String> sitepub;
    @FXML
    private TableColumn<publicite, String> descpub;
    @FXML
    private Button img_pub_but;
    @FXML
    private ImageView img_pub;
    public String pathString;
    @FXML
    private TableView<EventGrid> TabView_Event;
    @FXML
    private TableColumn<EventGrid, Integer> id_2;
    @FXML
    private TableColumn<EventGrid, String> titre_2;
    @FXML
    private TableColumn<EventGrid, String> lieu_2;
    @FXML
    private TableColumn<EventGrid, Integer> prix_2;
    @FXML
    private Button Btn_Ajouter_Event;
    @FXML
    private Button Btn_Modifier_Event;
    @FXML
    private Button Btn_Supprimer_Event;
    @FXML
    private TextField txtlibxx;
    @FXML
    private TextField txtadrxx;
    @FXML
    private TextField txtvillexx;
    @FXML
    private TextField txtpxxx;
    @FXML
    private TextArea txtdescrxx;
    @FXML
    private Label lblnom;
    @FXML
    private Label lblprenom;
    @FXML
    private Label lblemail;
    @FXML
    private Label lblpf;
    @FXML
    private Label lbladr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            prix_2.setCellValueFactory(new PropertyValueFactory<EventGrid, Integer>("prix"));
            lieu_2.setCellValueFactory(new PropertyValueFactory<EventGrid, String>("adresse"));
            titre_2.setCellValueFactory(new PropertyValueFactory<EventGrid, String>("titre"));
            id_2.setCellValueFactory(new PropertyValueFactory<EventGrid, Integer>("id_event"));
            //chargé les données a partir de la base  
            ServiceEvent ev = new ServiceEvent();
            //List<EventGrid> events = se.selectEventGrid(3);
            ObservableList<EventGrid> events = FXCollections.observableArrayList(ev.selectEventGrid(3));
            TabView_Event.getItems().setAll(events);

            System.out.println("Affichage du tableau !");
            ServicePub se = new ServicePub();
            //se.selectPub();
            titrepub.setCellValueFactory(new PropertyValueFactory<publicite, String>("titre_pub"));
            sitepub.setCellValueFactory(new PropertyValueFactory<publicite, String>("site_pub"));
            descpub.setCellValueFactory(new PropertyValueFactory<publicite, String>("desc_pub"));
            ObservableList<publicite> data = FXCollections.observableArrayList(se.selectPub());
            Tab_pub.setEditable(true);

            Tab_pub.setItems(data);

            //chargé les données a partir de la base  
            ServicePlan ps = new ServicePlan();
            List<PlanGrid> dataps = ps.selectPlanGrid(LoginController.id_u);
            tableView.getItems().setAll(dataps);
            //charger mes information
            lblnom.setText(LoginController.u.getNom());
            lblprenom.setText(LoginController.u.getPrenom());
            lbladr.setText(LoginController.u.getAdress());
            lblemail.setText(LoginController.u.getEmail());
            lblpf.setText(String.valueOf(LoginController.u.getPoint_fidelite()));
        } catch (Exception e) {
        }
    }

    @FXML
    private void OnclikEnregistrer(ActionEvent event) {

        Window owner = Btn_ajouter.getScene().getWindow();
        try {
            sous_categorie output = (sous_categorie) cmb_SC.getValue();
            if (output == null) {
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

            int idsc = output.getId_sc();
            ServicePlan ps = new ServicePlan();

            //save image 
            // File file = new File("C:\\Users\\chaima\\Desktop\\cha.jpg");
            //init array with file length
            byte[] bytesArray = new byte[(int) dataFile.length()];

            FileInputStream fis = new FileInputStream(dataFile);
            fis.read(bytesArray); //read file into bytes[]
            fis.close();

            //  byte[] bFile = new byte[(int) file.length()]; 
            entity.plan p1 = new entity.plan(0, txtlib.getText(), txtadr.getText(),
                    txtdescr.getText(), txtville.getText(), bytesArray,
                    null, null, txtpx.getText(), Double.parseDouble(lng.getText()), Double.parseDouble(lat.getText()), LoginController.id_u, idsc);

            int i = -1;
            i = ps.ajouterPlan(p1);
            if (i != -1) {
                Clean();
                AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Plan Ajouter avec succées", p1.getLibelle().toString());
                List<PlanGrid> dataps = ps.selectPlanGrid(LoginController.id_u);
                tableView.getItems().setAll(dataps);
            } else {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Probleme d'ajout!");
            }

        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", e.getStackTrace().toString());

        }
    }

    private void Clean() {
        Window owner = Btn_ajouter.getScene().getWindow();
        try {
            ServiceCategorie ps = new ServiceCategorie();
            List<categorie> ListCategorie = ps.select_categorie();
            cmb_categorie.getItems().setAll(ListCategorie);
            txtlib.setText("");
            txtadr.setText("");
            txtville.setText("");
            txtpx.setText("");
            txtimg.setText("");
            lat.setText("");
            lng.setText("");
            txtdescr.setText("");

        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", e.getStackTrace().toString());

        }
    }

    private void OnclikReset(ActionEvent event) {
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

        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", e.getStackTrace().toString());

        }
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
    private void initializecmbcat(ActionEvent event) {
        try {
            ServiceSous_categorie psc = new ServiceSous_categorie();
            categorie selectedCategorie = cmb_categorie.getSelectionModel().getSelectedItem();
            List<sous_categorie> List_Sous_categorieDiv = psc.select_Sous_categorie(selectedCategorie.getId_c());
            cmb_SC.getItems().setAll(List_Sous_categorieDiv);
        } catch (Exception e) {
        }
    }

    @FXML
    private void klikTableData(MouseEvent event) {
    }

    @FXML
    private void addPlan(ActionEvent event) {
        try {
            ModifierPlanController.id_plan = -1;
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("ModifierPlan.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    ServicePlan ps = new ServicePlan();
                    List<PlanGrid> dataps = ps.selectPlanGrid(LoginController.id_u);
                    tableView.getItems().setAll(dataps);
                }
            });
        } catch (Exception e) {
        }
    }

    @FXML
    private void EditPlan(ActionEvent event) {
        try {
            PlanGrid planGrid = tableView.getSelectionModel().getSelectedItem();
            if (planGrid == null) {
                AlertHelper.showAlert(Alert.AlertType.WARNING, tableView.getScene().getWindow(), "erreur!", " Veuillez selectionner un plan!");
            }
            int idp = planGrid.getId_p();
            if (idp != -1) {
                ModifierPlanController.id_plan = idp;
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("ModifierPlan.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    public void handle(WindowEvent we) {
                        ServicePlan ps = new ServicePlan();
                        List<PlanGrid> dataps = ps.selectPlanGrid(LoginController.id_u);
                        tableView.getItems().setAll(dataps);
                    }
                });
            } else {
                AlertHelper.showAlert(Alert.AlertType.WARNING, tableView.getScene().getWindow(), "erreur!", " Veuillez selectionner un plan!");
            }
            //stage.setMaximized(true); 

        } catch (Exception e) {
        }

    }

    @FXML
    private void SupprimerPlan(ActionEvent event) {

        try {
            PlanGrid planGrid = tableView.getSelectionModel().getSelectedItem();
            if (planGrid == null) {
                AlertHelper.showAlert(Alert.AlertType.WARNING, tableView.getScene().getWindow(), "erreur!", " Veuillez selectionner un plan!");
            } else {
                // AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, tableView.getScene().getWindow(), "Supprimer plan!", " Voulez vous supprimer un plan?"); 
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Suppression plan!");
                alert.setContentText("Voulez vous supprimer un plan?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {

                    ServicePlan ps = new ServicePlan();
                    ps.supprimerUser(planGrid.getId_p());
                    List<PlanGrid> dataps = ps.selectPlanGrid(LoginController.id_u);
                    tableView.getItems().setAll(dataps);
                } else {
                    // ... user chose CANCEL or closed the dialog
                }
            }
        } catch (Exception e) {

        }
    }

    @FXML
    private void RefreshPlan(ActionEvent event) {
        try {
            ServicePlan ps = new ServicePlan();
            List<PlanGrid> dataps = ps.selectPlanGrid(LoginController.id_u);
            tableView.getItems().setAll(dataps);

        } catch (Exception e) {
        }
    }

    @FXML
    private void AjouterPlan(Event event) {
        try {
            System.out.println("************");
            ServiceCategorie ps = new ServiceCategorie();
            List<categorie> ListCategorie = ps.select_categorie();
            cmb_categorie.getItems().setAll(ListCategorie);

        } catch (Exception e) {
        }
    }

    private boolean validate(String text) {
        return text.matches("[0-9]*");
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

    @FXML
    private void ajoutpub_onClick(ActionEvent event) throws FileNotFoundException {
        System.out.println("Ajout d'une ligne!");
        // int a=Integer.parseInt(LoginController.getId_user());
        ServicePub se = new ServicePub();
        se.ajouterPub(titre_pub.getText(), site_pub.getText(), pathString, desc_pub.getText(), 12);
        //  afficher();
        System.out.println("Affichage du tableau !");
        //se.selectPub();
        titrepub.setCellValueFactory(new PropertyValueFactory<publicite, String>("titre_pub"));
        sitepub.setCellValueFactory(new PropertyValueFactory<publicite, String>("site_pub"));
        descpub.setCellValueFactory(new PropertyValueFactory<publicite, String>("desc_pub"));
        ObservableList<publicite> data = FXCollections.observableArrayList(se.selectPub());
        Tab_pub.setItems(data);
        /* if (partage_checkbox.isSelected()){
    // String accesstoken = "EAAV1eQVDEJUBAHGOMCDQV67DM70OSUuMbuHVKgiSORnfrbZA3yd2JxYZCm2kSmPRFrZAg5M53ZA4eOhU7RpBpZAzRTRNKzxPZB7tmzRhVz2AAKPEaD1QI7pVPM8t9DQUJmCrq6ZBgd5N3B5nZA6KaDpNZCyaqy98ZCaZBkZCry1Nna3nEPh5nSxbj45AkmcXJh1ZC2SiskMjiCDggDAZDZD";
    // String accesstoken = "EAAFGNkvXHX4BAGjqWhrwMtZBC3zNsAGhl70iFmltAlHwDrgqhM69pQzDZC3UYTiyL4BtkZCUy4GiD0rSa2JW5w1cJ1Bfzr5GrtxHiPzuj4kbOkOypHZBV1meMSWwVuZA9gRINB6v0JGZBCLphoT73i5qtqgl3yRX8hsmIaT1A8yZBdXfXUZCpdbdykiEZCqTZAmdqx2xYeE9h4TQZDZD";
    String accesstoken =null;
    String domain="http://radixcode.com/";
   // String appId ="358673991277950";
String appId ="1536537523327125";
     String authUrl="https://graph.facebook.com/oauth/authorize?type=user_agent&client_id="+appId+"&redirect_uri="+domain
             +"&scope=publish_actions";
     System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
     WebDriver driver =new ChromeDriver();
      driver.get(authUrl);
     while(true){
     if (!driver.getCurrentUrl().contains("facebook.com")){
         String url =  driver.getCurrentUrl();
         accesstoken = url.replaceAll(".*#access_token=(.+)&.*", "$1");
          
          
           System.out.println(accesstoken);

           FacebookClient fbclient = new DefaultFacebookClient(accesstoken);
      AccessToken exAccesstoken = fbclient.obtainExtendedAccessToken("1536537523327125", "49aae95435785331636819fcc1db697d");
      FacebookClient fbclient1 = new DefaultFacebookClient(exAccesstoken.getAccessToken());
     User user =fbclient1.fetchObject("me",  User.class);
        
        FileInputStream fis = new FileInputStream(new File(pathString));
        FacebookType response=fbclient1.publish("me/photos", FacebookType.class, BinaryAttachment.with("pathString", fis)
        ,Parameter.with("message", titre_pub.getText()));
        System.out.println("fb.com/"+response.getId());
        driver.quit(); */

        //String Accesstoken ="EAAFGNkvXHX4BAGHRGZBC9BI4NijA9ZBx4rwh1vW9yV1pdDZCS7XxmpLuWYvD3aZCPcApr59453RY5hp3qG5QWuGIGDMKQVRlylYxsS0w6Lueh6VTfKuUPI5iMYcVV6V0AhLas2QZBVd7h20yHIP9DvShO5NYJUkZBZCqNnW2vKs54lN9GiSLDcFsy0ety7GoNZBka8D70ocxcAZDZD";         
// String Accesstoken ="EAACEdEose0cBAPXPbhoFuXMZBdY4swPvXXdOtsGOsY5Q9uX7i9UPStUQYYOsSaDRRpzLgtD3QZABA0Cbezkwp7e0xh63nsVq3DigbZCeIfSoc5qv5ZCp8aTeIQRhhdhQUQDpHw543VXs5MrNES68oOLUcb8jSagJW6ynlA6jnE5ah5IZA3Yd0tmgj26im4fRrBVCUywkXjgZDZD";
    }

    @FXML
    private void suppub_onClick(ActionEvent event) {
        System.out.println("Suppression d'une ligne !");
        ServicePub se = new ServicePub();
        // int a=Integer.parseInt(titre_pub.getText());
        se.supprimerPub(titre_pub.getText());
        // afficher();
        System.out.println("Affichage du tableau !");
        //se.selectPub();
        titrepub.setCellValueFactory(new PropertyValueFactory<publicite, String>("titre_pub"));
        sitepub.setCellValueFactory(new PropertyValueFactory<publicite, String>("site_pub"));
        descpub.setCellValueFactory(new PropertyValueFactory<publicite, String>("desc_pub"));
        ObservableList<publicite> data = FXCollections.observableArrayList(se.selectPub());
        Tab_pub.setItems(data);

    }

    @FXML
    private void modifpub_onClick(ActionEvent event) {
        System.out.println("Pub prete a modifier !");
        ServicePub se = new ServicePub();
        // int a=Integer.parseInt(titre_pub.getText());

        Image image1 = new Image("file:" + se.modifierPub(titre_pub.getText(), 3));
        System.out.println(se.modifierPub(titre_pub.getText(), 3));
        img_pub.setImage(image1);

        pathString = (se.modifierPub(titre_pub.getText(), 3));
        site_pub.setText(se.modifierPub(titre_pub.getText(), 4));
        desc_pub.setText(se.modifierPub(titre_pub.getText(), 5));
        //afficher();
    }

    @FXML
    private void validpub_onClick(ActionEvent event) {
        System.out.println("Modification terminée !");
        ServicePub se = new ServicePub();
        //   int a=Integer.parseInt(se.modifierPub(titre_pub.getText(), 1));
        //System.out.println(a);
        se.modifier2Pub(titre_pub.getText(), site_pub.getText(), desc_pub.getText(), pathString, ServicePub.getId_pub());
        // afficher();
        System.out.println("Affichage du tableau !");
        //se.selectPub();
        titrepub.setCellValueFactory(new PropertyValueFactory<publicite, String>("titre_pub"));
        sitepub.setCellValueFactory(new PropertyValueFactory<publicite, String>("site_pub"));
        descpub.setCellValueFactory(new PropertyValueFactory<publicite, String>("desc_pub"));
        ObservableList<publicite> data = FXCollections.observableArrayList(se.selectPub());
        Tab_pub.setItems(data);
    }

    @FXML
    private void imgpub_onClick(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image pour votre pub");
        Window stage = null;
        File file;
        file = fileChooser.showOpenDialog(stage);
        pathString = "src/" + file.getName();
        Image image = new Image("file:" + file.getPath());

        Path pth = file.toPath();

        File resourcesDirectory = new File("src/" + file.getName());
        Files.deleteIfExists(resourcesDirectory.toPath());
        Files.copy(pth, resourcesDirectory.toPath(), StandardCopyOption.COPY_ATTRIBUTES);
        img_pub.setImage(image);

    }

    @FXML
    private void ajouterEvent_onClick(ActionEvent event) throws IOException {
        EvenementController.add_modif = 1;
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Evenements.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void modifierEvent_onClick(ActionEvent event) {
        EvenementController.add_modif = 2;
        try {
            EventGrid eg = TabView_Event.getSelectionModel().getSelectedItem();
            if (eg == null) {
                AlertHelper.showAlert(Alert.AlertType.WARNING, TabView_Event.getScene().getWindow(), "erreur!", " Veuillez selectionner un evenement!");
            }
            int ide = eg.getId_event();

            if (ide != -1) {
                EvenementController.id_event = ide;

                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("Evenements.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                AlertHelper.showAlert(Alert.AlertType.WARNING, TabView_Event.getScene().getWindow(), "erreur!", " Veuillez selectionner un plan!");
            }
            //stage.setMaximized(true); 

        } catch (Exception e) {
        }

    }

    @FXML
    private void supprimerEvent_onClick(ActionEvent event) {
        try {
            EventGrid eg = TabView_Event.getSelectionModel().getSelectedItem();
            if (eg == null) {
                AlertHelper.showAlert(Alert.AlertType.WARNING, TabView_Event.getScene().getWindow(), "erreur!", " Veuillez selectionner un evenement!");
            } else {
                ServiceEvent se = new ServiceEvent();
                se.supprimerEvent(eg.getId_event());
                AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, TabView_Event.getScene().getWindow(), "Welcome ", "Evenement Supprimé avec Succée!");
                List<EventGrid> events = se.selectEventGrid(3);
                TabView_Event.getItems().setAll(events);
            }
        } catch (Exception e) {
        }
    }
}
