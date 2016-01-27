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
	
	// M�thodes
	public void run(){
		for(int i=0;i<10;i++){
			ouvrage.ecrire();
			System.out.println(nom+" a �crit sur l'ouvrage "+ouvrage.getNomLivre());
		}
	}
}
