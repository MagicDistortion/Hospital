package project.servlets.admins_only;

import project.Paginator;
import project.dao.DoctorsDAO;
import project.dao.PatientsDAO;
import project.models.users.Doctor;
import project.models.users.Patient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/admins_only/patients_sortlist")
public class PatientsList extends HttpServlet {
    private final DoctorsDAO doctorsDAO = new DoctorsDAO();
    private final PatientsDAO patientsDAO = new PatientsDAO();
    private final Paginator paginator = new Paginator();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Patient> patients = paginator.paginationPatientsList(req);
        if (patients.size() == 0) req.setAttribute("mes", ((Map<?, ?>) req.getAttribute("phrases")).get("langEmpty"));
        req.getRequestDispatcher("/admins_only/patients.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sort = "surname";
        if (req.getParameter("sort") != null) sort = req.getParameter("sort");
        req.setAttribute("sort", sort);
        switch (sort) {
            case "surname":
                sort = "WHERE current_doctor_id is null AND `status`!='discharged' ORDER BY surname";
                break;
            case "date":
                sort = "WHERE current_doctor_id is null AND `status`!='discharged' ORDER BY date_of_birth";
        }
        List<Patient> patients = patientsDAO.findAllPatients(sort);
        List<Doctor> doctors = doctorsDAO.findAllDoctors("Order by users.surname");
        if (patients.size() == 0) req.setAttribute("mes", ((Map<?, ?>) req.getAttribute("phrases")).get("langEmpty"));
        req.setAttribute("doctors", doctors);
        req.setAttribute("patients", patients);
        req.getRequestDispatcher("/admins_only/appoint_a_doctor.jsp").forward(req, resp);
    }
}
