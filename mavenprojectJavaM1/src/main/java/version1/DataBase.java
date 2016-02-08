/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package version1;
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
/**
 *
 * @author Brice
 */
public class DataBase {
    static Connection cnx;
    static Statement stat;
    static ResultSet rst,rst2;
		
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

    static boolean newuser(String id, String prenom, String nom, String email, String phone,String naissance, double visible) {
        String query = "INSERT INTO coordonnees VALUES ('"+id+"','"+nom+"','"+prenom+"','"+email+"','"+phone+"','"+naissance+"','"+visible+"')";
        return insertDB(query);
        
    }

     public static ArrayList<String> getlist(String message) {
        //String query = "SELECT nom FROM coordonnees WHERE nom LIKE '%"+message+"%' or prenom LIKE '%"+message+"%'";
        System.out.println(message);
        String query = "SELECT nom FROM coordonnees WHERE nom='"+message+"'";
        rst = selectDB(query);
        boolean add = true ;
            ArrayList<String> name = new ArrayList<>();
        try {
            while(rst.next() && add){
                add = name.add(rst.getString(1));
                
            }       			
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        String query2 = "SELECT prenom FROM coordonnees WHERE nom='"+message+"'";
        rst = selectDB(query2);
        try {
            while(rst.next() && add){
                add = name.add(rst.getString(1));
                }       			
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
            return name;
    }


    public static ArrayList<Integer> getnom(String motclé) {
        String query = "SELECT id FROM coordonnees WHERE nom LIKE '%"+motclé+"%' ";
            rst = selectDB(query);
            boolean add = true ;
            ArrayList<Integer> idsearch = new ArrayList<>();
      
        try {
            while(rst.next() && add){
                add = idsearch.add(rst.getInt(1));  
            } 
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
            return idsearch;
        
    }
   
    static ArrayList<String> getinfouser(int id) {
        String query = "SELECT * FROM coordonnees c, competence t,diplome d WHERE c.id ='"+id+"' and t.id='"+id+"' and d.id='"+id+"';'";
        rst = selectDB(query);
        boolean add = true ;
            ArrayList<String> name = new ArrayList<>();
        try {
            while(rst.next() && add){
                add = name.add(rst.getString(1));
            }       			
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
            return name;
    }

    static boolean addinfo(String id, String competence, String niv, String description, String diplome, String annee, double visible) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    String getinfo(String chnom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    boolean modifinfo(String id,String prenom, String nom, String email, String phone, LocalDate naissance, double visible) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
    

    
