package td2ex2;

public class Redacteur implements Runnable {

	// Attributs
	private Livre ouvrage;
	private String nom;
	
	// Constructeur
	public Redacteur(Livre ouvrage, String nom){
		this.ouvrage=ouvrage;
		this.nom=nom;
	}
	
	// Méthodes
	public void run(){
		for(int i=0;i<10;i++){
			ouvrage.ecrire();
			System.out.println(nom+" a écrit sur l'ouvrage "+ouvrage.getNomLivre());
		}
	}
}
