package package_v2;

public class Devices {
		private String name;
		private String type;
		private String OS;
		private int nbRoom;
		private NetworkCard nc;
		
		public Devices(String name, String type,String OS, int nbRoom){
			this.name = name;
			this.type= type;
			this.OS = OS;
			this.nbRoom = nbRoom;
			this.nc = null; 
		}
		
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getOS() {
			return OS;
		}
		public void setOS(String oS) {
			OS = oS;
		}
		public NetworkCard getNc() {
			return nc;
		}
		public void setNc(NetworkCard nc) {
			this.nc = nc;
		}
		
		public int getNbRoom() {
			return nbRoom;
		}
		public void setNbRoom(int nbRoom) {
			this.nbRoom = nbRoom;
		}
		
		public Devices AddDevice(){
			 
			
			
			
			
			
			return null;
		}
		
		
		
		
		
		
		
		
		
		
		
		
}
