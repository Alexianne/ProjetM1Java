package td3socketUDP;

/*
 * EchoClient.java
 *
 * Created on 6 septembre 2000, 11:11
 */

import java.io.*;
import java.net.*;

public class EchoClient extends Object {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		String req;
		BufferedReader fluxEntreeStandard;
		Socket leSocket;
		PrintStream fluxSortieSocket;
		BufferedReader fluxEntreeSocket;

		try {
			fluxEntreeStandard = new BufferedReader(new InputStreamReader(System.in));

			System.out.println("Tapez votre req : ");

			req = fluxEntreeStandard.readLine();

			System.out.println(req);

			leSocket = new Socket("localhost", 7); // socket sur echo

			System.err.println("Connection sur : " + leSocket);

			fluxSortieSocket = new PrintStream(leSocket.getOutputStream());
			fluxEntreeSocket = new BufferedReader(new InputStreamReader(leSocket.getInputStream()));

			fluxSortieSocket.println(req);

			String retour = fluxEntreeSocket.readLine();

			System.out.println("Reponse du serveur : " + retour);

			leSocket.close();
		} catch (UnknownHostException ex) {
			System.err.println("Machine inconnue : " + ex);
			ex.printStackTrace();
		} catch (IOException ex) {
			System.err.println("Erreur : " + ex);
			ex.printStackTrace();
		}
	}

}
