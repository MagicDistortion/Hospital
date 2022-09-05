package project.servlets.doctors_only;

import project.controller.DBManager;
import project.users.Doctor;
import project.users.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/doctors_only/update_status")
public class UpdatePatientStatus extends HttpServlet {
    DBManager dbManager = DBManager.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = (int) req.getSession().getAttribute("id_card");
        String status = req.getParameter("status");
        if (status.equals("discharged")) {
            if (dbManager.findAllAppointmentDetailsByID(id).size() == 0) {
                Doctor doctor = dbManager.findDoctorById(((User) req.getSession().getAttribute("user")).getId());
                dbManager.updateNumberOfPatients(doctor.getNumberOfPatients() - 1, doctor.getId());
                dbManager.dischargePatient(id);
            } else {
                req.setAttribute("messtatus", "not all appointments already done");
                req.getRequestDispatcher("/doctors_only/edit_hospital_cards.jsp").forward(req, resp);
                return;
            }
        }
        dbManager.updateHospitalCardStatus(status, id);
        req.getSession().setAttribute("status_patient", status);
        req.setAttribute("messtatus", ((Map<?, ?>) req.getAttribute("phrases")).get("langStatusUpdated"));

        req.getRequestDispatcher("/doctors_only/edit_hospital_cards.jsp").forward(req, resp);
    }
}
