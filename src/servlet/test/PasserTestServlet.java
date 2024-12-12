package servlet.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.Enumeration;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.AttributionTest;
import model.Candidat;
import model.ReponseTestCandidat;
import model.Test;

@WebServlet("/passerTest")
public class PasserTestServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String idTest = req.getParameter("test");

        try {
            Connection c = (Connection)req.getSession().getAttribute("connexion");

            Test t = new Test();
            t = t.getById(c, Integer.parseInt(idTest));

            req.setAttribute("test", t);
            RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/views/test/passer.jsp");
            disp.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String idTest = req.getParameter("idTest");
        try {
            Connection c = (Connection)req.getSession().getAttribute("connexiont");
            Enumeration<String> params = req.getParameterNames();
            Test t = new Test();
            Candidat candidat = (Candidat)req.getSession().getAttribute("candidat");

            t = t.getById(c, Integer.parseInt(idTest));

            AttributionTest a = new AttributionTest();

            a = a.getByCandidatAndTest(c, candidat, t);
            
            while(params.hasMoreElements()){;
                String paramName = params.nextElement();

                if(paramName.startsWith("question_")){
                    ReponseTestCandidat r = new ReponseTestCandidat();

                    int idQuestion = Integer.parseInt(paramName.split("_")[1]);
                    int idReponse = Integer.parseInt(req.getParameter(paramName));
                    r.setAttributionTest(c, a.getIdAttribution());
                    r.setReponse(c, idReponse);
                    r.setQuestion(c, idQuestion);
                    r.setDateSoumission(Timestamp.valueOf(LocalDateTime.now()));
                    
                    r.insert(c);
                }
            }

            RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/views/test/testSuccess.jsp");
            disp.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
    
}
