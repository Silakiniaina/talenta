package statut_conge;

import java.sql.Connection;
import java.sql.PreparedStatement;

import model.Employe;

public class ScUtils {

    public static boolean canTakeConge(Employe emp, Connection conn) {
        return hasFilledOneYear(emp, conn) && hasCongeLeft(emp, conn);
    }

    private static boolean hasFilledOneYear(Employe emp, Connection conn) {
        if (emp.getDateEmbauche() != null) {
            // return NOW - date embauche
        }

        // Get date embauche from database

        return false;
    }

    private static boolean hasCongeLeft(Employe emp, Connection conn) {
        // Check: (2.5 * (NOW - starting date in months)) - conge[obligatoire]

        return false;
    }

}
