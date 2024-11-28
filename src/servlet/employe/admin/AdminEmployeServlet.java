package servlet.employe.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.InformationEmploye;
import service.EmployeService;

@WebServlet("/admin/employes")
public class AdminEmployeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EmployeService employeService;

    public AdminEmployeServlet() {
        super();
        employeService = new EmployeService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<InformationEmploye> employes = employeService.getInformationsEmployeAvecConges();

        request.setAttribute("employes", employes);
        
        request.getRequestDispatcher("/WEB-INF/views/admin/employe/conges-employes.jsp").forward(request, response);
    }
}
