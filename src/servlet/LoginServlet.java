package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Admin;
import model.Candidat;

public class LoginServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       PrintWriter out = resp.getWriter();
       String mode = req.getParameter("role");
       String email = req.getParameter("email");
       String mdp = req.getParameter("mdp");

       RequestDispatcher disp = null;

       try {
            if(mode != null && mode.equals("candidat")){
                Candidat d = Candidat.login(email, mdp);
                if(d != null){
                    disp = req.getRequestDispatcher("/WEB-INF/views/accueilCandidat.jsp");
                    out.println("candidat connected : "+d.getNomCandidat());
                }else{
                    req.setAttribute("error", "Login incorecte");
                    disp = req.getRequestDispatcher("/WEB-INF/views/login.jsp");
                    out.println("Error login");
                }
            }else if(mode != null && mode.equals("admin")){
                Admin d = Admin.login(email, mdp);
                if(d != null){
                    disp = req.getRequestDispatcher("/WEB-INF/views/accueilAdmin.jsp");
                    out.println("admin connected : "+d.getNomAdmin());
                }else{
                    req.setAttribute("error", "Login incorecte");
                    disp = req.getRequestDispatcher("/WEB-INF/views/login.jsp");
                    out.println("Error login");
                }
            }
            disp.forward(req, resp);
       }catch (Exception e) {
            e.printStackTrace(out);
       }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out  = resp.getWriter();
        String type = req.getParameter("type");
        try {
            if(type != null && type.equals("candidat")){
                req.setAttribute("role", "candidat");
            }else if(type != null && type.equals("admin")){
                req.setAttribute("role", "admin");
            }   
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }

}
