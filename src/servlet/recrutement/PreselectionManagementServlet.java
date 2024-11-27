package servlet.recrutement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.PreselectionCandidat;

@WebServlet("/preselectionManagement")
public class PreselectionManagementServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        int idCandidat = Integer.parseInt(req.getParameter("candidat"));
        int idRecrutement = Integer.parseInt(req.getParameter("recrutement"));
        String mode = req.getParameter("mode");
        try {
            Connection c = (Connection)req.getSession().getAttribute("connexion");

            PreselectionCandidat pr = PreselectionCandidat.getByCandidatAndRecrutement(c, idCandidat, idRecrutement);

            if(mode != null){
                if(mode.equals("p")){
                    pr.preselectionner(c);
                }else if(mode.equals("e")){

                }else if(mode.equals("d")){

                }
            }
            resp.sendRedirect("preselection?idRecrutement="+idRecrutement);
        } catch (Exception e) {
           e.printStackTrace(out);
        }
    }
    
}
