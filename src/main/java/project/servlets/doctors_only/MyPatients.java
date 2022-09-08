package project.servlets.doctors_only;

import project.Paginator;
import project.models.users.Patient;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/doctors_only/my_patients")
public class MyPatients extends HttpServlet {
private final Paginator paginator=new Paginator();
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Patient>patientList = paginator.paginationMyPatients(req);
        if (patientList.size()==0)  req.setAttribute("mes",((Map<?, ?>)req.getAttribute("phrases")).get("langEmpty"));
        req.getRequestDispatcher("/doctors_only/my_patients.jsp").forward(req, resp);
    }
}
