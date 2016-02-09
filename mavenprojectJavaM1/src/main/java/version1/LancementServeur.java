/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package version1;
/**
 *
 * @author Brice
 */
public class LancementServeur
{
	private final static int port = 7;

    /**
     *
     * @param args
     */
    public static void main(String[] args)
	{
        System.out.println( "Serveur Lancé sur le PORT :" + port);
        GestionProtocole echange = new GestionProtocole();
        new ServeurConnect(echange, port).start();
        System.out.println("serveur détruit");
	}
}
