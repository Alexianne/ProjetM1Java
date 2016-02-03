/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package version1;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;

 /*
 * @author Brice
 */
public class Données {

    boolean newuser(String prenom, String nom, String email, String phone, LocalDate naissance, double visible, double id) {
        DatabaseConnect.newuser(prenom, nom, email, phone, naissance, visible, id);
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    String getlist() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    String getnom(String motclé) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    String getinfo(String chnom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    boolean modifinfo(String prenom, String nom, String email, String phone, LocalDate naissance, double visible, double id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
    
