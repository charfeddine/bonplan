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
import javafx.collections.FXCollections;  
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable; 
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import jdk.nashorn.internal.objects.NativeArray;

public class GMapsController implements Initializable, MapComponentInitializedListener {

    public Label bTest;

    @FXML
    private GoogleMapView mapView;
 
    private GoogleMap map;
    private GeocodingService G;
    public static double longitude;
    public static double latitude;
    public static int idsc;
    public static int idc;
    public static int id_p = -1;
    @FXML
    private VBox VBoxMain;
    @FXML
    private TextField txtlib;
    @FXML
    private TextField txtadr;
    @FXML
    private TextField txtville;
    @FXML
    private TextField txtpx;
    @FXML
    private TextArea txtdescr;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
    }
   public void createMap(){
          map = new GoogleMap();
        G = new GeocodingService();
        MapOptions mapOptions = new  MapOptions();
          mapOptions.center(new LatLong(36.8475700, 11.0938600))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(true)
                .panControl(true)
                .rotateControl(true)
                .scaleControl(true)
                .streetViewControl(true)
                .zoomControl(true)
                .zoom(14);

        map = mapView.createMap(mapOptions);
    }
    @Override
    public void mapInitialized() {
        // LatLong joeSmithLocation = new LatLong(36.8475700, 11.0938600); 
        //LatLong darnaLocation = new LatLong(36.835746, 11.115755); 
//        MapOptions mapOptions = new MapOptions();
//        mapOptions.center(new LatLong(36.8475700, 11.0938600))
//                .mapType(MapTypeIdEnum.TERRAIN)
//                .mapTypeControl(false)
//                .overviewMapControl(false)
//                .panControl(false)
//                .rotateControl(false)
//                .scaleControl(false)
//                .streetViewControl(false)
//                .zoomControl(false)
//                .zoom(14);
//        map = mapView.createMap(mapOptions);
  createMap();
        ServicePlan ps = new ServicePlan();
        //FXMLController.longitude=entry.getLongitude();  GMapsController.latitude=entry.getLatitude();

        List<plan> dataplan = FXCollections.observableArrayList();
        if (GMapsController.id_p != -1) {
            plan p = ps.selectPlanItem(GMapsController.id_p);
            dataplan.add(p);
            txtlib.setText(p.getLibelle());
            txtadr.setText(p.getAdresse());
            txtville.setText(p.getVille());
            txtpx.setText(p.getPrix());
            txtdescr.setText(p.getDescription());
            // p.setLongitude(GMapsController.longitude);
            // p.setLatitude(GMapsController.latitude);

        } else {
            dataplan = ps.selectPlanByType(GMapsController.idc, GMapsController.idsc);
        }
        dataplan.forEach((plan) -> {

            LatLong Location = new LatLong(plan.getLatitude(), plan.getLongitude());
            MarkerOptions markerOptions1 = new MarkerOptions();
            markerOptions1.position(Location);
            Marker mapMarker = new Marker(markerOptions1);
            map.addMarker(mapMarker);
            map.setCenter(Location);
            InfoWindowOptions infoWindowdana = new InfoWindowOptions();
            infoWindowdana.content("<h2>" + plan.getLibelle() + "</h2>"
                    + "Current Location: " + plan.getAdresse() + "<br>");
            InfoWindow darnaInfoWindow = new InfoWindow(infoWindowdana);
            darnaInfoWindow.open(map, mapMarker);

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
