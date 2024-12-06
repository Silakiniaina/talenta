package servlet.recrutement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Contrat;
import model.Recrutement;
import model.TypeContrat;

@WebServlet("/contrat")
public class ContratServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String idCandidat = request.getParameter("idCandidat");
        String idRecrutement = request.getParameter("idRecrutement");
        try {
            List<TypeContrat> ls = TypeContrat.getAll();

            request.setAttribute("idCandidat", idCandidat);
            request.setAttribute("typeContrats", ls);
            request.setAttribute("idRecrutement", idRecrutement);
            RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/views/admin/evalution/contrat.jsp");
            disp.forward(request,response);
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String dateDebut = request.getParameter("debut");
        String dateFin = request.getParameter("fin");
        int type = Integer.parseInt(request.getParameter("type"));
        int candidat = Integer.parseInt(request.getParameter("idCandidat"));
        int recrutement = Integer.parseInt(request.getParameter("idRecrutement"));
        double salaire = Double.parseDouble(request.getParameter("salaire"));
        try {
            Connection connexion = (Connection)request.getSession().getAttribute("connexion");

            Contrat c = new Contrat();
            c.setDateDebutContrat(dateDebut);
            c.setDateFinContrat(dateFin);
            c.setTypeContrat(type);
            c.setCandidat(connexion,candidat);
            c.setSalaireBase(salaire);

            Recrutement r = new Recrutement();
            r.setIdRecrutement(recrutement);
            r = r.getById(connexion);

            c.insert(r);
            out.println("Contrat fait");
        } catch (SQLException e) {
            e.printStackTrace(out);
        }
    }
}
