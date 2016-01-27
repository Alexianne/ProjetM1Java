package td2ex2;

public class Livre {
	
	// Attributs
	private String titre;
	private int nbLecteur=0;
	
	// Constructeur
	public Livre(String titre){
		this.titre=titre;
	}
	
	// Méthodes
	public String getNomLivre(){
		return titre;
	}
	
	public synchronized void ecrire(){
		while(nbLecteur>0){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized void commencerLecture(){
		nbLecteur++;
	}
	
	public void lire(){
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized void finirLecture(){
		nbLecteur--;
		if (nbLecteur==0){
			notifyAll();
		}
	}

}
