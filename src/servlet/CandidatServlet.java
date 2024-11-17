package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Candidat;
import model.Competence;
import model.Genre;
import model.RecrutementCandidat;

public class CandidatServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       PrintWriter out = resp.getWriter();
       String idRecrutement = req.getParameter("idRecrutement");
       try {
        List<Genre> listGenre = Genre.getAll();
        List<Competence> listCompetences = Competence.getAll();

        req.setAttribute("genres", listGenre);
        req.setAttribute("competences", listCompetences);
        req.setAttribute("recr", idRecrutement);
        RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/views/candidat.jsp");
        disp.forward(req, resp);
        
       } catch (Exception e) {
        e.printStackTrace(out);
       }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       PrintWriter out = resp.getWriter();
       String nom = req.getParameter("nom");
       String prenom = req.getParameter("prenom");
       String adresse = req.getParameter("adresse");
       String dtn = req.getParameter("dtn");
       int genre = Integer.parseInt(req.getParameter("genre"));
       String idRecrutement = req.getParameter("recr");
       try {
        Candidat c = new Candidat(nom, prenom, dtn, genre, adresse);

        Candidat candidat = c.insert();

        RecrutementCandidat r = new RecrutementCandidat(Integer.parseInt(idRecrutement), candidat.getIdCandidat(), Date.valueOf(LocalDate.now()));
        r.insert();

        resp.sendRedirect("candidat");
       } catch (Exception e) {
        e.printStackTrace(out);
       }
    }
    
}
