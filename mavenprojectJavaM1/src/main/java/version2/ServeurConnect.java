/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package version2;
import java.net.ServerSocket;
import version2.GestionProtocole;
/**
 *
 * @author Brice
 */
public class ServeurConnect {
    private final static int port = 7; //utilisation du Port 7 par default

    /**
     *
     * @param gestionprotocole
     */
    public ServeurConnect(GestionProtocole gestionprotocole)
    {
    	this(gestionprotocole, port);
    }

    /**
     *
     * @param gestionprotocole
     * @param port1
     */
    public ServeurConnect(GestionProtocole gestionprotocole, int port1) // Création d'un serveur aevc en parametre un estionnaire de protocole et un port
    {
    	GestionProtocole gestionserveur= gestionprotocole;
    	ServerSocket socketEcoute = null;

        try
        {
            socketEcoute= new ServerSocket(port1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        System.out.println("Création Serveur PORT :" + port);
        System.out.println(socketEcoute);

        while(true)
        {
            System.out.println("Attend qu'un client arrive ...");
            try
            {
                new ClientManage(socketEcoute.accept(), (GestionProtocole) gestionserveur.clone()).start();
                System.out.println("Lancement d'un Client Management...");
                System.out.println("Un nouveau client à était lancé");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Lancement Serveur ( 2 parametres necessaires port et gestionnaire proto)
     */
    public void start()// Lancer un serveur 
    {
		new ServeurConnect(new GestionProtocole(), port);
    }

    
}
