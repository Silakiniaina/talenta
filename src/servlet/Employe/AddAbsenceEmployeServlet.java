package servlet.Employe;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Employe;
import model.employe.Absence;
import model.employe.TypeAbsence;

@WebServlet("/addAbsence")
public class AddAbsenceEmployeServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        try {
            Connection c = (Connection)req.getSession().getAttribute("connexion");

            List<Employe> employes = Employe.getAll(c);
            List<TypeAbsence> types = TypeAbsence.getAll();

            req.setAttribute("employes", employes);
            req.setAttribute("types", types);
            RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/views/admin/employe/addAbsence.jsp");
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

            Date debut = Date.valueOf(LocalDate.parse(debutStr));
            Date fin = Date.valueOf(LocalDate.parse(finStr));

            Absence p  = new Absence();
            p.setEmploye(c, emp);
            p.setDateDebut(debut);
            p.setDateFin(fin);
            p.setTypeAbsence(c, type);

            p.insert(c);

            resp.sendRedirect("addAbsence");
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
    
}
