package package_v2;

import java.sql.*;
import javax.swing.DefaultComboBoxModel;

		//connectDB();  Connexion à la BDD
		//AddRoom(Room); Ajouter une Room à la BDD




public class DBMana {
		static Connection cnx;
		static Statement stat;
		static ResultSet rst;
		
		public static Connection connectDB(){
			 try {
				 
			      
			      String url = "jdbc:mysql://localhost:3306/TPjava";
			      String user = "test";
			      String passwd = "pass";

			      Class.forName("com.mysql.jdbc.Driver");
			      System.out.println("Driver OK");
			      
			      Connection conn = DriverManager.getConnection(url, user, passwd);
			      System.out.println("Connexion effective !");         
			      return conn;
			      
			    } catch (Exception e) {
			      e.printStackTrace();
					return null;      

			    }
			
		}
		
		public static void AddDBRoom(Room room1){
			try{
                            String query = "INSERT INTO Room VALUES ('"+room1.getSiteName()+"','"+room1.getType()+"','"+room1.getNbRoom()+"','"+room1.getNbDevices()+"','"+room1.getNbIntercoDev()+"')"; 
                            cnx = connectDB();
                            stat=cnx.createStatement();
                            stat.executeUpdate(query);
                            System.out.println("Produit bien ajouté ! ");
                            String query2 = "SELECT NbRoom FROM Site WHERE Name ='"+room1.getSiteName()+"'";
                            rst=stat.executeQuery(query2);
                            Integer result = null;
                            while (rst.next())
                            {
				result = rst.getInt(1);
                            }
                            System.out.println("avant le result = "+result);
                            result=result+1;
                            System.out.println("apres le result = "+result);
                            String query3 = "UPDATE Site SET NbRoom="+result+" WHERE Name ='"+room1.getSiteName()+"'";
                            stat.executeUpdate(query3);
				
			}catch(SQLException e){
				System.out.println(e.getMessage());
			}
		}
		
		public static void AddDBDev(Devices dev){
			try{	
				
				String query = "INSERT INTO Devices VALUES ('"+dev.getName()+"','"+dev.getType()+"','"+dev.getOS()+"','"+dev.getNc().getIdcard()+"','"+dev.getNbRoom()+"')"; 
				cnx = connectDB();
				stat=cnx.createStatement();
				stat.executeUpdate(query);
				System.out.println("Périphérique bien ajouté ! ");
				
			}catch(SQLException e){
				System.out.println(e.getMessage());
			}
		}
		
		public static void AddDBNC(NetworkCard nc){
			try{
				
				
				String query = "INSERT INTO NetworkCard VALUES ('"+nc.getDevicename()+"','"+nc.getIdcard()+"','')"; 
				cnx = connectDB();
				stat=cnx.createStatement();
				stat.executeUpdate(query);
				System.out.println("Carte réseau bien ajoutée ! ");
				
				
			}
			catch(SQLException e){
				System.out.println(e.getMessage());
			}	
		}
		
		public static void setDBConstr(NetworkCard nc){
			try{
				
				String temp = nc.getIdcard().substring(0, 6);
				System.out.println(temp);
				String query = "SELECT Name FROM Constructeurs WHERE IDCard='"+temp+"'"; 
				
				cnx = connectDB();
				stat=cnx.createStatement();
				rst = stat.executeQuery(query);
				String result = null;
				while (rst.next())
				{
				   result = rst.getString(1);
				   
				}
				System.out.println(result);
				nc.setConstr(result);
				
				String setconstr = "UPDATE NetworkCard SET Constr='"+nc.getConstr()+"' WHERE IDCard='"+nc.getIdcard()+"'  ";
				stat.execute(setconstr);
				System.out.println("ID Constructeur changé ! ");
			}
			catch(SQLException e){
				System.out.println(e.getMessage());
			}	
				
		}
		
		public static Integer getDBNbRoom(Site site) {
			
			try{
				cnx=DBMana.connectDB();
				stat=cnx.createStatement();
				String query = "SELECT COUNT(*) FROM Room WHERE SITE='"+site.getName()+"'";
				rst = cnx.createStatement().executeQuery(query);
				int result =0;
				while (rst.next())
				{
					   result = rst.getInt(1);
					   
				}			
				
				return result;
								
			}catch(SQLException e){
				e.printStackTrace();
				return null;
			}
		
			
		}

		
		public static void AddDBSite(Site site) {
	
			try{
				cnx=connectDB();
				stat=cnx.createStatement();
				site.setNbRoom(getDBNbRoom(site));
				String query = "INSERT INTO Site VALUES('"+site.getName()+"','"+site.getAddress()+"','"+site.getNbRoom()+"')";
				stat.execute(query);					
				
			}catch(SQLException e){
				e.printStackTrace();
				
			}
		
			
	}
                
     public static DefaultComboBoxModel selectDBSite(DefaultComboBoxModel listSite) {
         try{
            String query = "SELECT Name FROM Site";
            cnx = DBMana.connectDB();
            stat=cnx.createStatement();
            rst = stat.executeQuery(query);
            String result = null;
            while (rst.next())
            {
                result = rst.getString(1);
                listSite.addElement(result);
				   
            }
            return listSite;
         }
         catch(SQLException e){
            e.printStackTrace();
            return null;
        }
     }

    static boolean roomExist(String roomNum, String siteName) {
        try{
            cnx=DBMana.connectDB();
            stat=cnx.createStatement();
            String query = "SELECT NbRoom FROM Site WHERE Name='"+siteName+"'";
            rst = stat.executeQuery(query);
            int result =0;
            while (rst.next())
            {
                result = rst.getInt(1);
            }
            System.out.println(result);
            if(result == 0){
                return true;
            }
            else{
                String query2 = "SELECT COUNT(*) FROM Room WHERE NbRoom='"+roomNum+"'";
                rst = stat.executeQuery(query2);
                int result2 =0;
                while (rst.next())
                {
                   result2 = rst.getInt(1);
                }
                System.out.println(result2);
                if(result2 == 0){
                    return true;
                }else{
                    return false;
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
            return false;
	}
    }

public static DefaultComboBoxModel selectDBRoom(DefaultComboBoxModel listSite, String siteName) {
         try{
            String query = "SELECT NbRoom FROM Room WHERE Site='"+siteName+"'";
            cnx = DBMana.connectDB();
            stat=cnx.createStatement();
            rst = stat.executeQuery(query);
            String result = null;
            while (rst.next())
            {
                result = rst.getString(1);
                listSite.addElement(result);
				   
            }
            return listSite;
         }
         catch(SQLException e){
            e.printStackTrace();
            return null;
        }
     }
		
		
	
}
