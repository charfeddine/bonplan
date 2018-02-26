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
 * @author chaima
 */
public class PlanGrid {
    private final SimpleIntegerProperty id_p = new SimpleIntegerProperty(0);
    private final SimpleStringProperty libelle = new SimpleStringProperty("");
    private final SimpleStringProperty description = new SimpleStringProperty("");
    private final SimpleStringProperty prix = new SimpleStringProperty("");
    private final SimpleStringProperty adresse = new SimpleStringProperty("");
      private final SimpleStringProperty Ville = new SimpleStringProperty("");
    private final SimpleStringProperty TypePlan = new SimpleStringProperty("");
 private final SimpleStringProperty Etat = new SimpleStringProperty(""); 
  private final SimpleStringProperty Position = new SimpleStringProperty(""); 
    private final SimpleIntegerProperty id_u = new SimpleIntegerProperty(0); 
      private final SimpleStringProperty Utisateur = new SimpleStringProperty(""); 
    public PlanGrid() {

    }
 
    public PlanGrid(int id_p, String libelle, String description, String prix,
            String adresse, String TypePlan,String Etat,String Position,String Ville,
            int id_u,String Utisateur) {

        setid_p(id_p);
        setlibelle(libelle);
        setdescription(description);
        setprix(prix);
        setadresse(adresse);
        setTypePlan(TypePlan);
       setEtat(Etat);
        setPosition(Position);
          setVille(Ville);
           setid_u(id_u);
            setUtisateur(Utisateur);
    }
     public void setUtisateur( String Utisat) {
      Utisateur.set(Utisat);
    }
      public void setid_u(int idu  ) {
        id_u.set(idu);  
    }
    public String getUtisateur() {
        return Utisateur.get();
    }public int getid_u() {
        return id_u.get();
    }
    
     public void setVille(String Vile ) {
        Ville.set(Vile);
    }
    public void setPosition(String Pos ) {
        Position.set(Pos);
    }
 public void setEtat(String Eta ) {
        Etat.set(Eta);
    }
    public void setid_p(int id) {
        id_p.set(id);
    }

    public void setlibelle(String lib) {
        libelle.set(lib);
    }

    public void setdescription(String desc) {
        description.set(desc);
    }

    public String getTypePlan() {
        return TypePlan.get();
    }

    public void setTypePlan(String typp) {
        TypePlan.set(typp);
    }

    public void setprix(String pr) {
        prix.set(pr);
    }

    public void setadresse(String adr) {
        adresse.set(adr);
    }

    public int getId_p() {
        return id_p.get();
    }

    public String getLibelle() {
        return libelle.get();
    }

    public String getDescription() {
        return description.get();
    }

    public String getPrix() {
        return prix.get();
    }

    public String getAdresse() {
        return adresse.get();
    }
 public String getEtat() {
        return Etat.get();
    }
  public String getPosition() {
        return Position.get();
    }
  public String getVille() {
        return Ville.get();
    }
  
}
