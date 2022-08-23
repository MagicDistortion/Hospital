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

@WebServlet("/nurses_only/update_status")
public class UpdateStatus extends HttpServlet {
    DBManager dbManager = DBManager.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String status =req.getParameter("status");
        dbManager.updateAppointmentStatus(status,id);
        req.setAttribute("message","status updated");

        req.getRequestDispatcher("/nurses_only/my_appointments.jsp").forward(req, resp);
    }
}
