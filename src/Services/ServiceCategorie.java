/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DBAccess.DataSource;
import IService.ICategorie;
import entity.categorie;
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
public class ServiceCategorie implements ICategorie {
    Connection con = DataSource.getInstance().getConnection();
    private Statement ste;

    public ServiceCategorie() {
        try {
            ste = con.createStatement();
        } catch (SQLException exx) {
            System.out.println(exx);
        }
 
}

    @Override
    public List<categorie> select_categorie() {
          try { 
            List<categorie> Ss_c = new ArrayList<>();
             categorie  sc = new categorie();
            ResultSet rest = ste.executeQuery("SELECT * FROM  categorie ");
            while (rest.next()) { 
                sc.setId_c(rest.getInt(1));
                sc.setLibelle(rest.getString(2));
                sc.setDesc(rest.getString(3)); 
              Ss_c.add(sc) ; 
              sc = new categorie();
            }
            return Ss_c;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
            return null;    
        }
    } //To change body of generated methods, choose Tools | Templates.
    
    

}
