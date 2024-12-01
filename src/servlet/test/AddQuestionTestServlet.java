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
import model.QuestionTest;

@WebServlet("/addQuestionTest")
public class AddQuestionTestServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String idTest = req.getParameter("idTest");
        String texte_question = req.getParameter("texte_question");

        try {

            Connection c = (Connection)req.getSession().getAttribute("connextion");
            
            QuestionTest qt = new QuestionTest();
            qt.setIdTest(Integer.parseInt(idTest));
            qt.setTexteQuestion(texte_question);

            qt.insert(c);

            resp.sendRedirect("detailsTest?test="+idTest);
        } catch (SQLException e) {
            e.printStackTrace(out);
        }
    }
    
}
