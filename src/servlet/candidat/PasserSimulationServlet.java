package servlet.candidat;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Candidat;
import model.QuestionSimulation;
import model.Simulation;
import model.SimulationCandidat;
import model.utils.Database;

@WebServlet("/passer-simulation")
public class PasserSimulationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idSimulation = Integer.parseInt(request.getParameter("id"));
            Connection conn = Database.getConnection();
            
            // Récupérer la simulation
            Simulation simulation = Simulation.getById(conn, idSimulation);
            if (simulation == null) {
                response.sendRedirect(request.getContextPath() + "/simulations");
                return;
            }
            
            // Récupérer les questions
            List<QuestionSimulation> questions = simulation.getQuestions();
            
            request.setAttribute("simulation", simulation);
            request.setAttribute("questions", questions);
            request.getRequestDispatcher("/WEB-INF/views/simulations/passer.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Connection connexion = (Connection)request.getSession().getAttribute("connexion");
            int idSimulation = Integer.parseInt(request.getParameter("id"));
            Candidat candidat = (Candidat) request.getSession().getAttribute("candidat");
            
            // Créer une nouvelle attribution
            SimulationCandidat simCandidat = new SimulationCandidat();
            simCandidat.setSimulation(idSimulation);
            simCandidat.setCandidat(connexion,candidat.getIdCandidat());
            simCandidat.setStatus(1); // Status "En cours" par exemple
            simCandidat.save(connexion);
            
            // Enregistrer les réponses
            List<QuestionSimulation> questions = Simulation.getById(connexion, idSimulation).getQuestions();
            for (QuestionSimulation question : questions) {
                String reponse = request.getParameter("question_" + question.getIdQuestionSimulation());
                if (reponse != null && !reponse.trim().isEmpty()) {
                    // Sauvegarder la réponse...
                }
            }
            
            response.sendRedirect(request.getContextPath() + "/simulations");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}