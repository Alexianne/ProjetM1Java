/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package version1;

import java.net.InetAddress;
import java.util.Enumeration;
import java.util.Vector;

/**
 *
 * @author Yassine Mahlaoui
 */
public class ClientMessage {

    private static Vector<ClientMessage> clients;

    static {
        clients = new Vector<ClientMessage>();
    }

    private int portClient;
    private InetAddress ipClient;
    private String nomClient;

    public ClientMessage(int portClient, InetAddress ipClient, String nomClient) {
        this.portClient = portClient;
        this.ipClient = ipClient;
        this.nomClient = nomClient;
        System.out.println("New Client => " + this.portClient + ", " +  this.ipClient + ", " + this.nomClient);
    }

    public InetAddress getIpClient() {
        return ipClient;
    }

    public void setIpClient(InetAddress ipClient) {
        this.ipClient = ipClient;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public int getPortClient() {
        return portClient;
    }

    public void setPortClient(int portClient) {
        this.portClient = portClient;
    }

    public static void addClient(ClientMessage client) {
        clients.add(client);
    }

    public static ClientMessage getClients(String nom) {
        Enumeration<ClientMessage> E = clients.elements();
        while (E.hasMoreElements()) {
            ClientMessage tmp = E.nextElement();
            if (tmp.getNomClient().equals(nom))
                return tmp;
        }
        return null;
    }

    public static boolean isClient(String nom) {
        Enumeration<ClientMessage> E = clients.elements();
        while (E.hasMoreElements()) {
            if (E.nextElement().getNomClient().equals(nom))
                return true;
        }
        return false;
    }

}
