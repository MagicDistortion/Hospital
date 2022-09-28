package project.servlets.doctors_only;

import project.dao.AppointmentDetailsDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/doctors_only/update_status_appointment")
public class UpdateStatusAppointment extends HttpServlet {
    private final AppointmentDetailsDAO appointmentDetailsDAO = new AppointmentDetailsDAO();
    int id;
    String status;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Integer.parseInt(req.getParameter("id")) != 0 && req.getParameter("status") != null) {
            id = Integer.parseInt(req.getParameter("id"));
            status = req.getParameter("status");
            if (status.equals("delete")) {
                appointmentDetailsDAO.deleteAppointment(id);
            } else appointmentDetailsDAO.updateAppointmentStatus(status, id);
        }
        resp.sendRedirect("get_appointments");
    }
}

