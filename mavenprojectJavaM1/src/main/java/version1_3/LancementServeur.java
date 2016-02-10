/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package version1_3;

import version1_3.GestionProtocole;
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
        GestionProtocole echanger = new GestionProtocole();
        new ServeurConnect(echanger, port).start();
        System.out.println("serveur détruit");
	}
}
