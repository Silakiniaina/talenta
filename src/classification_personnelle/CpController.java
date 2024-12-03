package classification_personnelle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.utils.Database;

public class CpController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int categorieId;
        List<ClassificationPersonnelle> cpList = new ArrayList<>();

        // Services
        ClassificationPersonnelle cp = new ClassificationPersonnelle();

        try (Connection conn = Database.getConnection()) {
            String categorieIdStr = req.getAttribute("categorie_id").toString();

            try {
                categorieId = Integer.parseInt(categorieIdStr);
                cpList = cp.getByCategorie(categorieId, conn);
            } catch (NumberFormatException nfe) {
                cpList = cp.getAll(conn);
            }
        } catch (SQLException e) {
            // Log exception here
        }

        req.setAttribute("cp-list", cpList);
        // TODO: Redirect to the view
    }

}
