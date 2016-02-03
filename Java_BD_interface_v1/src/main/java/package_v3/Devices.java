package package_v3;


public class Devices {
    
    /* Attributes */
    private String devName;
    private String typeDev;
    private String OS;
    private String siteName;
    private String numRoom;
    
    /* Constructor */
    public Devices(String devName, String typeDev,String OS, String siteName, String numRoom){
        this.devName = devName;
        this.typeDev = typeDev;
        this.OS = OS;
        this.siteName = siteName;
        this.numRoom = numRoom;
    }	

    /* Methods */
    public String getDevName() {
	return devName;
    }
    public void setDevName(String devName) {
	this.devName = devName;
    }
    public String getTypeDev() {
        return typeDev;
    }
    public void setTypeDev(String typeDev) {
        this.typeDev = typeDev;
    }
    public String getOS() {
	return OS;
    }
    public void setOS(String oS) {
	OS = oS;
    }
    public String getSiteName() {
        return siteName;
    }
    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
    public String getNumRoom() {
        return numRoom;
    }
    public void setNumRoom(String numRoom) {
        this.numRoom = numRoom;
    }	
    public Devices AddDevice(){		
        return null;
    }
				
}
