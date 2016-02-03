package package_v2;

import java.util.Scanner;


public class Main {



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Room room2 = new Room ( " "," " , " ");
		/*
		System.out.println("Salam ou Alikoum   \n");
		System.out.println("Entrez le nom du local : \n");
		String tempstr = sc.nextLine();
		room2.setSiteName(tempstr);
		System.out.println("Entrez le numéro de la salle : \n");
		int tempint = sc.nextInt();
		room2.setNbRoom(tempint);
		System.out.println("Entrez le type de Salle (Tp, Cours, Serveur) : \n");
		sc.nextLine();
		tempstr = sc.nextLine();
		room2.setType(tempstr);
		System.out.println("Entrez le nombre d'équipements d'interco : ");
		tempint = sc.nextInt();
		room2.setNbIntercoDev(tempint);
		System.out.println("Entrez le nombre de devices : ");
		tempint = sc.nextInt();
		room2.setNbDevices(tempint);
		DBMana.AddRoom(room2);
		*/
		//Site site1 = new Site ("TBS","33 Rue des chênes");
		//DBMana.AddDBSite(site1);
                //Site site2 = new Site ("U2","118 route de narbonne");
		//DBMana.AddDBSite(site2);
		//Devices dev = new Devices("ma","PC","Linux", 301);
		NetworkCard cn = new NetworkCard("DAUD","0000F0569466");
		//dev.setNc(cn);
		//DBMana.AddDBDev(dev);
		DBMana.AddDBNC(cn);
		DBMana.setDBConstr(cn);
		
	}

}
