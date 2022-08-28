package project.servlets.admins_only;

import project.controller.DBManager;
import project.users.Doctor;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestWrapper;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admins_only/give_a_doctor")
public class GiveADoctor extends HttpServlet {
    DBManager dbManager = DBManager.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int doctorId = Integer.parseInt(req.getParameter("doctor"));
        int patientId = Integer.parseInt(req.getParameter("id"));
        Doctor doctor= dbManager.findDoctorById(doctorId);
        dbManager.appointADoctor(doctor, patientId);
        req.getRequestDispatcher("/admins_only/patients_sortlist").forward(req,resp);
    }
}
