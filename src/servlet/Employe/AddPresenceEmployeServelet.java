package servlet.Employe;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.sql.Connection;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Employe;
import model.employe.Presence;
import model.utils.GeneralUtil;

@WebServlet("/addPresence")
public class AddPresenceEmployeServelet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        try {
            Connection c = (Connection)req.getSession().getAttribute("connexion");

            List<Employe> employes = Employe.getAll(c);

            req.setAttribute("employes", employes);
            RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/views/admin/employe/addPresence.jsp");
            disp.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out  = resp.getWriter();
        int emp = Integer.parseInt(req.getParameter("emp"));
        String entreeStr = req.getParameter("entree");
        String sortieStr = req.getParameter("sortie");

        try {
            Connection c = (Connection)req.getSession().getAttribute("connexion");

            Timestamp entree = GeneralUtil.formatToTimestamp(entreeStr);
            Timestamp sortie = GeneralUtil.formatToTimestamp(sortieStr);

            Presence p  = new Presence();
            p.setEmploye(c, emp);
            p.setDateHeureEntree(entree);
            p.setDateHeureSortie(sortie);

            p.insert(c);

            resp.sendRedirect("addPresence");
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }

    
}