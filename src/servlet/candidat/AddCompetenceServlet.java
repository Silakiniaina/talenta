package servlet.candidat;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Candidat;
import model.Competence;

public class AddCompetenceServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        try {
            HttpSession session = req.getSession();
            Candidat c = (Candidat)session.getAttribute("candidat");
            List<Competence> listCompetence = c.getListCompetence();
            List<Competence> ls = Competence.getAll();

            req.setAttribute("allCompetence", ls);
            req.setAttribute("candidat_competence", listCompetence);
            RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/views/candidat/addCompetence.jsp");
            disp.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String competence = req.getParameter("competence");
        try {
            HttpSession session = req.getSession(false);
            Candidat c = (Candidat)session.getAttribute("candidat");
            Connection con = (Connection)session.getAttribute("connexion");


            Competence comp = new Competence();
            comp = comp.getById(con, Integer.parseInt(competence));
            c.insertCompetence(comp);
            c.getCompetences(con);

            resp.sendRedirect("addCompetence");
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
    
}
