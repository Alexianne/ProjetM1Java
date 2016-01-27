package td2ex1;

public class Philosophe implements Runnable {

	// Attributs
	private String nom;
	private Fourchette droite;
	private Fourchette gauche;
	
	// Constructeur
	public Philosophe (String nom, Fourchette droite, Fourchette gauche){
		this.nom=nom;
		this.droite=droite;
		this.gauche=gauche;
	}
	
	// Méthodes
	public void manger(){
		droite.prendreFourchette();
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gauche.prendreFourchette();
		System.out.println(nom+" mange");
		droite.poserFourchette();
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gauche.poserFourchette();
	}
	
	public void penser(){
		System.out.println(nom+" pense");
	}
	
	public void run(){
		for (int i=0;i<10;i++){
			this.manger();
			System.out.println(nom+" a mangé "+(i+1)+" fois.");
			this.penser();
		}
	}
}
