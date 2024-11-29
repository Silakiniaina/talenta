package servlet.shared;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Admin;
import model.Candidat;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       PrintWriter out = resp.getWriter();
       String mode = req.getParameter("role");
       String email = req.getParameter("email");
       String mdp = req.getParameter("mdp");
       RequestDispatcher disp = null;

       try {
            req.setAttribute("role", mode);
            if(mode != null && mode.equals("candidat")){
                Candidat d = Candidat.login(email, mdp);
                if(d != null){
                    req.getSession(false).setAttribute("candidat", d);
                    disp = req.getRequestDispatcher("/WEB-INF/views/candidat/accueilCandidat.jsp");
                    disp.forward(req, resp);
                }else{
                   throw new Exception("Login incorecte");
                }
            }else if(mode != null && mode.equals("admin")){
                Admin d = Admin.login(email, mdp);
                if(d != null){
                    disp = req.getRequestDispatcher("/WEB-INF/views/admin/accueilAdmin.jsp");
                    disp.forward(req, resp);
                }else{
                    throw new Exception("Login incorecte");
                }
            }
        }catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            e.printStackTrace(out);
            doGet(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("role");
        RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/views/shared/login.jsp");
        try {
            if(type != null && type.equals("candidat")){
                req.setAttribute("role", "candidat");
            }else if(type != null && type.equals("admin")){
                req.setAttribute("role", "admin");
            }   
            disp.forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
        }finally{
            disp.forward(req, resp);
        }
    }
}
