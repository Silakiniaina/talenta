package servlet.test;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Test;
import model.utils.Database;

@WebServlet("/tests")
public class ListeTestServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Connection conn = Database.getConnection();
            List<Test> tests = Test.getAll(conn);
            request.setAttribute("tests", tests);
            request.getRequestDispatcher("/WEB-INF/views/test/liste.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
