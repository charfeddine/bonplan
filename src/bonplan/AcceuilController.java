/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bonplan;

import Services.ServicePub;
import entity.publicite;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author a7med
 */
public class AcceuilController implements Initializable {

    @FXML
    private Button connect;
    @FXML
    private GridPane gridaffichage;
    @FXML
    private TextField recherche_champ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Services.ServicePub se = new ServicePub();
        List<publicite> publicites = new ArrayList<>();
        publicites = se.selectPub();
        int j = 0;

        ColumnConstraints colConstraint = new ColumnConstraints(120);
        colConstraint.setHalignment(HPos.LEFT);

        RowConstraints rowConstraints = new RowConstraints(130);
        rowConstraints.setValignment(VPos.CENTER);
        /* ScrollPane scroll = new ScrollPane(gridaffichage);
    scroll.isVisible();
    scroll.setFitToWidth(true);
   scroll.setFitToHeight(true);
    scroll.setPrefSize(240, 200);
    //scroll.setMinSize(240, 200);
    scroll.setMaxSize(240, 200);*/
        // gridaffichage.getColumnConstraints().addAll(colConstraint, colConstraint, colConstraint);

        for (publicite e : publicites) {
            if (j < 5) {
                Image i = new Image("file:" + e.getImg_pub());
                /**/

                //  Image i1 = new Image("file:"+e.getImg_pub());
                ImageView imageView = new ImageView();
                imageView.setFitHeight(180);
                imageView.setFitWidth(220);
                imageView.setImage(i);
                //slide_image.setImage(i);
                //gridaffichage.add(imageView, j, 0);
                Label label = new Label(e.getTitre_pub());
                label.setContentDisplay(ContentDisplay.TOP);
                label.setGraphic(imageView);
                /**
                 * *
                 */
                gridaffichage.add(label, j, 0);
                j++;

            }
        }

    }

    @FXML
    private void seConnecter(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        stage.setMaximized(true);

        Stage stage2 = (Stage) connect.getScene().getWindow();
        stage2.close();
         
    }

    @FXML
    private void recherche_plan(ActionEvent event) {
        
            gridaffichage.getChildren().clear();
            ServicePub se = new ServicePub();
            List<publicite> publicites = new ArrayList<>();
            publicites = se.selectPub();
            int j = 0;
            int k = 0;
            ColumnConstraints colConstraint = new ColumnConstraints(120);
            colConstraint.setHalignment(HPos.LEFT);

            RowConstraints rowConstraints = new RowConstraints(130);
            rowConstraints.setValignment(VPos.CENTER);
            for (publicite e : publicites) {
                if (e.getTitre_pub().contains(recherche_champ.getText())) {
                    System.out.println("ok !!!!");

                    Image i = new Image("file:" + e.getImg_pub());
                    ImageView imageView = new ImageView();
                    imageView.setFitHeight(180);
                    imageView.setFitWidth(220);
                    imageView.setImage(i);

                    //gridaffichage.add(imageView, j, 0);
                    Label label = new Label(e.getTitre_pub());
                    label.setContentDisplay(ContentDisplay.TOP);
                    label.setGraphic(imageView);
                    if (j < 5) {
                        gridaffichage.add(label, j, 0);
                        j++;
                    } else {

                        gridaffichage.add(label, k, 1);
                        k++;
                  

                }
            }
        }
    }


    @FXML
    private void recherche_entree(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            gridaffichage.getChildren().clear();
            ServicePub se = new ServicePub();
            List<publicite> publicites = new ArrayList<>();
            publicites = se.selectPub();
            int j = 0;
            int k = 0;
            ColumnConstraints colConstraint = new ColumnConstraints(120);
            colConstraint.setHalignment(HPos.LEFT);

            RowConstraints rowConstraints = new RowConstraints(130);
            rowConstraints.setValignment(VPos.CENTER);
            for (publicite e : publicites) {
                if (e.getTitre_pub().contains(recherche_champ.getText())) {
                    System.out.println("ok !!!!");

                    Image i = new Image("file:" + e.getImg_pub());
                    ImageView imageView = new ImageView();
                    imageView.setFitHeight(180);
                    imageView.setFitWidth(220);
                    imageView.setImage(i);

                    //gridaffichage.add(imageView, j, 0);
                    Label label = new Label(e.getTitre_pub());
                    label.setContentDisplay(ContentDisplay.TOP);
                    label.setGraphic(imageView);
                    if (j < 5) {
                        gridaffichage.add(label, j, 0);
                        j++;
                    } else {

                        gridaffichage.add(label, k, 1);
                        k++;
                    }

                }
            }
        }
    }

}
