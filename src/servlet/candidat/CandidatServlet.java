package servlet.candidat;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Candidat;
import model.RecrutementCandidat;

@WebServlet("/candidat")
public class CandidatServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       PrintWriter out = resp.getWriter();
       String idRecrutement = req.getParameter("idRecrutement");
       try {
            HttpSession session = req.getSession();
            Candidat candidat = (Candidat)session.getAttribute("candidat");
            Connection c = (Connection)session.getAttribute("connexion");
            
            RecrutementCandidat rec = new RecrutementCandidat();
            rec.setRecrutement(c,Integer.parseInt(idRecrutement));
            rec.setCandidat(candidat);
            rec.setDatePostule(Date.valueOf(LocalDate.now()));
            rec.insert();
            resp.sendRedirect("recrutement?role=candidat");
        
       } catch (Exception e) {
            e.printStackTrace(out);
       }
    }
}
