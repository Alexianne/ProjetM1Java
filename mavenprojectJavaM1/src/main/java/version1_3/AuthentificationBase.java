        /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package version1_3;

import version1.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.UUID;
import java.awt.List;
import java.sql.*;
//import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import static version1_3.DataBase.cnx;

/**
 *
 * @author Brice
 */

public class AuthentificationBase {
    static Connection cnx;
    static Statement stat;
    static ResultSet rst;
    
    /**
     *
     * @return
     */
    public static Connection connectAuth(){
        try {
            String url = "jdbc:mysql://localhost:3306/authentification";
            String user = "root";
            String passwd = "stri";

            try {
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("Driver OK");
            }
            catch(ClassNotFoundException cnfe){
                System.out.println("Driver Fail");
            }
            Connection conn = DriverManager.getConnection(url, user, passwd);
            System.out.println("Connexion effective !");         
            return conn;
        } 
        catch (Exception e) {
            return null;
        }
        }
    
    /**
     *
     * @param query
     * @return
     */
    public static ResultSet selectAuth(String query){
        try {
            cnx = connectAuth();
            stat=cnx.createStatement();
            rst = stat.executeQuery(query);
        } 
        catch (Exception e) {
            try {
                rst.getString("ERR200");
            } catch (SQLException ex) {
                Logger.getLogger(AuthentificationBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rst;
     }

    /**
     *
     * @param query
     * @return
     */
    public static boolean insertAuth(String query){
        try {
            cnx = connectAuth();
            stat=cnx.createStatement();
            stat.executeUpdate(query);
            return true;
        }  
        catch (Exception e) {
            return false;
        }
        }
        
     boolean newauth(String pseudo, String mdp) {
       UUID id = UUID.randomUUID();
       String query = "INSERT INTO login VALUES ('"+id+"','"+pseudo+"','"+mdp+"')";
       return insertAuth(query);
    }

    /**
     *
     * @param pseudo
     * @param mdp
     * @return
     */
    public static ArrayList<String> auth(String pseudo, String mdp) {
       String query = "SELECT id FROM login WHERE pseudo='"+pseudo+"' and mdp='"+mdp+"'";
       rst = selectAuth(query);
       boolean add = true ;
            ArrayList<String> id = new ArrayList<>();
        try {
            while(rst.next() && add){
                add = id.add(rst.getString(1));
            }       			
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
            return id;
    }

    boolean suppcompte(String id) {
        String query = "DELETE FROM login WHERE id='"+id+"'";
        return insertAuth(query);
        }
}