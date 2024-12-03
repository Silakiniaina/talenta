package servlet.paie;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Employe;

@WebServlet("/listPaie")
public class EtatPaieServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        try {
            Connection c = (Connection)req.getSession().getAttribute("connexion");

            List<Employe> employes = Employe.getAll(c);
            req.setAttribute("employes", employes);
            req.getRequestDispatcher("/WEB-INF/views/admin/paie/etatPaie.jsp").forward(req, resp);
        } catch (Exception e) {
           e.printStackTrace(out);
        }
    }
    
}
