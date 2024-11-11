package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Competence;
import model.Personne;
import model.Poste;


public class CompetenceServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out= response.getWriter();
        try {
            List<Competence> prog= Competence.getAllTechniqueProg();
            List<Competence> base= Competence.getAllTechniqueBase();
            List<Competence> outil= Competence.getAllTechniqueOutil();
            List<Competence> diplomes= Competence.getAllDiplome();
            List<Competence> langues= Competence.getAllLangue();
            List<Competence> autres= Competence.getAllAutre();

            RequestDispatcher dispat = request.getRequestDispatcher("/WEB-INF/views/formulaireCv.jsp");
            request.setAttribute("prog", prog);
            request.setAttribute("base", base);
            request.setAttribute("outil", outil);
            request.setAttribute("diplomes", diplomes);
            request.setAttribute("langues", langues);
            request.setAttribute("autres", autres);
            dispat.forward(request, response);
        } catch (SQLException e) {
           
            e.printStackTrace();
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        PrintWriter out = resp.getWriter();
        if(method != null){
            String idPersonne = req.getParameter("idPersonne");
            String[] techniques = req.getParameterValues("techniques");
            String diplome = req.getParameter("diplomes");
            try {
                Personne p = Personne.getById(Integer.parseInt(idPersonne));
                for(int i=0; i<techniques.length; i++){
                    Competence c = Competence.getById(Integer.parseInt(techniques[i]));
                    p.getAcquis().add(c);
                }
                p.insertCompetence();
                resp.sendRedirect("ajout-cv");
            } catch (SQLException e) {
                e.printStackTrace(out);
            }
        }else{
            doGet(req, resp);
        }
    }
}
