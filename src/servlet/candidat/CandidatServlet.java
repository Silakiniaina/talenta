package servlet.candidat;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Candidat;
import model.RecrutementCandidat;

public class CandidatServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       PrintWriter out = resp.getWriter();
       String idRecrutement = req.getParameter("idRecrutement");
       try {
            HttpSession session = req.getSession();
            Candidat candidat = (Candidat)session.getAttribute("candidat");
            RecrutementCandidat rec = new RecrutementCandidat();
            rec.setRecrutement(Integer.parseInt(idRecrutement));
            rec.setCandidat(candidat);
            rec.setDatePostule(Date.valueOf(LocalDate.now()));
            rec.insert();
            resp.sendRedirect("recrutement?role=candidat");
        
       } catch (Exception e) {
            e.printStackTrace(out);
       }
    }
}
