package servlet.recrutement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Candidat;
import model.Competence;
import model.NotificationCandidat;
import model.Recrutement;

@WebServlet("/recrutement")
public class RecrutementServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String mode = req.getParameter("mode");
        RequestDispatcher disp = null;
        String role = req.getParameter("role");
        try {
            Connection c = (Connection)req.getSession().getAttribute("connexion");
            Competence comp = new Competence();
            List<Recrutement> listRecrutement = Recrutement.getAll();
            List<Competence> listCompetences = comp.getAll(c);

            req.setAttribute("recrutements", listRecrutement);
            req.setAttribute("competences", listCompetences);

            if(role != null){
                HttpSession session = req.getSession();
                Candidat candidat = (Candidat)session.getAttribute("candidat");
                List<NotificationCandidat> notifications = NotificationCandidat.getAllNotVueByCandidat(candidat.getIdCandidat());

                req.setAttribute("notifications", notifications);
                disp = req.getRequestDispatcher("/WEB-INF/views/candidat/recrutementCandidat.jsp");
            }else{
                if(mode != null && mode.equals("i")){
                    String idPoste = req.getParameter("idPoste");
    
                    req.setAttribute("idPoste", idPoste);
                    disp = req.getRequestDispatcher("/WEB-INF/views/admin/recrutement/addRecrutement.jsp");
                }else{
                    disp = req.getRequestDispatcher("/WEB-INF/views/admin/recrutement/recrutement.jsp");
                }
            }
            disp.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String dateDebut = req.getParameter("debut");
        String dateFin = req.getParameter("fin");
        int nb = Integer.parseInt(req.getParameter("nombre"));
        int poste = Integer.parseInt(req.getParameter("poste"));
        String description = req.getParameter("desc");
        try {
            Connection connexion = (Connection)req.getSession().getAttribute("connexion");

            Recrutement r = new Recrutement();
            r.setDateDebut(dateDebut);
            r.setDateFin(dateFin);
            r.setNombre(nb);
            r.setPoste(connexion,poste);
            r.setDescriptionRecrutement(description);

            r.insert();

            Candidat c = new Candidat();

            List<Candidat> candidats = c.getAll(connexion);
            for(Candidat candidat : candidats){
                NotificationCandidat nc = new NotificationCandidat();
                nc.setCandidat(connexion,candidat.getIdCandidat());
                nc.setContenuNotification("Recrutement entre : "+dateDebut+" et "+dateFin);
                nc.setTargetLink("recrutement?role=candidat");

                nc.insert();
            }

            resp.sendRedirect("recrutement");
        } catch (SQLException e) {
            e.printStackTrace(out);
        }
    } 
}
