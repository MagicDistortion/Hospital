package project.servlets;

import project.Paginator;
import project.models.users.Doctor;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/* єтот сервлет возвращает список врачей */
@WebServlet("/doctors_sortlist")
public class DoctorsList extends HttpServlet {
    private final Paginator paginator = new Paginator();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Doctor> doctors = paginator.paginationDoctorsList(req);
        req.getRequestDispatcher("doctors.jsp").forward(req, resp);
    }
}
