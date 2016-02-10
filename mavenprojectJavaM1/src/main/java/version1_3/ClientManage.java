/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package version1_3;

import version1.*;
import java.io.*;
import java.net.*;
import version1.GestionProtocole;
/**
 *
 * @author Brice
 */

public class ClientManage extends Thread
{
    private String reqclient; 
    private String repserv; 
    private Socket socketService;
    private PrintStream fluxSortieSocket;
    private BufferedReader fluxEntreeSocket;
    private GestionProtocole gestionprotocole;
    private boolean fin;
   
    /**
     *
     * @param pSocketService
     * @param pgestionprotocole
     */
    public ClientManage(Socket pSocketService, GestionProtocole pgestionprotocole)
	{
        socketService = pSocketService;
        gestionprotocole = pgestionprotocole;
        try
        {
            fluxEntreeSocket= new BufferedReader(new InputStreamReader(socketService.getInputStream()));
            fluxSortieSocket= new PrintStream(socketService.getOutputStream());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		fin = false;
		reqclient = null;
		repserv = null;
	}

    /**
     * Gestion des socket et transmission des message au gestionnaire de protocole pour traitement
     */
    public void communiquer()
    {
        try
        {
            reqclient = fluxEntreeSocket.readLine();
            System.out.println(reqclient);
            repserv = gestionprotocole.traitement(reqclient);
            System.out.println(repserv);
            fluxSortieSocket.println(repserv);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fin = true;
        }
    }

    /**
     *  Fermeture Socket
     */
    public void close()
    {
        try
        {
            this.socketService.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void start()
    {
        try
        {
            while(!fluxEntreeSocket.ready())
        	{
                    // System.out.println("attend ...");
        	}
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        while(!fin)
        {
            communiquer();
        }
        
        close();
    }
}
    

