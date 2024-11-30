package servlet.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Admin;
import model.Test;
import model.utils.GeneralUtil;

@WebServlet("/addTest")
public class AjoutTestServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        try {
            RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/views/test/addTest.jsp");
            disp.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out  = resp.getWriter();
        String titre = req.getParameter("titre");
        String description = req.getParameter("desc");
        String dateCreationStr = req.getParameter("dateCreation");
        try {
            Admin responsable = (Admin)req.getSession().getAttribute("admin");
            Connection c = (Connection)req.getSession().getAttribute("connexion");

            Test t = new Test();
            t.setTitre(titre);
            t.setDateCreation(GeneralUtil.formatToTimestamp(dateCreationStr));
            t.setDescription(description);
            t.setResponsable(responsable.getIdAdmin());

            t.insert(c);
            resp.sendRedirect("addTest");
        } catch (Exception e) {
           e.printStackTrace(out);
        }
    }
}
