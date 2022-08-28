package project.servlets.patients_only;

import project.appointments.AppointmentDetails;
import project.controller.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/patients_only/my_appointments")
public class MyAppointments extends HttpServlet {
    DBManager dbManager = DBManager.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = (int) req.getSession().getAttribute("id");
        List<AppointmentDetails> appointments = dbManager.findAllAppointmentDetailsByID(id);


        req.setAttribute("appointments", appointments);
        if (appointments.size() == 0)  req.setAttribute("mes", req.getSession().getAttribute("langEmpty"));
        req.getRequestDispatcher("/patients_only/my_appointments.jsp").forward(req, resp);
    }
}
