/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bonplan;

import com.lynden.gmapsfx.GoogleMapView;
import entity.CategorieEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Linab
 */
public class SearchEventController implements Initializable {

    @FXML
    private Pane Pane_event;
    @FXML
    private GoogleMapView gmaps;
    @FXML
    private Button btn_searhEvent;
    @FXML
    private Slider slide_event;
    @FXML
    private ComboBox<CategorieEvent> combo_event;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
     ObservableList<CategorieEvent> combo_eventData = FXCollections.observableArrayList();
     combo_eventData.add(new CategorieEvent(1,"Art et Théatre"));
     combo_eventData.add(new CategorieEvent(2,"Camping et rando"));
     combo_eventData.add(new CategorieEvent(3,"Sport et Activité"));
     combo_event.setItems(combo_eventData);
     
     combo_event.setCellFactory((comboBox) -> {
    return new ListCell<CategorieEvent>() {
        
        protected void updateItem(CategorieEvent item, boolean empty) {
            super.updateItem(item, empty);

            if (item == null || empty) {
                setText(null);
            } else {
                setText(item.getId_cat() + " " + item.getLibelle());
            }
        }
    };
});

     combo_event.setOnAction((event) -> {
    CategorieEvent selectedCat = combo_event.getSelectionModel().getSelectedItem();
    System.out.println("ComboBox Action (selected: " + selectedCat.toString() + ")");
});
     
     slide_event.valueProperty().addListener((observable, oldValue, newValue) -> {
    System.out.println("Slider Value Changed (newValue: " + newValue.intValue() + ")");
});
     
    }    

    @FXML
    private void btn_Search(ActionEvent event) {
        
    }
    
}
