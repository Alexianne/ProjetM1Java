package packageProjet;

import java.sql.*;


		//connectDB();  Connexion à la BDD
		//AddRoom(Room); Ajouter une Room à la BDD




public class DBMana {
		static Connection cnx;
		static Statement stat;
		static ResultSet rst;
		
		public static Connection connectDB(){
			 try {
				 
			      
				  String url = "jdbc:mysql://localhost:3306/TPjava";
			      String user = "manu";
			      String passwd = "guildwars1";

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
				
				String query = "INSERT INTO Room VALUES ("+room1.getNbRoom()+",'"+room1.getType()+"',"+room1.getNbIntercoDev()+","+room1.getNbDevices()+",'"+room1.getSiteName()+"')"; 
				cnx = connectDB();
				stat=cnx.createStatement();
				stat.executeUpdate(query);
				System.out.println("Produit bien ajouté ! ");
				
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
		
		
	
}
