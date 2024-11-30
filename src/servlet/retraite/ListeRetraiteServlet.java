package servlet.retraite;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Employe;
import model.utils.Database;

@WebServlet("/retraite-liste")
public class ListeRetraiteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out= resp.getWriter();
        try {
            List<Employe> employesRetraite = Employe.getAllReadyForRetraite(Database.getConnection());
            Gson gson= new Gson();
            // out.println(gson.toJson(employesRetraite));
            req.setAttribute("employesRetraite", employesRetraite);
            req.getRequestDispatcher("/WEB-INF/views/admin/retraite/listeRetraite.jsp").forward(req, resp);

        } catch (SQLException e) {
            e.printStackTrace(resp.getWriter());
        }
    }
}
