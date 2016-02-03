/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package version1;
import java.awt.List;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
/**
 *
 * @author Brice
 */
public class DatabaseConnect {
    static Connection cnx;
    static Statement stat;
    static ResultSet rst;
		
    public static Connection connectDB(){
        try {
            String url = "jdbc:mysql://localhost:3306/donnees_utilisateur";
            String user = "root";
            String passwd = "stri";

            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver OK");

            Connection conn = DriverManager.getConnection(url, user, passwd);
            System.out.println("Connexion effective !");         
            return conn;
        } 
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
    /**
     *
     * @param query
     * @return
     */
    public static ResultSet selectDB(String query){
        try {
            cnx = connectDB();
            stat=cnx.createStatement();
            rst = stat.executeQuery(query);
        } 
        catch (Exception e) {
        }
        return rst;
     }
     
      public static boolean insertDB(String query){
        try {
            cnx = connectDB();
            stat=cnx.createStatement();
            stat.executeUpdate(query);
            return true;
        } 
        catch (Exception e) {
            return false;
        }
    
    }   

    static void newuser(String prenom, String nom, String email, String phone, LocalDate naissance, double visible, double id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
