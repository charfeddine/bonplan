/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DBAccess.DataSource;
import IService.ISous_categorie;
import entity.sous_categorie;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chaima
 */
public class ServiceSous_categorie implements ISous_categorie{
Connection con = DataSource.getInstance().getConnection();
    private Statement ste;

    public ServiceSous_categorie() {
        try {
            ste = con.createStatement();
        } catch (SQLException exx) {
            System.out.println(exx);
        }

    }
    @Override
    public int ajoutersc(sous_categorie sc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimersc(int id_sc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<sous_categorie> select_Sous_categorie(int id_c) {
       
        try { 
            List<sous_categorie> Ss_c = new ArrayList<>();
             sous_categorie  sc = new sous_categorie();
            ResultSet rest = ste.executeQuery("SELECT * FROM  sous_categorie  WHERE  id_c ="+id_c);
            while (rest.next()) { 
                sc.setId_sc(rest.getInt(1));
                sc.setLibelle(rest.getString(2));
                sc.setDesc(rest.getString(3));
                 sc.setId_c(rest.getInt(4));
              Ss_c.add(sc) ; 
              sc = new sous_categorie();
            }
            return Ss_c;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSous_categorie.class.getName()).log(Level.SEVERE, null, ex);
            return null;    
        }
    }
    
    //To change body of generated methods, choose Tools | Templates.
    }
    

