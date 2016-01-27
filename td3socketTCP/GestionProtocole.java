package td3socketTCP;

import java.util.Date;

public class GestionProtocole {
	// Attributs
	private BanqueSimple banque;
	// Contructeur
	public GestionProtocole(){
		banque = new BanqueSimple();
		
	}
	// Méthodes
	
	public String creation(String message){
		String msg[] = message.split(" ");
		String id=msg[1];
		double somme;
		try {
			somme = Double.parseDouble(msg[2]);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ERREUR double attendu";
		}
		banque.creerCompte(id, somme);
		
		return "OK creation";
	}
	
	public String position(String message){
		try {
			String msg[] = message.split(" ");
			String id=msg[1];
			double solde=banque.getSolde(id);
			Date derniereOpe=banque.getDerniereOperation(id);
			return "POS "+solde+" "+derniereOpe.toString();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ERREUR compte inexistant";
		}
	}
	
	public String ajout(String message){
		try {
			String msg[] = message.split(" ");
			String id=msg[1];
			double somme=Double.parseDouble(msg[2]);
			banque.ajouter(id, somme);
			return "OK ajout";
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ERREUR double attendu";
		}
		catch (NullPointerException e2){
			e2.printStackTrace();
			return "ERREUR compte inexistant";
		}
	}
	
	public String retrait(String message){
		try {
			String msg[] = message.split(" ");
			String id=msg[1];
			double somme=Double.parseDouble(msg[2]);
			banque.retirer(id, somme);
			return "OK retrait";
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ERREUR double attendu";
		}
		catch (NullPointerException e2){
			e2.printStackTrace();
			return "ERREUR compte inexistant";
		}
	}
	
	public String traitement(String message){
		String result;
		String type[] = message.split(" ");
		switch (type[0]){
		case "CREATION":
			result=creation(message);
			return result;
		case "POSITION":
			result=position(message);
			return result;
		case "AJOUT":
			result=ajout(message);
			return result;
		case "RETRAIT":
			result=retrait(message);
			return result;
		default:
			return "La requête est inconnue";
		}
	}
}
