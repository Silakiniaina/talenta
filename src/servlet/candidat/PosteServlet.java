package servlet.candidat;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Competence;
import model.Departement;
import model.Poste;
import model.classification.Hierarchie;

@WebServlet("/poste")
public class PosteServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String mode = req.getParameter("mode");
        RequestDispatcher disp = null; 
        try {
            Connection c = (Connection)req.getSession().getAttribute("connexion");
            if(mode != null && mode.equals("i")){
                List<Departement> listeDepartements = Departement.getAll();
                List<Competence> listeCompetences = new Competence().getAll(c);
                List<Hierarchie> hierarchies = new Hierarchie().getAll(c);

                req.setAttribute("departements", listeDepartements);
                req.setAttribute("competences", listeCompetences);
                req.setAttribute("hierarchies", hierarchies);
                disp = req.getRequestDispatcher("/WEB-INF/views/admin/poste/addPoste.jsp");
            }else{
                Poste p = new Poste();
                List<Poste> listPoste = p.getAll(c);
                req.setAttribute("postes", listPoste);
                disp = req.getRequestDispatcher("/WEB-INF/views/admin/poste/poste.jsp");
            }
            disp.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String nom = req.getParameter("nom");
        String dept =  req.getParameter("dept");
        String hierarchie = req.getParameter("hierarchie");
        String annee_xp_str = req.getParameter("annee-xp");
        try {
            Connection c = (Connection)req.getSession().getAttribute("connexion");
            Poste p = new Poste();
            p.setDepartement(Integer.parseInt(dept));
            p.setHierarchie(c, Integer.parseInt(hierarchie));
            p.setNomPoste(nom);
            p.setAnneeExperienceRequise(Integer.parseInt(annee_xp_str));

            p.insert(c);
            resp.sendRedirect("poste");
        } catch (SQLException e) {
            e.printStackTrace(out);
        }
    } 
    

}
