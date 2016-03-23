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
import java.net.UnknownHostException;

/**
 *
 * @author Xav
 */
public class Messagerie_Sender {

    private int serverPort;
    private InetAddress serverAddress;

    /**
     *
     * @param serverPort
     * @param serverAddress
     * @throws UnknownHostException
     */
    public Messagerie_Sender(int serverPort, String serverAddress) throws UnknownHostException {
        this.serverPort = serverPort;
        this.serverAddress = InetAddress.getByName(serverAddress);
    }

    /**
     *
     * @param message
     * @param nameLocal
     * @param to
     * @throws SocketException
     * @throws IOException
     */
    public void sendMessage(String message, String nameLocal, String to) throws SocketException, IOException {
        String msg = Util.MSG + Util.DELIM + nameLocal + Util.DELIM + to + Util.DELIM + message + Util.DELIM;
        send(msg);
    }
    
    /**
     *
     * @param portLocal
     * @param nameLocal
     * @throws SocketException
     * @throws IOException
     */
    public void sayHallo(int portLocal, String nameLocal) throws SocketException, IOException {
        String msg = Util.HELLO + Util.DELIM + portLocal + Util.DELIM + nameLocal + Util.DELIM;
        send(msg);
    }

    private void send(String msg) throws SocketException, IOException {
        byte [] data = msg.getBytes();

        DatagramPacket packet = new DatagramPacket(data, data.length, this.serverAddress, this.serverPort);

        DatagramSocket socket = new DatagramSocket();
        socket.send(packet);
    }

}
