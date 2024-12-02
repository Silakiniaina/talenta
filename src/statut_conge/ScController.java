package statut_conge;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ScController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // #1: Create congé instance from request

            // #2: Check if employee can take a leave
            // #2a: true -> Create validation record in the database
            // #2b: false -> Redirect back to leave form with an error message
        } catch (Exception e) {
            // Redirect to the leave form
        }
    }

}
