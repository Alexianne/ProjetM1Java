/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package version1_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.UUID;
import java.io.PrintStream;
import java.net.Socket;
import java.net.ServerSocket;
import version1.GestionProtocole;
/**

/**
 *
 * @author Brice
 */
public class ClientConnect {    

    private Socket socketService;
    private PrintStream fluxSortieSocket;
    private BufferedReader fluxEntreeSocket;

    /**
     *
     * @param port
     */
    public ClientConnect(int port) // utilisation du 7 par default
    {
        try
        {
            socketService= new Socket("127.0.0.1",port);// si nim serveur saisissable ATTENTION valeur >65535
            fluxEntreeSocket = new BufferedReader(new InputStreamReader(socketService.getInputStream()));
            fluxSortieSocket = new PrintStream(socketService.getOutputStream());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("Connexion localhost Port :"+port);
    }

    /**
     *
     * @param message
     * @return
     * @throws IOException
     */

    public String communiquer(String message) throws IOException
    {
        //Verification :
        //System.out.println(message);
        fluxSortieSocket.println(message);
        fluxSortieSocket.flush();
        return fluxEntreeSocket.readLine();
    }
    
    /**
     *  Fermeture socket
     */
    public void fermer()
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
}