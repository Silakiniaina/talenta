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
     * Checks if an employee is eligible to take leave (congé).
     * The method evaluates the following conditions:
     * 1. The employee must have completed at least one year in the company.
     * 2. The employee must have sufficient leave balance remaining.
     * 3. The employee's position (poste) must not be under-staffed during the
     * requested leave period.
     *
     * @param emp          The employee requesting leave.
     * @param startingDate The starting date of the requested leave period.
     * @param conn         The database connection.
     * @return {@code true} if the employee is eligible to take leave, {@code false}
     *         otherwise.
     * @throws SQLException If an SQL error occurs while querying the database.
     */
    public static boolean canTakeConge(Employe emp, Date startingDate, Connection conn) throws SQLException {
        return hasFilledOneYear(emp, conn) && hasCongeLeft(emp, conn) && !isPosteEmpty(emp, startingDate, conn);
    }

    /**
     * Checks if the number of employees available in a specific position (poste) is
     * below the defined threshold.
     * The method compares the total number of employees in a poste to the number of
     * employees currently on leave.
     *
     * @param emp          The employee whose poste is being evaluated.
     * @param startingDate The starting date for the evaluation period.
     * @param conn         The database connection.
     * @return {@code true} if the number of available employees in the poste is
     *         below the threshold, {@code false} otherwise.
     * @throws SQLException If an SQL error occurs while querying the database.
     */
    private static boolean isPosteEmpty(Employe emp, Date startingDate, Connection conn) throws SQLException {
        int threshold = 6;
        int idPoste = emp.getPoste().getIdPoste();

        return getCountEmploye(idPoste, conn) - getCountEmployeEnConge(idPoste, startingDate, conn) < threshold;
    }

    /**
     * Gets the count of employees in a specific position (poste) who are currently
     * on leave
     * during a specific period.
     *
     * @param postId       The ID of the poste.
     * @param startingDate The date used to check if employees are on leave during
     *                     this period.
     * @param conn         The database connection.
     * @return The number of employees on leave for the specified poste during the
     *         specified period,
     *         or {@code -1} if no results are found or an error occurs.
     * @throws SQLException If an SQL error occurs while querying the database.
     */
    private static int getCountEmployeEnConge(int postId, Date startingDate, Connection conn) throws SQLException {
        String query = "SELECT COUNT(id) AS total FROM v_conge_actif WHERE id_poste = ? AND date_debut <= ? AND date_fin >= ?";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, postId);
            statement.setObject(2, startingDate);
            statement.setObject(3, startingDate);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("total");
            }
        }

        return -1;
    }

    /**
     * Gets the total number of employees assigned to a specific position (poste).
     *
     * @param postId The ID of the poste.
     * @param conn   The database connection.
     * @return The total number of employees assigned to the specified poste,
     *         or {@code -1} if no results are found or an error occurs.
     * @throws SQLException If an SQL error occurs while querying the database.
     */
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
     * @return {@code true} if the employee has completed at least one year of
     *         service,
     *         {@code false} otherwise.
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
     * @return {@code true} if the employee has leave left, {@code false} otherwise.
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
        String query = "SELECT total_conge FROM v_conge_pris WHERE id_employe = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
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
     * @return The working period in the specified unit, or {@code -1} if the unit
     *         is
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
     * @return The {@link Employe} object if found, or {@code null} if not found.
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
