package servlet.conge;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Conge;

@WebServlet("/conge/planning")
public class PlanningServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            List<Conge> planningConges = Conge.getPlanning();
            req.setAttribute("planningConges", planningConges);
            req.getRequestDispatcher("/WEB-INF/views/conge/planningConge.jsp").forward(req, resp);

        } catch (SQLException e) {
            e.printStackTrace(resp.getWriter());
        }
    }

}
