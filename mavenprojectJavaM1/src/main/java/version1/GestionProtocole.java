/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package version1;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Locale;
import java.util.UUID;


/**
 *
 * @author Brice
 */
 
public class GestionProtocole implements Cloneable{
	// Attributs
	private AuthentificationBase servAuth;
        private DataBase servData;
	// Contructeur

    /**
     *
     */
	public GestionProtocole(){
		servAuth = new AuthentificationBase();
                servData = new DataBase();
		
	}
	// Méthodes
	
    /**
     *
     * @param message
     * @return
     */
    public String newauth(String message){
		try{
                String msg[] = message.split(" ");
		String pseudo = msg[1];
		String mdp =msg[2];
                    boolean id = servAuth.newauth(pseudo,mdp);
		return "OK "+id;
                } catch (NullPointerException e) {
			return "ERREUR Authentification Création de compte";
		}
	}
	
    /**
     *
     * @param message
     * @return
     */
    public String auth(String message){
            //System.out.println("MESS : "+message);
		try {
			String msg[] = message.split(" ");
			String pseudo=msg[1];
			String mdp=msg[2];
                        String err = null;
			try {
                            ArrayList<String> rep = servAuth.auth(pseudo,mdp);
                            err = rep.get(0);
                        }
                        catch(IndexOutOfBoundsException e){
                           err = "ERR200";
                        }
                        return "OK "+err;
		} catch (NullPointerException e) {
			return "ERREUR ERR200";
		}
	}
	
    /**
     *
     * @param message
     * @return
     */
    public String newuser(String message){
		try {
			String msg[] = message.split(" ");
			String id = msg[1];
                        String nom = msg[2];
			String prenom = msg[3];
                        String email = msg[4];
                        String phone = msg[5];
                        String naissance = msg[6];
                        double visible = Double.parseDouble(msg[7]);
                        System.out.println(visible);
			boolean rep = servData.newuser(id,nom,prenom,email,phone,naissance,visible);
                        return "OK "+rep;
		} catch (NullPointerException e) {
			return "ERREUR DATA création compte ";
		}
	}
	
	public String getlist(String message){
		try {
                        String msg[] = message.split(" ");
			String nom = msg[1];
			ArrayList<String> list = servData.getlist(nom);
                        String res = String.join(" ", list);
			return "OK "+res;
		}
		catch (NullPointerException e){
			return "ERREUR Récupération Données Impossible";
		}
	}

    /**
     *
     * @param message
     * @return
     */

        
        public String getinfo(String message){
                String msg[] = message.split(" ");
		String id = msg[1];
		try {
			ArrayList<String> listinfo = servData.getinfo(id);
                        String res = String.join(" ", listinfo);
			return "OK "+res;
		}
		catch (NullPointerException e){
			return "ERREUR Récupération des NOMS Impossible";
		}
	}
        
    /**
     *
     * @param message
     * @return
     */
    public String getnom(String message){
                String msg[] = message.split(" ");
		String motclé = msg[1];
		try {
			ArrayList<Integer> listnom = servData.getnom(motclé);
			return "OK"+listnom;
		}
		catch (NullPointerException e){
			return "ERREUR Récupération des NOMS Impossible";
		}
	}
        
    /**
     *
     * @param message
     * @return
     */
    public String modifinfo(String message){
		try {
			String msg[] = message.split(" ");
			String id = msg[1];
                        String prenom = msg[2];
			String nom = msg[3];
                        String email = msg[4];
                        String phone = msg[5];
                        String naissance = msg[6]; 
                        double visible = Double.parseDouble(msg[7]);
			boolean modif = servData.modifinfo(id,prenom,nom,email,phone,naissance,visible);
                        return "OK "+modif;
		} catch (NullPointerException e) {
			return "ERREUR DATA Modification compte ";
		}
	}
                        
    /**
     *
     * @param message
     * @return
     */
    public String addinfo(String message){
		try {
			String msg[] = message.split(" ");
			String id = msg[1];
                        String competence = msg[2];
			String niv = msg[3];
                        String description = msg[4];
                        String diplome = msg[5];
                        String annee = msg[6];
                        double visible = Double.parseDouble(msg[7]);
			boolean info = servData.addinfo(id,competence,niv,description,diplome,annee,visible);
                        return "OK "+info;
		} catch (NullPointerException e) {
			return "ERREUR DATA Modification compte ";
		}
	}
                                                
    /**
     *
     * @param message
     * @return
     */
    public String suppcompte(String message){
		try {
			String msg[] = message.split(" ");
			String id = msg[1];
			boolean rep;
                        rep = servAuth.suppcompte(id);
                        return "OK "+rep;
		} catch (NullPointerException e) {
			return "ERREUR Authentification compte inexistant";
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
		case "NEWAUTH":
			result=newauth(message);
			return result;
		case "AUTH":
			result=auth(message);
			return result;
		case "NEWUSER":
			result=newuser(message);
			return result;
		case "GETLIST":
			result=getlist(message);
			return result;
		case "GETNOM":
			result=getnom(message);
			return result;
                case "GETINFO":
			result=getinfo(message);
			return result;
                case "MODIFINFO":
			result=modifinfo(message);
			return result;
                case "SUPPCOMPTE":
			result=suppcompte(message);
			return result;
                case "ADDINFO":
                        result=addinfo(message);
			return result;
                default:
			return "La requête est inconnue";
		}
        }
                @Override
        public Object clone() throws CloneNotSupportedException {
	    GestionProtocole gestionprotocole = null;
	    try {
	    	// On récupère l'instance à renvoyer par l'appel de la 
	      	// méthode super.clone()
	      	gestionprotocole = (GestionProtocole) super.clone();
	    } catch(CloneNotSupportedException cnse) {
	      	// Ne devrait jamais arriver car nous implémentons 
	      	// l'interface Cloneable
	      	cnse.printStackTrace(System.err);
	    }
	    
	    
	    // on renvoie le clone
	    return gestionprotocole;
	}
}