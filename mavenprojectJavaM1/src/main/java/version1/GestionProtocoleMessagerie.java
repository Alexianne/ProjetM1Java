/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package version1;

import java.util.ArrayList;

/**
 *
 * @author Alexandra
 */
public class GestionProtocoleMessagerie implements Cloneable {
    // Attributs
        private ClientConnect client1;
	private ClientConnect client2;
	// Contructeur

    /**
     *
     */
	public GestionProtocoleMessagerie(){
            client1 = null;
            client2 = null;
		
	}
	// Méthodes
	
    /**
     *
     * @param message
     * @return
     */
    public String mess1(String message){
                String msg[] = message.split(" ");
                String idClient2 = msg[1];
		try {
			//
                        return " ";
		}
		catch (NullPointerException e){
			return "ERREUR Envoie du message impossible";
		}
	}
    
    /**
     *
     * @param message
     * @return
     */
    public String mess2(String message){
                String msg[] = message.split(" ");
		try {
			//
                        return " ";
		}
		catch (NullPointerException e){
			return "ERREUR Envoie du message impossible";
		}
	}
    
    /**
     *
     * @param message
     * @return
     */
    public String traitement(String message){
		String result;
		String type[] = message.split(" ");
		switch (type[0]){
		case "MESS1":
			result=mess1(message);
			return result;
                case "MESS2":
			result=mess2(message);
			return result;
                default:
			return "La requête est inconnue";
		}
        }
                @Override
        public Object clone() throws CloneNotSupportedException {
	    GestionProtocoleMessagerie gestionprotocolemess = null;
	    try {
	    	// On récupère l'instance à renvoyer par l'appel de la 
	      	// méthode super.clone()
	      	gestionprotocolemess = (GestionProtocoleMessagerie) super.clone();
	    } catch(CloneNotSupportedException cnse) {
	      	// Ne devrait jamais arriver car nous implémentons 
	      	// l'interface Cloneable
	      	cnse.printStackTrace(System.err);
	    }
	    
	    
	    // on renvoie le clone
	    return gestionprotocolemess;
	}
}
