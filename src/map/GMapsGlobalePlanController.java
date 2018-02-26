package map;
 
import entity.plan;
import Services.ServicePlan;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Duration;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author chaima
 */
public class GMapsGlobalePlanController implements Initializable, MapComponentInitializedListener {

    @FXML
    private GoogleMapView mapView;
    private GoogleMap map;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
    }

    @Override
    public void mapInitialized() {
        // LatLong joeSmithLocation = new LatLong(36.8475700, 11.0938600); 
        //LatLong darnaLocation = new LatLong(36.835746, 11.115755); 
        MapOptions mapOptions = new MapOptions();
        mapOptions.center(new LatLong(36.8475700, 11.0938600))
                .mapType(MapTypeIdEnum.TERRAIN)
                .mapTypeControl(true)
                .overviewMapControl(true)
                .panControl(true)
                .rotateControl(true)
                .scaleControl(true)
                .streetViewControl(true)
                .zoomControl(true)
                .zoom(8);
        map = mapView.createMap(mapOptions);

        ServicePlan ps = new ServicePlan();
        //FXMLController.longitude=entry.getLongitude();  GMapsController.latitude=entry.getLatitude();

        List<plan> dataplan = FXCollections.observableArrayList();

        dataplan = ps.selectPlanByType(-1, -1);
        dataplan.forEach(new Consumer<plan>() {
            @Override
            public void accept(plan plan) {
                MarkerOptions markerOptions = new MarkerOptions();

                LatLong Location = new LatLong(plan.getLatitude(), plan.getLongitude());
                MarkerOptions markerOptions1 = new MarkerOptions();
                markerOptions1.position(Location);
                URL url = getClass().getResource("/ressources/markers.png");
                if(url!=null)
                markerOptions1.icon(url.getPath() );
                Marker mapMarker = new Marker(markerOptions1);

                map.addMarker(mapMarker);
                InfoWindowOptions infoWindowdana = new InfoWindowOptions();
               
                
                
                infoWindowdana.content("<h4>" + plan.getLibelle() + "</h4>"
                        + "Adresse: " + plan.getAdresse() + "<br>");
                InfoWindow darnaInfoWindow = new InfoWindow(infoWindowdana);
                darnaInfoWindow.open(map, mapMarker);
            }
        });

        //tableView.getItems().setAll(dataps);
        //Set the initial properties of the map.
        //Add markers to the map
        //markerOptionsdarna.title("chz kelibia"); 
    }

    @FXML
    public void Satelite(ActionEvent actionEvent) {
        map.setMapType(MapTypeIdEnum.SATELLITE);
    }

    @FXML
    public void Hybrid(ActionEvent actionEvent) {
        map.setMapType(MapTypeIdEnum.HYBRID);
    }

    @FXML
    public void Road(ActionEvent actionEvent) {
        map.setMapType(MapTypeIdEnum.ROADMAP);
    }

    @FXML
    public void Terrain(ActionEvent actionEvent) {
        map.setMapType(MapTypeIdEnum.TERRAIN);
    }

}
