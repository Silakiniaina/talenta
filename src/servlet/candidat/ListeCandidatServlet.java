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
import model.Recrutement;
import model.Candidat;

public class ListeCandidatServlet extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String idRecrutement = req.getParameter("idRecrutement");
        try {

            Connection connexion = (Connection)req.getSession().getAttribute("connexion");

            Recrutement r = new Recrutement();
            r.setIdRecrutement(Integer.parseInt(idRecrutement));
            r = r.getById(connexion);

            List<Candidat> ls = r.getListCandidats();

            req.setAttribute("candidats", ls);
            RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/views/admin/recrutement/listeCandidat.jsp");
            disp.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
}
