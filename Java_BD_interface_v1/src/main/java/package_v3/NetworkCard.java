package package_v3;



public class NetworkCard {
      
    /* Attributes */
    private String devName; 
    private String intName;
    private String intercoDevName;
    private String macAddr;
    private String ipAddr;

    /* Constructor */
    public NetworkCard(String devName,String intName, String intercoDevName, String macAddr, String ipAddr){
        this.devName = devName;
        this.intName = intName;
        this.intercoDevName = intercoDevName;
        this.macAddr = macAddr;
        this.ipAddr = ipAddr;
    }
    
    /* Methods */
    public String getDevName() {
        return devName;
    }
    public void setDevName(String devName) {
        this.devName = devName;
    }
    public String getIntName() {
        return intName;
    }
    public void setIntName(String intName) {
        this.intName = intName;
    }
    public String getIntercoDevName() {
        return intercoDevName;
    }
    public void setIntercoDevName(String intercoDevName) {
        this.intercoDevName = intercoDevName;
    }
    public String getMacAddr() {
        return macAddr;
    }
    public void setMacAddr(String macAddr) {
        this.macAddr = macAddr;
    }
    public String getIpAddr() {
        return ipAddr;
    }
    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }
   
    // Avec le constructeur, cherche dans BDD et récupère le début de l'addr MAC
    /*
    public String setConstrMacAddr(String maker){
        VOIR DANS BDMana.fetchDBMacAddrConstr()
    }
    */
    
    public String generateIpAddr(IntercoDev intercoDev){
        String ipAddress="192.168.0.1";
        //select préfix ip avec intercoDev
        //random fin ip (test non existant)
        return ipAddress;
    }
    
}
	