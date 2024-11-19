package servlet.simulation;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Simulation;
import model.utils.Database;

@WebServlet("/simulations")
public class ListeSimulationsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Connection conn = Database.getConnection();
            List<Simulation> simulations = Simulation.getAll(conn);
            request.setAttribute("simulations", simulations);
            request.getRequestDispatcher("/WEB-INF/views/simulations/liste.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
