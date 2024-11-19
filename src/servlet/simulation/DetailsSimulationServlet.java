package servlet.simulation;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Simulation;
import model.utils.Database;

public class DetailsSimulationServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String idSimulation = req.getParameter("idSimulation");
        try {
            Connection c = Database.getConnection();
            Simulation s = Simulation.getById(c, Integer.parseInt(idSimulation));

            req.setAttribute("simulation", s);
            RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/views/simulations/detailsSimulation.jsp");
            disp.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }
    
}
