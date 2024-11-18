package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Candidat;
import model.RecrutementCandidat;

public class ListeCandidatServlet extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String idRecrutement = req.getParameter("idRecrutement");
        try {
            List<Candidat> ls = RecrutementCandidat.getCandidatByRecrutement(Integer.parseInt(idRecrutement));

            req.setAttribute("candidats", ls);
            RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/views/listeCandidat.jsp");
            disp.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
}
