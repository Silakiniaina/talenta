package servlet.finContrat;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.FinContrat;
import model.utils.Database;

@WebServlet("/finContrat-add")
public class AddFinContratServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;
        PrintWriter out= resp.getWriter();
        try {

            String idEmployeStr = req.getParameter("employe");
            String typeContratStr = req.getParameter("typeContrat");
            String motif = req.getParameter("motif");
            String depot= req.getParameter("depot");

            if (idEmployeStr != null && !idEmployeStr.isEmpty() && 
                typeContratStr != null && !typeContratStr.isEmpty() &&
                depot != null && !depot.isEmpty()) {

                conn = Database.getConnection();

                int idEmploye = Integer.parseInt(idEmployeStr);
                int typeContrat = Integer.parseInt(typeContratStr);
                Date preavis = Date.valueOf(depot); 

                FinContrat finContrat = new FinContrat(conn, idEmploye, typeContrat, motif, preavis);
                finContrat.insert(conn);

                resp.sendRedirect(req.getContextPath() + "/finContrat-notif?idEmploye="+ idEmploye+ "&idTypeContrat="+typeContrat);
                out.println("lalala");
            } else {
                out.println("Erreur de l'enregistrement");
            }
        } catch (SQLException | IllegalArgumentException e) {
            e.printStackTrace();
            out.println("Erreur SQL ou argument invalide: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}
