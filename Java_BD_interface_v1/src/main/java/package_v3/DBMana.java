package package_v3;

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

//connectDB();  Connexion à la BDD
//AddRoom(Room); Ajouter une Room à la BDD

public class DBMana {
    static Connection cnx;
    static Statement stat;
    static ResultSet rst;
		
    public static Connection connectDB(){
        try {
            String url = "jdbc:mysql://localhost:3306/TPjava2";
            String user = "test";
            String passwd = "pass";

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
    
  
    // Avec le constructeur, cherche dans BDD et retourne le début de l'addr MAC
    public String fetchDBMacAddrConstr(String maker){
        String macAddress = "";
        String query = "SELECT IDCard FROM Constructeurs WHERE ConstrName '"+maker+"'; ";
        rst = DBMana.selectDB(query);
        try {
            macAddress = rst.getString(1);
            return macAddress;
        } catch (SQLException ex) {
            Logger.getLogger(NetworkCard.class.getName()).log(Level.SEVERE, null, ex);
            return macAddress;
        }
    }
    
    //Paramétre le routeur auquel le Device est connecté, vérifie les adresses IP en donne une valide et stocke sur BDD
    public static String setDBIPDevice(String IntercoDevName, String DevName, String intName){
        String ipAddrDev; 
        //String ipAddrDB;
        String ipAddrIntercoDev = "0.0.0.0";
        String check = "Novalue";
        boolean IPexist = true;
        int i = 1;
        String query = "SELECT IpAddr FROM Interfaces WHERE InterCoDevName ='"+IntercoDevName+"' AND IntName='"+intName+"';";
        String selectAllIP = "SELECT IpAddr FROM NetworkCards WHERE IntercoDevName = '"+IntercoDevName+"'; ";
        //String insert;
        rst = selectDB(query);
        String IPregex = 
        "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
        Pattern pattern = Pattern.compile(IPregex);
        Matcher matcher;      
        try {
            while (rst.next()){
                ipAddrIntercoDev = rst.getString(1);
            }
            matcher = pattern.matcher(ipAddrIntercoDev);
            if (matcher.find()) 
                ipAddrIntercoDev = matcher.group();
            else
                ipAddrIntercoDev = "0.0.0.0";
            if(DBMana.isIntercoDevSwitch(IntercoDevName)){
                ipAddrIntercoDev = selectDBIpSwitch(IntercoDevName);
                
                
            }
            ipAddrDev = ipAddrIntercoDev.substring( 0, ipAddrIntercoDev.length() -1);
            rst = selectDB(selectAllIP);     
            while( (i<= 255) && IPexist ){
                i++;
                check = ipAddrDev + i;
                while (rst.next() && IPexist ){
                    IPexist = check.equals(rst.getString(1));
                }
                rst.beforeFirst();
            }
            System.out.println(IPexist);
            System.out.println(check);
            if(i==256){
                i=2;
                check = ipAddrDev + i;              
            }   
            //insert = "UPDATE NetworkCards SET IpAddr = '"+check+"' WHERE DevName = '"+DevName+"';" ;
            //insertDB(insert);
            System.out.println(ipAddrDev);
            System.out.println(check);
        } catch (SQLException ex) {
            Logger.getLogger(DBMana.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }
    
    

    public static String getDBDebutMac(String constr)  {
        
            String query = "SELECT IDCard FROM Constructeurs WHERE ConstrName='"+constr+"'";
            rst = selectDB(query);
            String result ="";
        try {
            result = rst.getString(1);
            			
        } catch (SQLException ex) {
            Logger.getLogger(DBMana.class.getName()).log(Level.SEVERE, null, ex);
        }
            return result;
        
    }
    

    /*
    public static Integer getDBNumRoom(String Site)  {
        
            String query = "SELECT NumRoom FROM Rooms WHERE SiteName='"+Site+"'";
            rst = selectDB(query);
            int result =0;
            int cpt = getDBCountRoom(Site);
        try {
            while (rst.next())
            {
                result = rst.getInt(1);
            }			
        } catch (SQLException ex) {
            Logger.getLogger(DBMana.class.getName()).log(Level.SEVERE, null, ex);
        }
            return result;
        
    }*/
    
    public static Integer getDBCountRoom(String Site)  {
        
            String query = "SELECT COUNT(*) FROM Rooms WHERE SiteName='"+Site+"'";
            rst = selectDB(query);
            int result =0;
        try {
            result = rst.getInt(1);
            			
        } catch (SQLException ex) {
            Logger.getLogger(DBMana.class.getName()).log(Level.SEVERE, null, ex);
        }
            return result;
        
    }
    
  
            
    public static Integer getDBNbRoom(Site site) {
        
            String query = "SELECT COUNT(*) FROM Rooms WHERE SiteName='"+site.getSiteName()+"'";
            rst = selectDB(query);
            int result =0;
        try {
            while (rst.next())
            {
                result = rst.getInt(1);
            }			
        } catch (SQLException ex) {
            Logger.getLogger(DBMana.class.getName()).log(Level.SEVERE, null, ex);
        }
            return result;
        
    }
    
    public static ArrayList<Integer> getDBArrayNumRoom(String SiteName){
        String query = "SELECT NumRoom FROM Rooms WHERE SiteName='"+SiteName+"' ;";
            rst = selectDB(query);
            boolean add = true ;
            ArrayList<Integer> numroom = new ArrayList<>();
        try {
            while(rst.next() && add){
                add = numroom.add(rst.getInt(1));
            }
             
            
            			
        } catch (SQLException ex) {
            Logger.getLogger(DBMana.class.getName()).log(Level.SEVERE, null, ex);
        }
            return numroom;
        
    }
    
    public static ArrayList<String> getDBArrayIntercoDev(int Room){
        String query = "SELECT InterCoDevName FROM IntercoDev WHERE NumRoom='"+Room+"' ;";
            rst = selectDB(query);
            boolean add = true ;
            ArrayList<String> NameIntercoDev = new ArrayList<>();
        try {
            while(rst.next() && add){
                add = NameIntercoDev.add(rst.getString(1));
            }
             
            
            			
        } catch (SQLException ex) {
            Logger.getLogger(DBMana.class.getName()).log(Level.SEVERE, null, ex);
        }
            return NameIntercoDev;
        
    }
    

    public static ArrayList<String> getDBArrayDevices(int Room){
        String query = "SELECT DevName FROM Devices WHERE NumRoom='"+Room+"' ;";
            rst = selectDB(query);
            boolean add = true ;
            ArrayList<String> DevName = new ArrayList<>();
            
        try {
            while(rst.next() && add){
                add = DevName.add(rst.getString(1));
            }
          			
        } catch (SQLException ex) {
            Logger.getLogger(DBMana.class.getName()).log(Level.SEVERE, null, ex);
        }
            return DevName;
    }   

    public static ArrayList<String> getDBArrayInterface(String intercoDevName){
        String query = "SELECT IntName FROM Interfaces WHERE IntercoDevName='"+intercoDevName+"' ;";
            rst = selectDB(query);
            boolean add = true ;
            ArrayList<String> intName = new ArrayList<>();
        try {
            while(rst.next() && add){
                add = intName.add(rst.getString(1));
            } 			
        } catch (SQLException ex) {
            Logger.getLogger(DBMana.class.getName()).log(Level.SEVERE, null, ex);
        }
            return intName;
    }
    
    public static Map<String, String> selectDBMapIpInt (){
        String query = "SELECT DevName, IpAddr FROM NetworkCards WHERE IpAddr IS NOT NULL  ;" ;
        rst = selectDB(query);
        boolean add = true ;
        Map<String, String> IPHash = new HashMap<>();
        
        try {
            
            while(rst.next() && add){
                IPHash.put( rst.getString(1), rst.getString(2) );
            } 			
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DBMana.class.getName()).log(Level.SEVERE, null, ex);
        }
            return IPHash;

    }
    
    public static String selectDBIpAddr (String interf, String intercoDev){
        String query = "SELECT IpAddr FROM Interfaces WHERE IntName='"+interf+"' AND InterCoDevName='"+intercoDev+"'  ;" ;
        rst = selectDB(query);
        String ipAddr = "";
        try {
            while(rst.next()){
                ipAddr=rst.getString(1);
            } 	
        } catch (SQLException ex) {
            Logger.getLogger(DBMana.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ipAddr;

    }

    
    public static void AddDBRoom(Room room1){
        String query = "INSERT INTO Rooms VALUES ('"+room1.getSiteName()+"','"+room1.getNumRoom()+"','"+room1.getTypeRoom()+"')";
        insertDB(query);
        System.out.println("Produit bien ajouté ! ");
    }
    
    public static void AddDBIntercoDev(IntercoDev intercoDev){
        String query = "INSERT INTO IntercoDev VALUES ('"+intercoDev.getIntercoDevName()+"','"+intercoDev.getTypeIntercoDev()+"','"+intercoDev.getSiteName()+"','"+intercoDev.getNumRoom()+"')";
        insertDB(query);
        System.out.println("Périphérique bien ajouté ! ");
    }
		
    public static void AddDBDev(Devices dev){
        String query = "INSERT INTO Devices VALUES ('"+dev.getDevName()+"','"+dev.getTypeDev()+"','"+dev.getOS()+"','"+dev.getSiteName()+"','"+dev.getNumRoom()+"')";
        insertDB(query);
        System.out.println("Périphérique bien ajouté ! ");
    }
    
    public static void AddDBInterface(Interface interf){
        String query = "INSERT INTO Interfaces VALUES ('"+interf.getIntName()+"','"+interf.getIntercoDevName()+"','"+interf.getIpAddr()+"', '"+interf.getIntState()+"')";
        insertDB(query);
        System.out.println("Interface bien ajouté ! ");
    }
    public static void UpdateDBInterface(Interface interf){
        String query = "UPDATE Interfaces SET IpAddr='"+interf.getIpAddr()+"', IntState='"+interf.getIntState()+"' WHERE IntName='"+interf.getIntName()+"' AND IntercoDevName='"+interf.getIntercoDevName()+"'";
        System.out.println("query : "+query);
        insertDB(query);
        System.out.println("Interface bien ajouté ! ");
    }

    public static void AddDBNC(NetworkCard nc){
        String query = "INSERT INTO NetworkCards VALUES ('"+nc.getDevName()+"', '"+nc.getIntName()+"', '"+nc.getIntercoDevName()+"','"+nc.getMacAddr()+"','"+nc.getIpAddr()+"')";
        insertDB(query);
        System.out.println("Carte réseau bien ajoutée ! ");	
    }
		
    public static void AddDBSite(Site site) {
        site.setNbRoom(getDBNbRoom(site));
        String query = "INSERT INTO Sites VALUES('"+site.getSiteName()+"','"+site.getAddress()+"')";
        insertDB(query);		
    }
                
    public static DefaultComboBoxModel selectDBSite(DefaultComboBoxModel listSite) {
        try{
            String query = "SELECT SiteName FROM Sites";
            
            rst = selectDB(query);
            String result;
            while (rst.next())
            {
                result = rst.getString(1);
                listSite.addElement(result);
                System.out.println("selected site : "+result);
            }
            return listSite;
         }
         catch(SQLException e){
            return null;
        }
    }

    static boolean roomExist(String roomNum, String siteName) {
        try{
            
            String query = "SELECT COUNT(*) FROM Rooms WHERE SiteName='"+siteName+"'";
            rst = selectDB(query);
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
                String select = "SELECT COUNT(*) FROM Rooms WHERE NumRoom='"+roomNum+"'";
                rst = selectDB(select);
                int result2 =0;
                while (rst.next())
                {
                   result2 = rst.getInt(1);
                }
                System.out.println(result2);
                return result2 == 0;
            }
        }catch(SQLException e){
            e.printStackTrace();
            return false;
	}
    }

    public static DefaultComboBoxModel selectDBRoom(DefaultComboBoxModel listSite, String siteName) {
        try{
            String query = "SELECT NumRoom FROM Rooms WHERE SiteName='"+siteName+"'";
            
            rst = selectDB(query);
            String result;
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
    
    public static DefaultComboBoxModel selectDBConstr(DefaultComboBoxModel listConstr) {
        try{
            String query = "SELECT ConstrName FROM Constructeurs";
            
            rst = selectDB(query);
            String result;
            while (rst.next())
            {
                result = rst.getString(1);
                listConstr.addElement(result);		   
            }
            return listConstr;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    
    public static DefaultComboBoxModel selectDBIntercoDev(DefaultComboBoxModel listIntercoDev) {
        try{
            String query = "SELECT InterCoDevName FROM IntercoDev";
            
            rst = selectDB(query);
            String result;
            while (rst.next())
            {
                result = rst.getString(1);
                listIntercoDev.addElement(result);		   
            }
            return listIntercoDev;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    
    public static boolean isIntercoDevSwitch(String intercoDev) {
        try{
            String query = "SELECT TypeIntercoDev FROM IntercoDev WHERE IntercoDevName='"+intercoDev+"'";
            rst = selectDB(query);
            String type="";
            while (rst.next())
            {
                type = rst.getString(1);		   
            }
            if("Switch".equals(type)){
                return true;
            }
            else
                return false;
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    
    public static String selectDBIpSwitch(String intercoDev) {
        try{
            String query = "SELECT IpAddr FROM Interfaces WHERE IntercoDevName='"+intercoDev+"' AND IntName='#1'";
            rst = selectDB(query);
            String ipAddr="";
            while (rst.next())
            {
                ipAddr = rst.getString(1);		   
            }
            return ipAddr;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    
    public static DefaultComboBoxModel selectDBIntercoDev(DefaultComboBoxModel listIntercoDev, String typeDev) {
        try{
            String query = "SELECT InterCoDevName FROM IntercoDev WHERE TypeIntercoDev='"+typeDev+"'";
            rst = selectDB(query);
            String result;
            while (rst.next())
            {
                result = rst.getString(1);
                listIntercoDev.addElement(result);		   
            }
            return listIntercoDev;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    
    public static DefaultComboBoxModel selectDBDev(DefaultComboBoxModel listDev) {
        try{
            String query = "SELECT DevName FROM Devices";
            
            rst = selectDB(query);
            String result;
            while (rst.next())
            {
                result = rst.getString(1);
                listDev.addElement(result);		   
            }
            return listDev;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    
    public static DefaultComboBoxModel selectDBInt(DefaultComboBoxModel listInt, String intercoDev) {
        try{
            String query = "SELECT IntName FROM Interfaces WHERE IntercoDevName='"+intercoDev+"' AND IntState='down'";
            
            rst = selectDB(query);
            String result;
            while (rst.next())
            {
                result = rst.getString(1);
                listInt.addElement(result);		   
            }
            return listInt;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    
    public static DefaultComboBoxModel selectDBNC(DefaultComboBoxModel listNC, String dev) {
        try{
            String query = "SELECT MacAddr FROM NetworkCards WHERE DevName='"+dev+"'";
            
            rst = selectDB(query);
            String result;
            while (rst.next())
            {
                result = rst.getString(1);
                listNC.addElement(result);		   
            }
            return listNC;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    

    public static boolean intercoDevExist(String intercoDevName) {
        try{
            String query = "SELECT COUNT(*) FROM IntercoDev WHERE IntercoDevName='"+intercoDevName+"'";
            rst = selectDB(query);
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
                return false;
            }
        }catch(SQLException e){
            e.printStackTrace();
            return false;
	}
    }
    
    public static boolean devExist(String devName) {
        try{
            String query = "SELECT COUNT(*) FROM Devices WHERE DevName='"+devName+"'";
            rst = selectDB(query);
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
                return false;
            }
        }catch(SQLException e){
            e.printStackTrace();
            return false;
	}
    }
	

    
    public static boolean checkID(String login, String pass) throws SQLException{
        String loginDB;
        String passDB;
        boolean check = false;
        String query = "SELECT * FROM Users ;";
        rst = selectDB(query);
        
        while (rst.next() && !(check))
            {
                loginDB = rst.getString(1);
                passDB = rst.getString(2);
                check = pass.equals(passDB) && login.equals(loginDB);
            }
        
        return check;
    }
    
    /*
    public static boolean supprNCDevRoom(int Room){
            String query = "DELETE FROM NetworkCards WHERE DevName = '"+Room+"' ";
            return insertDB(query);
            
    }
    */
    
    public static boolean supprNCDevName(String DevName){
            String query = "DELETE FROM NetworkCards WHERE DevName = '"+DevName+"'; ";
            return insertDB(query);
            
    }
    
    public static boolean supprNCInterCoDevName(String InterCoDevName){
            String query = "DELETE FROM NetworkCards WHERE IntercoDevName = '"+InterCoDevName+"'; ";
            return insertDB(query);
            
    }
    
    public static boolean supprNCMac(String MacAddr){
            String query = "DELETE FROM NetworkCards WHERE MacAddr = '"+MacAddr+"'; ";
            return insertDB(query);
            
    }
    
    public static boolean supprInterCoDevInterface(String intercoDevName, String intName){
            String query = "DELETE FROM Interfaces WHERE InterCoDevName = '"+intercoDevName+"' AND IntName='"+intName+"' ";
            return insertDB(query);
    }
    
    public static boolean supprInterCoDevName(String intercoDevName){
            String query = "DELETE FROM IntercoDev WHERE InterCoDevName = '"+intercoDevName+"'; ";
            insertDB(query);
            supprNCInterCoDevName(intercoDevName);
            String query2 = "SELECT IntName FROM Interfaces WHERE IntercoDevName='"+intercoDevName+"' ;";
            rst = selectDB(query2);
            boolean suppr = true ;
            try {
                while(rst.next() && suppr){
                    suppr = supprInterCoDevInterface(intercoDevName, rst.getString(1));
                } 			
            } catch (SQLException ex) {
                Logger.getLogger(DBMana.class.getName()).log(Level.SEVERE, null, ex);
            }
            return suppr;
    }
    
    public static boolean supprInterCoDevRoom(int Room) {
            String query = "DELETE FROM IntercoDev WHERE NumRoom = '"+Room+"'; ";
            String select = "SELECT IntercoDevName FROM IntercoDev WHERE NumRoom = '"+Room+"'; " ;
            boolean check = insertDB(query);
            rst = selectDB(select);
             
        try {
            while(rst.next()){
                check = check && supprNCInterCoDevName(rst.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBMana.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            return check;
    }
    
    public static boolean supprInterCoDevSite(String Site) {
            String query = "DELETE FROM IntercoDev WHERE SiteName = '"+Site+"'; ";
            String select = "SELECT Room FROM Rooms WHERE SiteName = '"+Site+"'; " ;
            boolean check = true;
            rst = selectDB(select);
             
        try {
            while(rst.next()){
                check = check && supprInterCoDevRoom(rst.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBMana.class.getName()).log(Level.SEVERE, null, ex);
        }
        check = check && insertDB(query);
            return check;
    }
    
    public static boolean supprDevName(String Name){
            String query = "DELETE FROM Devices WHERE DevName = '"+Name+"'; ";
            supprNCDevName(Name);
            return insertDB(query);
    }
    
    public static boolean supprDevRoom(int Room){
            String query = "DELETE FROM Devices WHERE NumRoom = '"+Room+"'; ";
            String select = "SELECT DevName FROM Devices WHERE NumRoom = '"+Room+"'; " ;
            boolean check = true;
            rst = selectDB(select);
             
        try {
            while(rst.next()){
                check = check && supprDevName(rst.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBMana.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        check = check &&  insertDB(query);    
        
            return check;
    }
    
    public static boolean supprDevSite(String Site) {
            String query = "DELETE FROM Devices WHERE SiteName = '"+Site+"'; ";
            String select = "SELECT NumRoom FROM Rooms WHERE SiteName = '"+Site+"'; " ;
            boolean check = insertDB(query);
            rst = selectDB(select);
             
        try {
            while(rst.next()){
                check = check && supprDevRoom(rst.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBMana.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            return check;
    }
    
    public static boolean supprRoom(int Room){
        boolean check;
        check = supprDevRoom(Room) && supprInterCoDevRoom(Room);
        
        return check;
        
    }
    
    public static boolean supprSite(String Site){
        
        String query = "DELETE FROM Sites WHERE SiteName = '"+Site+"'; ";
        String select = "SELECT NumRoom FROM Rooms WHERE SiteName = '"+Site+"'; " ;
        boolean check = true ;
        rst = selectDB(select);
             
        try {
            while(rst.next()){
                check = check && supprRoom(rst.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBMana.class.getName()).log(Level.SEVERE, null, ex);
        }
   
        check = check && insertDB(query);
        
        return check;
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
