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
import model.employe.Prime;
import model.employe.TypePrime;

@WebServlet("/addPrime")
public class AddPrimeEmployeServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        try {
            Connection c = (Connection)req.getSession().getAttribute("connexion");

            List<Employe> employes = Employe.getAll(c);
            List<TypePrime> types = TypePrime.getAll();

            req.setAttribute("employes", employes);
            req.setAttribute("types", types);
            RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/views/admin/employe/addPrime.jsp");
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

            Prime p  = new Prime();
            p.setEmploye(c, emp);
            p.setTypePrime(c, type);
            p.setMontantPrime(montant);

            p.insert(c);

            resp.sendRedirect("addPrime");
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
    
}
