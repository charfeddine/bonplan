/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DBAccess.DataSource;
import IService.Iplan;
import entity.PlanGrid;
import entity.plan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author chaima
 */
public class ServicePlan implements Iplan {

    Connection con = DataSource.getInstance().getConnection();
    private Statement ste;

    public ServicePlan() {
        try {
            ste = con.createStatement();
        } catch (SQLException exx) {
            System.out.println(exx);
        }

    }

    @Override
    public int ajouterPlan(plan p) {
        try {
            String req;
            //To change body of generated methods, choose Tools | Templates.
            req = "INSERT INTO plan (libelle, adresse, description, ville, image, avis, email, prix,longitude,latitude, id_u, id_sc) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,? )";
            //  "('"+e.getId()+"','"+e.getNom()+"','"+e.getPrenom()+"')";

            PreparedStatement pre = con.prepareStatement(req);
            // pre.setInt(1,e.getId());
            pre.setString(1, p.getLibelle());
            pre.setString(2, p.getAdresse());
            pre.setString(3, p.getDescription());
            pre.setString(4, p.getVille());
            pre.setBytes(5, p.getImg());
            pre.setString(6, p.getAvis());
            pre.setString(7, p.getEmail());
            pre.setString(8, p.getPrix());
            pre.setDouble(9, p.getLongitude());
            pre.setDouble(10, p.getLatitude());
            pre.setInt(11, p.getId_u());
            pre.setInt(12, p.getId_sc());

            int i = pre.executeUpdate();
            return i;

        } catch (SQLException ex) {
            Logger.getLogger(ServicePlan.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    } //To change body of generated methods, choose Tools | Templates.

    @Override
    public int ModifPlan(plan p) {
        try {
            String req;
            //To change body of generated methods, choose Tools | Templates.
            req = "Update plan set libelle='" + p.getLibelle() + "', adresse='" + p.getAdresse() + "', description='" + p.getDescription() + "'"
                    + ", ville='" + p.getVille() + "', avis='" + p.getAvis() + "', email='" + p.getEmail() + "',"
                    + " prix='" + p.getPrix() + "',longitude='" + p.getLongitude() + "',latitude='" + p.getLatitude() + "'"
                    + ", id_u='" + p.getId_u() + "', id_sc='" + p.getId_sc() + "'";
            if (p.getImg() != null) {
                req += "  , image=? ";
                //  + "'" + p.getImg() + "' ";
            }
            req += "  where id_p=" + p.getId_p();

            PreparedStatement pre = con.prepareStatement(req);
            pre.setBytes(1, p.getImg());
            //pre.setBinaryStream(1, input);
            int i = pre.executeUpdate();
            return i;

        } catch (SQLException ex) {
            Logger.getLogger(ServicePlan.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimerUser(int id) {
        try {
            String req = "delete from plan   where id_p='" + id + "'";

            ste.executeUpdate(req);
        } catch (SQLException ex) {

        }
    }

    @Override
    public List<plan> selectPlan() {
        List<plan> plans = new ArrayList<>();
        try {

            ResultSet rest = ste.executeQuery("select libelle,adresse,description from plan");
            while (rest.next()) {
                plan p = new plan();
                p.setLibelle(rest.getString(1));
                p.setAdresse(rest.getString(2));
                p.setDescription(rest.getString(3));

                System.out.println("*****************");
                plans.add(p);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ServicePlan.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (plan e : plans) {
            String ch = "Libelle: " + e.getLibelle() + " Adresse: " + e.getAdresse() + " description: " + e.getDescription();

            System.out.println(ch);

        }
        return plans;

    } //To change body of generated methods, choose Tools | Templates.

    @Override
    public List<PlanGrid> selectPlanGrid(int id_u) {
        List<PlanGrid> plans = new ArrayList<>();
        try {

            ResultSet rest = ste.executeQuery("select p.id_p, p.libelle,p.description,"
                    + " p.prix, p.adresse"
                    + ",sc.libelle,c.libelle,p.longitude,p.latitude,p.etat,p.Ville from plan p INNER join"
                    + " sous_categorie sc on p.id_sc=sc.id_sc INNER JOIN "
                    + "categorie c on sc.id_c=c.id_c where p.id_u=" + id_u);
            while (rest.next()) {
                PlanGrid p = new PlanGrid();
                p.setid_p(rest.getInt(1));
                p.setlibelle(rest.getString(2));
                p.setdescription(rest.getString(3));
                p.setprix(rest.getString(4));
                p.setadresse(rest.getString(5));
                String Scat = (rest.getString(6));
                String cat = (rest.getString(7));
                p.setTypePlan(cat + "/" + Scat);
                String longi = String.valueOf(rest.getDouble(8));
                String lat = String.valueOf(rest.getDouble(9));
                p.setPosition("Long:" + longi + "/ Lat:" + lat);
                int eta = rest.getInt(10);
                String valid = "Non Valide";
                if (eta == 1) {
                    valid = "Valide";
                }
                p.setEtat(valid);
                p.setVille(rest.getString(11));
                plans.add(p);

            }
            return plans;
        } catch (SQLException ex) {
            Logger.getLogger(ServicePlan.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public plan selectPlanItem(int id_p) {
        try {
            plan p = new plan();
            ResultSet rest = ste.executeQuery("select p.id_p, p.libelle,p.description, p.prix, "
                    + "p.adresse,p.id_sc,c.id_c"
                    + ",sc.libelle,c.libelle,p.longitude,p.latitude,p.Ville  from plan p INNER join"
                    + " sous_categorie sc on p.id_sc=sc.id_sc INNER JOIN "
                    + "categorie c on sc.id_c=c.id_c where p.id_p=" + id_p);
            while (rest.next()) {

                p.setId_p(rest.getInt(1));
                p.setLibelle(rest.getString(2));
                p.setDescription(rest.getString(3));
                p.setPrix(rest.getString(4));
                p.setAdresse(rest.getString(5));
                p.setId_sc(rest.getInt(6));
                p.setId_c(rest.getInt(7));
                p.setLibSousCat(rest.getString(8));
                p.setLibCat(rest.getString(9));
                p.setLongitude(rest.getDouble(10));
                p.setLatitude(rest.getDouble(11));
                p.setVille(rest.getString(12));
            }
            return p;
        } catch (SQLException ex) {
            Logger.getLogger(ServicePlan.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    @Override
    public List<PlanGrid> selectPlanGridByType(int id_c, int id_Sc) {
        List<PlanGrid> plans = new ArrayList<>();
        try {
            String req = "select p.id_p, p.libelle,p.description,"
                    + " p.prix, p.adresse"
                    + ",sc.libelle,c.libelle,p.longitude,p.latitude from plan p INNER join"
                    + " sous_categorie sc on p.id_sc=sc.id_sc INNER JOIN "
                    + "categorie c on sc.id_c=c.id_c where p.etat=1 ";
            if (id_Sc != -1) {
                req += " and p.id_sc=" + id_Sc;
            } else {
                req += " and p.id_c=" + id_c;
            }
            ResultSet rest = ste.executeQuery(req);
            while (rest.next()) {
                PlanGrid p = new PlanGrid();
                p.setid_p(rest.getInt(1));
                p.setlibelle(rest.getString(2));
                p.setdescription(rest.getString(3));
                p.setprix(rest.getString(4));
                p.setadresse(rest.getString(5));
                String Scat = (rest.getString(6));
                String cat = (rest.getString(7));
                p.setTypePlan(cat + "/" + Scat);

                plans.add(p);

            }
            return plans;
        } catch (SQLException ex) {
            Logger.getLogger(ServicePlan.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public ObservableList<plan> selectPlanByType(int id_c, int id_Sc) {
        ObservableList<plan> plans = FXCollections.observableArrayList();
        try {
            String req = "select p.id_p, p.libelle,p.description,"
                    + " p.prix, p.adresse"
                    + ",sc.libelle,c.libelle,p.longitude,p.latitude,p.image from plan p INNER join"
                    + " sous_categorie sc on p.id_sc=sc.id_sc INNER JOIN "
                    + "categorie c on sc.id_c=c.id_c where p.etat=1 ";
            if (id_Sc != -1) {
                req += " and p.id_sc=" + id_Sc;
            } else if (id_c != -1) {
                req += " and sc.id_c=" + id_c;
            }
            ResultSet rest = ste.executeQuery(req);
            while (rest.next()) {
                plan p = new plan();
                p.setId_p(rest.getInt(1));
                p.setLibelle(rest.getString(2));
                p.setDescription(rest.getString(3));
                p.setPrix(rest.getString(4));
                p.setAdresse(rest.getString(5));
                String Scat = (rest.getString(6));
                String cat = (rest.getString(7));
                //p.setTypePlan(cat + "/" + Scat); 
                p.setLongitude(rest.getDouble(8));
                p.setLatitude(rest.getDouble(9));
                p.setImg(rest.getBytes(10));
                plans.add(p);

            }
            return plans;
        } catch (SQLException ex) {
            Logger.getLogger(ServicePlan.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<PlanGrid> selectPlanGridValidation() {
        List<PlanGrid> plans = new ArrayList<>();
        try {

            ResultSet rest = ste.executeQuery("select p.id_p, p.libelle,p.description,"
                    + " p.prix, p.adresse"
                    + ",sc.libelle,c.libelle,p.longitude,p.latitude,p.etat,p.Ville "
                    + ",u.id_u,u.nom,u.prenom"
                    + " from plan p  INNER join user u on p.id_u=u.id_u"
                    + " INNER join sous_categorie sc on p.id_sc=sc.id_sc INNER JOIN "
                    + "categorie c on sc.id_c=c.id_c order by p.id_p desc ");
            while (rest.next()) {
                PlanGrid p = new PlanGrid();
                p.setid_p(rest.getInt(1));
                p.setlibelle(rest.getString(2));
                p.setdescription(rest.getString(3));
                p.setprix(rest.getString(4));
                p.setadresse(rest.getString(5));
                String Scat = (rest.getString(6));
                String cat = (rest.getString(7));
                p.setTypePlan(cat + "/" + Scat);
                String longi = String.valueOf(rest.getDouble(8));
                String lat = String.valueOf(rest.getDouble(9));
                p.setPosition("Long:" + longi + "/ Lat:" + lat);
                int eta = rest.getInt(10);
                String valid = "Non Valide";
                if (eta == 1) {
                    valid = "Valide";
                }
                p.setVille(rest.getString(11));
                p.setEtat(valid);
                p.setid_u(rest.getInt(12));
                  String nom = (rest.getString(13));
                String prenom = (rest.getString(14));
                p.setUtisateur(nom + " " + prenom);
                plans.add(p);

            }
            return plans;
        } catch (SQLException ex) {
            Logger.getLogger(ServicePlan.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public int UpdateValidationPlan(int idp, int etat) {
        try {
            String req;
            //To change body of generated methods, choose Tools | Templates.
            req = "Update plan set  etat=" + etat;
            if (etat == 0) {
                req += ",etatnotif=2 ";
            }
            req += " where id_p=" + idp;
            PreparedStatement pre = con.prepareStatement(req);

            int i = pre.executeUpdate();
            return i;

        } catch (SQLException ex) {
            Logger.getLogger(ServicePlan.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int UpdatePointfidelite(int idu,int pnt) {
        try {
            String req;
            //To change body of generated methods, choose Tools | Templates.
            req = "Update user set  point_fidelite=" + pnt + " where id_u=" + idu;

            PreparedStatement pre = con.prepareStatement(req);

            int i = pre.executeUpdate();
            return i;

        } catch (SQLException ex) {
            Logger.getLogger(ServicePlan.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int UpdatenotifPlan(int idp, int notif) {
        try {
            String req;
            //To change body of generated methods, choose Tools | Templates.
            req = "Update plan set  etatnotif=" + notif + " where id_p=" + idp;

            PreparedStatement pre = con.prepareStatement(req);

            int i = pre.executeUpdate();
            return i;

        } catch (SQLException ex) {
            Logger.getLogger(ServicePlan.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String selectTypeCompte(int idu) {

        try {
            String typuser = "";
            ResultSet rest = ste.executeQuery("SELECT  type  FROM  user  WHERE   id_u=" + idu);
            while (rest.next()) {
                typuser = (rest.getString(1));
            }
            return typuser;
        } catch (SQLException ex) {
            Logger.getLogger(ServicePlan.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<plan> selectPlanGridByTypeForNotif(int id_u) {
        List<plan> plans = new ArrayList<>();
        try {
            String req = "select p.id_p, p.libelle,p.description,"
                    + " p.prix, p.adresse"
                    + ",sc.libelle,c.libelle,p.longitude,p.latitude"
                    + ",p.etat,p.etatnotif "
                    + " from plan p INNER join"
                    + " sous_categorie sc on p.id_sc=sc.id_sc INNER JOIN "
                    + "categorie c on sc.id_c=c.id_c where id_u = " + id_u;

            ResultSet rest = ste.executeQuery(req);
            while (rest.next()) {
                plan p = new plan();
                p.setId_p(rest.getInt(1));
                p.setLibelle(rest.getString(2));
                p.setDescription(rest.getString(3));
                p.setPrix(rest.getString(4));
                p.setAdresse(rest.getString(5));
                String Scat = (rest.getString(6));
                String cat = (rest.getString(7));
                p.setLibSousCat(cat + "/" + Scat);
                p.setLongitude(rest.getDouble(8));
                p.setLatitude(rest.getDouble(9));
                p.setEtat(rest.getInt(10));
                p.setEtatnotif(rest.getInt(11));
                plans.add(p);
            }
            return plans;
        } catch (SQLException ex) {
            Logger.getLogger(ServicePlan.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
