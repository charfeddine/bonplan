/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import entity.PlanGrid;
import entity.plan;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author chaima
 */
public interface Iplan  {
     public int ajouterPlan(plan p);
          public int ModifPlan(plan p);
    public void supprimerUser(int id);
    public List<plan> selectPlan();
    public List<PlanGrid> selectPlanGrid(int id_u) ;
    public plan selectPlanItem (int id_p);
      public List<PlanGrid> selectPlanGridByType(int id_c,int id_Sc);
      public   ObservableList<plan> selectPlanByType(int id_c,int id_Sc);
       public List<PlanGrid> selectPlanGridValidation() ;
          public int UpdateValidationPlan(int idp,int etat);
    public String selectTypeCompte(int idu) ; 
    public List<plan> selectPlanGridByTypeForNotif(int id_u) ;
     public int UpdatenotifPlan(int idp,int notif);
         public int UpdatePointfidelite(int idu,int pnt);
}
