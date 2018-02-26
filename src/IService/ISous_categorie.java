/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import entity.sous_categorie;
import java.util.List;

/**
 *
 * @author chaima
 */
public interface ISous_categorie {
     public int ajoutersc(sous_categorie sc);
    public void supprimersc(int id_sc);
 public List<sous_categorie> select_Sous_categorie(int id_c);
}