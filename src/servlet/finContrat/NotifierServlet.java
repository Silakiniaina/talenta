package servlet.finContrat;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Employe;
import model.NotificationCandidat;
import model.TypeFinContrat;
import model.utils.Database;

@WebServlet("/finContrat-notif")
public class NotifierServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out= resp.getWriter();

        String idEmployeStr = req.getParameter("idEmploye");
        String idType= req.getParameter("idTypeContrat");
        if (idEmployeStr!=null && idType!=null) {
            try (Connection conn = Database.getConnection()) { 

                int idEmploye = Integer.parseInt(idEmployeStr);
                Employe employe = Employe.getById(conn, idEmploye);
                int idTypeContrat= Integer.parseInt(idType);
                TypeFinContrat tpf= TypeFinContrat.getById(idTypeContrat);
                
                if (employe != null) {
                    NotificationCandidat notification = new NotificationCandidat();
                    notification.setCandidat(conn, employe.getCandidat().getIdCandidat());
                    if (tpf.getId()==1){
                        notification.setContenuNotification("Vous avez ete mis en retraite");
                    }
                    else{
                        notification.setContenuNotification(" ");
                    }
                    notification.setTargetLink(""); 
                    notification.insert();

                    req.getRequestDispatcher("addFinContrat-form").forward(req, resp);;
                } else {

                    out.println("Erreur lors de l'envoi de notification");
                }
                
            } catch (NumberFormatException e) {

                out.print(e.fillInStackTrace());
                
            } catch (SQLException e) {
                out.print(e.fillInStackTrace());
            }
        } else {
            out.println("Employe et/ou typeContrat null");
        }
    }
}
