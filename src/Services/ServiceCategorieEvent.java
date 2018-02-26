/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DBAccess.DataSource;
import entity.Evenement;
import entity.CategorieEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Linab
 */
public class ServiceCategorieEvent {
      DataSource db;
    Connection cnx;
    Statement st;

    public ServiceCategorieEvent() {
        cnx = db.getInstance().getConnection();
        try {
            st = (Statement) cnx.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorieEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void ajouterCategorieEvent(CategorieEvent c) throws SQLException {
        
        
     
     try {
     
     
     String req = "INSERT INTO `categorieevent`(`id_Cat`, `libelle`)"
                            + "values(?,?)";

                    PreparedStatement preparedStatement;
                    preparedStatement = cnx.prepareStatement(req);
                    preparedStatement.setInt(1,c.getId_cat());
                    preparedStatement.setString(2,c.getLibelle()); 
                    preparedStatement.executeUpdate();
                     System.out.println(" Catégorie Evennement Ajoutée avec succes ok ! !");

                    // st.executeUpdate(req);
                } catch (SQLException ex) {
                    Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
                }
     }
     
     public void modifierEvent(CategorieEvent c){
              try {
                    String req = "UPDATE `categorieevent` SET `libelle`=? WHERE `id_Cat`=?";
                            
                    PreparedStatement preparedStatement;
                    preparedStatement = cnx.prepareStatement(req);
                    preparedStatement.setString(1, c.getLibelle());
                    preparedStatement.setInt(1,c.getId_cat());
                    
                    preparedStatement.executeUpdate();
                    System.out.println(" Catégorie modifié ok ! !");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                };
     
        }
     
      public void supprimerPlan(Integer i) {
        try {
            String req = "delete from categorieevent where id_Cat =?";
            PreparedStatement preparedStatement;
            preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setInt(1, i);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
      
      public List<CategorieEvent> getAllCategorieEvent() {
        CategorieEvent catEvent = null;
        List<CategorieEvent> catEvents = new ArrayList<>();
        String req = "SELECT * FROM `categorieevent`";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = cnx.prepareStatement(req);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                catEvent = new CategorieEvent(resultSet.getInt(1), resultSet.getString(2));
                catEvents.add(catEvent);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return catEvents;
    }
     
    
    
    
}
