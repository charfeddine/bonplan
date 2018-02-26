package Services;

import DBAccess.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import entity.User;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Mahdi
 */
public class ServiceUser {

    private Connection con;
    private static ServiceUser data;
    /* pour l'essai*/

    DataSource db;
    Connection cnx;
    Statement st;
    static DataSource ds = DataSource.getInstance();
    List<User> users = new ArrayList<>();

    public ServiceUser() {

        try {

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev", "root", "");
            System.out.println("connexion Ok ! !");
        } catch (SQLException x) {

            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, x);
        }
        cnx = db.getInstance().getConnection();
        try {
            st = (Statement) cnx.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServicePub.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() {
        return con;
    }

    public static ServiceUser getInstance() {
        if (data == null) {
            data = new ServiceUser();
        }
        return data;
    }

    public void ajouterUserAns(String nom, String prenom, String email, int telephone, String login, String password) {
        try {
            String req = "INSERT INTO user (`nom`, `prenom`,`email`,`telephone` ,`login`,`password`,`type`,`etat_compte`) VALUES ('" + nom + "','" + prenom + "','" + email + "','" + telephone + "', '" + login + "','" + password + "','1','vérifier')";

            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<User> selectUsers() throws SQLException {
        List<User> list = new ArrayList<>();

        try {
            String sql = "select * from user";
            Connection con = ServiceUser.getInstance().getConnection();
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet rest = preparedStatement.executeQuery();
            while (rest.next()) {
                User u = new User();
                u.setId_u(rest.getInt(1));
                u.setNom(rest.getString(2));
                u.setPrenom(rest.getString(3));
                u.setLogin(rest.getString(11));
                u.setPassword(rest.getString(12));

                list.add(u);

            }
            con.close();

        } catch (SQLException e) {
        }
        return list;
    }

    //Les services de l'admin//  
    public void ajouterA(String nom) throws SQLException {
        cnx = db.getInstance().getConnection();

        st = (Statement) cnx.createStatement();

        String req = "UPDATE `user` SET `type`='2' WHERE `nom`='" + nom + "'";

        st.executeUpdate(req);

    }

    public void supprimerA(String nom) throws SQLException {
        cnx = db.getInstance().getConnection();

        st = (Statement) cnx.createStatement();

        String req = "UPDATE `user` SET `type`='1' WHERE `nom`='" + nom + "'";

        st.executeUpdate(req);

    }

    public void bloquerA(String nom) throws SQLException {
        cnx = db.getInstance().getConnection();

        st = (Statement) cnx.createStatement();

        String req = "UPDATE `user` SET `etat_compte`='bloquer' WHERE `nom`='" + nom + "'";

        st.executeUpdate(req);

    }

    public void débloquerA(String nom) throws SQLException {
        cnx = db.getInstance().getConnection();

        st = (Statement) cnx.createStatement();

        String req = "UPDATE `user` SET `etat_compte`='vérifier' WHERE `nom`='" + nom + "'";

        st.executeUpdate(req);

    }

    //Les services de l'admin en haut//  
    public List<User> selectUserA(int id_u) {
        List<User> users = new ArrayList<>();
        try {

            ResultSet rest = st.executeQuery("select id_u, nom,prenom from user");
            while (rest.next()) {
                User u = new User();
                u.setId_u(rest.getInt(1));
                u.setNom(rest.getString(2));
                u.setPrenom(rest.getString(3));

                users.add(u);

            }
            return users;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public List<User> selectUser() {

        try {

            ResultSet rest
                    = st.executeQuery("select * from user");
            while (rest.next()) {
//                User u = new User();
//                u.setId_u(rest.getInt(1));
//               u.setNom(rest.getString(2));
//               u.setPrenom(rest.getString(3));
//                u.setLogin(rest.getString(4));
//                u.setType(rest.getString(10));
//                u.setEtat_compte(rest.getString(13));
                User u = new User(rest.getString("nom"), rest.getString("prenom"),
                        rest.getString("type"),
                        rest.getString("etat_compte"));
                u.setId_u(rest.getInt("id_u"));
                u.setAdress(rest.getString("adresse"));
                u.setEmail(rest.getString("email"));
                u.setSexe(rest.getString("sexe"));
                u.setLogin(rest.getString("login"));
                u.setTelephone(rest.getInt("telephone"));
                u.setPoint_fidelite(rest.getInt("point_fidelite"));

                String etatcpt = "";
                if ("1".equals(rest.getString("etat_compte"))) {
                    etatcpt = "Activer";
                } else {
                    etatcpt = "Desactiver";
                }
                u.setEtat_compte(etatcpt);
                users.add(u);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServicePub.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (User u : users) {
            String ch = "Le nom : " + u.getNom() + " Prenom : " + u.getPrenom();

            System.out.println(ch);
        }
        return users;

    }

    public User getUtilisateurByLoginPassword(String Login, String Password) {
        User u = null;
        try {
            st = (Statement) cnx.createStatement();
            ResultSet rest = st.executeQuery("select * from user  WHERE login='" + Login + "' and password='" + Password + "'");
            if (rest.next()) {
                u = new User(rest.getString("nom"), rest.getString("prenom"), rest.getString("type"), rest.getString("etat_compte"));
            }
            u.setId_u(rest.getInt("id_u"));
            u.setAdress(rest.getString("adresse"));
            u.setEmail(rest.getString("email"));
            u.setPoint_fidelite(rest.getInt("point_fidelite"));
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(u);
        return u;
    }

    public User getUtilisateurByMail(String mail) {
        User u = null;
        try {
            st = (Statement) cnx.createStatement();
            ResultSet rest = st.executeQuery("select * from user  WHERE email ='" + mail + "'");
            if (rest.next()) {
                u = new User(rest.getString("nom"), rest.getString("prenom"), rest.getString("password"), rest.getString("login"), rest.getString("type"), rest.getString("etat_compte"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(u);
        return u;
    }

    public double getEtatBloquer() throws SQLException {
        User u = null;

        st = (Statement) cnx.createStatement();
        ResultSet rest = st.executeQuery("select count(*) as a from user  WHERE etat_compte like 'bloquer'");
        double x = 0;
        try {
            while (rest.next()) {
                x = rest.getInt("a");

            }

        } catch (Exception e) {
            System.out.println(e);

        }
        return x;
    }

    public double getEtatVerifier() throws SQLException {
        User u = null;

        st = (Statement) cnx.createStatement();
        ResultSet rest = st.executeQuery("select count(*) as a from user  WHERE etat_compte like 'vérifier'");
        double x = 0;
        try {
            while (rest.next()) {
                x = rest.getInt("a");

            }

        } catch (Exception e) {
            System.out.println(e);

        }
        return x;
    }

    public User selectUtilsateurItem(int id_Ut) {
        User u = null;
        try {
            st = (Statement) cnx.createStatement();
            ResultSet rest = st.executeQuery("select * from user  WHERE id_u=" + id_Ut);
            if (rest.next()) {
                u = new User(rest.getString("nom"), rest.getString("prenom"),
                        rest.getString("type"),
                        rest.getString("etat_compte"));
                u.setId_u(rest.getInt("id_u"));
                u.setAdress(rest.getString("adresse"));
                u.setEmail(rest.getString("email"));
                u.setPoint_fidelite(rest.getInt("point_fidelite"));

                u.setId_u(rest.getInt("id_u"));
                u.setAdress(rest.getString("adresse"));
                u.setEmail(rest.getString("email"));
                u.setSexe(rest.getString("sexe"));
                u.setLogin(rest.getString("login"));
                u.setTelephone(rest.getInt("telephone"));
                u.setPoint_fidelite(rest.getInt("point_fidelite"));
                u.setDateNaissance(rest.getDate("DateNaissance"));
                u.setPassword(rest.getString("Password"));
            }
            return u;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public int ajouterUser(User p) {
        try {
            String req;
            //To change body of generated methods, choose Tools | Templates.
            req = "INSERT INTO user(nom, prenom, email, telephone, sexe, dateNaissance, adresse,"
                    + " point_fidelite, type, login, password, etat_compte) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,? )";

            PreparedStatement pre = con.prepareStatement(req);
            // pre.setInt(1,e.getId());
            pre.setString(1, p.getNom());
            pre.setString(2, p.getPrenom());
            pre.setString(3, p.getEmail());
            pre.setInt(4, p.getTelephone());
            pre.setString(5, p.getSexe());
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(p.getDateNaissance().getTime());
            pre.setDate(6, sqlDate);
            pre.setString(7, p.getAdress());
            pre.setInt(8, 0);
            pre.setString(9, p.getType());
            pre.setString(10, p.getLogin());
            pre.setString(11, p.getPassword());
            pre.setString(12, p.getEtat_compte());

            int i = pre.executeUpdate();
            return i;

        } catch (SQLException ex) {
            Logger.getLogger(ServicePlan.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    } //To change body of generated methods, choose Tools | Templates.

    public int ModifUser(User p) {
        try {
            String req;
            //To change body of generated methods, choose Tools | Templates.

            req = "UPDATE user SET  nom='" + p.getNom() + "',prenom='" + p.getPrenom() + "',email='" + p.getEmail() + "'"
                    + ",telephone='" + p.getTelephone() + "',sexe='" + p.getSexe() + "' "
                    + ",adresse='" + p.getAdress() + "' ,type='" + p.getType() + "',login='" + p.getLogin() + "' "
                    + ",password='" + p.getPassword() + "',etat_compte='" + p.getEtat_compte() + "' ";
                  if(p.getDateNaissance()!=null){
                  req+=" ,dateNaissance=?";
                  }
                   req+= "WHERE id_u='" + p.getId_u() + "'";

            PreparedStatement pre = con.prepareStatement(req);
                 if(p.getDateNaissance()!=null){
              java.util.Date utilDate = new java.util.Date();
               java.sql.Date sqlDatess = new java.sql.Date(p.getDateNaissance().getTime());
                pre.setDate(1,  sqlDatess);
                 }
            int i = pre.executeUpdate();
            return i;

        } catch (SQLException ex) {
            Logger.getLogger(ServicePlan.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } //To change body of generated methods, choose Tools | Templates.
    }

    public void supprimerUser(int id_u) {
        try {
            st = (Statement) cnx.createStatement();
            String req = "delete from user   where id_u='" + id_u + "'";

            st.executeUpdate(req);
        } catch (SQLException ex) {

        }
    }

}
