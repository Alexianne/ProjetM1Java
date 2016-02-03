/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package version1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *
 * @author Brice
 */
 
public class GestionProtocole {
	// Attributs
	private Authentification servAuth;
        private Données servData;
	// Contructeur
	public GestionProtocole(){
		servAuth = new Authentification();
                servData = new Données();
		
	}
	// Méthodes
	
	public String newauth(String message){
		try{
                String msg[] = message.split(" ");
		String pseudo = msg[1];
		String mdp =msg[2];
                int id = servAuth.newauth(pseudo,mdp);
		return "OK "+id;
                } catch (NullPointerException e) {
			return "ERREUR Authentification Création de compte";
		}
	}
	
	public String auth(String message){
		try {
			String msg[] = message.split(" ");
			String pseudo=msg[1];
			String mdp=msg[2];
			boolean rep;
                        rep = servAuth.auth(pseudo,mdp);
                        return "OK "+rep;
		} catch (NullPointerException e) {
			return "ERREUR Authentification compte inexistant";
		}
	}
	
	public String newuser(String message){
		try {
			String msg[] = message.split(" ");
			String prenom = msg[1];
			String nom = msg[2];
                        String email = msg[3];
                        String phone = msg[4];
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd mm yyyy", Locale.FRANCE);
                        LocalDate naissance = LocalDate.parse(msg[6], formatter);
                        double visible = Double.parseDouble(msg[6]);
                        double id = Double.parseDouble(msg[6]);
			boolean rep;
                        rep = servData.newuser(prenom,nom,email,phone,naissance,visible,id);
                        return "OK "+rep;
		} catch (NullPointerException e) {
			return "ERREUR DATA création compte ";
		}
	}
	
	public String getlist(String message){
		try {
			String list = servData.getlist(message);
			return "OK"+list;
		}
		catch (NullPointerException e){
			return "ERREUR Récupération Données Impossible";
		}
	}
        
        public String getinfo(String message){
                String msg[] = message.split(" ");
		String chnom = msg[1];
		try {
			String listinfo = servData.getinfo(chnom);
			return "OK"+listinfo;
		}
		catch (NullPointerException e){
			return "ERREUR Récupération des NOMS Impossible";
		}
	}
        
                public String getnom(String message){
                String msg[] = message.split(" ");
		String motclé = msg[1];
		try {
			String listnom = servData.getnom(motclé);
			return "OK"+listnom;
		}
		catch (NullPointerException e){
			return "ERREUR Récupération des NOMS Impossible";
		}
	}
        
                	public String modifinfo(String message){
		try {
			String msg[] = message.split(" ");
			String prenom = msg[1];
			String nom = msg[2];
                        String email = msg[3];
                        String phone = msg[4];
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd mm yyyy", Locale.FRANCE);
                        LocalDate naissance = LocalDate.parse(msg[6], formatter);
                        double visible = Double.parseDouble(msg[6]);
                        double id = Double.parseDouble(msg[6]);
			boolean modif;
                        modif = servData.modifinfo(prenom,nom,email,phone,naissance,visible,id);
                        return "OK "+modif;
		} catch (NullPointerException e) {
			return "ERREUR DATA Modification compte ";
		}
	}
                        
                        public String test(String message){
		try {
			String msg[] = message.split(" ");
			String prenom = msg[1];
			String nom = msg[2];
                        String email = msg[3];
                        String phone = msg[4];
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd mm yyyy", Locale.FRANCE);
                        LocalDate naissance = LocalDate.parse(msg[6], formatter);
                        double visible = Double.parseDouble(msg[6]);
                        double id = Double.parseDouble(msg[6]);
			boolean modif;
                        modif = servData.modifinfo(prenom,nom,email,phone,naissance,visible,id);
                        return "OK "+modif;
		} catch (NullPointerException e) {
			return "ERREUR DATA Modification compte ";
		}
	}
                                                
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
                default:
			return "La requête est inconnue";
		}
	}
}