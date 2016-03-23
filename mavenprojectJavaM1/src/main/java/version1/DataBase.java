/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package version1;
import java.awt.List;
import java.sql.*;
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
		
    /**
     *
     * @return
     */
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

    /**
     *
     * @param query
     * @return
     */
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

    static boolean newuser(String id, String nom, String prenom,  String email, String phone,String naissance, double visible) {
        String query = "INSERT INTO coordonnees VALUES ('"+id+"','"+nom+"','"+prenom+"','"+email+"','"+phone+"','"+naissance+"','"+visible+"')";
        return insertDB(query);
        
    }

    /**
     *
     * @param message
     * @return
     */
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

    /**
     *
     * @param motclé
     * @return
     */
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

    /**
     *
     * @param nom
     * @param prenom
     * @return
     */
    public static ArrayList<String> getinfo2(String nom,String prenom) {
        //String query = "SELECT nom FROM coordonnees WHERE nom LIKE '%"+message+"%' or prenom LIKE '%"+message+"%'";
        System.out.println(nom+" "+prenom);
        String query = "SELECT nom FROM coordonnees WHERE nom ='"+nom+"' and prenom = '"+prenom+"'";
        rst = selectDB(query);
        boolean add = true ;
            ArrayList<String> info = new ArrayList<>();
        try {
            while(rst.next() && add){
                add = info.add(rst.getString(1));
                System.out.println(info);
            }       			
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        // SOLUTION PROVISOIRE DEBUT *******************************************************************
        query = "SELECT prenom FROM coordonnees WHERE nom ='"+nom+"' and prenom = '"+prenom+"'";
        rst = selectDB(query);
            
        try {
            while(rst.next() && add){
                add = info.add(rst.getString(1));
                System.out.println(info);
            }       			
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        query = "SELECT email FROM coordonnees WHERE nom ='"+nom+"' and prenom = '"+prenom+"'";
        rst = selectDB(query);
            
        try {
            while(rst.next() && add){
                add = info.add(rst.getString(1));
                System.out.println(info);
            }       			
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        query = "SELECT telephone FROM coordonnees WHERE nom ='"+nom+"' and prenom = '"+prenom+"'";
        rst = selectDB(query);
            
        try {
            while(rst.next() && add){
                add = info.add(rst.getString(1));
                System.out.println(info);
            }       			
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        query = "SELECT naissance FROM coordonnees WHERE nom ='"+nom+"' and prenom = '"+prenom+"'";;
        rst = selectDB(query);
            
        try {
            while(rst.next() && add){
                add = info.add(rst.getString(1));
                System.out.println(info);
            }       			
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        query = "SELECT id FROM coordonnees WHERE nom ='"+nom+"' and prenom = '"+prenom+"'";;
        rst = selectDB(query);
            
        try {
            while(rst.next() && add){
                add = info.add(rst.getString(1));
                System.out.println(info);
            }       			
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        // SOLUTION PROVISOIRE FIN *******************************************************************
        return info;
        }
            
    /**
     *
     * @param message
     * @return
     */
    public static ArrayList<String> getinfo(String message) {
        //String query = "SELECT nom FROM coordonnees WHERE nom LIKE '%"+message+"%' or prenom LIKE '%"+message+"%'";
        System.out.println(message);
        String query = "SELECT nom FROM coordonnees WHERE id ='"+message+"'";
        rst = selectDB(query);
        boolean add = true ;
            ArrayList<String> info = new ArrayList<>();
        try {
            while(rst.next() && add){
                add = info.add(rst.getString(1));
                System.out.println(info);
            }       			
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        // SOLUTION PROVISOIRE DEBUT *******************************************************************
        query = "SELECT prenom FROM coordonnees WHERE id ='"+message+"'";
        rst = selectDB(query);
            
        try {
            while(rst.next() && add){
                add = info.add(rst.getString(1));
                System.out.println(info);
            }       			
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        query = "SELECT email FROM coordonnees WHERE id ='"+message+"'";
        rst = selectDB(query);
            
        try {
            while(rst.next() && add){
                add = info.add(rst.getString(1));
                System.out.println(info);
            }       			
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        query = "SELECT telephone FROM coordonnees WHERE id ='"+message+"'";
        rst = selectDB(query);
            
        try {
            while(rst.next() && add){
                add = info.add(rst.getString(1));
                System.out.println(info);
            }       			
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        query = "SELECT naissance FROM coordonnees WHERE id ='"+message+"'";
        rst = selectDB(query);
            
        try {
            while(rst.next() && add){
                add = info.add(rst.getString(1));
                System.out.println(info);
            }       			
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        // SOLUTION PROVISOIRE FIN *******************************************************************
        return info;
        }
   
    static ArrayList<String> getinfouser(String message) {
        System.out.println(message);
        String query = "SELECT diplome FROM diplome WHERE id ='"+message+"'";
        rst = selectDB(query);
        boolean add = true ;
            ArrayList<String> info = new ArrayList<>();
        try {
            while(rst.next() && add){
                add = info.add(rst.getString(1));
                System.out.println(info);
            }       			
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        // SOLUTION PROVISOIRE DEBUT *******************************************************************
        query = "SELECT annee FROM diplome WHERE id ='"+message+"'";
        rst = selectDB(query);
            
        try {
            while(rst.next() && add){
                add = info.add(rst.getString(1));
                System.out.println(info);
            }       			
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return info;
    }
        
  static ArrayList<String> getinfouser1(String message) {
      
        String query = "SELECT competence FROM competence WHERE id ='"+message+"'";
        rst = selectDB(query);
        boolean add = true ;
        ArrayList<String> info = new ArrayList<>();
        try {
            while(rst.next() && add){
                add = info.add(rst.getString(1));
                System.out.println(info);
            }       			
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
         query = "SELECT description FROM competence WHERE id ='"+message+"'";
        rst = selectDB(query);
            
        try {
            while(rst.next() && add){
                add = info.add(rst.getString(1));
                System.out.println(info);
            }       			
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return info;
        }// SOLUTION PROVISOIRE FIN *******************************************************************

    static boolean addinfocomp(String id, String competence, String description, double visible, double like, String likeur ) {
        String query = "INSERT INTO competence  VALUES ('"+id+"','"+competence+"','"+description+"','"+visible+"','"+like+"','"+likeur+"')";
        return insertDB(query);
    }

    static boolean addinfodip(String id, String diplome, String annee) {
        String query = "INSERT INTO diplome  VALUES ('"+id+"','"+diplome+"','"+annee+"')";
        return insertDB(query);
    }


    boolean modifinfo(String id,String prenom, String nom, String email, String phone, String naissance, double visible) {
        String query = "Update coordonnees SET nom='"+nom+"',prenom='"+prenom+"',email='"+email+"',telephone='"+phone+"',naissance='"+naissance+"',visibilite='"+visible+"' WHERE id = '"+id+"'";
        return insertDB(query);
    }
    
    
      static ArrayList<String> getinfouser2(String message) {
            System.out.println(message);
        String query = "SELECT diplome FROM diplome WHERE id ='"+message+"'";
        rst = selectDB(query);
        boolean add = true ;
            ArrayList<String> info = new ArrayList<>();
        try {
            while(rst.next() && add){
                add = info.add(rst.getString(1));
                System.out.println(info);
            }       			
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        // SOLUTION PROVISOIRE DEBUT *******************************************************************
        query = "SELECT annee FROM diplome WHERE id ='"+message+"'";
        rst = selectDB(query);
            
        try {
            while(rst.next() && add){
                add = info.add(rst.getString(1));
                System.out.println(info);
            }       			
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        query = "SELECT competence FROM competence WHERE id ='"+message+"'";
        rst = selectDB(query);
        
        try {
            while(rst.next() && add){
                add = info.add(rst.getString(1));
                System.out.println(info);
            }       			
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
         query = "SELECT description FROM competence WHERE id ='"+message+"'";
        rst = selectDB(query);
            
        try {
            while(rst.next() && add){
                add = info.add(rst.getString(1));
                System.out.println(info);
            }       			
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return info;
        }

       boolean setonline(String id) {
       int online = 1;
       String query = "UPDATE coordonees SET on='"+online+"' WHERE id ='"+id+"';";
       return insertDB(query);
    }
    
    /**
     *
     * @return
     */
    public static ArrayList<String> online() {
        int online = 1;
       String query = "SELECT prenom FROM coordonnees WHERE on='"+online+"'";
       rst = selectDB(query);
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

    ArrayList<String> getlike(String id) {
        
        String query = "SELECT nblike FROM competence WHERE id ='"+id+"'";
        rst = selectDB(query);
        boolean add = true ;
        ArrayList<String> info = new ArrayList<>();
        try {
            while(rst.next() && add){
                add = info.add(rst.getString(1));
                System.out.println(info);
            }       			
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
            String query2 = "SELECT likeur FROM competence WHERE id ='"+id+"'";
            rst = selectDB(query2);
            
        try {
            while(rst.next() && add){
                add = info.add(rst.getString(1));
                System.out.println(info);
            }       			
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    return info;
    }
         //To change body of generated methods, choose Tools | Templates.

    @SuppressWarnings("empty-statement")
    boolean addLike(String id, String nom) {
        
        String query = "SELECT nblike FROM competence WHERE id ='"+id+"'";
        rst = selectDB(query);
        boolean add = true ;
        ArrayList<String> info = new ArrayList<>();
        try {
            while(rst.next() && add){
                add = info.add(rst.getString(1));
                System.out.println(info);
            }       			
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
            String query2 = "SELECT likeur FROM competence WHERE id ='"+id+"'";
            rst = selectDB(query2);
            ArrayList<String> info2 = new ArrayList<>();
        try {
            while(rst.next() && add){
                add = info2.add(rst.getString(1));
                System.out.println(info2);
            }       			
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        String likeur = nom;
        String vlike = info.get(0);
        int like = Integer.parseInt(vlike);
        like = like+1;
        likeur = likeur+" "+info2.get(0);
        System.out.println(like);
        System.out.println(info2);
        System.out.println(likeur);
        String query3 = "UPDATE donnees_utilisateur.competence SET nblike ='"+like+"', likeur='"+likeur+"' WHERE id ='"+id+"'";
        System.out.println(query3);
        return insertDB(query3);
 }

    boolean addmess(String id, String name, String mess) {
       String query = "INSERT INTO messagerie  VALUES ('"+id+"','"+name+"','"+mess+"')";
       return insertDB(query);
    }

    ArrayList<String> getmess(String id) {
        String query = "SELECT name FROM messagerie WHERE id ='"+id+"'";
        rst = selectDB(query);
        boolean add = true ;
        ArrayList<String> info = new ArrayList<>();
        try {
            while(rst.next() && add){
                add = info.add(rst.getString(1));
                System.out.println(info);
            }       			
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
                    String query2 = "SELECT message FROM messagerie WHERE id ='"+id+"'";
            rst = selectDB(query2);
            
        try {
            while(rst.next() && add){
                add = info.add(rst.getString(1));
                System.out.println(info);
            }       			
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    return info;
    }

    boolean suppmess(String id) {
        String query = "DELETE FROM messagerie WHERE id='"+id+"'";
        return insertDB(query);
    }
}

   