package td2ex2;

public class Lecteur implements Runnable {

	// Attributs
		private Livre ouvrage;
		private String nom;
		
		// Constructeur
		public Lecteur(Livre ouvrage, String nom){
			this.ouvrage=ouvrage;
			this.nom=nom;
		}
		
		// Méthodes
		public void run(){
			for(int i=0;i<10;i++){
				ouvrage.commencerLecture();
				ouvrage.lire();
				System.out.println(nom+" a lu l'ouvrage "+ouvrage.getNomLivre());
				ouvrage.finirLecture();
				
			}
		}
}
