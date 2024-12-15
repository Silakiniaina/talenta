package servlet.conge;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Conge;
import model.Employe;
import model.TypeConge;

@WebServlet("/addConge")
public class AjoutCongeServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        try {
            Connection c = (Connection)req.getSession().getAttribute("connexion");

            List<Employe> employes = Employe.getAll(c);
            List<TypeConge> types = TypeConge.getAll();

            req.setAttribute("employes", employes);
            req.setAttribute("types", types);
            RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/views/admin/employe/addConge.jsp");
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
        String debutStr = req.getParameter("debut");
        String finStr = req.getParameter("fin");

        try {
            Connection c = (Connection)req.getSession().getAttribute("connexion");

            Conge conge = new Conge();

            conge.setDateDebut(Date.valueOf(debutStr));
            conge.setDateFin(Date.valueOf(finStr));
            conge.setTypeConge(c,type);
            conge.setEmploye(c, emp);

            conge.insert(c);

            resp.sendRedirect("addConge");
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
    
}