package project.servlets.doctors_only;

import project.models.appointments.Appointment;
import project.dao.AppointmentsDAO;
import project.dao.HospitalCardDAO;
import project.dao.NursesDAO;
import project.models.hospitalcard.HospitalCard;
import project.models.users.Nurse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/doctors_only/edit_hospital_cards")
public class EditHospitalCard extends HttpServlet {
    private final NursesDAO nursesDAO = new NursesDAO();
    private final HospitalCardDAO hospitalCardDAO = new HospitalCardDAO();
    private final AppointmentsDAO appointmentsDAO = new AppointmentsDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Appointment> allAppointments = appointmentsDAO.findAllAppointments();
        req.getSession().setAttribute("appoints", allAppointments);

        List<Nurse> allNurse = nursesDAO.findAllNurse();
        req.getSession().setAttribute("nurses", allNurse);

        int patientId = Integer.parseInt(req.getParameter("id"));
        HospitalCard hospitalCard = hospitalCardDAO.getHospitalCard(patientId);
        req.getSession().setAttribute("back", req.getHeader("referer"));
        req.getSession().setAttribute("hospital_card", hospitalCard);

        if (hospitalCard.getDiagnosis() == null)
            hospitalCard.setDiagnosis(((Map<?, ?>) req.getAttribute("phrases")).get("langNotExistYet").toString());
        if (hospitalCard.getCurrentDoctorName() == null && hospitalCard.getCurrentDoctorSurname() == null) {
            hospitalCard.setCurrentDoctorName(((Map<?, ?>) req.getAttribute("phrases")).get("langNotAssigned").toString());
            hospitalCard.setCurrentDoctorSurname(((Map<?, ?>) req.getAttribute("phrases")).get("langNotAssigned").toString());
        }
        req.getRequestDispatcher("/doctors_only/edit_hospital_cards.jsp").forward(req, resp);
    }
}



