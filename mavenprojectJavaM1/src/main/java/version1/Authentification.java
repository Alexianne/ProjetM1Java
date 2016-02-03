/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package version1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Brice
 */

public class Authentification {
    public static void main() {/* Connexion à la base de données */
String url = "jdbc:mysql://localhost:3306/authentification";
String utilisateur = "root";
String motDePasse = "stri";
Connection connexion = null;
try {
    connexion = DriverManager.getConnection( url, utilisateur, motDePasse );

    /* Ici, nous placerons nos requêtes vers la BDD */
    /* ... */

} catch ( SQLException e ) {
    /* Gérer les éventuelles erreurs ici */
} finally {
    if ( connexion != null )
        try {
            /* Fermeture de la connexion */
            connexion.close();
        } catch ( SQLException ignore ) {
            /* Si une erreur survient lors de la fermeture, il suffit de l'ignorer. */
        }
}

}
        
    int newauth(String pseudo, String mdp) {
        /* Exécution d'une requête d'écriture */
        int ajout = statement.executeUpdate( "INSERT INTO Utilisateur (email, mot_de_passe, nom, date_inscription) VALUES ('jmarc@mail.fr', MD5('lavieestbelle78'), 'jean-marc', NOW());" );
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    boolean auth(String pseudo, String mdp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    boolean suppcompte(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
