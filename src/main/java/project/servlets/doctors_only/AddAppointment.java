package project.servlets.doctors_only;

import project.models.appointments.Appointment;
import project.dao.AppointmentsDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/doctors_only/add_appointment")
public class AddAppointment extends HttpServlet {
    private final AppointmentsDAO appointmentsDAO = new AppointmentsDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/doctors_only/add_appointment.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        if (appointmentsDAO.findAppointmentByName(name) == null) {
            appointmentsDAO.insertAppointment(new Appointment(name));
            req.setAttribute("mes", ((Map<?, ?>) req.getAttribute("phrases")).get("langSuccessfulAdd"));
        } else req.setAttribute("mes", ((Map<?, ?>) req.getAttribute("phrases")).get("langAllReadyExist"));
        req.getRequestDispatcher("/doctors_only/add_appointment.jsp").forward(req, resp);
    }
}
