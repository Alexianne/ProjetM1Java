/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package version1;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
/**
 *
 * @author Brice
 */
public class Données {

    boolean newuser(String prenom, String nom, String email, String phone, LocalDate naissance, double visible, double id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    String getlist() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    String getnom(String motclé) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    String getinfo(String chnom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    boolean modifinfo(String prenom, String nom, String email, String phone, LocalDate naissance, double visible, double id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


	class CompteEnBanque {
		private double solde;
		private Date derniereOperation;

		public CompteEnBanque(double solde) {
			this.solde = solde;
			derniereOperation = new Date(); // recupere la date courante
		}

		public double getSolde() {
			return solde;
		}

		public Date getDerniereOperation() {
			return derniereOperation;
		}

		public void ajouter(double somme) {
			solde += somme;
			derniereOperation = new Date(); // recupere la date courante
		}

		public void retirer(double somme) {
			solde -= somme;
			derniereOperation = new Date(); // recupere la date courante
		}
	}

	HashMap<String, CompteEnBanque> comptes;

	public BanqueSimple() {
		comptes = new HashMap<String, CompteEnBanque>();
	}

	public void creerCompte(String id, double somme) {
		comptes.put(id, new CompteEnBanque(somme));
	}

	public void ajouter(String id, double somme) {
		CompteEnBanque cpt = comptes.get(id);
		cpt.ajouter(somme);
	}

	public void retirer(String id, double somme) {
		CompteEnBanque cpt = comptes.get(id);
		cpt.retirer(somme);
	}

	public double getSolde(String id) {
		CompteEnBanque cpt = comptes.get(id);
		return cpt.getSolde();
	}

	public Date getDerniereOperation(String id) {
		CompteEnBanque cpt = comptes.get(id);
		return cpt.getDerniereOperation();
	}

	public static void main(String[] args) {
		BanqueSimple s = new BanqueSimple();
		s.creerCompte("ABC1234", 1000);
		s.ajouter("ABC1234", 100);
		s.retirer("ABC1234", 30);
		double solde = s.getSolde("ABC1234");
		Date date = s.getDerniereOperation("ABC1234");
		System.out.println("ABC1234 -> " + solde + " " + date);
	}
}
    
