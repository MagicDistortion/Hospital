package project.servlets.doctors_only;

import project.dao.AppointmentDetailsDAO;
import project.dao.DoctorsDAO;
import project.dao.HospitalCardDAO;
import project.models.hospitalcard.HospitalCard;
import project.models.users.Doctor;
import project.models.users.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/doctors_only/update_status")
public class UpdatePatientStatus extends HttpServlet {
    private final DoctorsDAO doctorsDAO = new DoctorsDAO();
    private final HospitalCardDAO hospitalCardDAO = new HospitalCardDAO();
    private final AppointmentDetailsDAO appointmentDetailsDAO = new AppointmentDetailsDAO();
    private HospitalCard hospitalCard;
    int id;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute(("hospital_card")) != null) {
            hospitalCard = ((HospitalCard) req.getSession().getAttribute("hospital_card"));
            id =hospitalCard.getId();
        }
        String status = req.getParameter("status");
        if (status.equals("discharged")) {
            if (appointmentDetailsDAO.findAllAppointmentDetailsByID(id).size() == 0) {
                Doctor doctor = doctorsDAO.findDoctorById(((User) req.getSession().getAttribute("user")).getId());
                doctorsDAO.updateNumberOfPatients(doctor.getNumberOfPatients() - 1, doctor.getId());
                hospitalCardDAO.dischargePatient(id);
            } else {
                req.setAttribute("messtatus", "not all appointments already done");
                req.getRequestDispatcher("/doctors_only/edit_hospital_cards.jsp").forward(req, resp);
                return;
            }
        }
        hospitalCardDAO.updateHospitalCardStatus(status, id);
        hospitalCard.setStatus(status);
        req.setAttribute("messtatus", ((Map<?, ?>) req.getAttribute("phrases")).get("langStatusUpdated"));
        req.getRequestDispatcher("/doctors_only/edit_hospital_cards.jsp").forward(req, resp);
    }
}
