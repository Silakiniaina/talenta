package statut_conge;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

import model.Candidat;
import model.Employe;
import model.Poste;

public class ScUtils {

    private ScUtils() {
    }

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

    private static double getAllCongeCount(Employe emp, Connection conn) throws SQLException {
        double monthlyFactor = 2.5;

        if (emp.getDateEmbauche() != null) {
            emp = getEmployeById(emp.getIdEmploye(), conn);
        }

        LocalDate hireDate = emp.getDateEmbauche().toLocalDate();
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(hireDate, currentDate);

        return monthlyFactor * period.getMonths();
    }

    private static Period getWorkingPeriod(Date hireDate) {
        LocalDate localHireDate = hireDate.toLocalDate();
        LocalDate currentDate = LocalDate.now();

        return Period.between(localHireDate, currentDate);
    }

    private static Employe getEmployeById(int id, Connection conn) throws SQLException {
        Employe emp = null;

        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Employe WHERE id_employe = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Candidat candidat = new Candidat();
                candidat.setIdCandidat(rs.getInt("id_candidat"));

                Poste poste = new Poste();
                poste.setIdPoste(rs.getInt("id_poste"));

                Date dateEmbauche = rs.getDate("date_embauche");

                emp = new Employe();
                emp.setIdEmploye(id);
                emp.setCandidat(conn, id);
                emp.setPoste(id);
                emp.setDateEmbauche(dateEmbauche);
            }
        }

        return emp;
    }

}