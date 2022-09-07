package project.servlets.doctors_only;

import project.appointments.AppointmentDetails;
import project.dao.AppointmentDetailsDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/doctors_only/get_appointments")
public class GetAppointmentDetails extends HttpServlet {
    private final AppointmentDetailsDAO appointmentDetailsDAO =new AppointmentDetailsDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = (int) req.getSession().getAttribute("id_card");
        List<AppointmentDetails> appointmentsDetails = appointmentDetailsDAO.findAllAppointmentDetailsByID(id);
        if (appointmentsDetails.size() == 0)  req.setAttribute("mes",((Map<?, ?>)req.getAttribute("phrases")).get("langEmpty"));
        req.setAttribute("appointments", appointmentsDetails);
        req.getRequestDispatcher("/doctors_only/edit_hospital_cards.jsp").forward(req, resp);
    }
}




