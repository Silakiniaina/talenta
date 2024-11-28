package servlet.retraite;

import java.io.IOException;
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

@WebServlet("/retraite-add")
public class AddRetraiteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;
        
        try {

            String idEmployeStr = req.getParameter("employe");
            String typeContratStr = req.getParameter("typeContrat");
            String motif = req.getParameter("motif");
            String preavisStr = req.getParameter("preavis");
            String dateFinStr = req.getParameter("dateFin");

            if (idEmployeStr != null && !idEmployeStr.isEmpty() && 
                typeContratStr != null && !typeContratStr.isEmpty() && 
                motif != null && !motif.isEmpty() && 
                preavisStr != null && !preavisStr.isEmpty() && 
                dateFinStr != null && !dateFinStr.isEmpty()) {

                conn = Database.getConnection();

                int idEmploye = Integer.parseInt(idEmployeStr);
                int typeContrat = Integer.parseInt(typeContratStr);
                Date preavis = Date.valueOf(preavisStr); 
                Date dateFin = Date.valueOf(dateFinStr);

                FinContrat finContrat = new FinContrat(conn, idEmploye, typeContrat, motif, preavis, dateFin);

                finContrat.insert(conn);

                resp.sendRedirect(req.getContextPath() + "/retraite/liste");
            } else {
                req.setAttribute("errorMessage", "Tous les champs sont requis.");
                req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
            }
        } catch (SQLException | IllegalArgumentException e) {
            e.printStackTrace();
            req.setAttribute("errorMessage", "Erreur lors de l'insertion du contrat de retraite.");
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
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
