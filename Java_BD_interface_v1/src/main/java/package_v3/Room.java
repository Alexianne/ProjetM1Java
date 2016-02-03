package package_v3;


public class Room {
    
    /* Attributes */
    private String siteName;
    private String numRoom;
    private String typeRoom;
    
    /* Constructor */
    public Room(String siteName, String numRoom, String typeRoom){
        this.siteName = siteName;
        this.numRoom = numRoom;
        this.typeRoom = typeRoom;
    }

    /* Methods */
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
    public String getTypeRoom() {
        return typeRoom;
    }
    public void setTypeRoom(String typeRoom) {
        this.typeRoom = typeRoom;
    }
 
}
