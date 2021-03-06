package td3socketTCP;

/*
 * EchoServer.java
 *
 * Created on 12 septembre 2000, 11:07
 */

import java.net.*;
import java.io.*;

/**
 * @author Patrice Torguet
 */
public class EchoServer extends Object {

	/** Port par défaut */
	public final static int portEcho = 7;

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		ServerSocket leServeur = null;
		Socket connexionCourante;
		GestionProtocole gestProto = new GestionProtocole();

		try {
			leServeur = new ServerSocket(portEcho);
		} catch (IOException ex) {
			// fin de connexion
			System.err.println("Impossible de cr�er un socket serveur sur ce port : "+ ex);

			try {
				// on demande un port anonyme
				leServeur = new ServerSocket(0);
			} catch (IOException ex2) {
				// fin de connexion
				System.err.println("Impossible de cr�er un socket serveur : "+ ex);
			}
		}

		if (leServeur != null) {
			try {
				System.err.println("En attente de connexion sur le port : "+ leServeur.getLocalPort());
				while (true) {
					connexionCourante = leServeur.accept();

					System.err.println("Nouvelle connexion : "+ connexionCourante);
					
					try {
						PrintStream fluxSortieSocket = new PrintStream(connexionCourante.getOutputStream());
						BufferedReader fluxEntreeSocket = new BufferedReader(new InputStreamReader(connexionCourante.getInputStream()));

						while (true) {
							String req = fluxEntreeSocket.readLine();
							
							if(req==null) break;
							
							System.out.println("req : " + req);
							
							String rep=gestProto.traitement(req);

							fluxSortieSocket.println(rep);


							
						}
						System.err.println("Fin de connexion");
					} catch (IOException ex) {
						// fin de connexion
						System.err.println("Fin de connexion : " + ex);
					}

					connexionCourante.close();
				}
			} catch (Exception ex) {
				// erreur de connexion
				System.err.println("Une erreur est survenue : " + ex);
				ex.printStackTrace();
			}
		}
	}

}