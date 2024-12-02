package servlet.conge;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Candidat;
import model.Conge;
import model.utils.Database;

@WebServlet("/conge-planning")
public class PlanningServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out= resp.getWriter();
        try {
            Connection conn= Database.getConnection();
            List<Conge> planningConges = Conge.getPlanning();
            req.setAttribute("planningConges", planningConges);
            // Gson gson= new Gson();
            // out.println(gson.toJson(planningConges));
            req.getRequestDispatcher("/WEB-INF/views/admin/conge/planningConge.jsp").forward(req, resp);

        } catch (SQLException e) {
            e.printStackTrace(resp.getWriter());
        }
    }

}
