package servlet.demission;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Employe;
import model.NotificationAdmin;
import model.NotificationCandidat;
import model.TypeFinContrat;
import model.utils.Database;

@WebServlet("/demission-notif")
public class NotificationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out= resp.getWriter();
        Gson gson= new Gson();

        String idEmployeStr = req.getParameter("idCandidat");
        String date= req.getParameter("date");

        if (idEmployeStr!=null && date!=null) {
            try (Connection conn = Database.getConnection()) { 

                int idCandidat = Integer.parseInt(idEmployeStr);
                Employe employe = Employe.getEmployeByIdCandidat(conn, idCandidat);
                
                if (employe != null) {
                    NotificationAdmin notification = new NotificationAdmin();
                    notification.setContenuNotification("Demande de demission de "+ employe.getCandidat().getNomCandidat());
                    notification.setTargetLink(""); 
                    notification.insert();

                    req.getRequestDispatcher("/WEB-INF/views/employe/accueilEmploye.jsp").forward(req, resp);
                } else {

                    out.println("Erreur lors de l'envoi de notification");
                }
                
            } catch (NumberFormatException e) {
                e.printStackTrace();
                out.print(e.fillInStackTrace());
                
            } catch (SQLException e) {
                out.print(e.fillInStackTrace());
            }
        } else {
            out.println("Employe et/ou typeContrat null");
        }
    }
}
