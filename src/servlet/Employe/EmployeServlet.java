package servlet.Employe;
import model.Employe;
import model.Candidat;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class EmployeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Candidat candidat = Candidat.login(email, password);

            if (candidat != null) {
                Employe employe =Employe.getEmployeFromCandidat(candidat); 
                

                if (employe != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("employe", employe);
                    response.sendRedirect("accueilEmploye.jsp"); 
                } else {
                    response.sendRedirect("accueilCandidat.jsp");
                }
            } else {
                response.sendRedirect("login.jsp?error=1");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); 
    }
}}