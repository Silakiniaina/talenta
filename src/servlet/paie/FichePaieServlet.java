package servlet.paie;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.paie.FichePaie;

@WebServlet("/fichePaie")
public class FichePaieServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String idEmploye = req.getParameter("idEmploye");
        try {
            Connection c = (Connection)req.getSession().getAttribute("connexion");

            FichePaie fp = new FichePaie(c, Integer.parseInt(idEmploye));
            req.setAttribute("fichePaie", fp);
            req.getRequestDispatcher("/WEB-INF/views/admin/paie/fichePaie.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
    
}
