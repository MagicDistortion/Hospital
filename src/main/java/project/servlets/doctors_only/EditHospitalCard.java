package project.servlets.doctors_only;


import project.appointments.Appointment;
import project.dao.AppointmentsDAO;
import project.dao.HospitalCardDAO;
import project.dao.NursesDAO;
import project.hospitalcard.HospitalCard;
import project.users.Nurse;
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
    private final NursesDAO nursesDAO=new NursesDAO();
    private final HospitalCardDAO hospitalCardDAO= new HospitalCardDAO();
    private final AppointmentsDAO appointmentsDAO = new AppointmentsDAO();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Appointment> allAppointments = appointmentsDAO.findAllAppointments();
        req.getSession().setAttribute("appoints", allAppointments);

        List<Nurse> allNurse = nursesDAO.findAllNurse();
        req.getSession().setAttribute("nurses", allNurse);

        int patientId = Integer.parseInt(req.getParameter("id"));
        HospitalCard hospitalCard = hospitalCardDAO.getHospitalCard(patientId);

        req.getSession().setAttribute("back", req.getParameter("back"));
        req.getSession().setAttribute("patient_surname", hospitalCard.getPatientsSurname());
        req.getSession().setAttribute("patient_name", hospitalCard.getPatientsName());
        req.getSession().setAttribute("create_time", hospitalCard.getCreateTime());
        req.getSession().setAttribute("id_card", hospitalCard.getId());
        req.getSession().setAttribute("status_patient", hospitalCard.getStatus());

        if (hospitalCard.getDiagnosis() != null) {
            req.getSession().setAttribute("diagnosis", hospitalCard.getDiagnosis());
        } else
            req.getSession().setAttribute("diagnosis", ((Map<?, ?>) req.getAttribute("phrases")).get("langNotExistYet"));

        if (hospitalCard.getCurrentDoctorName() != null && hospitalCard.getCurrentDoctorSurname() != null) {
            req.getSession().setAttribute("current_doctorSurname", hospitalCard.getCurrentDoctorSurname());
            req.getSession().setAttribute("current_doctorName", hospitalCard.getCurrentDoctorName());
        } else {
            req.getSession().setAttribute("current_doctorSurname", ((Map<?, ?>) req.getAttribute("phrases")).get("langNotAssigned"));
            req.getSession().setAttribute("current_doctorName", ((Map<?, ?>) req.getAttribute("phrases")).get("langNotAssigned"));
        }
        req.getRequestDispatcher("/doctors_only/edit_hospital_cards.jsp").forward(req, resp);
    }
}



