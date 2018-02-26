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
public class sous_categorie {
 private int id_sc;
      private int id_c;
  private String libelle;
    private String Desc;

    public sous_categorie() {
          //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return libelle;
    }

    

    public sous_categorie(int id_sc, int id_c, String libelle) {
        this.id_sc = id_sc;
        this.id_c = id_c;
        this.libelle = libelle;
    }
    public int getId_sc() {
        return id_sc;
    }

    public void setId_sc(int id_sc) {
        this.id_sc = id_sc;
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
  

    
}
