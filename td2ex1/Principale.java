package td2ex1;

public class Principale {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Fourchette f1 = new Fourchette();
		Fourchette f2 = new Fourchette();
		Fourchette f3 = new Fourchette();
		Fourchette f4 = new Fourchette();
		Fourchette f5 = new Fourchette();
		
		Philosophe p1 = new Philosophe("Toto", f1, f2);
		Philosophe p2 = new Philosophe("Tata", f2, f3);
		Philosophe p3 = new Philosophe("Titi", f4, f3); // On inverse l'ordre dans lequel le philosophe prend ces fourchettes pour manger
		Philosophe p4 = new Philosophe("Tete", f4, f5); // 					  afin d'éviter la situation d'inter blocage
		Philosophe p5 = new Philosophe("Tutu", f5, f1);
		
		Thread t1 = new Thread(p1);
		Thread t2 = new Thread(p2);
		Thread t3 = new Thread(p3);
		Thread t4 = new Thread(p4);
		Thread t5 = new Thread(p5);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		
	}

}
