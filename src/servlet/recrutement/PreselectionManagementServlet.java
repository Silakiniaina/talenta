package servlet.recrutement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.NotificationCandidat;
import model.PreselectionCandidat;
import model.Recrutement;

@WebServlet("/preselectionManagement")
public class PreselectionManagementServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        int idCandidat = Integer.parseInt(req.getParameter("candidat"));
        int idRecrutement = Integer.parseInt(req.getParameter("recrutement"));
        String mode = req.getParameter("mode");
        try {
            Connection c = (Connection)req.getSession().getAttribute("connexion");

            PreselectionCandidat pr = PreselectionCandidat.getByCandidatAndRecrutement(c, idCandidat, idRecrutement);
            Recrutement r = new Recrutement();
            r.setIdRecrutement(idRecrutement);
            r = r.getById(c);

            NotificationCandidat notif = new NotificationCandidat();
            notif.setCandidat(c, idCandidat);
            notif.setTargetLink("#");
            notif.setDateNotification(Timestamp.valueOf(LocalDateTime.now()));

            if(mode != null){
                if(mode.equals("p")){
                    pr.preselectionner(c);
                    notif.setContenuNotification("Votre candidature pour le poste de : "+r.getPoste().getNomPoste()+" a ete preselectionner");
                }else if(mode.equals("e")){
                    notif.setContenuNotification("Votre candidature pour le poste de : "+r.getPoste().getNomPoste()+" a ete eliminer");
                }else if(mode.equals("d")){
                    pr.depreselectionner(c);
                    notif.setContenuNotification("Votre candidature pour le poste de : "+r.getPoste().getNomPoste()+" a ete depreselectionner");
                }
                notif.insert();
            }
            resp.sendRedirect("preselection?idRecrutement="+idRecrutement);
        } catch (Exception e) {
           e.printStackTrace(out);
        }
    }
    
}
