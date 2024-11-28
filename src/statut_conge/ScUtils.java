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
            emp = getEmployeById(emp.getIdEmploye(), conn);
        }

        return getWorkingPeriod(emp.getDateEmbauche(), "years") >= 1;
    }

    private static boolean hasCongeLeft(Employe emp, Connection conn) throws SQLException {
        // Check: (2.5 * (NOW - starting date in months)) - conge[obligatoire]
        double allCongeCount = getAllCongeCount(emp, conn);
        double congeTaken = getCongeTaken(emp, conn);

        return ((allCongeCount - congeTaken) != 0);
    }

    private static double getCongeTaken(Employe emp, Connection conn) throws SQLException {
        double congeLeft = 0;

        // TODO: Write query for conge_left
        try (PreparedStatement stmt = conn.prepareStatement(null)) {
            stmt.setInt(1, emp.getIdEmploye());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                congeLeft = rs.getDouble("conge_left");
            }
        }

        return congeLeft;
    }

    private static double getAllCongeCount(Employe emp, Connection conn) throws SQLException {
        double monthlyFactor = 2.5;

        if (emp.getDateEmbauche() != null) {
            emp = getEmployeById(emp.getIdEmploye(), conn);
        }

        return monthlyFactor * getWorkingPeriod(emp.getDateEmbauche(), "months");
    }

    private static Period getWorkingPeriod(Date hireDate) {
        LocalDate localHireDate = hireDate.toLocalDate();
        LocalDate currentDate = LocalDate.now();

        return Period.between(localHireDate, currentDate);
    }

    private static int getWorkingPeriod(Date hireDate, String unit) {
        if (unit.equalsIgnoreCase("days")) {
            return getWorkingPeriod(hireDate).getDays();
        }

        if (unit.equalsIgnoreCase("months")) {
            return getWorkingPeriod(hireDate).getMonths();
        }

        if (unit.equalsIgnoreCase("years")) {
            return getWorkingPeriod(hireDate).getYears();
        }

        return -1;
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
