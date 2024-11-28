package statut_conge;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

import model.Employe;

public class ScUtils {

    public static boolean canTakeConge(Employe emp, Connection conn) throws SQLException {
        return hasFilledOneYear(emp, conn) && hasCongeLeft(emp, conn);
    }

    private static boolean hasFilledOneYear(Employe emp, Connection conn) throws SQLException {
        if (emp.getDateEmbauche() != null) {
            LocalDate hireDate = emp.getDateEmbauche().toLocalDate();
            LocalDate currentDate = LocalDate.now();
            Period period = Period.between(hireDate, currentDate);
            return period.getYears() >= 1;
        }

        try (PreparedStatement stmt = conn.prepareStatement("SELECT date_embauche FROM Employe WHERE id_employe = ?")) {
            stmt.setInt(1, emp.getIdEmploye());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Date sqlHireDate = rs.getDate("date_embauche");

                if (sqlHireDate != null) {
                    LocalDate hireDate = sqlHireDate.toLocalDate();
                    LocalDate currentDate = LocalDate.now();
                    Period period = Period.between(hireDate, currentDate);
                    return period.getYears() >= 1;
                }
            }
        }

        return false;
    }

    private static boolean hasCongeLeft(Employe emp, Connection conn) {
        // Check: (2.5 * (NOW - starting date in months)) - conge[obligatoire]

        return false;
    }

}
