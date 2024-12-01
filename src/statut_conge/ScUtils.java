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

    /**
     * Checks if an employee can take leave.
     * 
     * @param emp  The employee object.
     * @param conn The database connection.
     * @return true if the employee has filled one year and has leave remaining,
     *         false otherwise.
     * @throws SQLException If a database access error occurs.
     */
    public static boolean canTakeConge(Employe emp, Connection conn) throws SQLException {
        return hasFilledOneYear(emp, conn) && hasCongeLeft(emp, conn) && !postIsEmpty(emp, conn);
    }

    private static boolean postIsEmpty(Employe emp, Connection conn) throws SQLException {
        // Check: count(employe total) - count(employe en congé) < threshold
        int threshold = 6;

        return getCountEmploye(emp.getPoste().getIdPoste(), conn) < threshold;
    }

    private static int getCountEmployeEnConge(int postId, Date startingDate, Connection conn) throws SQLException {
        String query = "SELECT COUNT(id) AS total FROM v_conge_actif WHERE date_debut <= ? AND date_fin >= ?";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setObject(1, startingDate);
            statement.setObject(2, startingDate);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("total");
            }
        }

        return -1;
    }

    private static int getCountEmploye(int postId, Connection conn) throws SQLException {
        String query = "SELECT COUNT(id) AS total FROM Employe WHERE id_poste = ?";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, postId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("total");
            }
        }

        return -1;
    }

    /**
     * Checks if the employee has completed one year of service.
     * 
     * @param emp  The employee object.
     * @param conn The database connection.
     * @return true if the employee has completed at least one year of service,
     *         false otherwise.
     * @throws SQLException If a database access error occurs.
     */
    private static boolean hasFilledOneYear(Employe emp, Connection conn) throws SQLException {
        if (emp.getDateEmbauche() != null) {
            emp = getEmployeById(emp.getIdEmploye(), conn);
        }

        return getWorkingPeriod(emp.getDateEmbauche(), "years") >= 1;
    }

    /**
     * Checks if the employee has any remaining leave balance.
     * 
     * @param emp  The employee object.
     * @param conn The database connection.
     * @return true if the employee has leave left, false otherwise.
     * @throws SQLException If a database access error occurs.
     */
    private static boolean hasCongeLeft(Employe emp, Connection conn) throws SQLException {
        double allCongeCount = getAllCongeCount(emp, conn);
        double congeTaken = getCongeTaken(emp, conn);

        return ((allCongeCount - congeTaken) != 0);
    }

    /**
     * Retrieves the total leave taken by an employee.
     * 
     * @param emp  The employee object.
     * @param conn The database connection.
     * @return The total leave taken by the employee.
     * @throws SQLException If a database access error occurs.
     */
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

    /**
     * Calculates the total leave count for an employee.
     * 
     * @param emp  The employee object.
     * @param conn The database connection.
     * @return The total leave count for the employee.
     * @throws SQLException If a database access error occurs.
     */
    private static double getAllCongeCount(Employe emp, Connection conn) throws SQLException {
        double monthlyFactor = 2.5;

        if (emp.getDateEmbauche() != null) {
            emp = getEmployeById(emp.getIdEmploye(), conn);
        }

        return monthlyFactor * getWorkingPeriod(emp.getDateEmbauche(), "months");
    }

    /**
     * Gets the working period of an employee as a {@link Period} object.
     * 
     * @param hireDate The hire date of the employee.
     * @return The working period as a {@link Period} object.
     */
    private static Period getWorkingPeriod(Date hireDate) {
        LocalDate localHireDate = hireDate.toLocalDate();
        LocalDate currentDate = LocalDate.now();

        return Period.between(localHireDate, currentDate);
    }

    /**
     * Gets the working period of an employee in a specific time unit.
     * 
     * @param hireDate The hire date of the employee.
     * @param unit     The time unit to calculate the period ("days", "months", or
     *                 "years").
     * @return The working period in the specified unit, or -1 if the unit is
     *         invalid.
     */
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

    /**
     * Retrieves an employee by their ID from the database.
     * 
     * @param id   The employee ID.
     * @param conn The database connection.
     * @return The {@link Employe} object if found, or null if not found.
     * @throws SQLException If a database access error occurs.
     */
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
