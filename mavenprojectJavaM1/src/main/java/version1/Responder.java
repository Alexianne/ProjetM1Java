/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package version1;

import version1.Util;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 *
 * @author Xav
 */
public class Responder extends Thread {

    private DatagramPacket packet;

    /**
     *
     * @param packet
     */
    public Responder(DatagramPacket packet) {
        this.packet = packet;
        this.start();
    }

    @Override
    public void run() {
        String msg = new String(packet.getData());
        
        StringTokenizer token = new StringTokenizer(msg, Util.DELIM);
        Vector<String> elements = new Vector<String>();
        while (token.hasMoreTokens())
            elements.add(token.nextToken());

        if (elements.firstElement().equals(Util.HELLO) && !ClientMessage.isClient(elements.elementAt(2))) {
            int port = Integer.parseInt(elements.elementAt(1));
            InetAddress host = packet.getAddress();
            String name = elements.elementAt(2);

            ClientMessage newOne = new ClientMessage(port, host, name);
            ClientMessage.addClient(newOne);
            System.out.println("Yow, hallo from " + name + " at port " + port + "  in address " + host);
        }
        else if (elements.firstElement().equals(Util.MSG)) {
            String name = elements.elementAt(2);
            ClientMessage to = ClientMessage.getClients(name);

            if (to == null)
                return;
            
            String from = elements.elementAt(1);
            String message = elements.elementAt(3);
            String msgToForward = from + Util.DELIM + message + Util.DELIM;


            System.out.println("Yow, message from " + from + " to " + name + " at port " + to.getPortClient() + "  in address " + to.getIpClient());

            DatagramPacket toSendPacket = new DatagramPacket(msgToForward.getBytes(),
                                                                msgToForward.getBytes().length,
                                                                    to.getIpClient(),
                                                                        to.getPortClient());
            try {
                DatagramSocket toSendSocket = new DatagramSocket();
                toSendSocket.send(toSendPacket);
            } catch (SocketException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
