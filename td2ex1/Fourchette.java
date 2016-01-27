package td2ex1;

public class Fourchette {
	// Attributs
	private boolean utilisee;
	
	// Constructeur
	public Fourchette(){
		this.utilisee=false;
	}
	
	// Méthodes
	public synchronized boolean prendreFourchette(){
		if(this.utilisee==true){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.utilisee=true;
		return true;
	}
	
	public synchronized boolean poserFourchette(){
		if(this.utilisee==false){
			return false;
		}
		this.utilisee=false;
		notify();
		return true;
	}
}
