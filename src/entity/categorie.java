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
public class categorie {
     private int id_c; 
  private String libelle;
    private String Desc;

    public categorie() {
    }

    public categorie(int id_c, String libelle, String Desc) {
        this.id_c = id_c;
        this.libelle = libelle;
        this.Desc = Desc;
    }

    public int getId_c() {
        return id_c;
    }

    public void setId_c(int id_c) {
        this.id_c = id_c;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String Desc) {
        this.Desc = Desc;
    }
    
   @Override
    public String toString() {
        return libelle;
    }

}
