package servlet.demission;

import model.DemandeDemission;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/demission-liste")
public class ListeDemandesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<DemandeDemission> demandes = DemandeDemission.getAll(); 
            request.setAttribute("demandes", demandes);  
            request.getRequestDispatcher("/WEB-INF/views/admin/demission/listeDemandes.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }
}
