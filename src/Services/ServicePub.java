/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DBAccess.DataSource;
import entity.publicite;
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
 * @author a7med
 */
public class ServicePub {
      DataSource db;
     Connection cnx;
     Statement st ;
     

  //   List<equipe> equipes = new ArrayList<equipe>();
 List<publicite> publicites = new ArrayList<>();
    public ServicePub() {
        
       cnx = db.getInstance().getConnection();
         try {
             st= (Statement) cnx.createStatement();
         } catch (SQLException ex) {
             Logger.getLogger(ServicePub.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    public void ajouterPub(String titre,String site_web,String img,String desc,int id_u)
    {
         
         try {
            
             String req ="INSERT INTO publicite (image,text, site_web, description, id_u) "
                     + "VALUES ('"+img+"','" + titre+"', '" + site_web + "', '" + desc + "',"+id_u+")";
              
             st.executeUpdate(req);
         } catch (SQLException ex) {
             Logger.getLogger(ServicePub.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    
   
    public List<publicite> selectPub() {
       
          
        try {
           
            ResultSet rest= 
                    st.executeQuery("select * from publicite");
            while(rest.next()){
                publicite e = new publicite();
               e.setId_pub(rest.getString(1));
                 e.setImg_pub(rest.getString(2));
                e.setTitre_pub(rest.getString(3));
                e.setSite_pub(rest.getString(4));
                e.setDesc_pub(rest.getString(5));
               e.setId_u(rest.getString(6));
               
              publicites.add(e);
            
             
                                
            }
               
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicePub.class.getName()).log(Level.SEVERE, null, ex);
        }
      for(publicite e : publicites){
            String ch ="id: "+e.getId_pub()+" img: "+e.getImg_pub()+ " titre: "+e.getTitre_pub()+" description: "+e.getDesc_pub();
            
          System.out.println(ch);

       //  return equipes;
    }
   return publicites;

   
    
}
       
    public void supprimerPub(String titre)
    {
         
         try {
            String req ="delete from publicite where text like '"+titre+"'"; 
                    
             st.executeUpdate(req);
         } catch (SQLException ex) {
             Logger.getLogger(ServicePub.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    String id;
       String titre;
    String desc;
    String img;
    String site;
     public String modifierPub(String titre_pub,int check)
    {
      
           try {
           
            ResultSet rest= 
                    st.executeQuery("select * from publicite where text='"+titre_pub+"'");
            while(rest.next()){
                publicite e = new publicite();
                e.setId_pub(rest.getString(1));
                e.setImg_pub(rest.getString(2));
               e.setTitre_pub(rest.getString(3));
                e.setSite_pub(rest.getString(4));
                e.setDesc_pub(rest.getString(5));
               
                publicites.add(e);
                
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ServicePub.class.getName()).log(Level.SEVERE, null, ex);
        }
            

          for(publicite e : publicites){
              id=e.getId_pub();
        titre = e.getTitre_pub();
              desc=   e.getDesc_pub();
              site= e.getSite_pub();
              img = e.getImg_pub();
        
    }
          id_pub=Integer.parseInt(id);
    switch(check){
        case 1:return id;
         case 2:return titre;
        case 3:return img;
        case 4:return site;
        case 5:return desc;
    }
           
      return id;
    }

      public void modifier2Pub(String titre,String site_web,String desc,String img,int id)
    {
         
         try {
             
            String req ="update publicite set text='"+titre+"',description='"+desc+"',image='"+img+"'"
                    + ",site_web='"+site_web+"' where id_pub='"+id+"'"; 
                    
             st.executeUpdate(req);
         } catch (SQLException ex) {
             Logger.getLogger(ServicePub.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
      
      
      public static int id_pub;
      public static int getId_pub(){
      return id_pub;
  }
      
    
}
