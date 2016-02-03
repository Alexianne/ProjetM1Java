package package_v3;


public class Site {
	
    /* Attributs */
    private String siteName;
    private String address;
    public int nbRoom;

    /* Constructor */
    public Site(String siteName, String address){
            this.siteName = siteName;
            this.address = address;
    }
    
    /* Methods */
    public String getSiteName() {
        return siteName;
    }
    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getNbRoom() {
        return nbRoom;
    }
    public void setNbRoom(int nbRoom) {
        this.nbRoom = nbRoom;
    }
	
}
		