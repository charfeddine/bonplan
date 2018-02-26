/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ben-h
 */
public class EventGrid {
    private final SimpleIntegerProperty id_event = new SimpleIntegerProperty(0);
    private final SimpleStringProperty titre = new SimpleStringProperty("");
    private final SimpleStringProperty adresse = new SimpleStringProperty("");
    private final SimpleIntegerProperty prix = new SimpleIntegerProperty(0);

    public EventGrid() {
    }

    
    public EventGrid(int id_event, String titre, String adresse, int prix) {
        setId_event(id_event);
        setTitre(titre);
        setAdresse(adresse);
        setPrix(prix);
    }
    
    public void setId_event(int id){
        id_event.set(id);
    }
    
    public void setTitre(String titr){
        titre.set(titr);
    }
    
    public void setAdresse(String adr){
        adresse.set(adr);
    }

    public void setPrix(int prx){
        prix.set(prx);
    }
     
    public int getId_event() {
        return id_event.get();
    }
    
    public String getAdresse() {
        return adresse.get();
    }
    
    public String getTitre() {
        return titre.get();
    }
    
    public int getPrix() {
        return prix.get();
    }

    
    
}
