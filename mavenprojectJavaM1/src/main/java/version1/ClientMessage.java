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
 * @author Xav
 */
public class ClientMessage {

    private static Vector<ClientMessage> clients;

    static {
        clients = new Vector<ClientMessage>();
    }

    private int portClient;
    private InetAddress ipClient;
    private String nomClient;

    /**
     *
     * @param portClient
     * @param ipClient
     * @param nomClient
     */
    public ClientMessage(int portClient, InetAddress ipClient, String nomClient) {
        this.portClient = portClient;
        this.ipClient = ipClient;
        this.nomClient = nomClient;
        System.out.println("New Client => " + this.portClient + ", " +  this.ipClient + ", " + this.nomClient);
    }

    /**
     *
     * @return
     */
    public InetAddress getIpClient() {
        return ipClient;
    }

    /**
     *
     * @param ipClient
     */
    public void setIpClient(InetAddress ipClient) {
        this.ipClient = ipClient;
    }

    /**
     *
     * @return
     */
    public String getNomClient() {
        return nomClient;
    }

    /**
     *
     * @param nomClient
     */
    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    /**
     *
     * @return
     */
    public int getPortClient() {
        return portClient;
    }

    /**
     *
     * @param portClient
     */
    public void setPortClient(int portClient) {
        this.portClient = portClient;
    }

    /**
     *
     * @param client
     */
    public static void addClient(ClientMessage client) {
        clients.add(client);
    }

    /**
     *
     * @param nom
     * @return
     */
    public static ClientMessage getClients(String nom) {
        Enumeration<ClientMessage> E = clients.elements();
        while (E.hasMoreElements()) {
            ClientMessage tmp = E.nextElement();
            if (tmp.getNomClient().equals(nom))
                return tmp;
        }
        return null;
    }

    /**
     *
     * @param nom
     * @return
     */
    public static boolean isClient(String nom) {
        Enumeration<ClientMessage> E = clients.elements();
        while (E.hasMoreElements()) {
            if (E.nextElement().getNomClient().equals(nom))
                return true;
        }
        return false;
    }

}
