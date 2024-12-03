package classification_personnelle;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CpController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int categorieId = Integer.parseInt(req.getAttribute("categorie_id").toString());

            // Fetch the Cp by categorie_id
            // Redirect to the view with the attribute cp
        } catch (NumberFormatException nfe) {
            // TODO: Log exception in here
        }
    }

}
