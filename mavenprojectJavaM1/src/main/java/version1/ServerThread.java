/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package version1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author Yassine Mahlaoui
 */
public class ServerThread extends Thread {

    private byte [] tampon;
    private int length = 1024;
    private boolean turn = true;
    private int port;
    private DatagramPacket packet;
    private DatagramSocket socket;

    public ServerThread (int port) {
        this.port = port;
        this.tampon = new byte[length];
    }

    @Override
    public void run() {
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException ex) {
            ex.printStackTrace();
            System.exit(0);
        }
        System.out.println("Started ..");
        while (turn) {
            packet = new DatagramPacket(tampon, length);
            try {
                socket.receive(packet);
                System.out.println("new message ...");
                new Responder(packet);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
        }
    }
    
}
