package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CompetenceRequise;
import model.Poste;

public class PosteServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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

            for(int i=0; i<competences.length; i++){
                CompetenceRequise cr = new CompetenceRequise(Integer.parseInt(competences[i]), Integer.parseInt(experiences[i]));
                p.getListCompetence().add(cr);
            }

            p.insert();
            resp.sendRedirect("recrutement");
        } catch (SQLException e) {
            e.printStackTrace(out);
        }
    } 
    

}
