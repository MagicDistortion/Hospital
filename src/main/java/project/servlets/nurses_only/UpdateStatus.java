package project.servlets.nurses_only;

import project.controller.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/nurses_only/update_status")
public class UpdateStatus extends HttpServlet {
    DBManager dbManager = DBManager.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String status = req.getParameter("status");
        dbManager.updateAppointmentStatus(status, id);
        resp.sendRedirect("nurses_appointments");
    }
}
