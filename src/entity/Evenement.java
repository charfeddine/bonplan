/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;



/**
 *
 * @author ben-h
 */
public class Evenement {
    private int id_event;
    private String titre;
    private String adresse;
    private String description;
    private String image;
    private Date date_event;
    private int prix;
    private String tel;
    private int id_user;
    private int id_cat;

    public Evenement() {
    }

    
    public Evenement(int id_event, String titre, String adresse, String description, String image, Date date_event, int prix, String tel, int id_user, int id_cat) {
        this.id_event = id_event;
        this.titre = titre;
        this.adresse = adresse;
        this.description = description;
        this.image = image;
        this.date_event = date_event;
        this.prix = prix;
        this.tel = tel;
        this.id_user = id_user;
        this.id_cat = id_cat;
    }

    public int getId_event() {
        return id_event;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDate_event() {
        return date_event;
    }

    public void setDate_event(Date date_event) {
        this.date_event = date_event;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id_event=" + id_event + ", titre=" + titre + ", adresse=" + adresse + ", description=" + description + ", image=" + image + ", date_event=" + date_event + ", prix=" + prix + ", tel=" + tel + ", id_user=" + id_user + ", id_cat=" + id_cat + '}';
    }

    
    
}
