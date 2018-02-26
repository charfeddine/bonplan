/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bonplan;

import Services.ServicePlan;
import static bonplan.ConsultationPlanController.id_Sc;
import static bonplan.ConsultationPlanController.id_c;
import entity.PlanGrid;
import entity.plan;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.imageio.ImageIO;
import map.GMapsController;

/**
 * FXML Controller class
 *
 * @author chaima
 */
public class consultation_ImgController implements Initializable {

    public static int id_c = -1;
    public static int id_Sc = -1;
       public static String titre ="";
    @FXML
    private ScrollPane flowScrollPane;

    private LoadImageTask loadImageTask;
    @FXML
    private AnchorPane Main;
    @FXML
    private TilePane Imgcharge;
    @FXML
    private Label LblTitre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            LblTitre.setText(titre);
            Imgcharge.prefWidthProperty().bind(flowScrollPane.widthProperty());
            loadImages();
        } catch (Exception e) {
        }
    }

    private void loadImages() {
        // Annuler une fois le déplacement lors du chargement des images 
        if (loadImageTask != null && loadImageTask.isDone() == false) {
            loadImageTask.onCancelled = () -> {
                Platform.runLater(this::startLoadImages);
            };
            loadImageTask.cancel();
            loadImageTask = null;
            return;
        }
        startLoadImages();
    }

    private void startLoadImages() {

        Imgcharge.getChildren().clear();
        loadImageTask = new LoadImageTask();
        Thread t = new Thread(loadImageTask);
        t.setDaemon(true);
        t.start();
    }

    class LoadImageTask extends Task<Void> {

        Runnable onCancelled;
        int thumbnailStartIndex;

        @Override
        protected Void call() throws Exception {

            int index = 0;
            thumbnailStartIndex = index;

            ServicePlan ps = new ServicePlan();

            FileInputStream fileInputStream = null;
            // File file = new File("C:\\Users\\inf_znaidi\\Desktop\\test.jpg");

            // byte[] bFile = new byte[(int) file.length()];
            //read file into bytes[]
            // fileInputStream = new FileInputStream(file);
            //fileInputStream.read(bFile);
            ObservableList<plan> dataps = ps.selectPlanByType(consultation_ImgController.id_c, consultation_ImgController.id_Sc);
            File directory = new File(String.valueOf("C:\\bonplans"));
            if (!directory.exists()) {
                directory.mkdir();
            }
            for (plan p : dataps) {
                String pathe = "C:\\bonplans\\" + p.getLibelle() + ".jpg";
                Path path = Paths.get(pathe);
                Files.write(path, p.getImg());
                File file = new File(pathe);
                fileInputStream = new FileInputStream(file);
                fileInputStream.read(p.getImg());
                createImage(p, index);
                index++;
            }
            return null;
        }

        private void createImage(plan plans, final int index) throws IOException {
            String descp_button = plans.getLibelle().toString()+
                    "\n "+plans.getAdresse().toString();
            final ImageView imageView = createImageView(plans);
            final String fileName = descp_button ;

            final Button button = new Button(fileName);
            button.setPrefHeight(110.0);
            button.setPrefWidth(130.0);
            button.setGraphic(imageView);
            button.setContentDisplay(ContentDisplay.TOP);
            button.setTextAlignment(TextAlignment.JUSTIFY);
            button.getStyleClass().add("thumbnail");

            Path pth = null;

            button.setOnAction((ActionEvent t) -> {
                Platform.runLater(() -> {
//show in map 
                    // GMapsController.idsc=1;
                    Window owner = Imgcharge.getScene().getWindow();
                    try {

                        //FXMLController.longitude = plans.getLongitude();
                        //FXMLController.latitude = plans.getLatitude();
                           GMapsController.id_p = plans.getId_p();
                        Stage primaryStage = new Stage();
                        Parent root = FXMLLoader.load(getClass().getResource("/map/GMaps.fxml"));
                        primaryStage.setTitle("Google Maps");
                        primaryStage.setScene(new Scene(root, 1000, 700));
                        primaryStage.show();
                    } catch (Exception e) {
                        AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", e.getMessage());
                    }

                });
            });

            Platform.runLater(
                    () -> {
                        final Tooltip tooltip = new Tooltip(fileName);
                        tooltip.getStyleClass().add("filenameTooltip");
                        button.setTooltip(tooltip);
                        button.setGraphic(imageView);
                        Imgcharge.getChildren().add(button);
                    });
        }

        private ImageView createImageView(plan entry) throws IOException {

            byte[] source = entry.getImg();
            ByteArrayInputStream bis = new ByteArrayInputStream(source);
            Image image2 = new Image(bis, 180.0, 140.0, true, true);
            final ImageView imageView = new ImageView(image2);
            imageView.setCache(true);

            imageView.setFitHeight(140.0);
            imageView.setFitWidth(180.0);
            imageView.setPreserveRatio(true);

            return imageView;
        }
    }
}
