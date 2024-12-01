package servlet.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ReponseTestPossible;

@WebServlet("/addReponseTest")
public class AddReponseTestServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String idQuestion = req.getParameter("idQuestion");
        String idTest = req.getParameter("idTest");
        String texte_reponse = req.getParameter("texte_reponse");

        try {

            Connection c = (Connection)req.getSession().getAttribute("connextion");
            
            ReponseTestPossible qt = new ReponseTestPossible();
            qt.setIdQuestionTest(Integer.parseInt(idQuestion));
            qt.setTexteReponse(texte_reponse);

            qt.insert(c);

            resp.sendRedirect("detailsTest?test="+idTest);
        } catch (SQLException e) {
            e.printStackTrace(out);
        }
    }
    
}
