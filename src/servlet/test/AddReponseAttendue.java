package servlet.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.QuestionTest;
import model.ReponseTestPossible;

@WebServlet("/addReponseAttendue")
public class AddReponseAttendue extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       PrintWriter out = resp.getWriter();
       String idQuestionStr = req.getParameter("idQuestion");
       String idReponseStr = req.getParameter("idReponse");
       String idTest = req.getParameter("idTest");
       try {
        Connection c = (Connection)req.getSession().getAttribute("connexion");
        ReponseTestPossible rtp = new ReponseTestPossible();
        QuestionTest qt = new QuestionTest();

        rtp = rtp.getById(c, Integer.parseInt(idReponseStr));
        qt = qt.getById(c, Integer.parseInt(idQuestionStr));

        List<ReponseTestPossible> ls = qt.getReponsePossible();
        for(ReponseTestPossible r : ls){
            r.removeAttendue(c, qt);
        }

        rtp.makeAttendue(c, qt);

        resp.sendRedirect("detailsTest?test="+idTest);
       } catch (Exception e) {
        e.printStackTrace(out);
       }
    }
    
}
