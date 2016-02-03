package packageProjet;

public class Room {
    private String siteName;
    private int nbRoom;
    private String type;
    private int nbIntercoDev;
    private int nbDevices;
    
    
    //Constructor
    public Room(String siteName, int nbRoom, String type){
        this.setSiteName(siteName);
        this.setNbRoom(nbRoom);
        this.setType(type);
        this.setNbDevices(0);
        this.setNbIntercoDev(0);
    }


	public String getSiteName() {
		return siteName;
	}


	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}


	public int getNbRoom() {
		return nbRoom;
	}


	public void setNbRoom(int nbRoom) {
		this.nbRoom = nbRoom;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public int getNbIntercoDev() {
		return nbIntercoDev;
	}


	public void setNbIntercoDev(int nbIntercoDev) {
		this.nbIntercoDev = nbIntercoDev;
	}


	public int getNbDevices() {
		return nbDevices;
	}


	public void setNbDevices(int nbDevices) {
		this.nbDevices = nbDevices;
	}
    
	public Room AddRoom(){
		
		
		
		
		return null;
	}
    
}
