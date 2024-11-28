package servlet.retraite;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Employe;
import model.NotificationCandidat;
import model.utils.Database;

@WebServlet("/retraite/notifier-retraite")
public class NotifierRetraiteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        Connection conn= null;
        String idEmployeStr = req.getParameter("idEmploye");
        if (idEmployeStr != null) {
            try {
                conn= Database.getConnection();
                int idEmploye = Integer.parseInt(idEmployeStr);
                Employe employe = Employe.getById(conn, idEmploye);
                LocalDate oneMonth= LocalDate.now().plusMonths(1); //1 mois apres la date actuelle
                
                if (employe != null) {
                    NotificationCandidat notification = new NotificationCandidat();
                    notification.setCandidat(conn, employe.getCandidat().getIdCandidat()); 
                    notification.setContenuNotification("Votre retraite sera planifie a partir du "+ oneMonth);
                    notification.setTargetLink("/retraite/liste"); 
                    notification.insert();

                    resp.sendRedirect(req.getContextPath() + "/retraite/liste");
                } 
                else {

                    req.setAttribute("errorMessage", "Employé non trouvé.");
                    req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
                }
            } catch (NumberFormatException | SQLException e) {
                e.printStackTrace();
                req.setAttribute("errorMessage", "Erreur lors de l'insertion de la notification.");
                req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("errorMessage", "ID Employé manquant.");
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
    }
}
