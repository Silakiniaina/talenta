package servlet.candidat;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Competence;
import model.CompetenceRequise;
import model.Departement;
import model.Poste;

public class PosteServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String mode = req.getParameter("mode");
        RequestDispatcher disp = null; 
        try {
            if(mode != null && mode.equals("i")){
                List<Departement> listeDepartements = Departement.getAll();
                List<Competence> listeCompetences = Competence.getAll();

                req.setAttribute("departements", listeDepartements);
                req.setAttribute("competences", listeCompetences);
                disp = req.getRequestDispatcher("/WEB-INF/views/admin/poste/addPoste.jsp");
            }else{
                List<Poste> listPoste = Poste.getAll();
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
        String[] competences = req.getParameterValues("competences");
        String[] experiences = req.getParameterValues("experiences");
        try {
            Poste p = new Poste();
            p.setDepartement(Integer.parseInt(dept));
            p.setNomPoste(nom);

            if(competences != null){
                for(int i=0; i<competences.length; i++){
                    CompetenceRequise cr = new CompetenceRequise(Integer.parseInt(competences[i]), Integer.parseInt(experiences[i]));
                    p.getListCompetence().add(cr);
                }
            }

            p.insert();
            out.println("Poste inseree");
        } catch (SQLException e) {
            e.printStackTrace(out);
        }
    } 
    

}
