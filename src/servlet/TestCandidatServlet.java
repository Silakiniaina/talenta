package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Candidat;
import model.Questionaire;
import model.ReponseQuestionnaire;

public class TestCandidatServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String idCandidat = req.getParameter("idCandidat");
        try {

            List<Questionaire> questionaires = Questionaire.getQuestionairesTest();
            
            req.setAttribute("candidat", Candidat.getById(Integer.parseInt(idCandidat)));
            req.setAttribute("questionaires", questionaires);
            RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/views/testCandidat.jsp");
            disp.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String idCandidat = req.getParameter("idCandidat");
        try {
            List<Questionaire> questions = Questionaire.getQuestionairesTest();
            for(int i=0; i<questions.size(); i++){
                String paramname = "quest"+questions.get(i).getIdQuestionaire();
                String paramvalue = req.getParameter(paramname);

                ReponseQuestionnaire r = new ReponseQuestionnaire(questions.get(i).getIdQuestionaire(), Integer.parseInt(idCandidat), Integer.parseInt(paramvalue));
                r.insert();
            }
        out.println("response send");
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
    
}