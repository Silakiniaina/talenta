package servlet.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Test;
import model.utils.Database;

@WebServlet("/detailsTest")
public class DetailsTestServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String test = req.getParameter("test");
        try {
            Connection c = Database.getConnection();
            Test s = new Test();
            s = s.getById(c, Integer.parseInt(test));

            req.setAttribute("test", s);
            RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/views/test/detailsTest.jsp");
            disp.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }
    
}
