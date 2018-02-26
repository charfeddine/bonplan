/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author chaima
 */
public class plan {
     private int id_p;
     private String libelle;
     private String adresse;

     private String description;
 
    private String ville;
    private String avis;

    private String email;
  
     private String prix;
    private int id_u;
    private int id_c;
    private int id_sc;
    
    private double longitude ; 
    private double latitude ; 
      private  byte[] img ; 
   private int etat ;  

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getEtatnotif() {
        return etatnotif;
    }

    public void setEtatnotif(int etatnotif) {
        this.etatnotif = etatnotif;
    }
   private int etatnotif;
    public plan(int id_p, String libelle, String prix,  byte[] img) {
        this.id_p = id_p;
        this.libelle = libelle;
        this.prix = prix;
        this.img = img;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }
   private String LibCat;
    private String LibSousCat;
    
    public plan(int id_p, String libelle, String adresse, String description,
            String ville, byte[] image, String avis, String email, String prix, double longitude,double latitude
            ,int id_u, int id_sc) {
        this.id_p = id_p;
        this.libelle = libelle;
        this.adresse = adresse;
        this.description = description;

        this.ville = ville;
        this.img = image;
        this.avis = avis;

        this.email = email;
        
        this.prix = prix;
        this.longitude = longitude;
        this.latitude = latitude;

        this.id_u = id_u;
        this.id_sc = id_sc;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public plan() {
         //To change body of generated methods, choose Tools | Templates.
    }

    public int getId_p() {
        return id_p;
    }

    public int getId_u() {
        return id_u;
    }

    public void setId_u(int id_u) {
        this.id_u = id_u;
    }

    public int getId_sc() {
        return id_sc;
    }

    public void setId_sc(int id_sc) {
        this.id_sc = id_sc;
    }

    public void setId_p(int id_p) {
        this.id_p = id_p;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvis() {
        return avis;
    }

    public void setAvis(String avis) {
        this.avis = avis;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    

    public int getId_c() {
        return id_c;
    }

    public void setId_c(int id_c) {
        this.id_c = id_c;
    }

    public String getLibCat() {
        return LibCat;
    }

    public void setLibCat(String LibCat) {
        this.LibCat = LibCat;
    }

    public String getLibSousCat() {
        return LibSousCat;
    }

    public void setLibSousCat(String LibSousCat) {
        this.LibSousCat = LibSousCat;
    }

    @Override
    public String toString() {
        return "Plan{" + "adresse=" + adresse + ", ville=" + ville + ", description=" + description + '}';
    }

}
  

