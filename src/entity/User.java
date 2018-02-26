

package entity;



import java.time.LocalDate;
import java.util.Date;


/**
 *
 * @author Mahdi
 */
public class User {
    private int id_u;
    private String nom;
    private String prenom;
    private String email;
    private int telephone;
    private String sexe; 
    private Date dateNaissance;
    private String adress;
    private String type;
    private String login;
    private String password;
    private String etat_compte;
    private int point_fidelite;
    
    

    public User() {
    }
    public User(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }
    public User(String nom, String prenom, String type, String etat_compte) {
        this.nom = nom;
        this.prenom = prenom;
        this.type = type;
        this.etat_compte = etat_compte;
    }
    public User(String nom, String prenom,String password ,String login,String type, String etat_compte) {
        this.nom = nom;
        this.prenom = prenom;
        this.login=login;
        this.password=password;
        this.type = type;
        this.etat_compte = etat_compte;
    }
    
    public User(int id_u, String nom, String prenom, String email, int telephone,String sexe,Date dateNaissance, String adress, String type, String login, String password,String etat_compte ,int point_fidelite) {
        this.id_u = id_u;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.sexe = sexe;
        this.dateNaissance = dateNaissance;
        this.adress = adress;
        this.type = type;
        this.login = login;
        this.password = password;
        this.etat_compte=etat_compte;
        this.point_fidelite = point_fidelite;
    }
    
    public User(String nom, String prenom, String email, int telephone, String type, String login, String password,String etat_compte) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.login = login;
        this.password = password;
        this.type = type;
        this.etat_compte = etat_compte;
     
    }
    public User(String nom, String prenom, String email, int telephone,String sexe,Date dateNaissance, String adress, String type, String login, String password) {
       
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.sexe = sexe;
        this.dateNaissance = dateNaissance;
        this.adress = adress;
        this.type = type;
        this.login = login;
        this.password = password;
        
    } 
  public User(int id,String nom, String prenom,String sexe, String email, String adress,
          int telephone,Date dateNaissance, String login, String password, String type, String etat_compte) {
          this.id_u = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.sexe = sexe;
        this.dateNaissance = dateNaissance;
        this.adress = adress;
        this.type = type;
        this.login = login;
        this.password = password;
             this.etat_compte = etat_compte;
        
    } 
  

    public int getId_u() {
        return id_u;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public int getTelephone() {
        return telephone;
    }

    public String getAdress() {
        return adress;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public String getSexe() {
        return sexe;
    }
    

    public int getPoint_fidelite() {
        return point_fidelite;
    }

    public String getType() {
        return type;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEtat_compte() {
        return etat_compte;
    }
    

    public void setId_u(int id_u) {
        this.id_u = id_u;
    } 

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setPoint_fidelite(int point_fidelite) {
        this.point_fidelite = point_fidelite;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEtat_compte(String etat_compte) {
        this.etat_compte = etat_compte;
    }
    

    
    
    }
    
    
    

