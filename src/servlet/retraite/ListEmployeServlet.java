package servlet.retraite;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Employe;
import model.TypeFinContrat;
import model.utils.Database;

@WebServlet("/retraite/addRetraite/formulaire")
public class ListEmployeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (Connection conn = Database.getConnection()) {

            List<Employe> employes = Employe.getAllReadyForRetraite(conn);
            List<TypeFinContrat> typesContrat = TypeFinContrat.getAll();

            req.setAttribute("employes", employes);
            req.setAttribute("typesContrat", typesContrat);
            req.getRequestDispatcher("/WEB-INF/views/admin/retraite/addRetraite.jsp").forward(req, resp);

        } catch (SQLException e) {
            e.printStackTrace(resp.getWriter());
            req.setAttribute("errorMessage", "Erreur lors de la récupération des données.");
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
    }
}
