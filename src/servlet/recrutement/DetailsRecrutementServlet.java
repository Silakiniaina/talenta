package servlet.recrutement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Recrutement;

@WebServlet("/detailsRecrutement")
public class DetailsRecrutementServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        int idRecrutement = Integer.parseInt(req.getParameter("recrutement"));
        try {
            
            Connection c = (Connection)req.getSession().getAttribute("connexion");

            Recrutement r = new Recrutement();
            r.setIdRecrutement(idRecrutement);
            r = r.getById(c);

            req.setAttribute("recrutement", r);
            RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/views/recrutement/detailsRecrutement.jsp");
            disp.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
    
}
