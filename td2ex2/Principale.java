package td2ex2;

public class Principale {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Livre o1 = new Livre("brouillon");
		
		Redacteur r1 = new Redacteur(o1, "Ecrivain1");
		Redacteur r2 = new Redacteur(o1, "Ecrivain2");
		Lecteur l1 = new Lecteur(o1, "Lecteur1");
		Lecteur l2 = new Lecteur(o1, "Lecteur2");
		Lecteur l3 = new Lecteur(o1, "Lecteur3");
		
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		Thread t3 = new Thread(l1);
		Thread t4 = new Thread(l2);
		Thread t5 = new Thread(l3);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		
	}

}
