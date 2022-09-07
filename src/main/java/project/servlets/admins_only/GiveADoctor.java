package project.servlets.admins_only;

import project.dao.DoctorsDAO;
import project.dao.HospitalCardDAO;
import project.users.Doctor;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admins_only/give_a_doctor")
public class GiveADoctor extends HttpServlet {
    private final DoctorsDAO doctorsDAO = new DoctorsDAO();
    private final HospitalCardDAO hospitalCardDAO = new HospitalCardDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int doctorId = Integer.parseInt(req.getParameter("doctor"));
        int patientId = Integer.parseInt(req.getParameter("id"));
        Doctor doctor= doctorsDAO.findDoctorById(doctorId);
        hospitalCardDAO.appointADoctor(doctor, patientId);
        req.getRequestDispatcher("/admins_only/patients_sortlist").forward(req,resp);
    }
}
