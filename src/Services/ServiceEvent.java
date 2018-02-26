/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DBAccess.DataSource;
import entity.Evenement;
import entity.EventGrid;
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
public class ServiceEvent {
      DataSource db;
    Connection cnx;
    Statement st;

    public ServiceEvent() {
        cnx = db.getInstance().getConnection();
        try {
            st = (Statement) cnx.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public int ajouterEvent(Evenement e) throws SQLException {
        try {
            String req = "INSERT INTO `evennement`(`titre`, `adresse`, `date_event`, `description`, `image`, `prix`, `contact`, `id_Cat`, `id_u`) "
                            + "values(?,?,?,?,?,?,?,?,?)";

                    PreparedStatement preparedStatement;
                    preparedStatement = cnx.prepareStatement(req);
                    preparedStatement.setString(1, e.getTitre());
                    preparedStatement.setString(2, e.getAdresse());
                    preparedStatement.setDate(3, e.getDate_event());
                    preparedStatement.setString(4, e.getDescription());
                    preparedStatement.setString(5, e.getImage());
                    preparedStatement.setInt(6, e.getPrix());
                    preparedStatement.setString(7, e.getTel());
                    preparedStatement.setInt(8,e.getId_cat());
                    preparedStatement.setInt(9,e.getId_user());
                   
                    int i = preparedStatement.executeUpdate();
                    System.out.println(" Evenement Ajouté avec succes ok ! !");
                    return i;

                    // st.executeUpdate(req);
                } catch (SQLException ex) {
                    Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
                    return -1;
                }
     }
     
     public int modifierEvent(Evenement e){
       
                try {
                    String req = "UPDATE `evennement` SET `titre`=?,`adresse`=?,`date_event`=?,"
                            + "`description`=?,`image`=?,`prix`=?,`contact`=?,`id_Cat`=?,`id_u`=? "
                            + "WHERE `id_event`=?";
                            
                    PreparedStatement preparedStatement;
                    preparedStatement = cnx.prepareStatement(req);
                    preparedStatement.setString(1, e.getTitre());
                    preparedStatement.setString(2, e.getAdresse());
                    preparedStatement.setDate(3,e.getDate_event());
                    preparedStatement.setString(4,e.getDescription());
                    preparedStatement.setString(5,e.getImage());
                    preparedStatement.setInt(6,e.getPrix());
                    preparedStatement.setString(7, e.getTel());
                    preparedStatement.setInt(8, e.getId_cat());
                    preparedStatement.setInt(9, e.getId_user());
                    preparedStatement.setInt(10, e.getId_event());
                    int i = preparedStatement.executeUpdate();
                    System.out.println(" Evenement modifié ok ! !");
                    return i;
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    return -1;
                }
     
        }
     
      public void supprimerEvent(Integer i) {
        try {
            String req = "delete from evennement where id_event =?";
            PreparedStatement preparedStatement;
            preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setInt(1, i);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
      
    public List<Evenement> getAllMyEvent(long id_user) {
        Evenement event = null;
        List<Evenement> events = new ArrayList<>();
        String req = "SELECT * FROM `evennement` WHERE `id_u`=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setLong(1, id_user);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                event = new Evenement(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getDate(6),resultSet.getInt(7),resultSet.getString(8),resultSet.getInt(9),resultSet.getInt(10));
                events.add(event);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return events;
    }
      
   public Evenement selectEventItem (int id_event) { 
        try {
                Evenement e = new Evenement();
            ResultSet rest = st.executeQuery("select * from evennement e where e.id_event = " +id_event);
            while (rest.next()) {
              
                e.setId_event(rest.getInt(1));
                e.setTitre(rest.getString(2));
                e.setAdresse(rest.getString(3));
                e.setDate_event(rest.getDate(4));
                e.setDescription(rest.getString(5)); 
                e.setImage(rest.getString(6));
                e.setPrix(rest.getInt(7));
                e.setTel(rest.getString(8));
                e.setId_cat(rest.getInt(9));
                e.setId_user(rest.getInt(10));
                 
            }
            return  e;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

   public List<EventGrid> selectEventGrid(int id_u) {
        List<EventGrid> events = new ArrayList<>();
        try {
            ResultSet rest = st.executeQuery("SELECT `id_event`, `titre`, `adresse`, `prix` FROM `evennement` WHERE `id_u` = " + id_u);
            while (rest.next()) {
                EventGrid e = new EventGrid();
                e.setId_event(rest.getInt(1));
                e.setTitre(rest.getString(2));
                e.setAdresse(rest.getString(3));
                e.setPrix(rest.getInt(4));
                events.add(e);
            }
            return events;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
}
