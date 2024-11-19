package servlet.candidat;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Candidat;

public class DetailsCandidatServlet extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String idCandidat = req.getParameter("idCandidat");
        try {
            Candidat c = Candidat.getById(Integer.parseInt(idCandidat));

            req.setAttribute("candidat", c);
            RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/views/candidat/detailsCandidat.jsp");
            disp.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
}
