package project.servlets.patients_only;

import project.models.appointments.AppointmentDetails;
import project.dao.AppointmentDetailsDAO;
import project.models.users.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/patients_only/my_appointments")
public class MyAppointments extends HttpServlet {
    private final AppointmentDetailsDAO appointmentDetailsDAO =new AppointmentDetailsDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = ((User) req.getSession().getAttribute("user")).getId();
        List<AppointmentDetails> appointments = appointmentDetailsDAO.findAllAppointmentDetailsByID(id);
        req.setAttribute("appointments", appointments);
        if (appointments.size() == 0)  req.setAttribute("mes",((Map<?, ?>)req.getAttribute("phrases")).get("langEmpty"));
        req.getRequestDispatcher("/patients_only/my_appointments.jsp").forward(req, resp);
    }
}
