package packageProjet;

import java.sql.*;
import java.util.ArrayList;



public class Site {
	
	static Connection cnx;
	static Statement stat;
	static ResultSet rst;
	
		//Attributs 
		private String name;
		private String address;
		public int nbRoom;
		
		//Constructor
		public Site(String name, String address){
			this.name = name;
			this.address = address;
			this.nbRoom = 0;
		}
			
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		
		public Integer getNbRoom() {
			return nbRoom;
				
		}
		
		public void setNbRoom(int nbRoom) {
			this.nbRoom = nbRoom;
		}
		
		
		
		
		
		
		
		
		
		
}
		