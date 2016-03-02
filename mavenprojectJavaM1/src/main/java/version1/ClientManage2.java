/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package version1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author Alexandra
 */
public class ClientManage2 extends Thread {
    private String reqclient1; 
    private String repclient2; 
    private Socket socketService;
    private PrintStream fluxSortieSocket;
    private BufferedReader fluxEntreeSocket;
    private GestionProtocoleMessagerie gestionprotocolemess;
    private boolean fin;
   
    /**
     *
     * @param pSocketService
     * @param pgestionprotocolemess
     */
    public ClientManage2(Socket pSocketService, GestionProtocoleMessagerie pgestionprotocolemess)
	{
        socketService = pSocketService;
        gestionprotocolemess = pgestionprotocolemess;
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
		reqclient1 = null;
		repclient2 = null;
	}

    /**
     * Gestion des socket et transmission des message au gestionnaire de protocole pour traitement
     */
    public void communiquer()
    {
        try
        {
            reqclient1 = fluxEntreeSocket.readLine();
            System.out.println(reqclient1);
            repclient2 = gestionprotocolemess.traitement(reqclient1);
            System.out.println(repclient2);
            fluxSortieSocket.println(repclient2);
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
    public void run()
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
