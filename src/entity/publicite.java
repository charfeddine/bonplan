/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author a7med
 */

public class publicite {
      String id_pub;
    String titre_pub;
    String img_pub;
    String site_pub;
    String desc_pub;
    String id_u;

    public publicite() {
    }

    public publicite(String id_pub, String titre_pub, String img_pub, String site_pub, String desc_pub, String id_u) {
        this.id_pub = id_pub;
        this.titre_pub = titre_pub;
        this.img_pub = img_pub;
        this.site_pub = site_pub;
        this.desc_pub = desc_pub;
        this.id_u = id_u;
    }

    public publicite(String titre_pub, String img_pub, String site_pub) {
        this.titre_pub = titre_pub;
        this.img_pub = img_pub;
        this.site_pub = site_pub;
    }
   

    public String getId_u() {
        return id_u;
    }

    public void setId_u(String id_u) {
        this.id_u = id_u;
    }

    

    public String getId_pub() {
        return id_pub;
    }

    public void setId_pub(String id_pub) {
        this.id_pub = id_pub;
    }

  

    public void setImg_pub(String img_pub) {
        this.img_pub = img_pub;
    }

    public String getImg_pub() {
        return img_pub;
    }

    public String getDesc_pub() {
        return desc_pub;
    }

    public String getSite_pub() {
        return site_pub;
    }

    public String getTitre_pub() {
        return titre_pub;
    }

    public void setDesc_pub(String desc_pub) {
        this.desc_pub = desc_pub;
    }

    public void setSite_pub(String site_pub) {
        this.site_pub = site_pub;
    }

    public void setTitre_pub(String titre_pub) {
        this.titre_pub = titre_pub;
    }
}
