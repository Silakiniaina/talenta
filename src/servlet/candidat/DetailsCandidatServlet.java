package servlet.candidat;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.Connection;

import model.Candidat;

@WebServlet("/detailsCandidat")
public class DetailsCandidatServlet extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String idCandidat = req.getParameter("idCandidat");
        try {
            Connection connexion = (Connection)req.getSession().getAttribute("connexion");

            Candidat c = new Candidat();
            c.setIdCandidat(Integer.parseInt(idCandidat));
            c = c.getById(connexion);

            req.setAttribute("candidat", c);
            RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/views/candidat/detailsCandidat.jsp");
            disp.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
}
