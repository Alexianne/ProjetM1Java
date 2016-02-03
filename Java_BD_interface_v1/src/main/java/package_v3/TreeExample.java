package package_v3;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
 
public class TreeExample extends JFrame
{
    
      
    private final JTree tree;
    public TreeExample()
    {
        int nbrooms;
        int i, j;
        Map<String, String> IPHash = new HashMap<>(); 
        IPHash = DBMana.selectDBMapIpInt();
               
        String Site = "U2";
        //La racine = Site 
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(Site);
        
        //Premier fils = Rooms 
  
        ArrayList<Integer> Rooms = new ArrayList<>(); 
        Rooms = DBMana.getDBArrayNumRoom(Site);
        
              
        //Second Fils IntercoDev et Devices
        ArrayList<String> Devices = new ArrayList<>();
        ArrayList<String> NameIntercoDev = new ArrayList<>();
        
        
        
        for ( i=0; i<Rooms.size(); i++ ){
            
            DefaultMutableTreeNode RoomsNode = new DefaultMutableTreeNode(Rooms.get(i));
            root.add(RoomsNode);
            NameIntercoDev = DBMana.getDBArrayIntercoDev(Rooms.get(i));
            Devices = DBMana.getDBArrayDevices(Rooms.get(i));
            
            
             //Ajout des Interco
            for ( j=0; j<NameIntercoDev.size(); j++ ){
                
               DefaultMutableTreeNode IntercoDevNode = new DefaultMutableTreeNode(NameIntercoDev.get(j) );
               RoomsNode.add(IntercoDevNode);             
            }
            
            //Ajout Devices 
            for ( j=0; j<Devices.size(); j++ ){  
                DefaultMutableTreeNode DevNode = new DefaultMutableTreeNode(Devices.get(j)+ "  " + IPHash.get(Devices.get(j)));    
                RoomsNode.add(DevNode);
            }
            
            
            
        }
     
        
        //Creation de l'arbre
        tree = new JTree(root);
        add(tree);
         
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("JTree Example");  
        this.setSize(800, 600);
       
        this.setVisible(true);
    }
     
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                new TreeExample();
                
            }
        });
    }        
}