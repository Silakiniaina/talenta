package servlet.employe.client;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Employe;
import model.InformationEmploye;
import service.EmployeService;

@WebServlet("/client-employe")
public class ClientEmployeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EmployeService employeService;

    public ClientEmployeServlet() {
        super();
        employeService = new EmployeService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("employe", new Employe());
        Employe sessionEmp = (Employe) session.getAttribute("employe");

        if (sessionEmp != null) {
            InformationEmploye employe = employeService.getEmployeById(sessionEmp.getIdEmploye());

            request.setAttribute("employe", employe);
            request.getRequestDispatcher("/WEB-INF/views/client/employe/conges-employes.jsp").forward(request,response);
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Veuillez vous connecter pour accéder à cette page");
        }
    }
}
