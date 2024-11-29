package servlet.recrutement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Competence;
import model.Education;
import model.Recrutement;
import model.TypeDiplome;

@WebServlet("/detailsRecrutement")
public class DetailsRecrutementServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        int idRecrutement = Integer.parseInt(req.getParameter("idRecrutement"));
        try {
            
            Connection c = (Connection)req.getSession().getAttribute("connexion");

            Recrutement r = new Recrutement();
            r.setIdRecrutement(idRecrutement);
            r = r.getById(c);

            int nbCandidature = r.getNombreCandidature(c);

            req.setAttribute("recrutement", r);
            req.setAttribute("nbCandidature", nbCandidature);
            RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/views/admin/recrutement/detailsRecrutement.jsp");
            disp.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
    
}
