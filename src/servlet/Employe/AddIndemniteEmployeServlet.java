package servlet.Employe;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Employe;
import model.employe.Indemnite;
import model.employe.TypeIndemnite;

@WebServlet("/addIndemnite")
public class AddIndemniteEmployeServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        try {
            Connection c = (Connection)req.getSession().getAttribute("connexion");

            List<Employe> employes = Employe.getAll(c);
            List<TypeIndemnite> types = TypeIndemnite.getAll();

            req.setAttribute("employes", employes);
            req.setAttribute("types", types);
            RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/views/admin/employe/addIndemnite.jsp");
            disp.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out  = resp.getWriter();
        int emp = Integer.parseInt(req.getParameter("emp"));
        int type = Integer.parseInt(req.getParameter("type"));
        double montant = Double.parseDouble(req.getParameter("montant"));

        try {
            Connection c = (Connection)req.getSession().getAttribute("connexion");

            Indemnite p  = new Indemnite();
            p.setEmploye(c, emp);
            p.setTypeIndemnite(c, type);
            p.setMontantIndemnite(montant);

            p.insert(c);

            resp.sendRedirect("addIndemnite");
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
    
}
