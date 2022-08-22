package project.servlets.nurses_only;

import project.appointments.AppointmentDetails;
import project.methods.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/nurses_only/nurses_appointments")
public class NursesAppointments extends HttpServlet {
    DBManager dbManager = DBManager.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = (int) req.getSession().getAttribute("id");
        List<AppointmentDetails> appointments = dbManager.findAllAppointmentsForNurse(id);
        req.setAttribute("appointments", appointments);
        if (appointments.size() == 0) req.setAttribute("mes", "empty");
        req.getRequestDispatcher("/nurses_only/my_appointments.jsp").forward(req, resp);
    }
}
