package servlet;

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
import model.Recrutement;

public class RecrutementServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String mode = req.getParameter("mode");
        RequestDispatcher disp = null;
        try {
            if(mode != null && mode.equals("i")){
                String idPoste = req.getParameter("idPoste");

                req.setAttribute("idPoste", idPoste);
                disp = req.getRequestDispatcher("/WEB-INF/views/addRecrutement.jsp");
            }else{
                List<Recrutement> listRecrutement = Recrutement.getAll();
                List<Competence> listCompetences = Competence.getAll();
    
                req.setAttribute("recrutements", listRecrutement);
                req.setAttribute("competences", listCompetences);
                disp = req.getRequestDispatcher("/WEB-INF/views/recrutement.jsp");
            }
            disp.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String dateDebut = req.getParameter("debut");
        String dateFin = req.getParameter("fin");
        int nb = Integer.parseInt(req.getParameter("nombre"));
        int poste = Integer.parseInt(req.getParameter("poste"));
        try {
            Recrutement r = new Recrutement();
            r.setDateDebut(dateDebut);
            r.setDateFin(dateFin);
            r.setNombre(nb);
            r.setPoste(poste);

            r.insert();
            resp.sendRedirect("recrutement");
        } catch (SQLException e) {
            e.printStackTrace(out);
        }
    } 
}
