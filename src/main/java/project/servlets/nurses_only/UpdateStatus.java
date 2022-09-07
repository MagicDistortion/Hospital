package project.servlets.nurses_only;

import project.dao.AppointmentDetailsDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/nurses_only/update_status")
public class UpdateStatus extends HttpServlet {
    private final AppointmentDetailsDAO appointmentDetailsDAO = new AppointmentDetailsDAO();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String status = req.getParameter("status");
        appointmentDetailsDAO.updateAppointmentStatus(status, id);
        resp.sendRedirect("nurses_appointments");
    }
}
